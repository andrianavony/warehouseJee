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
public class CasefileWithoutBatchError extends Exception {
    public CasefileWithoutBatchError(String Message){
        super(Message);
    }
}