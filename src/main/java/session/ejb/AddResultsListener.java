package session.ejb;

import entite.Analyse;
import entite.Batch;
import entite.Company;
import entite.Resultat;
import entite.Traca;
import entite.Wo;
import java.util.List;
import javax.annotation.PostConstruct;
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
public class AddResultsListener {

    @PersistenceContext ()
    public EntityManager em;
    
    //private entite.Resultat resultatInserted;
    
    @Inject RecopieAnalyse recopieAnalysis;
   
    @Inject 
    //@EJB
             public Find find;
    
    
    
    
    public AddResultsListener(){
        System.out.println(" Creation AddResultsListener debut =================================================**********************************************"+find);
        
        /*
        try {
           
            find = InitialContext.doLookup("java:global/classes/Find!session.ejb.Find");//java:global/classes/Find!session.ejb.Find, java:global/classes/Find
        } catch (NamingException ex) {
            Logger.getLogger(AddResultsListener.class.getName()).log(Level.SEVERE, null, ex);
        }
         System.out.println(" Creation AddResultsListener FIN =================================================**********************************************"+find);
//         System.out.println(" Ceration AddResultsListener =========================================="+find.em);
       */ 
    
    }
    
    @PostConstruct
    public void log(){
        System.out.println(" Post creation Ceration AddResultsListener =================================================***************************************"+find);
    }
        
    
    public void heritageDesResultats(entite.Resultat resultatInserted){
        
        System.out.println(" saisie résultats de "+resultatInserted.getResultat()+ " avec la valeur " + resultatInserted.getRawresultat());
       

        System.out.println(" *************** Saotra Find dans AddResultsListener ====================================================="+em);
        System.out.println(" *************** Saotra Find dans AddResultsListener=====================================================************************************************" + find);
        System.out.println(" *************** Saotra Find dans AddResultsListener=====================================================");

       Batch batch= find.getBatch(resultatInserted);
        List<entite.Batch> descendantsList= find.getListBatchDescendants(batch);
        if(null==descendantsList){
            return;
        }
        System.out.println("==================================================== descendantsList.size()  "+descendantsList.size());
        for (entite.Batch descendantsBatch: descendantsList ){
            System.out.println("Insertion heritage dans "+descendantsBatch);
            //facadeSaisieResultats.copieResultats(resultatInserted,descendantsBatch,Constant.typeDeCopie.HERITAGE, );
        }
        
    }
    
    
    /*
    public List<entite.Traca> listeWoProduction(Batch batchProduit) {
        return find.listeWoProduction(batchProduit);
    }

    public List<Batch>  getBatchFromTraca(Traca traca) {
        return find.getBatchFromTraca(traca);
    }

    public List<Batch> getListBatchDescendants(Resultat  resultatInserted) {
        return find.getListBatchDescendants(resultatInserted.getBatch());
    }
*/
    public List<Batch> getListBatchDescendants(Resultat  resultatInserted) {
        return getListBatchDescendants(resultatInserted.getBatch());
    }
    
    public List<entite.Traca> listeWoProduction(Batch batchProduit) {
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
    public List<Batch> getListBatchDescendants(Batch lotParent){
        
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
        return getBatchByBatch(resultat.getBatch());
    }
    
    public Batch getBatch(Analyse analysis) {        
        if(analysis == null){
            return null;
        }
        return getBatchByBatch(analysis.getBatch());
    }

    public Batch getBatchByBatch(Batch batch) {
        System.out.println(" entrer dans getBatchByIdbatch ***********************************************");
        
        TypedQuery<Batch> q =em.createNamedQuery("Batch.findByBatch", Batch.class);
        q.setParameter("batch",batch );
        if(q.getResultList().isEmpty()){
            return null;
        }
        return q.getResultList().get(0); 
    }
}   

    
