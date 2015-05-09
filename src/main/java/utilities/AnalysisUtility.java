/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import entite.Analyse;
import entite.Methode;
import entite.Modeleanalyse;
import entite.Resultat;
import entite.Sample;
import error.AnalysisWithoutSamplesError;
import error.ResultsWithoutAnalysisError;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;



/**
 *
 * @author S.ANDRIANAVONY
 */
@Stateless
public class AnalysisUtility {

    @PersistenceContext ()
    protected EntityManager em;
    
    
    @Inject ResultsUtility resultsUtility;
    
    public AnalysisUtility(){
        //System.out.println("Creation analysis uti =========================================================");
    }

     public Analyse createAnalysisViaInfoLims(Sample samplesCurrent, BigInteger limsanalysisorigrec, BigInteger limsanalysisid, String analysename,  String methodname, BigInteger limsidseries) 
                        throws AnalysisWithoutSamplesError
     {
         if(null==samplesCurrent){
             throw new AnalysisWithoutSamplesError("Dans Creation Analyse Via Info LIMS sample is null ");
         }
        Methode method = createOrRetreiveMethodeFromLims(limsanalysisid,  methodname);
        Analyse a=setDifferentInfo(samplesCurrent, method);
        a.setLimsidanalyse(limsanalysisid);
        a.setLimsidserie(limsidseries);
        a.setCreationdate(utilities.DateManager.getNow());
        a.setLimsanalyseorigrec(limsanalysisorigrec);
        a=em.merge(a);
        return a ;
    }
     public Analyse setDifferentInfo(Sample samplesCurrent,Methode method) {
        Analyse analysis=new Analyse();
        setInfoFromSample(analysis, samplesCurrent);
        //System.out.println(" *************** method.getModeleanalyse()  "+method.getModeleanalyse());
        setInfoFromModeleAnalysis(analysis, method.getModeleanalyse());
        setInfoFromMethode(analysis,  method);
        //analysisCurrent=em.merge(analysis);
        return analysis;
    }
    
     public Analyse setInfoFromModeleAnalysis(Analyse analysis, Modeleanalyse modeleanalyse) {
        //System.out.println(" ==<= modeleanalyse"+modeleanalyse);
        analysis.setModeleanalyse(modeleanalyse);
        analysis.setAnalysename(modeleanalyse.getAnalysename());
        analysis.setOfficialename(modeleanalyse.getOfficialname());

        return analysis;
    }
     
     public Analyse setInfoFromMethode(Analyse analysis, Methode method) {
         //System.out.println(" dans setInfoFromMethode debut "+analysis.getSample() );
        analysis.setMethode(method);
        analysis.setMethodename(method.getMethodename());
         //System.out.println(" dans setInfoFromMethode avant retour "+analysis.getSample() );
         analysis.setCreationdate(DateManager.getNow());
        return analysis;
    }
     
     
     public Analyse getOrCreateAnalysisViaLimsAnalysisOrigrec(Sample idsamples, BigInteger limsanalysisorigrec, BigInteger limsanalysisid, String analysename, String methodname, BigInteger limsidseries) throws AnalysisWithoutSamplesError {
        
        Analyse a=getAnalysisViaLimsAnalysisOrigrec(limsanalysisorigrec);
        
        if(null==a){
            return createAnalysisViaInfoLims( idsamples,  limsanalysisorigrec,  limsanalysisid,  analysename,    methodname,  limsidseries);
        }
        return a;           
        }
     
    public Analyse getAnalysisViaLimsAnalysisOrigrec( BigInteger limsanalysisorigrec){
        TypedQuery<Analyse> query ; 
        query= em.createNamedQuery("Analyse.findByLimsanalysisorigrec", Analyse.class);
            query.setParameter("limsanalysisorigrec",limsanalysisorigrec);
            List<Analyse> analysisesList =query.getResultList();
            if(analysisesList.isEmpty()){ 
             return null;
            }else {                    
                return analysisesList.get(0);
        }
            
    }
    
    public Analyse copierAnalysis(Analyse analysisACopier, Sample samples, Constant.typeDeCopie typeDeCopie) {
        Analyse analysisCopier = new Analyse();
        switch (typeDeCopie) {
            case HERITAGE :
                    analysisCopier = copierAnalysisHeritage(analysisACopier, samples);
                    break;
            }
                
            
        return analysisCopier;
    }

    public Analyse copierAnalysisHeritage(Analyse analysisACopier, Sample samples) {
        Analyse copie = copieAnalysis(analysisACopier);
        setInfoFromSample(copie,  samples);
        copie.setHerited(Boolean.TRUE);
        return copie;
    }
    
    public Analyse setInfoFromSample(Analyse analysis, Sample samples) {
        analysis.setArticle(samples.getArticle());
        analysis.setBatchname(samples.getBatchname());
        analysis.setLimssampleid(samples.getLimssampleid());
        analysis.setBatch(samples.getBatch());
        analysis.setCasefile(samples.getCasefile());
        analysis.setLimsbatchid(samples.getLimsbatchid());
        analysis.setLimsfolderno(samples.getLimsfolderno());
        analysis.setProdgroup(samples.getProdgroup());
        analysis.setSpecie(samples.getSpecie());
        analysis.setStage(samples.getStage());
        analysis.setSample(samples);
        return analysis;
    }

