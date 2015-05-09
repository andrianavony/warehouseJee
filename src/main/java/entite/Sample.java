/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import java.math.BigInteger;
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
    @NamedQuery(name = "Sample.findAll", query = "SELECT s FROM Sample s"),
    @NamedQuery(name = "Sample.findBySample", query = "SELECT s FROM Sample s WHERE s.sample = :sample"),
    @NamedQuery(name = "Sample.findByIscurrent", query = "SELECT s FROM Sample s WHERE s.iscurrent = :iscurrent"),
    @NamedQuery(name = "Sample.findByBatchname", query = "SELECT s FROM Sample s WHERE s.batchname = :batchname"),
    @NamedQuery(name = "Sample.findByLimssampleid", query = "SELECT s FROM Sample s WHERE s.limssampleid = :limssampleid"),
    @NamedQuery(name = "Sample.findByCreationdate", query = "SELECT s FROM Sample s WHERE s.creationdate = :creationdate"),
    @NamedQuery(name = "Sample.findByStatutlabel", query = "SELECT s FROM Sample s WHERE s.statutlabel = :statutlabel"),
    @NamedQuery(name = "Sample.findByApprobationstatutlabel", query = "SELECT s FROM Sample s WHERE s.approbationstatutlabel = :approbationstatutlabel"),
    @NamedQuery(name = "Sample.findByApporved", query = "SELECT s FROM Sample s WHERE s.apporved = :apporved"),
    @NamedQuery(name = "Sample.findByApporvedby", query = "SELECT s FROM Sample s WHERE s.apporvedby = :apporvedby"),
    @NamedQuery(name = "Sample.findByApporveddate", query = "SELECT s FROM Sample s WHERE s.apporveddate = :apporveddate"),
    @NamedQuery(name = "Sample.findByLimsbatchid", query = "SELECT s FROM Sample s WHERE s.limsbatchid = :limsbatchid"),
    @NamedQuery(name = "Sample.findByLimsfolderno", query = "SELECT s FROM Sample s WHERE s.limsfolderno = :limsfolderno"),
    @NamedQuery(name = "Sample.findBySampletype", query = "SELECT s FROM Sample s WHERE s.sampletype = :sampletype"),
    @NamedQuery(name = "Sample.findByDescription", query = "SELECT s FROM Sample s WHERE s.description = :description"),
    @NamedQuery(name = "Sample.findByStatut", query = "SELECT s FROM Sample s WHERE s.statut = :statut"),
    @NamedQuery(name = "Sample.findByApprobationstatut", query = "SELECT s FROM Sample s WHERE s.approbationstatut = :approbationstatut"),
    @NamedQuery(name = "Sample.findByProdgroup", query = "SELECT s FROM Sample s WHERE s.prodgroup = :prodgroup")
    //,@NamedQuery(name = "Sample.findByVersion", query = "SELECT s FROM Sample s WHERE s.version = :version")
    })
public class Sample implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long sample;
    private Boolean iscurrent;
    @Size(max = 50)
    @Column(length = 50)
    private String batchname;
    @Size(max = 50)
    @Column(length = 50)
    private String limssampleid;
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
    private BigInteger limsbatchid;
    @Size(max = 50)
    @Column(length = 50)
    private String limsfolderno;
    private BigInteger sampletype;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    private Boolean statut;
    private Boolean approbationstatut;
    @Size(max = 50)
    @Column(length = 50)
    private String prodgroup;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    //private Date version;*/
    @OneToMany(mappedBy = "copiedfromidsample")
    private List<Analyse> analysesCopiedfromidsample;
    @OneToMany(mappedBy = "sample")
    private List<Analyse> analyses;
    @OneToMany(mappedBy = "copiedfromidsample")
    private List<Resultat> resultatsCopiedfromidsample;
    @OneToMany(mappedBy = "sample")
    private List<Resultat> resultats;
    @JoinColumn(name = "STAGE", referencedColumnName = "STAGE")
    @ManyToOne
    private Stage stage;
    @JoinColumn(name = "ARTICLE", referencedColumnName = "ARTICLE")
    @ManyToOne
    private Article article;
    @JoinColumn(name = "BATCH", referencedColumnName = "BATCH")
    @ManyToOne
    private Batch batch;
    @JoinColumn(name = "CASEFILE", referencedColumnName = "CASEFILE")
    @ManyToOne
    private Casefile casefile;
    @JoinColumn(name = "SPECIE", referencedColumnName = "SPECIE")
    @ManyToOne
    private Specie specie;

    public Sample() {
    }

    public Sample(Long sample) {
        this.sample = sample;
    }

    /*public Sample(Long sample, Date version) {
        this.sample = sample;
        this.version = version;
    }*/

    public Long getSample() {
        return sample;
    }

    public void setSample(Long sample) {
        this.sample = sample;
    }

    public Boolean getIscurrent() {
        return iscurrent;
    }

    public void setIscurrent(Boolean iscurrent) {
        this.iscurrent = iscurrent;
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

    public BigInteger getSampletype() {
        return sampletype;
    }

    public void setSampletype(BigInteger sampletype) {
        this.sampletype = sampletype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getProdgroup() {
        return prodgroup;
    }

    public void setProdgroup(String prodgroup) {
        this.prodgroup = prodgroup;
    }

    /*public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }*/

    @XmlTransient
    public List<Analyse> getAnalysesCopiedfromidsample() {
        return analysesCopiedfromidsample;
    }

    public void setAnalysesCopiedfromidsample(List<Analyse> analysesCopiedfromidsample) {
        this.analysesCopiedfromidsample = analysesCopiedfromidsample;
    }

    @XmlTransient
    public List<Analyse> getAnalyses() {
        return analyses;
    }

    public void setAnalyses(List<Analyse> analyses) {
        this.analyses = analyses;
    }

    @XmlTransient
    public List<Resultat> getResultatsCopiedfromidsample() {
        return resultatsCopiedfromidsample;
    }

    public void setResultatsCopiedfromidsample(List<Resultat> resultatsCopiedfromidsample) {
        this.resultatsCopiedfromidsample = resultatsCopiedfromidsample;
    }

    @XmlTransient
    public List<Resultat> getResultats() {
        return resultats;
    }

    public void setResultats(List<Resultat> resultats) {
        this.resultats = resultats;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Casefile getCasefile() {
        return casefile;
    }

    public void setCasefile(Casefile casefile) {
        this.casefile = casefile;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sample != null ? sample.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sample)) {
            return false;
        }
        Sample other = (Sample) object;
        if ((this.sample == null && other.sample != null) || (this.sample != null && !this.sample.equals(other.sample))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Sample[ sample=" + sample + " ]";
    }
    
}
