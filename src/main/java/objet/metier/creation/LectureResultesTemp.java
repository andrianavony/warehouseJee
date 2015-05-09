/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objet.metier.creation;

import entite.Resultattemp;
import error.AnalysisWithoutSamplesError;
import error.BatchIdNotFindError;
import error.SampleWithoutCasefileError;
import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import session.ejb.I_FacadeSaisieResultats;

/**
 *
 * @author S.ANDRIANAVONY
 */
@Stateful
public class LectureResultesTemp {
@PersistenceContext ()
    protected EntityManager em;    
 
@Inject I_FacadeSaisieResultats facadeSaisieResultats;

/*
    private BigInteger bi_Idbatch=null;
    private String limsfolderno=null;
    private String limssampleid=null;
    private BigInteger limsanalysisorigrec=null;
    private BigInteger limsidanalysis=null;
    private String analysename=null;
    private String methodname=null;
    private BigInteger limsidseries=null;
*/
    
    entite.Batch batch=null;
    entite.Casefile casefile=null;
    entite.Sample samples=null;
    entite.Analyse analysis=null;
    entite.Resultat results=null;
        
public void process(){
 
    TypedQuery<entite.Resultattemp> q = em.createNamedQuery("Resultattemp.findByIsresultsinserted", entite.Resultattemp.class);
    q.setParameter("isresultsinserted", false);
    List<Resultattemp> listeResults=q.getResultList();
    
    
    process(listeResults);
}
    
    public void process(List<Resultattemp> listeResultatTemp){
        System.out.println( "**************************************************************************************************************** nouvelle "+analysis);
    for(Resultattemp result : listeResultatTemp ){
        proccesResult( result);
    }
    if(! listeResultatTemp.isEmpty()){
        doHeritage();
    }        
}
    
    public void proccesResult(entite.Resultattemp resultattemp){
        System.out.println(resultattemp.getResultattemp()+ " ******************************************************************************************************************  entrer dans proccesResult pour le lot "+resultattemp.getBatch());
        System.out.println(resultattemp.getResultattemp()+ "**************************************************************************************************************** nouvelle "+analysis);
        if(estUnNouveauLot(resultattemp)) {
            try {
                batch=facadeSaisieResultats.retrieveBatchFromIdBatch(resultattemp.getBatch());
            } catch (BatchIdNotFindError ex) {
                Logger.getLogger(LectureResultesTemp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        if(estUnNouveauDossierLot(resultattemp)) {
            try {
                casefile=facadeSaisieResultats.createOrRetrieveCasefile(batch, resultattemp.getLimsfolderno(),1);
            } catch (BatchIdNotFindError ex) {
                Logger.getLogger(LectureResultesTemp.class.getName()).log(Level.SEVERE, null, ex);            
            }
        }
        
        if(estUnNouveauSample(resultattemp)) {
            try {
                    samples=facadeSaisieResultats.createSample(casefile, resultattemp.getLimssampleid());
                } catch (SampleWithoutCasefileError ex) {
                    Logger.getLogger(LectureResultesTemp.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        System.out.println(resultattemp.getResultattemp()+ "**************************************************************************************************************** apres sample analysis "+analysis);

        if(estUnNouvelleAnalyses(resultattemp)) {
            System.out.println(resultattemp.getResultattemp()+ "**************************************************************************************************************** estUnNouvelleAnalyses "+analysis);
            if( null != analysis) {
                em.flush();
                System.out.println(" avant demande de heritage "+analysis.getAnalyse());
                //entite.Analyse  a = facadeSaisieResultats.getAnalysisCurrent();
                //System.out.println("**************************************************************************************************************** avant Heritage Analyse dans analyseManager  "+a.getAnalyse());
                System.out.println("**************************************************************************************************************** avant Heritage Analyse dans analyseManager  ");
                doHeritage();
                
            }
            System.out.println(resultattemp.getResultattemp()+ "**************************************************************************************************************** nouvelle Creation Analyse "+analysis);
            try {
                analysis=facadeSaisieResultats.createAnalysisViaInfoLims(samples, resultattemp.getLimsanalyseorigrec(), resultattemp.getLimsidanalyse(),resultattemp.getAnalysename(),resultattemp.getMethodename(), resultattemp.getLimsidserie());
                System.out.println(resultattemp.getResultattemp()+ "****************************************************************************************************************  Creation Analyse "+analysis);
            } catch (AnalysisWithoutSamplesError ex) {
                Logger.getLogger(LectureResultesTemp.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }else{
            System.out.println("************************************************************************************************************************************* ELSE (PAS) *** nouvelle "+analysis);
        }
        
        System.out.println(" demande de saisie résultats pour "+resultattemp.getMeasurename() );
            //(idsamples, limsanalysisorigrec, limsanalysisid, analysename,  methodname, limsidseries)
            analysis=facadeSaisieResultats.saisirResultatIssusLims
               (analysis, resultattemp.getLimsanalyseorigrec(), resultattemp.getMeasurename(), resultattemp.getLimsmeasureid(), resultattemp.getRawresultat(), resultattemp.getFormated(), resultattemp.getRepetition(), resultattemp.getUtilisateurname(), resultattemp.getDateofentry(), resultattemp.getStatutlabel());
            resultattemp.setIsresultatinserted(Boolean.TRUE);
            em.merge(resultattemp);
            System.out.println("****************************************************************************************************************" );
            System.out.println(resultattemp.getResultattemp()+ "**************************************************************************************************************** apres sample analysis "+analysis);
        System.out.println("************************************************************ FINI ****************************************************" );
    
    
    }

    public void doHeritage() {
        facadeSaisieResultats.doHeritage();
    }

    public boolean estUnNouveauLot(Resultattemp resultattemp) {
        if(null==batch){
            return true;
        }
        if(batch.getBatch().compareTo(resultattemp.getBatch())==0){
            return false;
        }
        return true;
    }

    public boolean estUnNouveauDossierLot(Resultattemp resultattemp) {
     if(null==casefile){
            return true;
        }
        if(casefile.getLimsfolderno().equalsIgnoreCase(resultattemp.getLimsfolderno())){
            return false;
        }
        return true;
    }


    private boolean estUnNouveauSample(Resultattemp resultattemp) {
        if(null==samples){
            return true;
        }
        if(samples.getLimssampleid().equalsIgnoreCase(resultattemp.getLimssampleid())){
            return false;
        }
        return true;
    }

    private boolean estUnNouvelleAnalyses(Resultattemp resultattemp) {
        if(null==analysis){
            System.out.println(" treu analysis null");
            return true;
        }
        if(analysis.getLimsidanalyse().compareTo(resultattemp.getLimsidanalyse())==0){
            System.out.println("false car "+ analysis.getLimsidanalyse() + " ==   "+resultattemp.getLimsidanalyse());
            return false;
        }
        System.out.println(" true par defaut  " +analysis.getLimsidanalyse() + " et "+resultattemp.getLimsidanalyse());
        return true;
    }
    
}

