/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.ejb;

import entite.Analyse ;
import entite.Batch;
import entite.Company;
import entite.Sample;
import entite.Traca;
import entite.Wo;
import error.AnalysisWithoutSamplesError;
import error.ResultsWithoutAnalysisError;
import error.SampleWithoutCasefileError;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PostPersist;
import javax.persistence.TypedQuery;

import utilities.AnalysisUtility;
import utilities.CasefileUtility;
import utilities.Constant;

/**
 *
 * @author S.ANDRIANAVONY
 */
@Stateless
public class RecopieAnalyse {
    
    @PersistenceContext ()
    protected EntityManager em;

    @Inject I_FacadeSaisieResultats facadeSaisieResultats;
    
    @Inject CasefileUtility casefileUtility;
    
    @Inject AnalysisUtility analysisUtility;
    
    
    //@PostPersist
    public void analysisListenerDoHeritage(Analyse analysisACopier){
        List<Analyse> analysisesList= null;
        try { 
            analysisesList=  doHeritage(analysisACopier);
        } catch (AnalysisWithoutSamplesError ex) {
            Logger.getLogger(RecopieAnalyse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SampleWithoutCasefileError ex) {
            Logger.getLogger(RecopieAnalyse.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(Analyse a : analysisesList){
            //System.out.println(" Insertion faite pour "+a);
        }
        
    }
    
    public List<Analyse> listeAnalyseAFaireHeriter(){
        TypedQuery<Analyse> query=em.createNamedQuery("Analyse.findByCopyStatus", Analyse.class);
        query.setParameter("copystatus", Constant.HERITAGETODO);
        return query.getResultList();
    }
    
    public List<Analyse> doHeritage(){
        List<Analyse> analysisesList = null;// listeAnalyseAFaireHeriter();
        List<Analyse> herite = null;
        if(analysisesList==null){
            return null;
        }
        for(Analyse a : analysisesList){
            try {
                herite= doHeritage(a);
            } catch (AnalysisWithoutSamplesError ex) {
                Logger.getLogger(RecopieAnalyse.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SampleWithoutCasefileError ex) {
                Logger.getLogger(RecopieAnalyse.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return herite;
        
    }
    
    public Sample createCasefileSample(Batch batch) throws SampleWithoutCasefileError {
        Sample samplesCurrentDLBatchDescendants = null;
        //System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        //System.out.println("Insertion heritage dans "+batch);
        entite.Casefile casefileHeritageTmp = facadeSaisieResultats.createOrRretriveCasefileForTypeDeCopie(batch, Constant.typeDeCopie.HERITAGE);
        //System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++<<<<<<<<<<<<<<<<<<<<< AVANT MERGE");
        entite.Casefile casefileHeritage= em.merge(casefileHeritageTmp);
        //System.out.println(casefileHeritage.getCasefile() + " case file Heritage "+casefileHeritage.getBatch());
        //System.out.println(casefileHeritageTmp.getCasefile() + " case file HeritageTMp "+casefileHeritageTmp.getBatch());
        
        //System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++<<<<<<<<<<<<<<<<<<<<< AVANT createOrRetreiveSampleCurrent");
        Sample samplesCurrentDLBatchDescendantsTmp = facadeSaisieResultats.createOrRetreiveSampleCurrent(casefileHeritage);
        //System.out.println(" Veriufication Sample "+samplesCurrentDLBatchDescendantsTmp.getBatch());
        //System.out.println("  "+samplesCurrentDLBatchDescendantsTmp.getCreationdate());
        samplesCurrentDLBatchDescendants=em.merge(samplesCurrentDLBatchDescendantsTmp);
        
        return samplesCurrentDLBatchDescendants;
    }
    
        /****
     * Si le lot descendant n'existe pas dans le systeme on ne le cree pas le
     * @throws il n'est pas normale qu'on ne puisse pas ajouter DL, un ES ou une analyses sue le DL
     */  
    public List<Analyse> doHeritage(Analyse analysisACopier) throws AnalysisWithoutSamplesError, SampleWithoutCasefileError {
        //System.out.println("**************************Heritage "+analysisACopier);
        List<Analyse> analyseHerited = new ArrayList<>();
         Batch batchACopier= getBatch(analysisACopier);
        List<entite.Batch> descendantsList= getListBatchDescendants(batchACopier);
        if(null==descendantsList){
            return analyseHerited;
        }
        
        for (entite.Batch descendantsBatch: descendantsList ){
            Sample samplesCurrentDLBatchDescendants=createCasefileSample(descendantsBatch);
          
            //System.out.println(" samplesCurrentDLBatchDescendants<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< "+samplesCurrentDLBatchDescendants.getSample());
            BigInteger limsidanalysis=analysisACopier.getLimsidanalyse();
            String methodName = analysisACopier.getMethodename();
           
            Analyse analysisDescendantsBatchCreation= analysisUtility.createAnalysis(samplesCurrentDLBatchDescendants,limsidanalysis , methodName);
            //Analyse analysisDescendantsBatch=em.merge(analysisDescendantsBatchTmp); 
            /*
            System.out.println("******************************************************************************************************************");
            System.out.println("******************************************************************************************************************"+analysisACopier);
            System.out.println("******************************************************************************************************************"+analysisACopier.getBatch());
            System.out.println("****************************************************************************************************************** a copier sur ");
            System.out.println("******************************************************************************************************************"+analysisDescendantsBatchCreation);
            System.out.println("******************************************************************************************************************"+analysisDescendantsBatchCreation.getBatch());
            System.out.println("******************************************************************************************************************");
            System.out.println("******************************************************************************************************************" +analysisDescendantsBatchCreation.getSample());
           */
            
            
            Analyse analysisDescendantsBatchTmp=null;
            try {
                analysisDescendantsBatchTmp = facadeSaisieResultats.copieResultats(analysisACopier,Constant.typeDeCopie.HERITAGE,analysisDescendantsBatchCreation );
            } catch (ResultsWithoutAnalysisError ex) {
                Logger.getLogger(RecopieAnalyse.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println("******************************************************************************************************************" +analysisDescendantsBatchTmp.getSample());
            Analyse analysisDescendantsBatch=em.merge(analysisDescendantsBatchTmp);
            analyseHerited.add(analysisDescendantsBatch);
            analysisACopier.setCopystatut(0);
            em.merge(analysisACopier);
        }
        return analyseHerited;
    }
    
    /***
     * trouve les lots qui ont été produits a partir du lot du résultats d'analyse
     * OF028712
     **/
    public List<entite.Batch> getListBatchDescendants(Batch lotParent){
        // System.out.println("*********** Dans FIND **************============================================"+em);
        List<entite.Traca> woProduction = listeWoProduction (lotParent);
        if(null==woProduction){
            return null;
        }
        if(woProduction.isEmpty()){
            return null;
        }
        
        List<entite.Batch> descendants = null; //new ArrayList<>(woProduction.size());
        for(entite.Traca traca : woProduction){
            //System.out.println(" traca trouver "+traca );
            descendants=getBatchFromTraca(traca);
            //System.out.println("descendants "+descendants.size());
        }
        
        return descendants;
    }
    
    public List<entite.Traca> listeWoProduction(Batch batchProduit) {
        //System.out.println("*********** Dans FIND **************============================================"+em);
        if(null == batchProduit){
            return null;
        }
        Wo idWo= batchProduit.getWo();
        Company idcompany =batchProduit.getCompany();
        //System.out.println("idWo "+idWo);
        //System.out.println("idcompany"+idcompany);
        TypedQuery<entite.Traca> q = em.createNamedQuery("Traca.findByIdwo_Production", entite.Traca.class);
        q.setParameter("idwo", idWo); 
        q.setParameter("idcompany", idcompany.getCompany());
        List<entite.Traca> tmp=q.getResultList();
        //System.out.println("q "+q);
        return tmp;
        
    }
    
    public List<Batch>  getBatchFromTraca(Traca traca) {
        TypedQuery<Batch> q =em.createNamedQuery("Batch.findByIdarticleBatchnameCompanynameIdWo", Batch.class);
        q.setParameter("batchname",traca.getBatchname() ); 
        q.setParameter("idarticle", traca.getArticle()); 
        q.setParameter("idcompany", traca.getCompany());
        String idwo =traca.getWo().getWoPK().getWo();
        //System.out.println("idwo "+idwo);
        q.setParameter("idwo", traca.getWo());
        
        return q.getResultList(); 
        
    }
    
    public Batch getBatch(Analyse analysis) {        
        if(analysis == null){
            return null;
        }
        return getBatchByIdbatch(analysis.getBatch().getBatch());
    }
    
    public Batch getBatchByIdbatch(BigInteger bi_idbatch) {
        Batch idbatch = new Batch(bi_idbatch);
        TypedQuery<Batch> q =em.createNamedQuery("Batch.findByBatch", Batch.class);
        q.setParameter("idbatch",bi_idbatch );
        if(q.getResultList().isEmpty()){
            return null;
        }
        return q.getResultList().get(0); 
    }

    
}
