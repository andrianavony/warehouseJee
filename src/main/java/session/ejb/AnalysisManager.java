/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.ejb;

import entite.Analyse;
import entite.Batch;
import entite.Company;
import entite.Methode;
import entite.Modeleanalyse;
import entite.Resultat;
import entite.Sample;
import entite.Traca;
import entite.Wo;
import error.AnalysisWithoutSamplesError;
import error.ResultsWithoutAnalysisError;
import error.SampleWithoutCasefileError;
import java.math.BigInteger;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import utilities.AnalysisUtility;
import utilities.Constant;
import utilities.DateManager;

/**
 *
 * @author S.ANDRIANAVONY
 */
@Stateful
public class AnalysisManager {

    @PersistenceContext ()
    protected EntityManager em;
    
    
    Analyse analysisCurrent;
    
    
//    @EJB I_FacadeSaisieResultats facadeSaisieResultats;
    
    @Inject ResultsManager resultsManager;
    
    @Inject AnalysisUtility analysisUtility;
    
    @Inject @Any Event<Analyse> eventOnAnalysis;
    
    /***
     * une analyse doit etre faite sur un ES
     */
    public Sample samplesCurrent;
    
    public AnalysisManager(){
       
    }
    
    
    public void setSamplesCurrent(    Sample samplesCurrent){
        this.samplesCurrent=samplesCurrent;
    }

        /****
     * Si le lot descendant n'existe pas dans le systeme on ne le cree pas le
     * @throws il n'est pas normale qu'on ne puisse pas ajouter DL, un ES ou une analyses sue le DL
     */
 /*   
    public List<Analyse> doHeritage(Analyse analysisACopier) throws AnalysisWithoutSamplesError, SampleWithoutCasefileError {
        System.out.println("**************************Heritage ");
        List<Analyse> analyseHerited = new ArrayList<>();
         Batch batch= getBatch(analysisACopier);
        List<entite.Batch> descendantsList= getListBatchDescendants(batch);
        if(null==descendantsList){
            return analyseHerited;
        }
        
        for (entite.Batch descendantsBatch: descendantsList ){
            System.out.println("Insertion heritage dans "+descendantsBatch);
            entite.Casefile casefileHeritage = facadeSaisieResultats.createOrRretriveCasefileForTypeDeCopie(batch, Constant.typeDeCopie.HERITAGE);
            
            Sample samplesCurrentDLBatchDescendants=null;
            samplesCurrentDLBatchDescendants = facadeSaisieResultats.getSampleCurrent(casefileHeritage);
            em.merge(samplesCurrentDLBatchDescendants);
            
            BigInteger limsidanalysis=analysisACopier.getLimsidanalyse();
            String methodName = analysisACopier.getMethodename();
            Analyse analysisDescendantsBatch= analysisUtility.createAnalysis(samplesCurrent,limsidanalysis , methodName);
            
            analysisDescendantsBatch = facadeSaisieResultats.copieResultats(analysisACopier,Constant.typeDeCopie.HERITAGE,analysisDescendantsBatch );
            analyseHerited.add(em.merge(analysisDescendantsBatch));
        }
        return analyseHerited;
    }
 */   
    public Analyse createAnalysis(BigInteger limsanalysisid, String methodname) throws AnalysisWithoutSamplesError {
        Analyse a;
        try {
            a = analysisUtility.createAnalysis(samplesCurrent, limsanalysisid, methodname);
        } catch (AnalysisWithoutSamplesError ex) {
            throw new AnalysisWithoutSamplesError("Sample Current is null");
        }
        //System.out.println(" dans createAnalyse ***************** " +a);
        return em.merge(a);
    }
    
