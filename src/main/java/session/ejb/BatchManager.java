/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.ejb;

import utilities.ArticleUtility;
import entite.Article;
import entite.Batch;
import entite.Casefile;
import entite.Company;
import entite.Variety;
import error.BatchIdNotFindError;
import error.CasefileWithoutBatchError;
import error.ResultsWithoutAnalysisError;
import error.SampleWithoutCasefileError;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import utilities.BatchFind;
import utilities.BatchUtility;

/**
 *
 * @author S.ANDRIANAVONY
 */
@Stateful
@LocalBean
public class BatchManager {
   @PersistenceContext ()
    protected EntityManager em;
   
   
   
   @Inject
   CasefileManager casefileManagerCurrent;
   @Inject BatchUtility batchUtility;
   @Inject BatchFind batchFind;
   @Inject ArticleManager articleManager;
    
   private Batch batchCurrent;
           
   public BatchManager(){
       
   }
   
    

   public Batch verificationBaseVide() throws javax.persistence.NoResultException  {
	   Batch b =null;
	   try {
		  b= batchFind.verificationBaseVide();
	   } catch ( javax.persistence.NoResultException ex ){
		   throw new javax.persistence.NoResultException ();
	   }
		  return b;
   }
   
   public List<Batch> findAllBatch(){
	   return batchFind.findAllBatch();
   }
   public Batch createBatch(String batchName,Article idarticle,String companyName){
        setBatchCurrent(em.merge(batchUtility.createBatch (batchName,idarticle,companyName)));
        return getBatchCurrent();
    }
   
    public Batch createBatch(String batchname, Article idarticle, String idcompany, BigInteger limsbatchid) {
        Batch b = createBatch(batchname, idarticle, idcompany  );
        b.setLimsbatchid(limsbatchid);
        batchCurrent =em.merge(b);
        return batchCurrent ; 
    }

    public Batch retrieveBatchFromBatch(BigInteger idbatch) throws BatchIdNotFindError  {
        List<Batch> batchList = batchFind.findExistingBatchByBatch(idbatch);
        if(batchList.isEmpty()){
            throw new BatchIdNotFindError("Pas de lot ayant le idBatch "+idbatch);
        }
        batchCurrent=em.merge(batchList.get(0));
        return batchCurrent;
    }

    /****
     * Creation ou recherche du lot à partir de son nom et du code article
     */
    public Batch createOrRetrieveBatch(String batchname, String articlename, String idcompany, BigInteger limsbatchid) {
        batchCurrent=em.merge(batchUtility.createOrRetrieveBatch(batchname, articlename, idcompany, limsbatchid));
        return batchCurrent;
    }

    Batch createOrRetrieveBatch(String batchname, Article idarticle, String idcompany, BigInteger limsbatchid) {
        batchCurrent=em.merge(batchUtility.createOrRetrieveBatch(batchname, idarticle, idcompany, limsbatchid));
        return batchCurrent;
    }
   
   
    public Batch createOrRetrieveBatch(String idspecie, String idvariety, String idstage, String idgeneration, String batchname, String companyname) {
        batchCurrent=em.merge(batchUtility.createOrRetrieveBatch(idspecie, idvariety, idstage, idgeneration, batchname, companyname));
        return batchCurrent;
    }

    /****
     * Creation ou recherche du lot à partir de son nom et de la variete 
     */    
    public Batch createOrUpdateBatchByVariety(String idvariety, String stageLabel, String idgeneration, String batchName, String compagnyName) {    
        Article article=articleManager.getArticleByVarietyStageLagel(idvariety, stageLabel, idgeneration);
        //System.out.println(stageLabel + " stageLabel => "+article);
        if(null==article){
            return null;
        }
        return createOrUpdateBatch(batchName,article,compagnyName);
    }
    
    
    
    public Batch createOrUpdateBatch(String batchName,String idarticle,String companyName){     
        List<Batch> batchsList= batchFind.findBatchExisting(batchName,idarticle,companyName);
        if(batchsList.isEmpty()){
            Article article = articleManager.createOrRetreiveArticle(idarticle);
            createBatch(batchName,article,companyName);
        } else {
            setBatchCurrent(batchsList.get(0));
        }    
        return getBatchCurrent();

    }
    
    public Batch createOrUpdateBatch(String batchName,Article idarticle,String companyName){
        List<Batch> batchsList= batchFind.findBatchExisting(batchName,idarticle.getArticle(),companyName);
        
        if(batchsList.isEmpty()){
            createBatch(batchName,idarticle,companyName);
        } else {
            setBatchCurrent(batchsList.get(0));
        }    
        return getBatchCurrent();

    }

    public Batch getBatchCurrent() {
        return batchCurrent;
    }

    public entite.Analyse addresults( BigInteger idModeleanalyse,String methodname, String mesurename, String rawresults) throws SampleWithoutCasefileError, CasefileWithoutBatchError, ResultsWithoutAnalysisError {
        entite.Casefile casefile= createOrRetriveCaseFileCurrent(); 
        return casefileManagerCurrent.addresults( idModeleanalyse, methodname, mesurename, rawresults);
    }



    public  entite.Casefile createOrRetriveCaseFileCurrent() throws CasefileWithoutBatchError {
        casefileManagerCurrent.setCurrentBatch(getBatchCurrent());
        entite.Casefile casefile = casefileManagerCurrent.createOrRetriveCaseFileCurrent();
        return casefile;
    }


    /**
     * @param batchCurrent the batchCurrent to set
     */
    public void setBatchCurrent(Batch batchCurrent) {
        this.batchCurrent = batchCurrent;
    }
    
    
    

    public Casefile createOrRetrieveCasefile(Batch idbatch, String limsfolderno, Integer numdemandelims) throws BatchIdNotFindError{
        Batch batchForRetreiveInformation = retrieveBatchFromBatch(idbatch.getBatch()); 
        
        
        casefileManagerCurrent.setCurrentBatch(batchForRetreiveInformation);
        return casefileManagerCurrent.createOrRetrieveCasefile( limsfolderno, numdemandelims);
    }

    

    
    
    
}
