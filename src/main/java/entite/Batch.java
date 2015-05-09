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
import javax.persistence.JoinColumns;
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
    @NamedQuery(name = "Batch.findAll", query = "SELECT b FROM Batch b"),
    @NamedQuery(name = "Batch.findByBatch", query = "SELECT b FROM Batch b WHERE b.batch = :batch"),
    @NamedQuery(name = "Batch.findByBatchname", query = "SELECT b FROM Batch b WHERE b.batchname = :batchname"),
    @NamedQuery(name = "Batch.findByDescription", query = "SELECT b FROM Batch b WHERE b.description = :description"),
    @NamedQuery(name = "Batch.findByProdgroup", query = "SELECT b FROM Batch b WHERE b.prodgroup = :prodgroup"),
    @NamedQuery(name = "Batch.findByPea", query = "SELECT b FROM Batch b WHERE b.pea = :pea"),
    @NamedQuery(name = "Batch.findByOrigin1", query = "SELECT b FROM Batch b WHERE b.origin1 = :origin1"),
    @NamedQuery(name = "Batch.findByOrigin2", query = "SELECT b FROM Batch b WHERE b.origin2 = :origin2"),
    @NamedQuery(name = "Batch.findByDiagram", query = "SELECT b FROM Batch b WHERE b.diagram = :diagram"),
    @NamedQuery(name = "Batch.findByCaliber", query = "SELECT b FROM Batch b WHERE b.caliber = :caliber"),
    @NamedQuery(name = "Batch.findByQuantity", query = "SELECT b FROM Batch b WHERE b.quantity = :quantity"),
    @NamedQuery(name = "Batch.findByUnits", query = "SELECT b FROM Batch b WHERE b.units = :units"),
    @NamedQuery(name = "Batch.findByLimsbatchid", query = "SELECT b FROM Batch b WHERE b.limsbatchid = :limsbatchid"),
    @NamedQuery(name = "Batch.findByContract", query = "SELECT b FROM Batch b WHERE b.contract = :contract"),
    @NamedQuery(name = "Batch.findByProducername", query = "SELECT b FROM Batch b WHERE b.producername = :producername"),
    @NamedQuery(name = "Batch.findByLimsfolderno", query = "SELECT b FROM Batch b WHERE b.limsfolderno = :limsfolderno"),
    @NamedQuery(name = "Batch.findByStatutlabel", query = "SELECT b FROM Batch b WHERE b.statutlabel = :statutlabel"),
    @NamedQuery(name = "Batch.findByApprobationstatutlabel", query = "SELECT b FROM Batch b WHERE b.approbationstatutlabel = :approbationstatutlabel"),
    @NamedQuery(name = "Batch.findByApporved", query = "SELECT b FROM Batch b WHERE b.apporved = :apporved"),
    @NamedQuery(name = "Batch.findByApporvedby", query = "SELECT b FROM Batch b WHERE b.apporvedby = :apporvedby"),
    @NamedQuery(name = "Batch.findByApporveddate", query = "SELECT b FROM Batch b WHERE b.apporveddate = :apporveddate"),
    @NamedQuery(name = "Batch.findByStatut", query = "SELECT b FROM Batch b WHERE b.statut = :statut"),
    @NamedQuery(name = "Batch.findByApprobationstatut", query = "SELECT b FROM Batch b WHERE b.approbationstatut = :approbationstatut")
  //,@NamedQuery(name = "Article.findByVersion", query = "SELECT a FROM Article a WHERE a.version = :version")
    ,@NamedQuery(name = "Batch.findByArticleBatchname", query = "SELECT b "
            + " FROM Batch b "
            + " WHERE b.batchname = :batchname "
            + " and b.article = :article "),
    @NamedQuery(name = "Batch.findByArticleBatchnameCompanyname", query = "SELECT b "
            + " FROM Batch b "
            + " WHERE b.batchname = :batchname "
            + " and b.article.article= :article "
            + " and b.company.company = :company"
    )
    ,@NamedQuery(name = "Batch.findByArticleBatchnameCompanynameWo", query = "SELECT b "
            + " FROM Batch b "
            + " WHERE b.batchname = :batchname "
            + " and b.article.article= :article "
            + " and b.wo = :wo "
            + " and b.company.company = :company"
            
    )
})
public class Batch implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(nullable = false)
    private BigInteger batch;
    @Size(max = 50)
    @Column(length = 50)
    private String batchname;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    @Size(max = 50)
    @Column(length = 50)
    private String prodgroup;
    @Size(max = 50)
    @Column(length = 50)
    private String pea;
    @Size(max = 50)
    @Column(length = 50)
    private String origin1;
    @Size(max = 50)
    @Column(length = 50)
    private String origin2;
    @Size(max = 50)
    @Column(length = 50)
    private String diagram;
    @Size(max = 50)
    @Column(length = 50)
    private String caliber;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 22)
    private Double quantity;
    @Size(max = 50)
    @Column(length = 50)
    private String units;
    private BigInteger limsbatchid;
    @Size(max = 50)
    @Column(length = 50)
    private String contract;
    @Size(max = 50)
    @Column(length = 50)
    private String producername;
    @Size(max = 50)
    @Column(length = 50)
    private String limsfolderno;
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
    private Boolean statut;
    private Boolean approbationstatut;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Version
    private Timestamp version;*/
    @OneToMany(mappedBy = "batch")
    private List<Analyse> analyses;
    @OneToMany(mappedBy = "copiedfromidbatch")
    private List<Analyse> analysesCopiedfromidbatch;
    @OneToMany(mappedBy = "batchname")
    private List<Trace> traces;
    @OneToMany(mappedBy = "batch")
    private List<Casefile> casefiles;
    @JoinColumn(name = "TREATEMENT", referencedColumnName = "TREATEMENT")
    @ManyToOne
    private Treatement treatement;
    @JoinColumn(name = "ARTICLE", referencedColumnName = "ARTICLE")
    @ManyToOne
    private Article article;
    @JoinColumn(name = "COMPANY", referencedColumnName = "COMPANY", insertable=false, updatable=false)
    @ManyToOne
    private Company company;
    @JoinColumn(name = "GENERATION", referencedColumnName = "GENERATION")
    @ManyToOne
    private Generation generation;
    @JoinColumn(name = "SPECIE", referencedColumnName = "SPECIE", insertable=false, updatable=false)
    @ManyToOne
    private Specie specie;
    @JoinColumn(name = "STAGE", referencedColumnName = "STAGE")
    @ManyToOne
    private Stage stage;
    @JoinColumn(name = "TRACE", referencedColumnName = "TRACE")
    @ManyToOne
    private Trace trace;
    @JoinColumns({    
    	@JoinColumn(name = "VARIETY", referencedColumnName = "VARIETY", nullable=true),
    	@JoinColumn(name = "SPECIE", referencedColumnName = "SPECIE", nullable=true)
   
    })@ManyToOne
    private Variety variety;
    @JoinColumns({    
    	@JoinColumn(name = "WO", referencedColumnName = "WO"),
    	@JoinColumn(name = "COMPANY", referencedColumnName = "COMPANY")
    })
    @ManyToOne
    private Wo wo;
    @OneToMany(mappedBy = "copiedfromidbatch")
    private List<Resultat> resultats;
    @OneToMany(mappedBy = "batch")
    private List<Sample> samples;

    public Batch() {
    }

    public Batch(BigInteger batch) {
        this.batch = batch;
    }

    /*public Batch(BigInteger batch, Timestamp version) {
        this.batch = batch;
        this.version = version;
    }*/

    public BigInteger getBatch() {
        return batch;
    }

    public void setBatch(BigInteger batch) {
        this.batch = batch;
    }

    public String getBatchname() {
        return batchname;
    }

    public void setBatchname(String batchname) {
        this.batchname = batchname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProdgroup() {
        return prodgroup;
    }

    public void setProdgroup(String prodgroup) {
        this.prodgroup = prodgroup;
    }

    public String getPea() {
        return pea;
    }

    public void setPea(String pea) {
        this.pea = pea;
    }

    public String getOrigin1() {
        return origin1;
    }

    public void setOrigin1(String origin1) {
        this.origin1 = origin1;
    }

    public String getOrigin2() {
        return origin2;
    }

    public void setOrigin2(String origin2) {
        this.origin2 = origin2;
    }

    public String getDiagram() {
        return diagram;
    }

    public void setDiagram(String diagram) {
        this.diagram = diagram;
    }

    public String getCaliber() {
        return caliber;
    }

    public void setCaliber(String caliber) {
        this.caliber = caliber;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnits() {
        return units;
    }

    public void setUnite(String units) {
        this.units = units;
    }

    public BigInteger getLimsbatchid() {
        return limsbatchid;
    }

    public void setLimsbatchid(BigInteger limsbatchid) {
        this.limsbatchid = limsbatchid;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getProducername() {
        return producername;
    }

    public void setProducername(String producername) {
        this.producername = producername;
    }

    public String getLimsfolderno() {
        return limsfolderno;
    }

    public void setLimsfolderno(String limsfolderno) {
        this.limsfolderno = limsfolderno;
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

    @XmlTransient
    public List<Analyse> getAnalysesCopiedfromidbatch() {
        return analysesCopiedfromidbatch;
    }

    public void setAnalysesCopiedfromidbatch(List<Analyse> analysesCopiedfromidbatch) {
        this.analysesCopiedfromidbatch = analysesCopiedfromidbatch;
    }


    @XmlTransient
    public List<Trace> getTraces() {
        return traces;
    }

    public void setTraces(List<Trace> traces) {
        this.traces = traces;
    }

    @XmlTransient
    public List<Casefile> getCasefiles() {
        return casefiles;
    }

    public void setCasefiles(List<Casefile> casefiles) {
        this.casefiles = casefiles;
    }

    public Treatement getTreatement() {
        return treatement;
    }

    public void setTreatement(Treatement treatement) {
        this.treatement = treatement;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Generation getGeneration() {
        return generation;
    }

    public void setGeneration(Generation generation) {
        this.generation = generation;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Trace getTrace() {
        return trace;
    }

    public void setTrace(Trace trace) {
        this.trace = trace;
    }

    public Variety getVariety() {
        return variety;
    }

    public void setVariety(Variety variety) {
        this.variety = variety;
    }

    public Wo getWo() {
        return wo;
    }

    public void setWo(Wo wo) {
        this.wo = wo;
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
        hash += (batch != null ? batch.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Batch)) {
            return false;
        }
        Batch other = (Batch) object;
        if ((this.batch == null && other.batch != null) || (this.batch != null && !this.batch.equals(other.batch))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Batch[ batch=" + batch + " ]";
    }
    
}
