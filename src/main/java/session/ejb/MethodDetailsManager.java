/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.ejb;

import entite.Measure;
import entite.Methode;
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
public class MethodDetailsManager {

    @PersistenceContext ()
    protected EntityManager em;
    
    public MethodDetailsManager() {
    }

    
    public Measure getOrCreateMeasure(Methode method, String measurename) {
        List<Measure> measureList =getMeasureList(method,  measurename);
        if(measureList.isEmpty()){
            return createMeasure(method, measurename);
        }else {
            return measureList.get(0);
        }
            
    }
    
    public List<Measure> getMeasureList(Methode method, String measurename) {
        BigInteger limsanalysisid=method.getLimsanalyseid();
        TypedQuery<Measure> query= em.createNamedQuery("Measure.findByLimsanalysisidMeasurename",Measure.class);
        query.setParameter("limsanalysisid", limsanalysisid);
        query.setParameter("measurename", measurename);
        
        List<Measure> measurList=query.getResultList();
                
        return measurList;
    }

    public Measure createMeasure(Methode method, String measurename) {
        Measure measures = new Measure();
        measures.setLimsanalyseid(method.getLimsanalyseid());
        measures.setMeasurename(measurename);
        measures=em.merge(measures);
        em.flush();
        System.out.println("+++++++++++++++++++++++++++++++++createMeasure "+measures.getMeasure());
        return measures;
    }
    
    
}