    /**
     * Cette methode permet de saisir les résultats avec l'identifiant LIMS de l'analyses et une des methode qui lui est associée.
     * On crée l'objet Methode qui contient une référence à une modele d'analyse.
     * Si c'est la même identifiant limsanalysisid on ajoute sur la même analyse
     * Sinon on crée une autre analyse
    **/
    public Analyse addresults( BigInteger limsanalysisid, String methodname, String mesureName, String rawresult) throws AnalysisWithoutSamplesError, ResultsWithoutAnalysisError{
        if(null==samplesCurrent) {
            throw new AnalysisWithoutSamplesError("No Sample current available ");
        }
        
        if(null==analysisCurrent){
            //System.out.println("Creation nouvelle analyses analyses car null ");
            analysisCurrent = createAnalysis(limsanalysisid, methodname);
            
        }else{
            if(! (limsanalysisid.compareTo(analysisCurrent.getLimsidanalyse())==0)){
                //System.out.println(limsanalysisid +" Creation nouvelle analyses analyses car différente "+analysisCurrent.getLimsidanalyse());
                analysisCurrent = createAnalysis(limsanalysisid, methodname);
            }
        }
        addresultsToAnalysisCurrent( mesureName, rawresult);
        em.flush();
        return analysisCurrent;
    }
    
    public Analyse addresultsToAnalysisCurrent(  String mesureName, String rawresult) throws ResultsWithoutAnalysisError{
    	Resultat r =analysisUtility.addresultsToAnalysis( analysisCurrent, mesureName,  rawresult);
        em.merge(r);
        analysisCurrent.setCopystatut(Constant.HERITAGETODO);
        return em.merge(analysisCurrent);
    }
            
      
    public entite.Resultat addresults( Methode method, String mesureName, String rawresult) {
        List<Analyse> analysisList= fingExistingAnalysis(  method);
        if(analysisList.isEmpty()){
//            analysisCurrent=createAnalysis(samplesCurrent, method);
        }else{
            analysisCurrent=analysisList.get(0);
        }
        analysisCurrent=em.merge(analysisCurrent);
        em.flush();
        return resultsManager.addresults(analysisCurrent, method, mesureName, rawresult);
        
        
    }
    
    public List<Analyse> fingExistingAnalysis(  Methode idmethod){
        Modeleanalyse idmodeleanalyse = idmethod.getModeleanalyse();
        
        
        TypedQuery<Analyse> query ;
        query= em.createNamedQuery("Analyse.findByIdsampleIdModeleanalyseIdMethod", Analyse.class);
            query.setParameter("idsamples", samplesCurrent);
            query.setParameter("idmodeleanalyse", idmodeleanalyse);
            query.setParameter("idmethod",idmethod);
            
            List<Analyse> analysisesList =query.getResultList();
            return analysisesList;
    }

    

    public Analyse setInfoFromSample(Analyse analysis, Sample samples) {
        return analysisUtility.setInfoFromSample(analysis, samples);
    }

    

    
    
    public Modeleanalyse createOrUpdateModeleanalyseXXX(BigInteger idmodeleanalyse) {
        
        TypedQuery<Modeleanalyse> query =em.createNamedQuery("Modeleanalyse.findByIdmodeleanalyse",Modeleanalyse.class);
        query.setParameter("idmodeleanalyse", idmodeleanalyse);        
        List<Modeleanalyse> modeleanalyseesList = query.getResultList();
        if(modeleanalyseesList.isEmpty()){
            Modeleanalyse modeleanalyse=new Modeleanalyse(idmodeleanalyse);
            return em.merge(modeleanalyse);
        }else{
            return modeleanalyseesList.get(0);
        }
    }

        public Analyse setInfoFromMethodXXX(Analyse analysis, BigInteger idmethod) {
        Methode method;
        TypedQuery<Methode> query =em.createNamedQuery("Method.findByIdmethod",Methode.class);
        query.setParameter("idmethod", idmethod);        
        List<Methode> methodList = query.getResultList();
        if(methodList.isEmpty()){
            method=new Methode(idmethod);
            method= em.merge(method);
        }else{
            method= methodList.get(0);
        }
        
        analysis.setMethode(method);
        analysis.setMethodename(method.getMethodename());
        
        return analysis;
    }
        
    

    
    
    
    

    

    
   

   

    public void setAnalysis(Analyse analysis) {
        samplesCurrent=analysis.getSample();
        analysisCurrent=analysis;
    }

   

    
    
    

    
    
 
    
public Analyse getOrCreateAnalysisViaLimsAnalysisOrigrec(Sample idsamples, BigInteger limsanalysisorigrec, BigInteger limsanalysisid, String analysename, String methodname, BigInteger limsidseries) throws AnalysisWithoutSamplesError {
        
        Analyse a= analysisUtility.getOrCreateAnalysisViaLimsAnalysisOrigrec(idsamples, limsanalysisorigrec, limsanalysisid, analysename, methodname, limsidseries);
        analysisCurrent=em.merge(a);
        return analysisCurrent;            
        }

