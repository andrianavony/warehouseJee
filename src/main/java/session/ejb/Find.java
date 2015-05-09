/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.ejb;

import entite.Analyse;
import entite.Batch;
import entite.Company;
import entite.Resultat;
import entite.Traca;
import entite.Wo;
import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

/**
 *
 * @author S.ANDRIANAVONY
 */
@Stateless
public class Find {
    //@PersistenceContext ()
    //protected EntityManager em;
    
    private static EntityManager em = null;

    public static void setEm(EntityManager entityManager) {
        em = entityManager;
    }

    
    private EntityManagerFactory emf;
    
    
    public Find(){
        //emf=Persistence.createEntityManagerFactory("dbwEJBwsPU");
        //em=emf.createEntityManager();
         //System.out.println("*********** Creation de FIND **************============================================"+em);
         //InitialContext ic=null;
         
         /*
        try {
            System.out.println("*********** dans try de FIND **************============================================");
            ic=new InitialContext();
            em=(EntityManager) ic.lookup("java:comp/env/persistence/dbwEJBwsPU");
            System.out.println("*********** Dans de FIND **************============================================"+em);
        } catch (NamingException ex) {
            Logger.getLogger(Find.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
         
    }
    @PostConstruct
    public void init(){
        System.out.println("*********** PostConstruct Creation de FIND **************============================================");
    }
    
    public List<entite.Traca> listeWoProduction(Batch batchProduit) {
        System.out.println("*********** Dans FIND **************============================================"+em);
        if(null == batchProduit){
            return null;
        }
        Wo idWo= batchProduit.getWo();
        Company idcompany =batchProduit.getCompany();
        System.out.println("idWo "+idWo);
        System.out.println("idcompany"+idcompany);
        TypedQuery<entite.Traca> q = em.createNamedQuery("Traca.findByIdwo_Production", entite.Traca.class);
        q.setParameter("idwo", idWo); 
        q.setParameter("idcompany", idcompany.getCompany());
        List<entite.Traca> tmp=q.getResultList();
        System.out.println("q "+q);
        return tmp;
        
    }
    
    public List<Batch>  getBatchFromTraca(Traca traca) {
        TypedQuery<Batch> q =em.createNamedQuery("Batch.findByIdarticleBatchnameCompanynameIdWo", Batch.class);
        q.setParameter("batchname",traca.getBatchname() ); 
        q.setParameter("idarticle", traca.getArticle()); 
        q.setParameter("idcompany", traca.getCompany());
        String idwo =traca.getWo().getWoPK().getWo();
        System.out.println("idwo "+idwo);
        q.setParameter("idwo", traca.getWo());
        
        return q.getResultList(); 
        
    }
    
    /***
     * trouve les lots qui ont été produits a partir du lot du résultats d'analyse
     * OF028712
     **/
    public List<entite.Batch> getListBatchDescendants(Batch lotParent){
         System.out.println("*********** Dans FIND **************============================================"+em);
        List<entite.Traca> woProduction = listeWoProduction (lotParent);
        if(null==woProduction){
            return null;
        }
        if(woProduction.isEmpty()){
            return null;
        }
        
        List<entite.Batch> descendants = null; //new ArrayList<>(woProduction.size());
        for(entite.Traca traca : woProduction){
            System.out.println(" traca trouver "+traca );
            descendants=getBatchFromTraca(traca);
            System.out.println("descendants "+descendants.size());
        }
        
        return descendants;
    }

    public Batch getBatch(Resultat  resultat) {        
        if(resultat == null){
            return null;
        }
        return getBatchByIdbatch(resultat.getBatch().getBatch());
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
