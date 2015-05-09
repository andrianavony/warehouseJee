/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author S.ANDRIANAVONY
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resultattemp.findAll", query = "SELECT r FROM Resultattemp r"),
    @NamedQuery(name = "Resultattemp.findByResult", query = "SELECT r FROM Resultattemp r WHERE r.resultattemp = :resultattemp"),
    @NamedQuery(name = "Resultattemp.findByMeasure", query = "SELECT r FROM Resultattemp r WHERE r.measure = :measure"),
    @NamedQuery(name = "Resultattemp.findByLimsmeasureid", query = "SELECT r FROM Resultattemp r WHERE r.limsmeasureid = :limsmeasureid"),
    @NamedQuery(name = "Resultattemp.findByLimsid", query = "SELECT r FROM Resultattemp r WHERE r.limsid = :limsid"),
    @NamedQuery(name = "Resultattemp.findByLimsidserie", query = "SELECT r FROM Resultattemp r WHERE r.limsidserie = :limsidserie"),
    @NamedQuery(name = "Resultattemp.findBySerie", query = "SELECT r FROM Resultattemp r WHERE r.serie = :serie"),
    @NamedQuery(name = "Resultattemp.findByLimssampleid", query = "SELECT r FROM Resultattemp r WHERE r.limssampleid = :limssampleid"),
    @NamedQuery(name = "Resultattemp.findByAnalyse", query = "SELECT r FROM Resultattemp r WHERE r.analyse = :analyse"),
    @NamedQuery(name = "Resultattemp.findByModeleanalyse", query = "SELECT r FROM Resultattemp r WHERE r.modeleanalyse = :modeleanalyse"),
    @NamedQuery(name = "Resultattemp.findByLimsidanalyse", query = "SELECT r FROM Resultattemp r WHERE r.limsidanalyse = :limsidanalyse"),
    @NamedQuery(name = "Resultattemp.findByLimsidmethode", query = "SELECT r FROM Resultattemp r WHERE r.limsidmethode = :limsidmethode"),
    @NamedQuery(name = "Resultattemp.findByMethode", query = "SELECT r FROM Resultattemp r WHERE r.methode = :methode"),
    @NamedQuery(name = "Resultattemp.findByMethodedetail", query = "SELECT r FROM Resultattemp r WHERE r.methodedetail = :methodedetail"),
    @NamedQuery(name = "Resultattemp.findBySpecification", query = "SELECT r FROM Resultattemp r WHERE r.specification = :specification"),
    @NamedQuery(name = "Resultattemp.findBySample", query = "SELECT r FROM Resultattemp r WHERE r.sample = :sample"),
    @NamedQuery(name = "Resultattemp.findByCasefile", query = "SELECT r FROM Resultattemp r WHERE r.casefile = :casefile"),
    @NamedQuery(name = "Resultattemp.findByBatch", query = "SELECT r FROM Resultattemp r WHERE r.batch = :batch"),
    @NamedQuery(name = "Resultattemp.findByLimsbatchid", query = "SELECT r FROM Resultattemp r WHERE r.limsbatchid = :limsbatchid"),
    @NamedQuery(name = "Resultattemp.findByLimsfolderno", query = "SELECT r FROM Resultattemp r WHERE r.limsfolderno = :limsfolderno"),
    @NamedQuery(name = "Resultattemp.findByBatchname", query = "SELECT r FROM Resultattemp r WHERE r.batchname = :batchname"),
    @NamedQuery(name = "Resultattemp.findByStatut", query = "SELECT r FROM Resultattemp r WHERE r.statut = :statut"),
    @NamedQuery(name = "Resultattemp.findByStatutlabel", query = "SELECT r FROM Resultattemp r WHERE r.statutlabel = :statutlabel"),
    @NamedQuery(name = "Resultattemp.findByApprobationstatut", query = "SELECT r FROM Resultattemp r WHERE r.approbationstatut = :approbationstatut"),
    @NamedQuery(name = "Resultattemp.findByApprobationstatutlabel", query = "SELECT r FROM Resultattemp r WHERE r.approbationstatutlabel = :approbationstatutlabel"),
    @NamedQuery(name = "Resultattemp.findByAnalysestatut", query = "SELECT r FROM Resultattemp r WHERE r.analysestatut = :analysestatut"),
    @NamedQuery(name = "Resultattemp.findByAnalysestatutlabel", query = "SELECT r FROM Resultattemp r WHERE r.analysestatutlabel = :analysestatutlabel"),
    @NamedQuery(name = "Resultattemp.findByAnalyseapprobationstatut", query = "SELECT r FROM Resultattemp r WHERE r.analyseapprobationstatut = :analyseapprobationstatut"),
    @NamedQuery(name = "Resultattemp.findByAnalyseapprobationstatutlabel", query = "SELECT r FROM Resultattemp r WHERE r.analyseapprobationstatutlabel = :analyseapprobationstatutlabel"),
    @NamedQuery(name = "Resultattemp.findByDescription", query = "SELECT r FROM Resultattemp r WHERE r.description = :description"),
    @NamedQuery(name = "Resultattemp.findByIsvirtual", query = "SELECT r FROM Resultattemp r WHERE r.isvirtual = :isvirtual"),
    @NamedQuery(name = "Resultattemp.findByIsfinal", query = "SELECT r FROM Resultattemp r WHERE r.isfinal = :isfinal"),
    @NamedQuery(name = "Resultattemp.findByIsresultat", query = "SELECT r FROM Resultattemp r WHERE r.isresultat = :isresultat"),
    @NamedQuery(name = "Resultattemp.findByIsrequired", query = "SELECT r FROM Resultattemp r WHERE r.isrequired = :isrequired"),
    @NamedQuery(name = "Resultattemp.findByOccurence", query = "SELECT r FROM Resultattemp r WHERE r.occurence = :occurence"),
    @NamedQuery(name = "Resultattemp.findByCounteranalyse", query = "SELECT r FROM Resultattemp r WHERE r.counteranalyse = :counteranalyse"),
    @NamedQuery(name = "Resultattemp.findByCounteranalyseidlims", query = "SELECT r FROM Resultattemp r WHERE r.counteranalyseidlims = :counteranalyseidlims"),
    @NamedQuery(name = "Resultattemp.findByOfficialename", query = "SELECT r FROM Resultattemp r WHERE r.officialename = :officialename"),
    @NamedQuery(name = "Resultattemp.findByMaingroup", query = "SELECT r FROM Resultattemp r WHERE r.maingroup = :maingroup"),
    @NamedQuery(name = "Resultattemp.findBySubgroup", query = "SELECT r FROM Resultattemp r WHERE r.subgroup = :subgroup"),
    @NamedQuery(name = "Resultattemp.findByMeasuremaster", query = "SELECT r FROM Resultattemp r WHERE r.measuremaster = :measuremaster"),
    @NamedQuery(name = "Resultattemp.findByMeasuredetail", query = "SELECT r FROM Resultattemp r WHERE r.measuredetail = :measuredetail"),
    @NamedQuery(name = "Resultattemp.findByRepetition", query = "SELECT r FROM Resultattemp r WHERE r.repetition = :repetition"),
    @NamedQuery(name = "Resultattemp.findBySubrepetition", query = "SELECT r FROM Resultattemp r WHERE r.subrepetition = :subrepetition"),
    @NamedQuery(name = "Resultattemp.findByRawresultat", query = "SELECT r FROM Resultattemp r WHERE r.rawresultat = :rawresultat"),
    @NamedQuery(name = "Resultattemp.findByFormated", query = "SELECT r FROM Resultattemp r WHERE r.formated = :formated"),
    @NamedQuery(name = "Resultattemp.findByApporved", query = "SELECT r FROM Resultattemp r WHERE r.apporved = :apporved"),
    @NamedQuery(name = "Resultattemp.findByApporveddate", query = "SELECT r FROM Resultattemp r WHERE r.apporveddate = :apporveddate"),
    @NamedQuery(name = "Resultattemp.findByApporvedby", query = "SELECT r FROM Resultattemp r WHERE r.apporvedby = :apporvedby"),
    @NamedQuery(name = "Resultattemp.findByDateofentry", query = "SELECT r FROM Resultattemp r WHERE r.dateofentry = :dateofentry"),
    @NamedQuery(name = "Resultattemp.findByUtilisateur", query = "SELECT r FROM Resultattemp r WHERE r.utilisateur = :utilisateur"),
    @NamedQuery(name = "Resultattemp.findByUtilisateurname", query = "SELECT r FROM Resultattemp r WHERE r.utilisateurname = :utilisateurname"),
    @NamedQuery(name = "Resultattemp.findByHerited", query = "SELECT r FROM Resultattemp r WHERE r.herited = :herited"),
    @NamedQuery(name = "Resultattemp.findByMeasurename", query = "SELECT r FROM Resultattemp r WHERE r.measurename = :measurename"),
    @NamedQuery(name = "Resultattemp.findByAnalysename", query = "SELECT r FROM Resultattemp r WHERE r.analysename = :analysename"),
    @NamedQuery(name = "Resultattemp.findByMethodename", query = "SELECT r FROM Resultattemp r WHERE r.methodename = :methodename"),
    @NamedQuery(name = "Resultattemp.findByCopiedfrom", query = "SELECT r FROM Resultattemp r WHERE r.copiedfrom = :copiedfrom"),
    @NamedQuery(name = "Resultattemp.findByIsresultatinserted", query = "SELECT r FROM Resultattemp r WHERE r.isresultatinserted = :isresultatinserted"),
    @NamedQuery(name = "Resultattemp.findByLimsanalyseorigrec", query = "SELECT r FROM Resultattemp r WHERE r.limsanalyseorigrec = :limsanalyseorigrec")
    //,@NamedQuery(name = "Resultattemp.findByVersion", query = "SELECT r FROM Resultattemp r WHERE r.version = :version")
    })
