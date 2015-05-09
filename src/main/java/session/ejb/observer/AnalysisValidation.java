/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.ejb.observer;

import entite.Analyse;
import error.AnalysisWithoutSamplesError;
import error.SampleWithoutCasefileError;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;

import session.ejb.RecopieAnalyse;

/**
 *
 * @author S.ANDRIANAVONY
 */
@Stateless
public class AnalysisValidation {
    
    @Inject RecopieAnalyse recopieAnalyse;

    public void onAnalysis(@Observes(during = TransactionPhase.AFTER_SUCCESS) Analyse analysisEvent){
        //analysisManager.doOnAnalysis(event);
        //System.out.println(recopieAnalyse + " dans Facade declenchement evenement " + analysisEvent.getAnalyse());
        try {
            //if(analysisEvent.getHerited()){
            recopieAnalyse.doHeritage(analysisEvent);
            //}
        } catch (AnalysisWithoutSamplesError ex) {
            Logger.getLogger(AnalysisValidation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SampleWithoutCasefileError ex) {
            Logger.getLogger(AnalysisValidation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
