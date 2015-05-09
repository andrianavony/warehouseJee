/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import entite.Article;
import entite.Batch;
import entite.Company;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import session.ejb.ArticleManager;

/**
 *
 * @author S.ANDRIANAVONY
 */
@Stateless
public class BatchUtility {
    @Inject BatchFind batchFind;
    
    @Inject ArticleManager articleManager;
    
    
    public Batch createBatch(String idspecie, String idvariety, String idstage, String idgeneration, String batchname, String companyname) {

        Article idarticle=articleManager.createOrRetreiveArticle(  idspecie, idvariety,idgeneration, idstage);
        return createBatch(batchname,idarticle, companyname);
    }
    
    public Batch createOrRetrieveBatch(String idspecie, String idvariety, String idstage, String idgeneration, String batchname, String companyname) {
        Article idarticle=articleManager.createOrRetreiveArticle(  idspecie, idvariety,idgeneration, idstage);
        BigInteger nullLimsbatchid=null;
        return createOrRetrieveBatch(batchname,idarticle, companyname, nullLimsbatchid);
    }
    
    
    

    public Batch createOrRetrieveBatch(String batchname, String articlename, String idcompany, BigInteger limsbatchid) {
        Article idarticle= articleManager.createOrRetreiveArticle(articlename);
        return createOrRetrieveBatch(batchname, idarticle, idcompany, limsbatchid);
    }
    
    
    public Batch createOrRetrieveBatch(String batchname, Article idarticle, String idcompany, BigInteger limsbatchid) {
         Batch b = null;
        List<Batch> batchsList =null;
        System.out.println("entrer dans Facade Create batch");
        batchsList = batchFind.findExistingBatchByLimsBatchId(limsbatchid);
        
        if(batchsList.isEmpty()){
            return createBatch(batchname, idarticle, idcompany, limsbatchid);
        }
        return batchsList.get(0);
    }
    
        public Batch createBatch(String batchName, Article idarticle, String companyName){ // {, String limsbatchid) {
            Batch batch =new Batch();
            batch.setBatchname(batchName);
            Company company =new Company(companyName);
            batch.setCompany(company);
            batch.setArticle(idarticle); 

            batch.setSpecie(idarticle.getSpecie());

            batch.setVariety(idarticle.getVariety());

            batch.setGeneration(idarticle.getGeneration());

            batch.setStage(idarticle.getStage());
                       
            return batch;

    }
        
    public Batch createBatch(String batchName, Article idarticle, String companyName, BigInteger limsbatchid) {
            Batch batch =createBatch(batchName, idarticle, companyName);
            batch.setLimsbatchid(limsbatchid);                      
            return batch;

    }    
}