    public Analyse copieAnalysis(Analyse analysisACopier) {
        Analyse copie = new Analyse();
        copie.setAnalysename(analysisACopier.getAnalysename());
        //q.setApporved(w.getApporved());
        //q.setApporvedby(w.getApporvedby());
        //q.setApporveddate(w.getApporveddate());
        copie.setCopiedfromidbatch(analysisACopier.getBatch());
        copie.setCopiedfromidsample(analysisACopier.getSample());
        copie.setCreationdate(utilities.DateManager.getNow());
        copie.setAnalyse(analysisACopier.getAnalyse());
        copie.setMethode(analysisACopier.getMethode());
        copie.setMethodedetail(analysisACopier.getMethodedetail());
        copie.setModeleanalyse(analysisACopier.getModeleanalyse());
        copie.setMotheranalyse(analysisACopier.getMotheranalyse());
        //q.setSerie(w.getSerie());
        copie.setIsvirtual(Boolean.TRUE);
        copie.setMethodename(analysisACopier.getMethodename());
        
        recopieResults(analysisACopier,copie);
        return copie;
    }

    public void recopieResults(Analyse analysisACopier, Analyse copie) {
        List<Resultat> resultsListAcopier= getResultsListFromAnalysis(analysisACopier);
        for(Resultat  r : resultsListAcopier){
            copierResults(r, copie);
        }
    }

    public List<Resultat> getResultsListFromAnalysis(Analyse analysisACopier) {
        TypedQuery<Resultat> query = em.createNamedQuery("Resultat.findByAnalyse", Resultat.class);
        query.setParameter("idanalysis", analysisACopier);
        return query.getResultList();        
    }

    public void copierResults(Resultat  r, Analyse copie) {
        Resultat c =ResultsUtility.getCopieResults(r);
        c.setAnalyse(copie);
        em.merge(c);
    }

    public Analyse createAnalysis(Sample samples, BigInteger limsidanalysis, String methodname) throws AnalysisWithoutSamplesError {
        //System.out.println("dans createAnalyse *************************** "+samples.getBatch());
       if(null == samples){
           throw new AnalysisWithoutSamplesError(" Sample cannot be null ");
       }
         Methode method = createOrRetreiveMethodeFromLims(limsidanalysis, methodname);
         //System.out.println(" dans Analyse Utilitie createAnalyse "+method);
         Modeleanalyse idmodeleanalyse = createOrRetreiveModeleanalyseFromLims(limsidanalysis);
         //System.out.println(" dans Analyse Utilitie createAnalyse "+ idmodeleanalyse);
         
         return createAnalysis(samples, idmodeleanalyse, method, limsidanalysis);
    }
    
    public Methode createOrRetreiveMethodeFromLims(BigInteger limsanalysisid, String methodname) {
        Modeleanalyse modeleanalyse = createOrRetreiveModeleanalyseFromLims(limsanalysisid);
        
        //em.merge(modeleanalyse);
        
        //System.out.println("modeleanalyse ************************* "+modeleanalyse.getModeleanalyse() );
        
        List<Methode> methodsList = fingExistingMethodFromLims(limsanalysisid,  methodname);
        
        if(methodsList.isEmpty()){            
            Methode method=new Methode();
            method.setMethodename(methodname);
            method.setModeleanalyse(modeleanalyse);
            method.setLimsanalyseid(limsanalysisid);
            method=em.merge(method);
            em.flush();
            //System.out.println(" ............................ lors de la creation de  la methode Idmethod="+method.getMethode()+"    "+method.getModeleanalyse().getModeleanalyse());
            return method;
        }else {
            //System.out.println("****************** "+methodsList.get(0));
            Methode methodSansModeleanalyse = methodsList.get(0);
            methodSansModeleanalyse.setModeleanalyse(modeleanalyse);
            em.merge(methodSansModeleanalyse);
            return methodsList.get(0);
        }
    }
    
        public Modeleanalyse createOrRetreiveModeleanalyseFromLims(BigInteger limsidanalysis) {
        List<Modeleanalyse> modeleanalyseesList =fingExistingModeleanalyseFromLims( limsidanalysis);
        if(modeleanalyseesList.isEmpty()){
            return createModelAnalysis(limsidanalysis);
        }else{
            //System.out.println(" modeleanalyseesList.get(0) "+modeleanalyseesList.get(0));
            return modeleanalyseesList.get(0);
        }
            
    }
     public List<Modeleanalyse> fingExistingModeleanalyseFromLims(BigInteger limsidanalysis){
        TypedQuery<Modeleanalyse> query ;
        
        //System.out.println("Em not null *********** limsidanalysis "+limsidanalysis);
        query= em.createNamedQuery("Modeleanalyse.findByLimsidanalysis", Modeleanalyse.class);
            query.setParameter("limsidanalysis",limsidanalysis);
            List<Modeleanalyse> modeleanalyseesList =query.getResultList();
            //System.out.println("modeleanalyseesList.size "+modeleanalyseesList.size());
            return modeleanalyseesList;
    }    
    
