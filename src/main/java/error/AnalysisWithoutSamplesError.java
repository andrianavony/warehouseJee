/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package error;

/**
 *
 * @author S.ANDRIANAVONY
 */
public class AnalysisWithoutSamplesError extends Exception {
    public AnalysisWithoutSamplesError(String Message){
        super(Message);
    }
}
