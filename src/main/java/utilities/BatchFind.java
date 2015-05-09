/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import entite.Article;
import entite.Batch;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author S.ANDRIANAVONY
 */
@Stateless
public class BatchFind {
    
    @PersistenceContext ()
    protected EntityManager em;
    
    
    
    public Batch verificationBaseVide()throws javax.persistence.NoResultException   {
        TypedQuery<Batch> query = em.createNamedQuery("Batch.findAll", Batch.class);
            Batch batch=query.getSingleResult();
            return batch;
    }
    
    public List<Batch> findAllBatch(){
        TypedQuery<Batch> query = em.createNamedQuery("Batch.findAll", Batch.class);
            List<Batch> batchsList =query.getResultList();
            return batchsList;
    }
    
    public List<Batch> findBatchExisting(String batchName,String idarticle,String idcompany){
        TypedQuery<Batch> query = em.createNamedQuery("Batch.findByArticleBatchnameCompanyname", Batch.class);
            query.setParameter("batchname", batchName);
            query.setParameter("article" ,idarticle);
            query.setParameter("company", idcompany);
            List<Batch> batchsList =query.getResultList();
            return batchsList;
    }
    
    public List<Batch> findExistingBatchByBatch(BigInteger idbatch) {
        Batch b=null;
        TypedQuery<Batch> q = em.createNamedQuery("Batch.findByBatch",Batch.class);
            q.setParameter("batch", idbatch);
       return  q.getResultList();
    }

    public List<Batch>   findExistingBatchByLimsBatchId(String limsbatchid) {
        if(null==limsbatchid){
            limsbatchid="-1";
        }
        BigInteger b =new BigInteger(limsbatchid);
        return findExistingBatchByLimsBatchId(b);
    }
    
    public List<Batch>  findExistingBatchByLimsBatchId(BigInteger limsbatchid) {
       
        TypedQuery<Batch> q = em.createNamedQuery("Batch.findByLimsbatchid",Batch.class);
        q.setParameter("limsbatchid", limsbatchid);
       return q.getResultList();
    }

    public List<Batch> findByArticleBatchname(String batchname, Article article) {
        TypedQuery<Batch> query =em.createNamedQuery("Batch.findByArticleBatchname",Batch.class)
                .setParameter("batchname", batchname)
                .setParameter("article", article);
         return query.getResultList();
    }
}
