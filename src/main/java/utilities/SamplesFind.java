/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import entite.Casefile;
import entite.Sample;
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
public class SamplesFind {
    @PersistenceContext ()
    protected EntityManager em;

    public List<Sample>  findSamplesCurrent(Casefile casefile) {
        TypedQuery<Sample> query = em.createNamedQuery("Samples.findByCasefileIscurrent", Sample.class);
        query.setParameter("idcasefile", casefile);
        return query.getResultList();
    }
    
    public List<Sample> findExistingSamples(String limssampleid) {
        TypedQuery<Sample> q = em.createNamedQuery("Samples.findByLimssampleid", Sample.class);
        q.setParameter("limssampleid", limssampleid);
        return q.getResultList();
    }
    
    
}
