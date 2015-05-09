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

import utilities.DateManager;

/**
 *
 * @author S.ANDRIANAVONY
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Specie.findAll", query = "SELECT s FROM Specie s"),
    @NamedQuery(name = "Specie.findBySpecie", query = "SELECT s FROM Specie s WHERE s.specie = :specie"),
    @NamedQuery(name = "Specie.findBySpeciename", query = "SELECT s FROM Specie s WHERE s.speciename = :speciename"),
    @NamedQuery(name = "Specie.findByOfficialname", query = "SELECT s FROM Specie s WHERE s.officialname = :officialname")
    //,    @NamedQuery(name = "Specie.findByVersion", query = "SELECT s FROM Specie s WHERE s.version = :version")
    })
public class Specie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String specie;
    @Size(max = 50)
    @Column(length = 50)
    private String speciename;
    @Size(max = 50)
    @Column(length = 50)
    private String officialname;
    /*
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Version
    private Timestamp version;
    */
    @OneToMany(mappedBy = "specie")
    private List<Analyse> analyses;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "specie")
    private List<Variety> varieties;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "specie")
    private List<Heritagebyspecieorigin> heritagebyspecieorigins;
    @OneToMany(mappedBy = "specie")
    private List<Casefile> casefiles;
    @OneToMany(mappedBy = "specie")
    private List<Batch> batches;
    @OneToMany(mappedBy = "specie")
    private List<Resultat> resultats;
    @OneToMany(mappedBy = "specie")
    private List<Sample> samples;
    @OneToMany(mappedBy = "specie")
    private List<Article> articles;

    public Specie() {
    }

    public Specie(String specie) {
        this.specie = specie;
        //this.version= DateManager.getNowTimestamp();
    }

    /* jaona
    public Specie(String specie, Timestamp version) {
        this.specie = specie;
        this.version = version;
    }
    */

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getSpeciename() {
        return speciename;
    }

    public void setSpeciename(String speciename) {
        this.speciename = speciename;
    }

    public String getOfficialname() {
        return officialname;
    }

    public void setOfficialname(String officialname) {
        this.officialname = officialname;
    }

    /*
    public Date getVersion() {
        return version;
    }

    public void setVersion(Timestamp version) {
        this.version = version;
    }
    */

    @XmlTransient
    public List<Analyse> getAnalyses() {
        return analyses;
    }

    public void setAnalyses(List<Analyse> analyses) {
        this.analyses = analyses;
    }

    @XmlTransient
    public List<Variety> getVarieties() {
        return varieties;
    }

    public void setVarieties(List<Variety> varieties) {
        this.varieties = varieties;
    }

    @XmlTransient
    public List<Heritagebyspecieorigin> getHeritagebyspecieorigins() {
        return heritagebyspecieorigins;
    }

    public void setHeritagebyspecieorigins(List<Heritagebyspecieorigin> heritagebyspecieorigins) {
        this.heritagebyspecieorigins = heritagebyspecieorigins;
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

    @XmlTransient
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (specie != null ? specie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Specie)) {
            return false;
        }
        Specie other = (Specie) object;
        if ((this.specie == null && other.specie != null) || (this.specie != null && !this.specie.equals(other.specie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Specie[ specie=" + specie + " ]";
    }
    
}
