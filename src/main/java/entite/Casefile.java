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
    @NamedQuery(name = "Casefile.findAll", query = "SELECT c FROM Casefile c"),
    @NamedQuery(name = "Casefile.findByCasefile", query = "SELECT c FROM Casefile c WHERE c.casefile = :casefile"),
    @NamedQuery(name = "Casefile.findByIscurrent", query = "SELECT c FROM Casefile c WHERE c.iscurrent = :iscurrent"),
    @NamedQuery(name = "Casefile.findByBatchname", query = "SELECT c FROM Casefile c WHERE c.batchname = :batchname"),
    @NamedQuery(name = "Casefile.findByCreationdate", query = "SELECT c FROM Casefile c WHERE c.creationdate = :creationdate"),
    @NamedQuery(name = "Casefile.findByStatutlabel", query = "SELECT c FROM Casefile c WHERE c.statutlabel = :statutlabel"),
    @NamedQuery(name = "Casefile.findByApprobationstatutlabel", query = "SELECT c FROM Casefile c WHERE c.approbationstatutlabel = :approbationstatutlabel"),
    @NamedQuery(name = "Casefile.findByApporved", query = "SELECT c FROM Casefile c WHERE c.apporved = :apporved"),
    @NamedQuery(name = "Casefile.findByApporvedby", query = "SELECT c FROM Casefile c WHERE c.apporvedby = :apporvedby"),
    @NamedQuery(name = "Casefile.findByApporveddate", query = "SELECT c FROM Casefile c WHERE c.apporveddate = :apporveddate"),
    @NamedQuery(name = "Casefile.findByProdgroup", query = "SELECT c FROM Casefile c WHERE c.prodgroup = :prodgroup"),
    @NamedQuery(name = "Casefile.findByCasefiletype", query = "SELECT c FROM Casefile c WHERE c.casefiletype = :casefiletype"),
    @NamedQuery(name = "Casefile.findByDescription", query = "SELECT c FROM Casefile c WHERE c.description = :description"),
    @NamedQuery(name = "Casefile.findByLimsfolderno", query = "SELECT c FROM Casefile c WHERE c.limsfolderno = :limsfolderno"),
    @NamedQuery(name = "Casefile.findByLimsbatchid", query = "SELECT c FROM Casefile c WHERE c.limsbatchid = :limsbatchid"),
    @NamedQuery(name = "Casefile.findByStatut", query = "SELECT c FROM Casefile c WHERE c.statut = :statut"),
    @NamedQuery(name = "Casefile.findByApprobationstatut", query = "SELECT c FROM Casefile c WHERE c.approbationstatut = :approbationstatut"),
    @NamedQuery(name = "Casefile.findByNumdemandelims", query = "SELECT c FROM Casefile c WHERE c.numdemandelims = :numdemandelims")
  //,@NamedQuery(name = "Article.findByVersion", query = "SELECT a FROM Article a WHERE a.version = :version")
})
public class Casefile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long casefile;
    private Boolean iscurrent;
    @Size(max = 50)
    @Column(length = 50)
    private String batchname;
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
    private String prodgroup;
    @Size(max = 50)
    @Column(length = 50)
    private String casefiletype;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    @Size(max = 50)
    @Column(length = 50)
    private String limsfolderno;
    private BigInteger limsbatchid;
    private Boolean statut;
    private Boolean approbationstatut;
    private Integer numdemandelims;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Version
    private Timestamp version;*/
    @OneToMany(mappedBy = "casefile")
    private List<Analyse> analyses;
    @JoinColumn(name = "STAGE", referencedColumnName = "STAGE")
    @ManyToOne
    private Stage stage;
    @JoinColumn(name = "ARTICLE", referencedColumnName = "ARTICLE")
    @ManyToOne
    private Article article;
    @JoinColumn(name = "SPECIE", referencedColumnName = "SPECIE")
    @ManyToOne
    private Specie specie;
    @JoinColumn(name = "BATCH", referencedColumnName = "BATCH")
    @ManyToOne
    private Batch batch;
    @OneToMany(mappedBy = "casefile")
    private List<Resultat> resultats;
    @OneToMany(mappedBy = "casefile")
    private List<Sample> samples;

    public Casefile() {
    }

    public Casefile(Long casefile) {
        this.casefile = casefile;
    }

    /*public Casefile(Long casefile, Timestamp version) {
        this.casefile = casefile;
        this.version = version;
    }*/

    public Long getCasefile() {
        return casefile;
    }

    public void setCasefile(Long casefile) {
        this.casefile = casefile;
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

    public String getProdgroup() {
        return prodgroup;
    }

    public void setProdgroup(String prodgroup) {
        this.prodgroup = prodgroup;
    }

    public String getCasefiletype() {
        return casefiletype;
    }

    public void setCasefiletype(String casefiletype) {
        this.casefiletype = casefiletype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLimsfolderno() {
        return limsfolderno;
    }

    public void setLimsfolderno(String limsfolderno) {
        this.limsfolderno = limsfolderno;
    }

    public BigInteger getLimsbatchid() {
        return limsbatchid;
    }

    public void setLimsbatchid(BigInteger limsbatchid) {
        this.limsbatchid = limsbatchid;
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

    public Integer getNumdemandelims() {
        return numdemandelims;
    }

    public void setNumdemandelims(Integer numdemandelims) {
        this.numdemandelims = numdemandelims;
    }

    /*public Date getVersion() {
        return version;
    }

    public void setVersion(Timestamp version) {
        this.version = version;
    }*/

    @XmlTransient
    public List<Analyse> getAnalyses() {
        return analyses;
    }

    public void setAnalyses(List<Analyse> analyses) {
        this.analyses = analyses;
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

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    @XmlTransient
    public List<Resultat> getResultats() {
        return resultats;
    }

    public void setResultats(List<Resultat> resultats) {
        this.resultats = resultats;
    }

    @XmlTransient
    public List<Sample> getSamples() {
        return samples;
    }

    public void setSamples(List<Sample> samples) {
        this.samples = samples;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (casefile != null ? casefile.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Casefile)) {
            return false;
        }
        Casefile other = (Casefile) object;
        if ((this.casefile == null && other.casefile != null) || (this.casefile != null && !this.casefile.equals(other.casefile))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Casefile[ casefile=" + casefile + " ]";
    }
    
}
