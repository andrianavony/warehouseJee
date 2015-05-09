/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import entite.Batch;
import entite.Casefile;
import entite.Sample;
import error.CasefileWithoutBatchError;
import error.IdcasefileNotFoundError;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 *
 * @author S.ANDRIANAVONY
 */
@Stateless
public class CasefileUtility {

    @Inject CaseFileFind caseFileFind;
    @Inject SamplesFind samplesFind;
    
    public Casefile createOrRretriveCasefileForTypeDeCopie(entite.Batch idbatch,Constant.typeDeCopie typeDeCopie) {
        List<Casefile> casefilesList =caseFileFind.findCasefileForTypedeCopie( idbatch, typeDeCopie);  
        return createOrRretriveFirstElement(casefilesList, idbatch, typeDeCopie);
    }
    
    public Casefile createOrRretriveFirstElement(List<Casefile> casefilesList, entite.Batch idbatch,Constant.typeDeCopie typeDeCopie){
        System.out.println(" entrer dans createOrRretriveFirstElement ");
        Casefile c =null;
        if(casefilesList.isEmpty()){
            System.out.println(" empty createOrRretriveFirstElement ");
            c=createCaseFile(idbatch);
            System.out.println(" empty apres createOrRretriveFirstElement "+c);
            c.setCasefiletype(typeDeCopie.toString());
            System.out.println(" empty apres createOrRretriveFirstElement "+c.getCasefiletype());
        } else {
            c=casefilesList.get(0);
        }
        System.out.println(" createOrRretriveFirstElement "+c.getCasefiletype());
        return c;
    }
    
    public Sample createOrRetriveSampleForTypeDeCopie(entite.Batch idbatch,Constant.typeDeCopie typeDeCopie) {
        Casefile c =createOrRretriveCasefileForTypeDeCopie(idbatch,typeDeCopie) ;
        return createOrRretriveSamplesCurrent(c) ;
    }
        
    public Sample createOrRretriveSamplesCurrent(Casefile casefile) {
        List<Sample> listSamplese = samplesFind.findSamplesCurrent(casefile);
        return createOrRretriveFirstElement(listSamplese,  casefile);
    }
    
    public Sample createOrRretriveFirstElement(List<Sample> listSamplese, Casefile casefile){
        if(listSamplese.isEmpty()){
            return createSamples(casefile, null);
        }
        return listSamplese.get(0);
    }
    
    public Sample createSamples( Casefile casefile, String limssampleid) {
        System.out.println("Dans create sample ******************************");
        Sample sample = new Sample();
        sample.setIscurrent(Boolean.TRUE);
        sample.setArticle(casefile.getArticle());
        sample.setBatchname(casefile.getBatchname());
        sample.setCreationdate(utilities.DateManager.getNow());
        sample.setStatutlabel("logged");
        sample.setBatch(casefile.getBatch());
        sample.setCasefile(casefile);
        sample.setSpecie(casefile.getSpecie());
        sample.setStage(casefile.getStage());
        sample.setLimssampleid(limssampleid);
        sample.setLimsfolderno(casefile.getLimsfolderno());
        return sample;
    }
    
    
    public Casefile createOrRetriveCaseFileCurrent(entite.Batch idbatch) throws CasefileWithoutBatchError {
        if(null== idbatch){
            throw new CasefileWithoutBatchError (" the Batch is null");
        }
        List<Casefile> casefilesList = caseFileFind.findCaseFile(idbatch);
        Casefile casefileCurrent = null;
        
        if(casefilesList.isEmpty()){
            casefileCurrent=createCaseFile(idbatch);
            return casefileCurrent;
        }
        
        for(Casefile casefile : casefilesList){
            if(casefile.getIscurrent()){
                casefileCurrent=casefile;
                return casefileCurrent;
            }
        }
        // aucun dossier n'est courant
        casefileCurrent=createCaseFile(idbatch);        
        return casefileCurrent;
    }
    
    public Casefile createCaseFile(entite.Batch batch) {
        
        Casefile casefile=new Casefile();
        casefile.setCreationdate(DateManager.getNow());
        casefile.setStatutlabel("current");
        casefile.setStatut(Boolean.TRUE);
        casefile.setIscurrent(Boolean.TRUE);
        
        setInfoFromBatch(casefile, batch);
        return casefile;
    }
        
    public void setInfoFromBatch(Casefile casefileCurrent,Batch batchCurrent){
        casefileCurrent.setBatch(batchCurrent);
        casefileCurrent.setArticle(batchCurrent.getArticle());

        casefileCurrent.setBatchname(batchCurrent.getBatchname());
        casefileCurrent.setCreationdate(DateManager.getNow());
        casefileCurrent.setStatutlabel("current");
        casefileCurrent.setStatut(Boolean.TRUE);
        casefileCurrent.setIscurrent(Boolean.TRUE);
        
        casefileCurrent.setProdgroup(batchCurrent.getProdgroup());
        casefileCurrent.setSpecie(batchCurrent.getSpecie());
        casefileCurrent.setStage(batchCurrent.getStage());
        casefileCurrent.setLimsbatchid(batchCurrent.getLimsbatchid());
        casefileCurrent.setLimsfolderno(batchCurrent.getLimsfolderno());       
    }

    public Casefile createCaseFile(Batch batch, String limsfolderno, Integer numdemandelims) {
        Casefile c=null;
        
        if(limsfolderno!=null){
            c=caseFileFind.findExistingFolderno (limsfolderno,numdemandelims);
        }        
        if(null==c){
            c= createCaseFile(batch);
            c.setLimsfolderno(limsfolderno);
            c.setNumdemandelims(numdemandelims);
        }
        return c;
    }

    public Casefile retrieveCasefile(BigInteger idcasefile) throws IdcasefileNotFoundError{
        return caseFileFind.retrieveCasefile(idcasefile);
    }

    public Casefile retrieveCasefile(String limsfolderno, Integer numdemandelims) throws IdcasefileNotFoundError{
        return caseFileFind.findExistingFolderno(limsfolderno, numdemandelims);
    }
    
}
