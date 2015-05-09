/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Reprensente une ligne à prendre en charge dans la table des resultats temporaire.
 * @author S.ANDRIANAVONY
 */
public class ResultsTempTampon {
    
    private BigInteger batch;
    private String limsfolderno;
    private String limssampleid;
    private BigInteger limsanalysisorigrec;
    private BigInteger limsidanalysis;
    private String analysename;
    private String methodname;
    private BigInteger limsidseries;
    
    List<ResultsTempResult> resultsTempResultList = new ArrayList<>();
    
    
    
    public ResultsTempTampon(entite.Resultattemp result){
        this.batch=result.getBatch();
        this.limsfolderno = result.getLimsfolderno();
        this.limssampleid =result.getLimssampleid();
        this.limsanalysisorigrec = result.getLimsanalyseorigrec();
        this.limsidanalysis= result.getLimsidanalyse();
        this.analysename = result.getAnalysename();
        this.methodname = result.getMethodename(); 
        this.limsidseries = result.getLimsidserie();
    }
    
    public Boolean addResultsTemp(entite.Resultattemp result){
        Boolean Ok = false;
        if(batch.compareTo(result.getBatch())==0){
            
        }
        return false;
    }

    public BigInteger getBatch() {
        return batch;
    }

    public void setBatch(BigInteger batch) {
        this.batch = batch;
    }

    public String getLimsfolderno() {
        return limsfolderno;
    }

    public void setLimsfolderno(String limsfolderno) {
        this.limsfolderno = limsfolderno;
    }

    public String getLimssampleid() {
        return limssampleid;
    }

    public void setLimssampleid(String limssampleid) {
        this.limssampleid = limssampleid;
    }

    public BigInteger getLimsanalyseorigrec() {
        return limsanalysisorigrec;
    }

    public void setLimsanalyseorigrec(BigInteger limsanalysisorigrec) {
        this.limsanalysisorigrec = limsanalysisorigrec;
    }

    public BigInteger getLimsidanalyse() {
        return limsidanalysis;
    }

    public void setLimsidanalysis(BigInteger limsidanalysis) {
        this.limsidanalysis = limsidanalysis;
    }

    public String getAnalysename() {
        return analysename;
    }

    public void setAnalysename(String analysename) {
        this.analysename = analysename;
    }

    public String getMethodename() {
        return methodname;
    }

    public void setMethodename(String methodname) {
        this.methodname = methodname;
    }

    public BigInteger getLimsidserie() {
        return limsidseries;
    }

    public void setLimsidserie(BigInteger limsidseries) {
        this.limsidseries = limsidseries;
    }

    public List<ResultsTempResult> getResultsTempResultList() {
        return resultsTempResultList;
    }

    public void setResultsTempResultList(List<ResultsTempResult> resultsTempResultList) {
        this.resultsTempResultList = resultsTempResultList;
    }
    
    
}
