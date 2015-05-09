/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.ejb;

import entite.Batch;
import entite.Casefile;
import error.CasefileWithoutBatchError;
import error.IdcasefileNotFoundError;
import error.ResultsWithoutAnalysisError;
import error.SampleWithoutCasefileError;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import utilities.Constant;
import utilities.DateManager;
import utilities.CasefileUtility;

/**
 *
 * @author S.ANDRIANAVONY
 */
@Stateful
public class CasefileManager {

    @PersistenceContext ()
    protected EntityManager em;
    
    public CasefileManager() {
    }

    
    @Inject SamplesManager samplesManager;  
    
    public Casefile casefileCurrent;
    @Inject CasefileUtility casefileUtility;
    public Batch batchCurrent ;
    
    public Casefile createOrRetriveCaseFileCurrent() throws CasefileWithoutBatchError{
        casefileCurrent =casefileUtility.createOrRetriveCaseFileCurrent(batchCurrent);
        return casefileCurrent;
    }

    
    public Casefile merge(){
        return em.merge(casefileCurrent);
    }
    
    public Casefile createCaseFilexxx() {
        //System.out.println("passe dans creation DL ******************");
        casefileCurrent=new Casefile();
        setInfoFromBatchCurrent();
        /*
        casefileCurrent =em.merge(casefileCurrent);
        samplesManager.setCasefile(casefileCurrent);
        //em.flush();
        //System.out.println("casefileCurrent "+casefileCurrent.getCasefile());
        */        
        return merge ();
    }
    
    public void setInfoFromBatchCurrent(){
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
        
     

    public entite.Analyse addresults(BigInteger idModeleanalyse, String methodname, String mesurename, String rawresults) throws SampleWithoutCasefileError, ResultsWithoutAnalysisError {
        //System.out.println("********************** dans DL manager addResults");
        return samplesManager.addresults(casefileCurrent,idModeleanalyse,methodname , mesurename, rawresults);
    }

    public void setCasefileCurrent(Casefile casefile) {
        casefileCurrent=casefile;
    }

    

   public  void setCurrentBatch(Batch idbatch) {
        this.batchCurrent=idbatch;
    }

   public Casefile createCaseFile(String limsfolderno, Integer numdemandelims) {
       //Casefile c =createCaseFile();
       Casefile c =casefileUtility.createCaseFile(batchCurrent);
       c.setLimsfolderno(limsfolderno);
       c.setNumdemandelims(numdemandelims);
       casefileCurrent=em.merge(c);
       return casefileCurrent;
    }

    public Casefile createOrRretriveCasefileForTypeDeCopie(entite.Batch idbatch,Constant.typeDeCopie typeDeCopie) {
        Casefile c =null;
        setCurrentBatch(idbatch);
        casefileCurrent = casefileUtility.createOrRretriveCasefileForTypeDeCopie(idbatch, typeDeCopie);
        /*if(null==casefileCurrent.getCasefile()){
            //System.out.println("*****************************************************casefileCurrent avant insertion "+casefileCurrent);
            
            em.persist(casefileCurrent);
            em.flush();
            //System.out.println("*****************************************************casefileCurrent apres insertion "+casefileCurrent);
        }
                */
        em.merge(casefileCurrent);        
        //System.out.println("*****************************************************casefileCurrent vaut "+casefileCurrent);
        return casefileCurrent;
           
    }

    public Casefile createOrRetrieveCasefile(String limsfolderno, Integer numdemandelims) {
        Casefile c=casefileUtility.createCaseFile(batchCurrent,limsfolderno,  numdemandelims );
        casefileCurrent=em.merge(c);
        return casefileCurrent;
    }

    public Casefile retrieveCasefile(BigInteger idcasefile) throws IdcasefileNotFoundError{
        Casefile c = casefileUtility.retrieveCasefile(idcasefile);
        return em.merge(c);  
    }

    Casefile retrieveCasefile(String limsfolderno, Integer numdemandelims) throws IdcasefileNotFoundError{
        Casefile c =casefileUtility.retrieveCasefile( limsfolderno,  numdemandelims) ;
        return em.merge(c);  
    }

    
    
}
