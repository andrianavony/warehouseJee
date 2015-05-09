/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import entite.Resultat;
import javax.ejb.Stateless;

/**
 *
 * @author S.ANDRIANAVONY
 */
@Stateless
public class ResultsUtility {

    public static Resultat getCopieResults(Resultat  m) {
        Resultat c = new Resultat();
        c.setCopiedfromidanalyse(m);
        c.setRawresultat(m.getRawresultat());
        c.setRepetition(m.getRepetition());
        c.setSubrepetition(m.getSubrepetition());
        c.setAnalysename(m.getAnalysename());
        c.setBatchname(m.getBatchname());
        
        return c;
    }
    
}
