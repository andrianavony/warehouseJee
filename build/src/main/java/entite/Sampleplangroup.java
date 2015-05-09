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
    @NamedQuery(name = "Sampleplangroup.findAll", query = "SELECT s FROM Sampleplangroup s"),
    @NamedQuery(name = "Sampleplangroup.findBySampleplangroup", query = "SELECT s FROM Sampleplangroup s WHERE s.sampleplangroup = :sampleplangroup"),
    @NamedQuery(name = "Sampleplangroup.findBySampleplangroupname", query = "SELECT s FROM Sampleplangroup s WHERE s.sampleplangroupname = :sampleplangroupname"),
    @NamedQuery(name = "Sampleplangroup.findByDescription", query = "SELECT s FROM Sampleplangroup s WHERE s.description = :description")
  //,@NamedQuery(name = "Article.findByVersion", query = "SELECT a FROM Article a WHERE a.version = :version")
})
public class Sampleplangroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long sampleplangroup;
    @Size(max = 50)
    @Column(length = 50)
    private String sampleplangroupname;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Version
    private Timestamp version;*/
    @OneToMany(mappedBy = "sampleplangroup")
    private List<Sampleplan> sampleplans;
    @JoinColumn(name = "ARTICLE", referencedColumnName = "ARTICLE")
    @ManyToOne
    private Article article;
    @JoinColumn(name = "GROUPOFSAMPLEPLANGROUP", referencedColumnName = "GROUPOFSAMPLEPLANGROUP")
    @ManyToOne
    private Groupofsampleplangroup groupofsampleplangroup;

    public Sampleplangroup() {
    }

    public Sampleplangroup(Long sampleplangroup) {
        this.sampleplangroup = sampleplangroup;
    }

    /*public Sampleplangroup(Long sampleplangroup, Timestamp version) {
        this.sampleplangroup = sampleplangroup;
        this.version = version;
    }*/

    public Long getSampleplangroup() {
        return sampleplangroup;
    }

    public void setSampleplangroup(Long sampleplangroup) {
        this.sampleplangroup = sampleplangroup;
    }

    public String getSampleplangroupname() {
        return sampleplangroupname;
    }

    public void setSampleplangroupname(String sampleplangroupname) {
        this.sampleplangroupname = sampleplangroupname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*public Date getVersion() {
        return version;
    }

    public void setVersion(Timestamp version) {
        this.version = version;
    }*/

    @XmlTransient
    public List<Sampleplan> getSampleplans() {
        return sampleplans;
    }

    public void setSampleplans(List<Sampleplan> sampleplans) {
        this.sampleplans = sampleplans;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Groupofsampleplangroup getGroupofsampleplangroup() {
        return groupofsampleplangroup;
    }

    public void setGroupofsampleplangroup(Groupofsampleplangroup groupofsampleplangroup) {
        this.groupofsampleplangroup = groupofsampleplangroup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sampleplangroup != null ? sampleplangroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sampleplangroup)) {
            return false;
        }
        Sampleplangroup other = (Sampleplangroup) object;
        if ((this.sampleplangroup == null && other.sampleplangroup != null) || (this.sampleplangroup != null && !this.sampleplangroup.equals(other.sampleplangroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Sampleplangroup[ sampleplangroup=" + sampleplangroup + " ]";
    }
    
}
