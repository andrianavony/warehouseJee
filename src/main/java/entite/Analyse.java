/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author S.ANDRIANAVONY
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Analyse.findAll", query = "SELECT a FROM Analyse a"),
    @NamedQuery(name = "Analyse.findByHerited", query = "SELECT a FROM Analyse a WHERE a.herited = :herited"),
    @NamedQuery(name = "Analyse.findByBatchname", query = "SELECT a FROM Analyse a WHERE a.batchname = :batchname"),
    @NamedQuery(name = "Analyse.findByLimssampleid", query = "SELECT a FROM Analyse a WHERE a.limssampleid = :limssampleid"),
    @NamedQuery(name = "Analyse.findByLimsidanalyse", query = "SELECT a FROM Analyse a WHERE a.limsidanalyse = :limsidanalyse"),
    @NamedQuery(name = "Analyse.findByAnalysename", query = "SELECT a FROM Analyse a WHERE a.analysename = :analysename"),
    @NamedQuery(name = "Analyse.findByMethodename", query = "SELECT a FROM Analyse a WHERE a.methodename = :methodename"),
    @NamedQuery(name = "Analyse.findByLimsidserie", query = "SELECT a FROM Analyse a WHERE a.limsidserie = :limsidserie"),
    @NamedQuery(name = "Analyse.findBySerie", query = "SELECT a FROM Analyse a WHERE a.serie = :serie"),
    @NamedQuery(name = "Analyse.findByCreationdate", query = "SELECT a FROM Analyse a WHERE a.creationdate = :creationdate"),
    @NamedQuery(name = "Analyse.findByStatutlabel", query = "SELECT a FROM Analyse a WHERE a.statutlabel = :statutlabel"),
    @NamedQuery(name = "Analyse.findByApprobationstatutlabel", query = "SELECT a FROM Analyse a WHERE a.approbationstatutlabel = :approbationstatutlabel"),
    @NamedQuery(name = "Analyse.findByApporved", query = "SELECT a FROM Analyse a WHERE a.apporved = :apporved"),
    @NamedQuery(name = "Analyse.findByApporvedby", query = "SELECT a FROM Analyse a WHERE a.apporvedby = :apporvedby"),
    @NamedQuery(name = "Analyse.findByApporveddate", query = "SELECT a FROM Analyse a WHERE a.apporveddate = :apporveddate"),
    @NamedQuery(name = "Analyse.findByLimscounteranalysesampelid", query = "SELECT a FROM Analyse a WHERE a.limscounteranalysesampelid = :limscounteranalysesampelid"),
    @NamedQuery(name = "Analyse.findByCounteranalyse", query = "SELECT a FROM Analyse a WHERE a.counteranalyse = :counteranalyse"),
    @NamedQuery(name = "Analyse.findByLimsid", query = "SELECT a FROM Analyse a WHERE a.limsid = :limsid"),
    @NamedQuery(name = "Analyse.findByLimsbatchid", query = "SELECT a FROM Analyse a WHERE a.limsbatchid = :limsbatchid"),
    @NamedQuery(name = "Analyse.findByLimsfolderno", query = "SELECT a FROM Analyse a WHERE a.limsfolderno = :limsfolderno"),
    @NamedQuery(name = "Analyse.findByMethodedetail", query = "SELECT a FROM Analyse a WHERE a.methodedetail = :methodedetail"),
    @NamedQuery(name = "Analyse.findByAnalyse", query = "SELECT a FROM Analyse a WHERE a.analyse = :analyse"),
    @NamedQuery(name = "Analyse.findByStatut", query = "SELECT a FROM Analyse a WHERE a.statut = :statut"),
    @NamedQuery(name = "Analyse.findByApprobationstatut", query = "SELECT a FROM Analyse a WHERE a.approbationstatut = :approbationstatut"),
    @NamedQuery(name = "Analyse.findByDescription", query = "SELECT a FROM Analyse a WHERE a.description = :description"),
    @NamedQuery(name = "Analyse.findByIsvirtual", query = "SELECT a FROM Analyse a WHERE a.isvirtual = :isvirtual"),
    @NamedQuery(name = "Analyse.findByIsfinal", query = "SELECT a FROM Analyse a WHERE a.isfinal = :isfinal"),
    @NamedQuery(name = "Analyse.findByOccurence", query = "SELECT a FROM Analyse a WHERE a.occurence = :occurence"),
    @NamedQuery(name = "Analyse.findByOfficialename", query = "SELECT a FROM Analyse a WHERE a.officialename = :officialename"),
    @NamedQuery(name = "Analyse.findByConsidered", query = "SELECT a FROM Analyse a WHERE a.considered = :considered"),
    @NamedQuery(name = "Analyse.findByProdgroup", query = "SELECT a FROM Analyse a WHERE a.prodgroup = :prodgroup"),
    @NamedQuery(name = "Analyse.findByCopiedfromlimsbathcname", query = "SELECT a FROM Analyse a WHERE a.copiedfromlimsbathcname = :copiedfromlimsbathcname"),
    @NamedQuery(name = "Analyse.findByIsresultatinserted", query = "SELECT a FROM Analyse a WHERE a.isresultatinserted = :isresultatinserted"),
    @NamedQuery(name = "Analyse.findByLimsanalyseorigrec", query = "SELECT a FROM Analyse a WHERE a.limsanalyseorigrec = :limsanalyseorigrec"),
    @NamedQuery(name = "Analyse.findByCopystatut", query = "SELECT a FROM Analyse a WHERE a.copystatut = :copystatut"),
    @NamedQuery(name = "Analyse.findByCopytype", query = "SELECT a FROM Analyse a WHERE a.copytype = :copytype")
    //,@NamedQuery(name = "Analyse.findByVersion", query = "SELECT a FROM Analyse a WHERE a.version = :version")
    })