public class Resultattemp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(nullable = false)
    private BigInteger resultattemp;
    private BigInteger measure;
    private BigInteger limsmeasureid;
    private BigInteger limsid;
    private BigInteger limsidserie;
    private BigInteger serie;
    @Size(max = 50)
    @Column(length = 50)
    private String limssampleid;
    private BigInteger analyse;
    private BigInteger modeleanalyse;
    private BigInteger limsidanalyse;
    @Size(max = 50)
    @Column(length = 50)
    private String limsidmethode;
    private BigInteger methode;
    private BigInteger methodedetail;
    private BigInteger specification;
    private BigInteger sample;
    private BigInteger casefile;
    private BigInteger batch;
    private BigInteger limsbatchid;
    @Size(max = 50)
    @Column(length = 50)
    private String limsfolderno;
    @Size(max = 50)
    @Column(length = 50)
    private String batchname;
    private Boolean statut;
    @Size(max = 50)
    @Column(length = 50)
    private String statutlabel;
    private Boolean approbationstatut;
    @Size(max = 50)
    @Column(length = 50)
    private String approbationstatutlabel;
    private Boolean analysestatut;
    @Size(max = 50)
    @Column(length = 50)
    private String analysestatutlabel;
    private Boolean analyseapprobationstatut;
    @Size(max = 50)
    @Column(length = 50)
    private String analyseapprobationstatutlabel;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    private Boolean isvirtual;
    private Boolean isfinal;
    private Boolean isresultat;
    private Boolean isrequired;
    private Short occurence;
    private Short counteranalyse;
    @Size(max = 50)
    @Column(length = 50)
    private String counteranalyseidlims;
    @Size(max = 50)
    @Column(length = 50)
    private String officialename;
    @Size(max = 50)
    @Column(length = 50)
    private String maingroup;
    @Size(max = 50)
    @Column(length = 50)
    private String subgroup;
    @Size(max = 50)
    @Column(length = 50)
    private String measuremaster;
    @Size(max = 50)
    @Column(length = 50)
    private String measuredetail;
    private Short repetition;
    private Short subrepetition;
    @Size(max = 50)
    @Column(length = 50)
    private String rawresultat;
    @Size(max = 50)
    @Column(length = 50)
    private String formated;
    private Boolean apporved;
    @Temporal(TemporalType.TIMESTAMP)
    private Date apporveddate;
    @Size(max = 50)
    @Column(length = 50)
    private String apporvedby;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateofentry;
    private BigInteger utilisateur;
    @Size(max = 50)
    @Column(length = 50)
    private String utilisateurname;
    private Boolean herited;
    @Size(max = 50)
    @Column(length = 50)
    private String measurename;
    @Size(max = 50)
    @Column(length = 50)
    private String analysename;
    @Size(max = 50)
    @Column(length = 50)
    private String methodename;
    @Size(max = 50)
    @Column(length = 50)
    private String copiedfrom;
    private Boolean isresultatinserted;
    private BigInteger limsanalyseorigrec;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    //private Date version;*/

    public Resultattemp() {
    }

    public Resultattemp(BigInteger resultattemp) {
        this.resultattemp = resultattemp;
    }

    /*public Resultattemp(Long result, Date version) {
        this.result = result;
        this.version = version;
    }*/

    public BigInteger getResultattemp() {
        return resultattemp;
    }

    public void setResultattemp(BigInteger resultattemp) {
        this.resultattemp= resultattemp;
    }

    public BigInteger getMeasure() {
        return measure;
    }

    public void setMeasure(BigInteger measure) {
        this.measure = measure;
    }

    public BigInteger getLimsmeasureid() {
        return limsmeasureid;
    }

    public void setLimsmeasureid(BigInteger limsmeasureid) {
        this.limsmeasureid = limsmeasureid;
    }

    public BigInteger getLimsid() {
        return limsid;
    }

    public void setLimsid(BigInteger limsid) {
        this.limsid = limsid;
    }

    public BigInteger getLimsidserie() {
        return limsidserie;
    }

    public void setLimsidserie(BigInteger limsidserie) {
        this.limsidserie = limsidserie;
    }

    public BigInteger getSerie() {
        return serie;
    }

    public void setSerie(BigInteger serie) {
        this.serie = serie;
    }

    public String getLimssampleid() {
        return limssampleid;
    }

    public void setLimssampleid(String limssampleid) {
        this.limssampleid = limssampleid;
    }

    public BigInteger getAnalyse() {
        return analyse;
    }

    public void setAnalyse(BigInteger analyse) {
        this.analyse = analyse;
    }

    public BigInteger getModeleanalyse() {
        return modeleanalyse;
    }

    public void setModeleanalyse(BigInteger modeleanalyse) {
        this.modeleanalyse = modeleanalyse;
    }

    public BigInteger getLimsidanalyse() {
        return limsidanalyse;
    }

    public void setLimsidanalyse(BigInteger limsidanalyse) {
        this.limsidanalyse = limsidanalyse;
    }

    public String getLimsidmethode() {
        return limsidmethode;
    }

    public void setLimsidmethode(String limsidmethode) {
        this.limsidmethode = limsidmethode;
    }

    public BigInteger getMethode() {
        return methode;
    }

    public void setMethode(BigInteger methode) {
        this.methode = methode;
    }

    public BigInteger getMethodedetail() {
        return methodedetail;
    }

    public void setMethodedetail(BigInteger methodedetail) {
        this.methodedetail = methodedetail;
    }

    public BigInteger getSpecification() {
        return specification;
    }

    public void setSpecification(BigInteger specification) {
        this.specification = specification;
    }

    public BigInteger getSample() {
        return sample;
    }

    public void setSample(BigInteger sample) {
        this.sample = sample;
    }

    public BigInteger getCasefile() {
        return casefile;
    }

    public void setCasefile(BigInteger casefile) {
        this.casefile = casefile;
    }

    public BigInteger getBatch() {
        return batch;
    }

    public void setBatch(BigInteger batch) {
        this.batch = batch;
    }

    public BigInteger getLimsbatchid() {
        return limsbatchid;
    }

    public void setLimsbatchid(BigInteger limsbatchid) {
        this.limsbatchid = limsbatchid;
    }

    public String getLimsfolderno() {
        return limsfolderno;
    }

    public void setLimsfolderno(String limsfolderno) {
        this.limsfolderno = limsfolderno;
    }

    public String getBatchname() {
        return batchname;
    }

    public void setBatchname(String batchname) {
        this.batchname = batchname;
    }

    public Boolean getStatut() {
        return statut;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }

    public String getStatutlabel() {
        return statutlabel;
    }

    public void setStatutlabel(String statutlabel) {
        this.statutlabel = statutlabel;
    }

    public Boolean getApprobationstatut() {
        return approbationstatut;
    }

    public void setApprobationstatut(Boolean approbationstatut) {
        this.approbationstatut = approbationstatut;
    }

    public String getApprobationstatutlabel() {
        return approbationstatutlabel;
    }

    public void setApprobationstatutlabel(String approbationstatutlabel) {
        this.approbationstatutlabel = approbationstatutlabel;
    }

    public Boolean getAnalysestatut() {
        return analysestatut;
    }

    public void setAnalysestatut(Boolean analysestatut) {
        this.analysestatut = analysestatut;
    }

    public String getAnalysestatutlabel() {
        return analysestatutlabel;
    }

    public void setAnalysestatutlabel(String analysestatutlabel) {
        this.analysestatutlabel = analysestatutlabel;
    }

    public Boolean getAnalyseapprobationstatut() {
        return analyseapprobationstatut;
    }

    public void setAnalyseapprobationstatut(Boolean analyseapprobationstatut) {
        this.analyseapprobationstatut = analyseapprobationstatut;
    }

    public String getAnalyseapprobationstatutlabel() {
        return analyseapprobationstatutlabel;
    }

    public void setAnalyseapprobationstatutlabel(String analyseapprobationstatutlabel) {
        this.analyseapprobationstatutlabel = analyseapprobationstatutlabel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsvirtual() {
        return isvirtual;
    }

    public void setIsvirtual(Boolean isvirtual) {
        this.isvirtual = isvirtual;
    }

    public Boolean getIsfinal() {
        return isfinal;
    }

    public void setIsfinal(Boolean isfinal) {
        this.isfinal = isfinal;
    }

    public Boolean getIsresultat() {
        return isresultat;
    }

    public void setIsresultat(Boolean isresultat) {
        this.isresultat = isresultat;
    }

    public Boolean getIsrequired() {
        return isrequired;
    }

    public void setIsrequired(Boolean isrequired) {
        this.isrequired = isrequired;
    }

    public Short getOccurence() {
        return occurence;
    }

    public void setOccurence(Short occurence) {
        this.occurence = occurence;
    }

    public Short getCounteranalyse() {
        return counteranalyse;
    }

    public void setCounteranalyse(Short counteranalyse) {
        this.counteranalyse = counteranalyse;
    }

    public String getCounteranalyseidlims() {
        return counteranalyseidlims;
    }

    public void setCounteranalyseidlims(String counteranalyseidlims) {
        this.counteranalyseidlims = counteranalyseidlims;
    }

    public String getOfficialename() {
        return officialename;
    }

    public void setOfficialename(String officialename) {
        this.officialename = officialename;
    }

    public String getMaingroup() {
        return maingroup;
    }

    public void setMaingroup(String maingroup) {
        this.maingroup = maingroup;
    }

    public String getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(String subgroup) {
        this.subgroup = subgroup;
    }

    public String getMeasuremaster() {
        return measuremaster;
    }

    public void setMeasuremaster(String measuremaster) {
        this.measuremaster = measuremaster;
    }

    public String getMeasuredetail() {
        return measuredetail;
    }

    public void setMeasuredetail(String measuredetail) {
        this.measuredetail = measuredetail;
    }

    public Short getRepetition() {
        return repetition;
    }

    public void setRepetition(Short repetition) {
        this.repetition = repetition;
    }

    public Short getSubrepetition() {
        return subrepetition;
    }

    public void setSubrepetition(Short subrepetition) {
        this.subrepetition = subrepetition;
    }

    public String getRawresultat() {
        return rawresultat;
    }

    public void setRawresultat(String rawresultat) {
        this.rawresultat = rawresultat;
    }

    public String getFormated() {
        return formated;
    }

    public void setFormated(String formated) {
        this.formated = formated;
    }

    public Boolean getApporved() {
        return apporved;
    }

    public void setApporved(Boolean apporved) {
        this.apporved = apporved;
    }

    public Date getApporveddate() {
        return apporveddate;
    }

    public void setApporveddate(Date apporveddate) {
        this.apporveddate = apporveddate;
    }

    public String getApporvedby() {
        return apporvedby;
    }

    public void setApporvedby(String apporvedby) {
        this.apporvedby = apporvedby;
    }

    public Date getDateofentry() {
        return dateofentry;
    }

    public void setDateofentry(Date dateofentry) {
        this.dateofentry = dateofentry;
    }

    public BigInteger getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(BigInteger utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getUtilisateurname() {
        return utilisateurname;
    }

    public void setUtilisateurname(String utilisateurname) {
        this.utilisateurname = utilisateurname;
    }

    public Boolean getHerited() {
        return herited;
    }

    public void setHerited(Boolean herited) {
        this.herited = herited;
    }

    public String getMeasurename() {
        return measurename;
    }

    public void setMeasurename(String measurename) {
        this.measurename = measurename;
    }

    public String getAnalysename() {
        return analysename;
    }

    public void setAnalysename(String analysename) {
        this.analysename = analysename;
    }

    public String getMethodename() {
        return methodename;
    }

    public void setMethodename(String methodename) {
        this.methodename = methodename;
    }

    public String getCopiedfrom() {
        return copiedfrom;
    }

    public void setCopiedfrom(String copiedfrom) {
        this.copiedfrom = copiedfrom;
    }

    public Boolean getIsresultatinserted() {
        return isresultatinserted;
    }

    public void setIsresultatinserted(Boolean isresultatinserted) {
        this.isresultatinserted = isresultatinserted;
    }

    public BigInteger getLimsanalyseorigrec() {
        return limsanalyseorigrec;
    }

    public void setLimsanalyseorigrec(BigInteger limsanalyseorigrec) {
        this.limsanalyseorigrec = limsanalyseorigrec;
    }

    /*public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resultattemp != null ? resultattemp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resultattemp)) {
            return false;
        }
        Resultattemp other = (Resultattemp) object;
        if ((this.resultattemp == null && other.resultattemp != null) || (this.resultattemp != null && !this.resultattemp.equals(other.resultattemp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Resultattemp[ result=" + resultattemp + " ]";
    }
    
}
