/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.ejb;

import entite.Analyse;
import entite.Batch;
import entite.Article;
import entite.Casefile;
import entite.Resultat;
import entite.Sample;
import error.AnalysisWithoutSamplesError;
import error.BatchIdNotFindError;
import error.ResultsWithoutAnalysisError;
import error.SampleWithoutCasefileError;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateful;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import utilities.BatchFind;
import utilities.BatchUtility;
import utilities.CaseFileFind;
import utilities.Constant;
import utilities.SamplesFind;

/**
 *
 * @author S.ANDRIANAVONY
 */
@Stateful
public class FacadeSaisieResultats implements I_FacadeSaisieResultats{

    @PersistenceContext ()
    protected EntityManager em;
    
    @Inject BatchManager batchManager;
    @Inject BatchUtility batchUtility;
    @Inject BatchFind batchFind;
    
    @Inject CasefileManager casefileManager;
    @Inject CaseFileFind caseFileFind;
    
    @Inject SamplesManager samplesManager;
    @Inject SamplesFind samplesFind;
    
    @Inject AnalysisManager analysisManager;
    
    
    
    public FacadeSaisieResultats(){
        
    }
    
    
    @Override
    public Batch retrieveBatchFromIdBatch(BigInteger idbatch) throws BatchIdNotFindError {
        return em.merge(batchManager.retrieveBatchFromBatch(idbatch));
    }

    @Override
    public Batch createOrRetrieveBatch(String batchname, String articlename, String idcompany, String limsbatchid) {
        BigInteger bi_limsbatchid= new BigInteger(limsbatchid);
        return createOrRetrieveBatch(batchname, articlename, idcompany, bi_limsbatchid);
    }
    
    @Override
    public Batch createOrRetrieveBatch(String batchname, String articlename, String idcompany, BigInteger limsbatchid) {
        Batch bTemp=batchManager.createOrRetrieveBatch(batchname, articlename, idcompany, limsbatchid);       
        Batch b=em.merge(bTemp);
        return b;
    }
    
    @Override
    public Batch createOrRetrieveBatch(String batchname, Article idarticle, String idcompany, String limsbatchid) {
        BigInteger bi_limsbatchid= new BigInteger(limsbatchid);
        return createOrRetrieveBatch( batchname,  idarticle,  idcompany,  bi_limsbatchid);
    }
    
    @Override
    public Batch createOrRetrieveBatch(String batchname, Article idarticle, String idcompany, BigInteger limsbatchid) {
        Batch bTemp =batchManager.createOrRetrieveBatch( batchname,  idarticle,  idcompany,  limsbatchid);
        Batch b=em.merge(bTemp);
        return b;
    }

    @Override
    public Casefile retrieveCasefile(BigInteger idcasefile) {
        Casefile cTemp=null;
        
        if(idcasefile==null){
            return null;
        }
        
        cTemp=caseFileFind.findExistingCasefile (idcasefile);
        
        if(null==cTemp){
            return null;
        }
        
        Casefile c=em.merge(cTemp);
        return c;
    }
    
    @Override
    public entite.Casefile createOrRretriveCasefileForTypeDeCopie(entite.Batch idbatch, Constant.typeDeCopie typeDeCopie){
        Casefile cTemp=null;
        casefileManager.setCurrentBatch(idbatch);
        cTemp= casefileManager.createOrRretriveCasefileForTypeDeCopie(idbatch,typeDeCopie);
        cTemp.setCasefiletype(typeDeCopie.toString());
        Casefile c=em.merge(cTemp);
        return c;
    }

    @Override
    public Casefile createOrRetrieveCasefile(Batch idbatch, String limsfolderno, Integer numdemandelims) throws BatchIdNotFindError {
        return batchManager.createOrRetrieveCasefile(idbatch, limsfolderno, numdemandelims);
    }

    @Override
    public Sample createSample(Casefile idcasefile, String limssampleid) throws SampleWithoutCasefileError {
        Sample sTemp =null;
        System.out.println(idcasefile + " creation sample avec idcasefile "+ limssampleid);
        samplesManager.setCasefile(idcasefile);
        sTemp =samplesManager.createSamples(limssampleid);
        Sample s = em.merge(sTemp);
        return s;
    }
    
    @Override
    public Analyse createAnalysis(Sample idsamples, BigInteger limsanalysisorigrec, BigInteger idmethod) {
        Analyse a=null;
        return a;
    }
    
    @Override
    public Analyse createAnalysisViaInfoLims(Sample idsamples, BigInteger limsanalysisorigrec, BigInteger limsanalysisid, String analysename,  String methodname, BigInteger limsidseries)
                    throws AnalysisWithoutSamplesError 
    {
        analysisManager.setSamplesCurrent(idsamples);
        Analyse a=analysisManager.createAnalysisViaInfoLims( idsamples,  limsanalysisorigrec,  limsanalysisid,  analysename,    methodname,  limsidseries) ;
        return a;
    }
    
    @Override
    public Analyse getOrCreateAnalysisViaLimsAnalysisOrigrec(Sample idsamples, BigInteger limsanalysisorigrec, BigInteger limsanalysisid, String analysename,  String methodname, BigInteger limsidseries)
            throws AnalysisWithoutSamplesError 
    {
        analysisManager.setSamplesCurrent(idsamples);
        Analyse a=analysisManager.getOrCreateAnalysisViaLimsAnalysisOrigrec( idsamples,  limsanalysisorigrec,  limsanalysisid,  analysename,    methodname,  limsidseries) ;
        return a;
    }