    public Analyse getOrCreateAnalysisViaLimsAnalysisOrigrec(String measurename, BigInteger limsmeasureid, String rawresults, String formated, Short repetition, String username, Date dateofentry, String statutsLabel) {
        Resultat r = analysisUtility.createResultViaLimsAnalysisOrigrec(analysisCurrent, measurename, limsmeasureid, rawresults, formated, repetition, username, dateofentry, statutsLabel);
        em.persist(r);
        Analyse a = em.merge(analysisCurrent);
        return a;
        
    }

    
    
    public void validation(){
                
        //em.flush();
        System.out.println(" dans analyse manager fire event on analysisCurrent + "+analysisCurrent.getAnalyse());
        eventOnAnalysis.fire(analysisCurrent);
        System.out.println("dans analyse manager APRES fire event on analysisCurrent  ");
    }

    public Analyse createAnalysisViaInfoLims(Sample idsamples, BigInteger limsanalysisorigrec, BigInteger limsanalysisid, String analysename, String methodname, BigInteger limsidseries) 
          throws AnalysisWithoutSamplesError 
    {
        if(null== idsamples){
            throw new AnalysisWithoutSamplesError("Dans Creation Analyse Via Info LIMS sample is null ");
        }
        analysisCurrent = analysisUtility.createAnalysisViaInfoLims(samplesCurrent, limsanalysisorigrec, limsanalysisid, analysename, methodname, limsidseries);
        em.persist(analysisCurrent);
        em.flush();
        return analysisCurrent;
    }

     public Analyse createAnalysisFromResults(Sample sample, Resultat resultatInserted) throws AnalysisWithoutSamplesError {
        samplesCurrent = sample;
        analysisCurrent =analysisUtility.createAnalysisFromResults(samplesCurrent, resultatInserted, resultatInserted.getLimsidanalyse());
        em.persist(analysisCurrent);
        
        return analysisCurrent;
    }
     
     public Analyse getAnalysisViaLimsAnalysisOrigrec( BigInteger limsanalysisorigrec){
        analysisCurrent = analysisUtility.getAnalysisViaLimsAnalysisOrigrec(limsanalysisorigrec);
        em.persist(analysisCurrent);
        return analysisCurrent;
    }


    

    
    
    /***
     * trouve les lots qui ont été produits a partir du lot du résultats d'analyse
     * OF028712
     **/
    public List<entite.Batch> getListBatchDescendants(Batch lotParent){
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
            
        }
        
        return descendants;
    }
    
    public List<entite.Traca> listeWoProduction(Batch batchProduit) {
        
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

  

    /****
     * l'analyse est déjà rataccher à un lot, DL, ES
     */
    public Analyse copierAnalysis(Analyse analysisACopier, Constant.typeDeCopie typeDeCopie, Analyse analyseCopie) throws ResultsWithoutAnalysisError {
        if(null==analysisACopier){
            throw new ResultsWithoutAnalysisError(" Demande de creation resultats sans analyses A Copier ");
        }
        Analyse analyseFilleTmp=analyseCopie;
        if(typeDeCopie== Constant.typeDeCopie.HERITAGE){
            analyseFilleTmp.setHerited(Boolean.TRUE);
            analyseFilleTmp.setCopytype(Constant.HERITAGE);
        }
        
        analyseFilleTmp.setCreationdate(DateManager.getNow());
        analyseFilleTmp.setCopiedfromidbatch(analysisACopier.getBatch());
        analyseFilleTmp.setCopiedfromidsample(analysisACopier.getSample());
        
        List<Resultat> listResults= analysisUtility.getResultsListFromAnalysis(analysisACopier);
        for(Resultat  resultACopier : listResults){
            Resultat copyResultTemp= analysisUtility.copyResultsToAnalysis(analyseFilleTmp, resultACopier);
            Resultat copyResult = em.merge(copyResultTemp);
            //System.out.println(copyResult.getResultat() + " merge faite "+copyResult.getSample());
            
        }
        Analyse analyseFille=em.merge(analyseFilleTmp);
        return analyseFille;
    }
    
}

  

