/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import entite.Casefile;
import entite.Sample;
import error.SampleWithoutCasefileError;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author S.ANDRIANAVONY
 */
@Stateless
public class SamplesUtility {
    
    @Inject SamplesFind samplesFind;

    public Sample createOrRetreiveSampleCurrent(Casefile casefileCurrent) throws SampleWithoutCasefileError  {
        //System.out.println("Dans SampleUtility createOrRetreiveSampleCurrent casefileCurrent.getBatch "+casefileCurrent.getBatch());
        if(null == casefileCurrent){
            //System.out.println(" caseffileCurrente vaut null ====================================<<<<<<<<<<<<<<<<<<");
            throw new SampleWithoutCasefileError("Case File must be provided before creating samples");
        }
        List<Sample>  sampelsList= samplesFind.findSamplesCurrent(casefileCurrent);
        if(sampelsList.isEmpty()){
            return createSample(casefileCurrent);
        }
        return sampelsList.get(0);
        
    }

    public Sample createSample(Casefile casefile) throws SampleWithoutCasefileError {
        if(null == casefile){
            throw new SampleWithoutCasefileError("Case File must be provided before creating samples");
        }
         
        Sample samples = new Sample();
        samples.setIscurrent(Boolean.TRUE);
        samples.setArticle(casefile.getArticle());
        samples.setBatchname(casefile.getBatchname());
        samples.setCreationdate(utilities.DateManager.getNow());
        samples.setStatutlabel("logged");
        samples.setBatch(casefile.getBatch());
        //System.out.println(casefile.getBatch() + " => dans creation samples.setBatch ******************************"+samples.getBatch());
        samples.setCasefile(casefile);
        samples.setSpecie(casefile.getSpecie());
        samples.setStage(casefile.getStage());
        //samplesCurrent.setLimssampleid(limssampleid);
        samples.setLimsfolderno(casefile.getLimsfolderno());
        //System.out.println("Dans create sample ******************************"+samples);
                
        return samples;
    }
    
}