    @Override
    public Analyse createAnalysisViaMethodDetails(Sample idsamples, BigInteger idmethoddetails) {
        Analyse a=null;
        return a;
    }

    @Override
    public Analyse createAnalysisViaLimsAnalysis(Sample idsamples, BigInteger limsidanalysis, String limsidmethod) {
        Analyse a=null;
        return a;
    }
    
    @Override
    public entite.Analyse saisirResultat(entite.Analyse idanalysis, String measurename, BigInteger idmeasure , String rawresults,String formated, Short repetition,String username, Date dateofentry, String statutsLabel){
        Analyse a=null;
        return a;
    }
    
    /***
     * il faut que l analyse existe déjà
     */
    public entite.Analyse saisirResultatIssusLims(entite.Analyse idanalysis, BigInteger limsanalysisorigrec, String measurename, BigInteger limsmeasureid , String rawresults,String formated, Short repetition,  String username, Date dateofentry, String statutsLabel){
        System.out.println(" ***************************** dans saisirResultatIssusLims "+idanalysis.getAnalyse()+" measurename "+ measurename+ " rawresults "+rawresults);
        Analyse a=idanalysis;
        if(null==idanalysis){
            a=analysisManager.getAnalysisViaLimsAnalysisOrigrec( limsanalysisorigrec);
        }
        if(null==a){
            return null;
        }
        analysisManager.setAnalysis(a);
        a=analysisManager.getOrCreateAnalysisViaLimsAnalysisOrigrec( measurename, limsmeasureid , rawresults,formated, repetition,  username, dateofentry, statutsLabel) ;
        return a;
    }

    /*
    public Batch findExistingBatchByBatchId(BigInteger idbatch) {
        return batchManager.findExistingBatchByIdBatch(idbatch);
    }

    public Batch findExistingBatchByLimsBatchId(String limsbatchid) {
        return batchManager.findExistingBatchByLimsBatchId(limsbatchid) ;
    }

    public Casefile findExistingCasefile(BigInteger idcasefile) {
        return casefileManager.findExistingCasefile(idcasefile);
    }

    public Casefile findExistingFolderno(String limsfolderno, Integer numdemandelims) {
        return casefileManager.findExistingFolderno(limsfolderno, numdemandelims);
    }
    */


    @Override
    public entite.Analyse copieResultats(Resultat  resultatInserted, Batch descendantsBatch, Constant.typeDeCopie typeDeCopie,Sample samplesDescendantsBatch, Analyse analysisDescendantsBatch) throws SampleWithoutCasefileError , AnalysisWithoutSamplesError{
        Casefile casefileTypedeCopie= createOrRretriveCasefileForTypeDeCopie(descendantsBatch,typeDeCopie);
        
        Sample samples= createSample(casefileTypedeCopie, null);
        
        BigInteger limsanalysisorigrec;
        BigInteger idmethod;
        
        return createAnalysis(samples, resultatInserted);
    }

    @Override
    public entite.Analyse createAnalysis(Sample samples, Resultat resultatInserted) throws AnalysisWithoutSamplesError{
        analysisManager.setSamplesCurrent(samples);
        return analysisManager.createAnalysisFromResults(samples, resultatInserted);
        
    }
    
    /****
     * analysisDescendantsBatch a déjà les informations sur les ES, DL et batch
     * @throws error.ResultsWithoutAnalysisError
     */
    //public entite.Analyse copieResultats(Analyse analysisACopier, Batch descendantsBatch, Constant.typeDeCopie typeDeCopie,Sample samplesDescendantsBatch, Analyse analysisDescendantsBatch){
    @Override
    public entite.Analyse copieResultats(Analyse analysisACopier,  Constant.typeDeCopie typeDeCopie, Analyse analysisDescendantsBatch) throws ResultsWithoutAnalysisError{
        
        System.out.println("Dans Facade analysisDescendantsBatch.getSample() "+analysisDescendantsBatch.getSample());
        analysisManager.setSamplesCurrent(analysisDescendantsBatch.getSample());
        return analysisManager.copierAnalysis(analysisACopier,typeDeCopie,analysisDescendantsBatch);
        
    }
    
    //public void onAnalysis(@Observes(during = TransactionPhase.AFTER_SUCCESS) Analyse event){
        //analysisManager.doOnAnalysis(event);
    //    System.out.println(" dans Facade declenchement evenement " + event.getAnalyse());
    //}

    @Override
    public Sample createOrRetreiveSampleCurrent(Casefile casefileHeritage) throws SampleWithoutCasefileError {
        System.out.println("dans getSampleCurrent casefileHeritage "+casefileHeritage);
       return samplesManager.createOrRetreiveSampleCurrent(casefileHeritage);
    }
    
    @Override
    public void doHeritage() {
        System.out.println(" dans facade demande de do heritage");
        analysisManager.validation();
    }
    
    @Override
    public entite.Analyse getAnalysisCurrent(){
        return analysisManager.analysisCurrent;
    }
    
}