public class Analyse implements Serializable {
    private static final long serialVersionUID = 1L;
    private Boolean herited;
    @Size(max = 50)
    @Column(length = 50)
    private String batchname;
    @Size(max = 50)
    @Column(length = 50)
    private String limssampleid;
    private BigInteger limsidanalyse;
    @Size(max = 50)
    @Column(length = 50)
    private String analysename;
    @Size(max = 50)
    @Column(length = 50)
    private String methodename;
    private BigInteger limsidserie;
    private BigInteger serie;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationdate;
    @Size(max = 50)
    @Column(length = 50)
    private String statutlabel;
    @Size(max = 50)
    @Column(length = 50)
    private String approbationstatutlabel;
    private Short apporved;
    @Size(max = 50)
    @Column(length = 50)
    private String apporvedby;
    @Temporal(TemporalType.TIMESTAMP)
    private Date apporveddate;
    @Size(max = 50)
    @Column(length = 50)
    private String limscounteranalysesampelid;
    private Short counteranalyse;
    private BigInteger limsid;
    private BigInteger limsbatchid;
    @Size(max = 50)
    @Column(length = 50)
    private String limsfolderno;
    private BigInteger methodedetail;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long analyse;
    private Boolean statut;
    private Boolean approbationstatut;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    private Boolean isvirtual;
    private Boolean isfinal;
    private Short occurence;
    @Size(max = 50)
    @Column(length = 50)
    private String officialename;
    private Boolean considered;
    @Size(max = 50)
    @Column(length = 50)
    private String prodgroup;
    @Size(max = 50)
    @Column(length = 50)
    private String copiedfromlimsbathcname;
    private Boolean isresultatinserted;
    private BigInteger limsanalyseorigrec;
    private Integer copystatut;
    @Size(max = 50)
    @Column(length = 50)
    private String copytype;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Version
    private Timestamp version;*/
    @JoinColumn(name = "BATCH", referencedColumnName = "BATCH")
    @ManyToOne
    private Batch batch;
    @JoinColumn(name = "METHODE", referencedColumnName = "METHODE")
    @ManyToOne
    private Methode methode;
    @OneToMany(mappedBy = "motheranalyse")
    private List<Analyse> analyses;
    @JoinColumn(name = "MOTHERANALYSE", referencedColumnName = "ANALYSE")
    @ManyToOne
    private Analyse motheranalyse;
    @JoinColumn(name = "ARTICLE", referencedColumnName = "ARTICLE")
    @ManyToOne
    private Article article;
    @JoinColumn(name = "CASEFILE", referencedColumnName = "CASEFILE")
    @ManyToOne
    private Casefile casefile;
    @OneToMany(mappedBy = "copiedfromidanalyse")
    private List<Analyse> analysesCopiedfromidanalyse;
    @JoinColumn(name = "COPIEDFROMIDANALYSE", referencedColumnName = "ANALYSE")
    @ManyToOne
    private Analyse copiedfromidanalyse;
    @JoinColumn(name = "COPIEDFROMIDBATCH", referencedColumnName = "BATCH")
    @ManyToOne
    private Batch copiedfromidbatch;
    @JoinColumn(name = "COPIEDFROMIDSAMPLE", referencedColumnName = "SAMPLE")
    @ManyToOne
    private Sample copiedfromidsample;
    @JoinColumn(name = "MODELEANALYSE", referencedColumnName = "MODELEANALYSE")
    @ManyToOne
    private Modeleanalyse modeleanalyse;
    @JoinColumn(name = "SAMPLE", referencedColumnName = "SAMPLE")
    @ManyToOne
    private Sample sample;
    @JoinColumn(name = "STAGE", referencedColumnName = "STAGE")
    @ManyToOne
    private Stage stage;
    @JoinColumn(name = "SPECIFICATION", referencedColumnName = "SPECIFICATION")
    @ManyToOne
    private Specification specification;
    @JoinColumn(name = "SPECIE", referencedColumnName = "SPECIE")
    @ManyToOne
    private Specie specie;
    @OneToMany(mappedBy = "analyse")
    private List<Resultat> resultats;

