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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
	@NamedQuery(name = "Variety.findBySpecieVariety", query = "SELECT v FROM Variety v WHERE v.varietyPK.specie = :specie and v.varietyPK.variety = :variety"),
    @NamedQuery(name = "Variety.findAll", query = "SELECT v FROM Variety v"),
    @NamedQuery(name = "Variety.findByVariety", query = "SELECT v FROM Variety v WHERE v.varietyPK.variety = :variety"),
    @NamedQuery(name = "Variety.findBySpecie", query = "SELECT v FROM Variety v WHERE v.varietyPK.specie = :specie"),
    @NamedQuery(name = "Variety.findByDescription", query = "SELECT v FROM Variety v WHERE v.description = :description"),
    @NamedQuery(name = "Variety.findByVarietyname", query = "SELECT v FROM Variety v WHERE v.varietyname = :varietyname"),
    @NamedQuery(name = "Variety.findByOfficialname", query = "SELECT v FROM Variety v WHERE v.officialname = :officialname")
  //,@NamedQuery(name = "Article.findByVersion", query = "SELECT a FROM Article a WHERE a.version = :version")
})
public class Variety implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VarietyPK varietyPK;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    @Size(max = 50)
    @Column(length = 50)
    private String varietyname;
    @Size(max = 50)
    @Column(length = 50)
    private String officialname;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Version
    private Timestamp version;*/
    @JoinColumn(name = "SPECIE", referencedColumnName = "SPECIE", nullable = false)
    @ManyToOne(optional = false, cascade=CascadeType.PERSIST)
    private Specie specie;
    @OneToMany(mappedBy = "variety")
    private List<Batch> batches;
    @OneToMany(mappedBy = "variety")
    private List<Article> articles;

    public Variety() {
    }

    public Variety(VarietyPK varietyPK) {
        this.varietyPK = varietyPK;
    }

    /*public Variety(VarietyPK varietyPK, Timestamp version) {
        this.varietyPK = varietyPK;
        this.version = version;
    }*/

    public Variety(String variety, String specie) {
        this.varietyPK = new VarietyPK(variety, specie);
    }

    public VarietyPK getVarietyPK() {
        return varietyPK;
    }

    public void setVarietyPK(VarietyPK varietyPK) {
        this.varietyPK = varietyPK;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVarietyname() {
        return varietyname;
    }

    public void setVarietyname(String varietyname) {
        this.varietyname = varietyname;
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

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    @XmlTransient
    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
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
        hash += (varietyPK != null ? varietyPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Variety)) {
            return false;
        }
        Variety other = (Variety) object;
        if ((this.varietyPK == null && other.varietyPK != null) || (this.varietyPK != null && !this.varietyPK.equals(other.varietyPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Variety[ varietyPK=" + varietyPK + " ]";
    }
    
}
