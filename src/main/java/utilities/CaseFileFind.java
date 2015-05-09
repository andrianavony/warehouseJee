/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import entite.Batch;
import entite.Casefile;
import error.IdcasefileNotFoundError;
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
public class CaseFileFind {
    @PersistenceContext ()
    protected EntityManager em;
    
    
    public  Casefile findExistingCasefile(BigInteger idcasefile) {
        Casefile c =null; 
       TypedQuery<Casefile> q = em.createNamedQuery("Casefile.findByCasefile", Casefile.class);
        q.setParameter("idcasefile", idcasefile);
        List<Casefile> casefiles =q.getResultList();
        if(casefiles.isEmpty()){
            return null;
        }
        c=casefiles.get(0);
        return c;
    }
    
    public  Casefile findExistingFolderno(String limsfolderno, Integer numdemandelims) {
        Casefile c =null; 
       TypedQuery<Casefile> q = em.createNamedQuery("Casefile.findByLimsfoldernoNumdemandelims", Casefile.class);
        q.setParameter("limsfolderno", limsfolderno);
        q.setParameter("numdemandelims", numdemandelims);
        List<Casefile> casefiles =q.getResultList();
        if(casefiles.isEmpty()){
            return null;
        }
        c=casefiles.get(0);
        return c;
    }
    
    public List<Casefile> findCaseFile(Batch batch) {

        
        TypedQuery<Casefile> query= em.createNamedQuery("Casefile.findByBatch", Casefile.class);
            query.setParameter("idbatch", batch);
            return query.getResultList();
    }
    
    public List<Casefile> findCasefileForTypedeCopie(entite.Batch idbatch,Constant.typeDeCopie typeDeCopie) {
       TypedQuery<Casefile> q = em.createNamedQuery("Casefile.findByBatchCasefiletype", Casefile.class);
        q.setParameter("idbatch", idbatch);
        q.setParameter("casefiletype", typeDeCopie.toString());
        List<Casefile> casefiles =q.getResultList();
        return casefiles;
    }

    public Casefile retrieveCasefile(BigInteger idcasefile) throws IdcasefileNotFoundError {
        Casefile c =null; 
       TypedQuery<Casefile> q = em.createNamedQuery("Casefile.findByCasefile", Casefile.class);
        q.setParameter("idcasefile", idcasefile);
        List<Casefile> casefiles =q.getResultList();
        if(casefiles.isEmpty()){
            throw new IdcasefileNotFoundError("Case file "+idcasefile +" not Found");
        }
        c=casefiles.get(0);
        return c;
    }
    
    public  Casefile retrieveCasefile(String limsfolderno, Integer numdemandelims) throws IdcasefileNotFoundError{
        Casefile c =null; 
       TypedQuery<Casefile> q = em.createNamedQuery("Casefile.findByLimsfoldernoNumdemandelims", Casefile.class);
        q.setParameter("limsfolderno", limsfolderno);
        q.setParameter("numdemandelims", numdemandelims);
        List<Casefile> casefiles =q.getResultList();
        if(casefiles.isEmpty()){
            throw new IdcasefileNotFoundError("Case file lims "+limsfolderno +" not Found for NumDemand "+ numdemandelims);
        }
        c=casefiles.get(0);
        return c;
    }
}
