/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.ejb;

import entite.Analyse;
import entite.Article;
import entite.Batch;
import entite.Casefile;
import entite.Resultat;
import entite.Sample;
import error.AnalysisWithoutSamplesError;
import error.BatchIdNotFindError;
import error.IdcasefileNotFoundError;
import error.ResultsWithoutAnalysisError;
import error.SampleWithoutCasefileError;
import java.math.BigInteger;
import java.util.Date;
import utilities.Constant;
import javax.enterprise.event.Observes;

/**
 *
 * @author S.ANDRIANAVONY
 */
public interface I_FacadeSaisieResultats {
    /***
     * Le lot existe déjà dans la base et on souhaite le recuperer.
     * @param idbatch IdBatch doit être connu (lors des imports des résutlats via batchtemp par exemple)
     *                on utilisera cette info pour retourne ce batch
     * @exception Si IdBatch ne correspond pas à un lot on le signale à l'appelant
     */
    public entite.Batch retrieveBatchFromIdBatch(BigInteger idbatch)  throws BatchIdNotFindError ;
    
    
    /***
     * On cherche d'abord à trouver un lot ayant l'identifiant LIMS 
     * S'il n'y a pas on crée un nouveau lot
     * @param batchname
     * @param idarticle 
     * @param idcompany
     * @param  limsbatchid 
     */
    public entite.Batch createOrRetrieveBatch( String batchname, String idarticle, String idcompany ,String limsbatchid);
    
    public entite.Batch createOrRetrieveBatch( String batchname, String idarticle, String idcompany ,BigInteger limsbatchid);
    
    /***
     * @param batchname
     * @param idarticle 
     * @param idcompany
     * @param  limsbatchid 
     */
    public entite.Batch createOrRetrieveBatch( String batchname, Article idarticle, String idcompany ,String limsbatchid);
    
    public entite.Batch createOrRetrieveBatch( String batchname, Article idarticle, String idcompany ,BigInteger limsbatchid);
    
    public entite.Casefile retrieveCasefile(BigInteger idcasefile) throws IdcasefileNotFoundError;
    
    public entite.Casefile createOrRetrieveCasefile(entite.Batch idbatch,String limsfolderno, Integer numdemandelims) throws BatchIdNotFindError;
    
    public entite.Casefile createOrRretriveCasefileForTypeDeCopie(entite.Batch idbatch,Constant.typeDeCopie typeDeCopie);
    
    public entite.Analyse  createAnalysis(Sample samples, Resultat resultatInserted) throws AnalysisWithoutSamplesError;
    
    public entite.Sample createSample(entite.Casefile idcasefile,    String limssampleid) throws SampleWithoutCasefileError ;
    
    public Analyse createAnalysisViaInfoLims(Sample idsamples, BigInteger limsanalysisorigrec, BigInteger limsanalysisid, String analysename,  String methodname, BigInteger limsidseries)
            throws AnalysisWithoutSamplesError ;
    
    /****
     * On souhaite reciperer l'analyse sur l'échantillon si elle existe au lieu de créee une nouvelle.
     * @param 
     */
    public Analyse getOrCreateAnalysisViaLimsAnalysisOrigrec(Sample idsamples, BigInteger limsanalysisorigrec, BigInteger limsanalysisid, String analysename,  String methodname, BigInteger limsidseries)
            throws AnalysisWithoutSamplesError 
            ;
            
    public entite.Analyse createAnalysis(entite.Sample idsamples,BigInteger idmodeleanalyse, BigInteger idmethod);
    
    public entite.Analyse createAnalysisViaMethodDetails(entite.Sample idsamples,BigInteger idmethoddetails);
    
    public entite.Analyse createAnalysisViaLimsAnalysis(entite.Sample idsamples,BigInteger limsidanalysis, String limsidmethod);
    
       
    public entite.Analyse saisirResultat(entite.Analyse idanalysis, String measurename, BigInteger idmeasure , String rawresults,String formated, Short repetition,String username, Date dateofentry, String statutsLabel);
    
    public entite.Analyse saisirResultatIssusLims(entite.Analyse idanalysis, BigInteger limsanalysisorigrec, String measurename, BigInteger limsmeasureid , String rawresults,String formated, Short repetition,  String username, Date dateofentry, String statutsLabel);
    
    /***
     * permet de faire une copie d'un résulta sur un lot données
     * 
     * @param resultat le srésultat à copier sur le lot descendant
     * @param descendantsBatch le lot qui va recevoir le résultat
     * @param  typeDeCopie  heritage, regroupement, ...
     * @param  samplesDescendantsBatch l échantillon qui portera le résultat.
     *        Si null on crée un nouveau échantillon sur le dossier lot de Type typeDeCopie
     * @param analysisDescendantsBatch l'analyse qui portera le résultat.
     *        Si null on crée une nouvelle analyse.
     * @return  l'analyse sur laquelle on a fait copie des résultats. Cette amanyse pourra être utiliser pour d'autres copie de résultats
     */
    public entite.Analyse copieResultats(Resultat  resultat, Batch descendantsBatch, Constant.typeDeCopie typeDeCopie,Sample samplesDescendantsBatch, Analyse analysisDescendantsBatch) throws SampleWithoutCasefileError , AnalysisWithoutSamplesError;
    
        /***
     * permet de faire une copie d'un résulta sur un lot données
     * 
     * @param analysisACopier l'analyse dont on souhaite recopier les srésultats sur le lot descendant
     * @param descendantsBatch le lot qui va recevoir le résultat
     * @param  typeDeCopie  heritage, regroupement, ...
     * @param  samplesDescendantsBatch l échantillon qui portera le résultat.
     *        Si null on crée un nouveau échantillon sur le dossier lot de Type typeDeCopie
     * @param analysisDescendantsBatch l'analyse qui portera le résultat.
     *        Si null on crée une nouvelle analyse.
     * @return  l'analyse sur laquelle on a fait copie des résultats. Cette amanyse pourra être utiliser pour d'autres copie de résultats
     */
    //public entite.Analyse copieResultats(Analyse analysisACopier, Batch descendantsBatch, Constant.typeDeCopie typeDeCopie,Sample samplesDescendantsBatch, Analyse analysisDescendantsBatch);
    
   public entite.Analyse copieResultats(Analyse analysisACopier,  Constant.typeDeCopie typeDeCopie, Analyse analysisDescendantsBatch)throws ResultsWithoutAnalysisError; 

    public Sample createOrRetreiveSampleCurrent(Casefile casefileHeritage) throws SampleWithoutCasefileError ;

    /****
     * facade saisie resultat a une analyse courrante qu'on demande de faire l heritage
     */
    public void doHeritage();

    public Analyse getAnalysisCurrent();

    
    
}
