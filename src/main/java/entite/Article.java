/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    @NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a"),
    @NamedQuery(name = "Article.findByArticle", query = "SELECT a FROM Article a WHERE a.article = :article"),
    @NamedQuery(name = "Article.findByDescription", query = "SELECT a FROM Article a WHERE a.description = :description"),
    @NamedQuery(name = "Article.findByArticlename", query = "SELECT a FROM Article a WHERE a.articlename = :articlename"),
    @NamedQuery(name = "Article.findByOfficialname", query = "SELECT a FROM Article a WHERE a.officialname = :officialname")
    //,@NamedQuery(name = "Article.findByVersion", query = "SELECT a FROM Article a WHERE a.version = :version")
    })
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String article;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    @Size(max = 50)
    @Column(length = 50)
    private String articlename;
    @Size(max = 50)
    @Column(length = 50)
    private String officialname;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Version
    private Timestamp version;*/
    @OneToMany(mappedBy = "article")
    private List<Analyse> analyses;
    @OneToMany(mappedBy = "article")
    private List<Trace> traces;
    @OneToMany(mappedBy = "article")
    private List<Sampleplangroup> sampleplangroups;
    @OneToMany(mappedBy = "article")
    private List<Casefile> casefiles;
    @OneToMany(mappedBy = "article")
    private List<Batch> batches;
    @OneToMany(mappedBy = "article")
    private List<Resultat> resultats;
    @OneToMany(mappedBy = "article")
    private List<Sample> samples;
    @JoinColumn(name = "STAGE", referencedColumnName = "STAGE", insertable=true, updatable=true)
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Stage stage;
    @JoinColumn(name = "GENERATION", referencedColumnName = "GENERATION", insertable=true, updatable=true)
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Generation generation;
    @JoinColumn(name = "SPECIE", referencedColumnName = "SPECIE", insertable=false, updatable=false)
    @ManyToOne
    private Specie specie;
    @JoinColumns({    
    	@JoinColumn(name = "VARIETY", referencedColumnName = "VARIETY", insertable=true, updatable=false, nullable=true),
    	@JoinColumn(name = "SPECIE", referencedColumnName = "SPECIE", insertable=true, updatable=false, nullable=true)
   
    })
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Variety variety;

    public Article() {
    }

    public Article(String article) {
        this.article = article;
    }

    /*public Article(String article, Timestamp version) {
        this.article = article;
        this.version = version;
    }*/

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArticlename() {
        return articlename;
    }

    public void setArticlename(String articlename) {
        this.articlename = articlename;
    }

    public String getOfficialname() {
        return officialname;
    }

    public void setOfficialname(String officialname) {
        this.officialname = officialname;
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
    public List<Trace> getTraces() {
        return traces;
    }

    public void setTraces(List<Trace> traces) {
        this.traces = traces;
    }

    @XmlTransient
    public List<Sampleplangroup> getSampleplangroups() {
        return sampleplangroups;
    }

    public void setSampleplangroups(List<Sampleplangroup> sampleplangroups) {
        this.sampleplangroups = sampleplangroups;
    }

    @XmlTransient
    public List<Casefile> getCasefiles() {
        return casefiles;
    }

    public void setCasefiles(List<Casefile> casefiles) {
        this.casefiles = casefiles;
    }

    @XmlTransient
    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
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

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
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

    public Variety getVariety() {
        return variety;
    }

    public void setVariety(Variety variety) {
        this.variety = variety;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (article != null ? article.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.article == null && other.article != null) || (this.article != null && !this.article.equals(other.article))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Article[ article=" + article + " ]";
    }
    
}