      public Modeleanalyse createModelAnalysis(BigInteger limsidanalysis) {
        Modeleanalyse modeleanalyse = new Modeleanalyse();
        modeleanalyse.setLimsidanalyse(limsidanalysis);
        modeleanalyse=em.merge(modeleanalyse);
        em.flush();
        //System.out.println(modeleanalyse.getModeleanalyse() + " *********** modeleanalyse ******************** "+modeleanalyse.getLimsidanalyse());
        return modeleanalyse;
    }
    public List<Methode> fingExistingMethodFromLims(BigInteger limsanalysisid, String methodname){
        TypedQuery<Methode> query ; 
        query= em.createNamedQuery("Method.findByLimsanalysisidMethodname", Methode.class);
            query.setParameter("limsanalysisid",limsanalysisid);
            query.setParameter("methodname", methodname);
            List<Methode> methodList =query.getResultList();
            return methodList;
    }  

    public Resultat addresultsToAnalysis(Analyse analysisCurrent, String mesureName, String rawresult) throws ResultsWithoutAnalysisError {
        if(null==analysisCurrent){
            throw new ResultsWithoutAnalysisError(" Demande de creation resultats sans analyses");
        }
        Resultat r = new Resultat();
        r.setDateofentry(DateManager.getNow());
        r.setBatch(analysisCurrent.getBatch());
        r.setMeasurename(mesureName);
        r.setRawresultat(rawresult);
        r.setAnalyse(analysisCurrent);
        //System.out.println("************ Creation de nouvelle annalyse dans dans addresultsToAnalyse "+r);
        return r;
    }
    
 public Analyse createAnalysisFromResults(Sample samplesCurrent, Resultat resultatInserted, BigInteger limsidanalysis) throws AnalysisWithoutSamplesError {
            
        return createAnalysis(samplesCurrent, resultatInserted.getModeleanalyse(),resultatInserted.getMethode(), limsidanalysis);
        
    }
  public Resultat createResultViaLimsAnalysisOrigrec(Analyse analysisCurrent, String measurename, BigInteger limsmeasureid, String rawresults, String formated, Short repetition, String username, Date dateofentry, String statutsLabel) {
        
        entite.Resultat r = new Resultat();
        r.setAnalyse(analysisCurrent);
        r.setArticle(analysisCurrent.getArticle());
        r.setBatchname(analysisCurrent.getBatchname());
        r.setLimssampleid(analysisCurrent.getLimssampleid());
        r.setLimsidanalyse(analysisCurrent.getLimsidanalyse());
                r.setAnalysename(analysisCurrent.getAnalysename());
        r.setMethodename(analysisCurrent.getMethodename());
        
        
        r.setRepetition(repetition);
        r.setMeasurename(measurename);
        r.setRawresultat(rawresults);
        r.setFormated(formated);
        r.setLimsidserie(limsmeasureid);
        r.setDateofentry(dateofentry);
        r.setStatutlabel(statutsLabel);
        r.setSerie(analysisCurrent.getSerie());
        
        return r;
        
    }

    public  Analyse createAnalysis(Sample samplesCurrent, Modeleanalyse idmodeleanalyse, Methode idmethod, BigInteger limsidanalysis ) throws AnalysisWithoutSamplesError {
        //System.out.println("dans createAnalyse samplesCurrent "+samplesCurrent.getBatch());
        if(null == samplesCurrent){
            throw new AnalysisWithoutSamplesError("Cannot create a analysis with a null Sample ");
        }
        Analyse a =new Analyse();
        //System.out.println(" dans Analyse Utilitie createAnalyse a (null OK) "+a.getSample());
        
        a=setInfoFromSample(a , samplesCurrent);
        System.out.println(" dans Analyse Utilitie createAnalyse a "+a.getSample());
        a=setInfoFromModeleAnalysis(a, idmodeleanalyse);
        System.out.println(" dans Analyse Utilitie createAnalyse a "+a.getModeleanalyse());
        a=setInfoFromMethode(a, idmethod);
        System.out.println(" dans Analyse Utilitie createAnalyse a "+a.getMethode());
        
        System.out.println(" avant retour "+a);
        a.setLimsidanalyse(limsidanalysis);
        return a;
    }

    public Resultat copyResultsToAnalysis(Analyse analysisCurrent, Resultat resultACopier) throws ResultsWithoutAnalysisError {
        if(null==analysisCurrent){
            throw new ResultsWithoutAnalysisError(" Demande de creation resultats sans analyses");
        }
        String mesureName=resultACopier.getMeasurename();
        String rawresult=resultACopier.getRawresultat();
        return addresultsToAnalysis(analysisCurrent,  mesureName,  rawresult);
    }
      
}
