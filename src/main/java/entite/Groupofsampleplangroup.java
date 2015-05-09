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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

/**
 *
 * @author S.ANDRIANAVONY
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groupofsampleplangroup.findAll", query = "SELECT g FROM Groupofsampleplangroup g"),
    @NamedQuery(name = "Groupofsampleplangroup.findByGroupofsampleplangroup", query = "SELECT g FROM Groupofsampleplangroup g WHERE g.groupofsampleplangroup = :groupofsampleplangroup"),
    @NamedQuery(name = "Groupofsampleplangroup.findByGroupofsampleplangroupname", query = "SELECT g FROM Groupofsampleplangroup g WHERE g.groupofsampleplangroupname = :groupofsampleplangroupname"),
    @NamedQuery(name = "Groupofsampleplangroup.findByDescription", query = "SELECT g FROM Groupofsampleplangroup g WHERE g.description = :description")
  //,@NamedQuery(name = "Article.findByVersion", query = "SELECT a FROM Article a WHERE a.version = :version")
})
public class Groupofsampleplangroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long groupofsampleplangroup;
    @Size(max = 50)
    @Column(length = 50)
    private String groupofsampleplangroupname;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Version
    private Timestamp version;*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupofsampleplangroup")
    private List<Heritagebygroupofsample> heritagebygroupofsamples;
    @OneToMany(mappedBy = "groupofsampleplangroup")
    private List<Sampleplangroup> sampleplangroups;

    public Groupofsampleplangroup() {
    }

    public Groupofsampleplangroup(Long groupofsampleplangroup) {
        this.groupofsampleplangroup = groupofsampleplangroup;
    }

    /*public Groupofsampleplangroup(Long groupofsampleplangroup, Timestamp version) {
        this.groupofsampleplangroup = groupofsampleplangroup;
        this.version = version;
    }*/

    public Long getGroupofsampleplangroup() {
        return groupofsampleplangroup;
    }

    public void setGroupofsampleplangroup(Long groupofsampleplangroup) {
        this.groupofsampleplangroup = groupofsampleplangroup;
    }

    public String getGroupofsampleplangroupname() {
        return groupofsampleplangroupname;
    }

    public void setGroupofsampleplangroupname(String groupofsampleplangroupname) {
        this.groupofsampleplangroupname = groupofsampleplangroupname;
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
    public List<Heritagebygroupofsample> getHeritagebygroupofsamples() {
        return heritagebygroupofsamples;
    }

    public void setHeritagebygroupofsamples(List<Heritagebygroupofsample> heritagebygroupofsamples) {
        this.heritagebygroupofsamples = heritagebygroupofsamples;
    }

    @XmlTransient
    public List<Sampleplangroup> getSampleplangroups() {
        return sampleplangroups;
    }

    public void setSampleplangroups(List<Sampleplangroup> sampleplangroups) {
        this.sampleplangroups = sampleplangroups;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupofsampleplangroup != null ? groupofsampleplangroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupofsampleplangroup)) {
            return false;
        }
        Groupofsampleplangroup other = (Groupofsampleplangroup) object;
        if ((this.groupofsampleplangroup == null && other.groupofsampleplangroup != null) || (this.groupofsampleplangroup != null && !this.groupofsampleplangroup.equals(other.groupofsampleplangroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Groupofsampleplangroup[ groupofsampleplangroup=" + groupofsampleplangroup + " ]";
    }
    
}
