/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.ejb;


import entite.Casefile;
import entite.Sample;
import entite.Analyse;
import error.AnalysisWithoutSamplesError;
import error.ResultsWithoutAnalysisError;
import error.SampleWithoutCasefileError;
import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import utilities.SamplesUtility;

/**
 *
 * @author S.ANDRIANAVONY
 */
@Stateful
public class SamplesManager {
    @PersistenceContext ()
    protected EntityManager em;
    
    @Inject  
    AnalysisManager analysisManager ;
    @Inject SamplesUtility samplesUtility;
    
    Sample samplesCurrent;
    
    Casefile casefileCurrent;
            
    public SamplesManager (){
        //System.out.println("entrerdans SamplesManager *************");
    }
    
    public entite.Analyse addresults(Casefile casefileCurrent, BigInteger idModeleanalyse, String methodname, String mesureName, String rawresult) throws SampleWithoutCasefileError, ResultsWithoutAnalysisError {
        
        //setCasefile(casefileCurrent);
        if(null==samplesCurrent){
            samplesCurrent=samplesUtility.createOrRetreiveSampleCurrent(casefileCurrent);
        }
        analysisManager.setSamplesCurrent(samplesCurrent);
        Analyse a =null;
        try {
            a = analysisManager.addresults(idModeleanalyse,  methodname,  mesureName,  rawresult);
        } catch (AnalysisWithoutSamplesError ex) {
            Logger.getLogger(SamplesManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return a;
    }

    public void setCasefile(Casefile casefileCurrent) {
        this.casefileCurrent=casefileCurrent;
    }
    
    public Sample createSamples(String limssampleid) throws SampleWithoutCasefileError {
        Sample samples = createSamplesWithLimssampleid(limssampleid);
         samplesCurrent = em.merge(samples);
         //System.out.println(samplesCurrent +" on retourne pour "+samplesCurrent.getLimssampleid());
         return samplesCurrent;
    }
    
    public Sample createSamplesWithLimssampleid(String limssampleid) throws SampleWithoutCasefileError {
        Sample s =createSamples();
        s.setLimssampleid(limssampleid);
        em.persist(s);
        return s;
    }
        
    public Sample createSamples() throws SampleWithoutCasefileError {
        return  samplesUtility.createSample(casefileCurrent);
    } 

    public Sample createOrRetreiveSampleCurrent(Casefile casefile) throws SampleWithoutCasefileError {
        setCasefile(casefile);
        Sample samples =samplesUtility.createOrRetreiveSampleCurrent(casefile);
        //em.merge(casefileCurrent);
        samplesCurrent = em.merge(samples);
        return samplesCurrent; 
    }

    
    
    
    
}
