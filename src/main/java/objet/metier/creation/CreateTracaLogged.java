/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objet.metier.creation;

import objet.metier.excel.TracaBean;
import entite.Article;
import entite.Batch;
import entite.Company;
import entite.Trace;
import entite.Wo;
import entite.WoPK;
import entite.Traca;
import entite.Variety;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
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
@LocalBean
public class CreateTracaLogged {
    

    @PersistenceContext
    protected EntityManager em;
    
    
    @Inject TracaBean tracaBean;

    

    /**
     * Prendre en charge les traces au statut Logged
     */
    public void createTraceAndBatchForTracaLogged(){
        
        /*
         TypedQuery<Variety> queryVar = em.createNamedQuery("Variety.findByIdvariety", Variety.class);
        System.out.println(" qreation query fate *********************************************");
            queryVar.setParameter("idvariety", "S10039");
            List<Variety> varietyList =queryVar.getResultList();
            
            System.out.println("varietyList "+ varietyList.size());
            
            assert(varietyList.size()==1);
        */
        TypedQuery<Traca> query =em.createNamedQuery("Traca.findTracaLogged",Traca.class);
        List<Traca>  tracaLoggedList = query.getResultList();
        System.out.println(" Nb de tracaLoggedList **************************************************************  "+tracaLoggedList.size());
        for(Traca tracaLogged : tracaLoggedList ){
            System.out.println("dans Boucle createTraceAndBatchForTracaLogged ******************************************* "+tracaLogged.getArticle());
            tracaBean.setInfo(tracaLogged);
            System.out.println(" demande de création ***************************************************");
            tracaBean.createTraca();
            tracaBean.setDone(tracaLogged);
        }        
    }

    

}
