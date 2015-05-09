/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.ejb;

import entite.Analyse;
import entite.Measure;
import entite.Methode;
import entite.Resultat;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author S.ANDRIANAVONY
 */
@Stateful
public class ResultsManager {

    @PersistenceContext ()
    protected EntityManager em;

    @Inject 
    MethodDetailsManager methodDetailsManager;
    
    public ResultsManager() {
        System.out.println("entrer dans ResultsManager ******************");
    }

    public Resultat addresults(Analyse analysis, Methode method, String mesureName, String rawresult) {
        Measure measures = createOrUpdateMeasure(method, mesureName);
        return addresults( analysis,  method, measures,  rawresult);
        
    }

    public Measure createOrUpdateMeasure(Methode method, String measurename) {
        return methodDetailsManager.getOrCreateMeasure( method, measurename);
    }

    public Resultat addresults(Analyse a, Methode method, Measure measures, String rawresult) {
        System.out.println("entrer dans addresults ******************");
        Resultat r = new Resultat();
        r.setRawresultat(rawresult);
        
        System.out.println("========================"+a.getAnalyse());
        r.setAnalyse(a);
        r.setAnalysename(a.getAnalysename());
        
        System.out.println("========================"+a.getArticle());
        r.setArticle(a.getArticle());
        System.out.println("========================"+a.getBatchname());
        r.setBatchname(a.getBatchname());
        r.setAnalysename(a.getAnalysename());
        
        System.out.println("========================"+method.getMethode());
        r.setMethode(method);
        r.setMethodename(a.getMethodename());
        
        r.setAnalyse(a);
        r.setModeleanalyse(a.getModeleanalyse());
        
        System.out.println("========================"+a.getBatch());
        r.setBatch(a.getBatch());
        r.setCasefile(a.getCasefile());
        r.setSample(a.getSample());
        
        System.out.println("========================"+measures.getMeasure());
        r.setMeasure(measures);
        System.out.println("========================"+measures.getMeasurename());
        r.setMeasurename(measures.getMeasurename());
        
        
        
        r.setCreationdate(utilities.DateManager.getNow());
        
        return em.merge(r);
        
    }
    
    
    
}