    public Analyse() {
    }

    public Analyse(Long analyse) {
        this.analyse = analyse;
    }

    /*public Analyse(Long analyse, Timestamp version) {
        this.analyse = analyse;
        this.version = version;
    }*/

    public Boolean getHerited() {
        return herited;
    }

    public void setHerited(Boolean herited) {
        this.herited = herited;
    }

    public String getBatchname() {
        return batchname;
    }

    public void setBatchname(String batchname) {
        this.batchname = batchname;
    }

    public String getLimssampleid() {
        return limssampleid;
    }

    public void setLimssampleid(String limssampleid) {
        this.limssampleid = limssampleid;
    }

    public BigInteger getLimsidanalyse() {
        return limsidanalyse;
    }

    public void setLimsidanalyse(BigInteger limsidanalyse) {
        this.limsidanalyse = limsidanalyse;
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

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public String getStatutlabel() {
        return statutlabel;
    }

    public void setStatutlabel(String statutlabel) {
        this.statutlabel = statutlabel;
    }

    public String getApprobationstatutlabel() {
        return approbationstatutlabel;
    }

    public void setApprobationstatutlabel(String approbationstatutlabel) {
        this.approbationstatutlabel = approbationstatutlabel;
    }

    public Short getApporved() {
        return apporved;
    }

    public void setApporved(Short apporved) {
        this.apporved = apporved;
    }

    public String getApporvedby() {
        return apporvedby;
    }

    public void setApporvedby(String apporvedby) {
        this.apporvedby = apporvedby;
    }

    public Date getApporveddate() {
        return apporveddate;
    }

    public void setApporveddate(Date apporveddate) {
        this.apporveddate = apporveddate;
    }

    public String getLimscounteranalysesampelid() {
        return limscounteranalysesampelid;
    }

    public void setLimscounteranalysesampelid(String limscounteranalysesampelid) {
        this.limscounteranalysesampelid = limscounteranalysesampelid;
    }

    public Short getCounteranalyse() {
        return counteranalyse;
    }

    public void setCounteranalyse(Short counteranalyse) {
        this.counteranalyse = counteranalyse;
    }

    public BigInteger getLimsid() {
        return limsid;
    }

    public void setLimsid(BigInteger limsid) {
        this.limsid = limsid;
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

    public BigInteger getMethodedetail() {
        return methodedetail;
    }

    public void setMethodedetail(BigInteger methodedetail) {
        this.methodedetail = methodedetail;
    }

    public Long getAnalyse() {
        return analyse;
    }

    public void setAnalyse(Long analyse) {
        this.analyse = analyse;
    }

    public Boolean getStatut() {
        return statut;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }

    public Boolean getApprobationstatut() {
        return approbationstatut;
    }

    public void setApprobationstatut(Boolean approbationstatut) {
        this.approbationstatut = approbationstatut;
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

    public Short getOccurence() {
        return occurence;
    }

    public void setOccurence(Short occurence) {
        this.occurence = occurence;
    }

    public String getOfficialename() {
        return officialename;
    }

    public void setOfficialename(String officialename) {
        this.officialename = officialename;
    }

    public Boolean getConsidered() {
        return considered;
    }

    public void setConsidered(Boolean considered) {
        this.considered = considered;
    }

    public String getProdgroup() {
        return prodgroup;
    }

    public void setProdgroup(String prodgroup) {
        this.prodgroup = prodgroup;
    }

    public String getCopiedfromlimsbathcname() {
        return copiedfromlimsbathcname;
    }

    public void setCopiedfromlimsbathcname(String copiedfromlimsbathcname) {
        this.copiedfromlimsbathcname = copiedfromlimsbathcname;
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

    public Integer getCopystatut() {
        return copystatut;
    }

    public void setCopystatut(Integer copystatut) {
        this.copystatut = copystatut;
    }

    public String getCopytype() {
        return copytype;
    }

    public void setCopytype(String copytype) {
        this.copytype = copytype;
    }

    /*public Date getVersion() {
        return version;
    }

    public void setVersion(Timestamp version) {
        this.version = version;
    }*/

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Methode getMethode() {
        return methode;
    }

    public void setMethode(Methode methode) {
        this.methode = methode;
    }

    @XmlTransient
    public List<Analyse> getAnalyses() {
        return analyses;
    }

    public void setAnalyses(List<Analyse> analyses) {
        this.analyses = analyses;
    }

    public Analyse getMotheranalyse() {
        return motheranalyse;
    }

    public void setMotheranalyse(Analyse motheranalyse) {
        this.motheranalyse = motheranalyse;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Casefile getCasefile() {
        return casefile;
    }

    public void setCasefile(Casefile casefile) {
        this.casefile = casefile;
    }

    @XmlTransient
    public List<Analyse> getAnalysesCopiedfromidanalyse() {
        return analysesCopiedfromidanalyse;
    }

    public void setAnalysesCopiedfromidanalyse(List<Analyse> analysesCopiedfromidanalyse) {
        this.analysesCopiedfromidanalyse = analysesCopiedfromidanalyse;
    }

    public Analyse getCopiedfromidanalyse() {
        return copiedfromidanalyse;
    }

    public void setCopiedfromidanalyse(Analyse copiedfromidanalyse) {
        this.copiedfromidanalyse = copiedfromidanalyse;
    }

    public Batch getCopiedfromidbatch() {
        return copiedfromidbatch;
    }

    public void setCopiedfromidbatch(Batch copiedfromidbatch) {
        this.copiedfromidbatch = copiedfromidbatch;
    }

    public Sample getCopiedfromidsample() {
        return copiedfromidsample;
    }

    public void setCopiedfromidsample(Sample copiedfromidsample) {
        this.copiedfromidsample = copiedfromidsample;
    }

    public Modeleanalyse getModeleanalyse() {
        return modeleanalyse;
    }

    public void setModeleanalyse(Modeleanalyse modeleanalyse) {
        this.modeleanalyse = modeleanalyse;
    }

    public Sample getSample() {
        return sample;
    }

    public void setSample(Sample sample) {
        this.sample = sample;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    @XmlTransient
    public List<Resultat> getResultats() {
        return resultats;
    }

    public void setResultats(List<Resultat> resultats) {
        this.resultats = resultats;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (analyse != null ? analyse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Analyse)) {
            return false;
        }
        Analyse other = (Analyse) object;
        if ((this.analyse == null && other.analyse != null) || (this.analyse != null && !this.analyse.equals(other.analyse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Analyse[ analyse=" + analyse + " ]";
    }
    
}
