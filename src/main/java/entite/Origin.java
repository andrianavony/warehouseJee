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
    @NamedQuery(name = "Origin.findAll", query = "SELECT o FROM Origin o"),
    @NamedQuery(name = "Origin.findByOrigin", query = "SELECT o FROM Origin o WHERE o.origin = :origin"),
    @NamedQuery(name = "Origin.findByOriginname", query = "SELECT o FROM Origin o WHERE o.originname = :originname"),
    @NamedQuery(name = "Origin.findByOrigincode", query = "SELECT o FROM Origin o WHERE o.origincode = :origincode"),
    @NamedQuery(name = "Origin.findByDescription", query = "SELECT o FROM Origin o WHERE o.description = :description")
  //,@NamedQuery(name = "Article.findByVersion", query = "SELECT a FROM Article a WHERE a.version = :version")
})
public class Origin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long origin;
    @Size(max = 50)
    @Column(length = 50)
    private String originname;
    @Size(max = 50)
    @Column(length = 50)
    private String origincode;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Version
    private Timestamp version;*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "origin1")
    private List<Heritagebyspecieorigin> heritagebyspecieorigins;

    public Origin() {
    }

    public Origin(Long origin) {
        this.origin = origin;
    }

    /*public Origin(Long origin, Timestamp version) {
        this.origin = origin;
        this.version = version;
    }*/

    public Long getOrigin() {
        return origin;
    }

    public void setOrigin(Long origin) {
        this.origin = origin;
    }

    public String getOriginname() {
        return originname;
    }

    public void setOriginname(String originname) {
        this.originname = originname;
    }

    public String getOrigincode() {
        return origincode;
    }

    public void setOrigincode(String origincode) {
        this.origincode = origincode;
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
    public List<Heritagebyspecieorigin> getHeritagebyspecieorigins() {
        return heritagebyspecieorigins;
    }

    public void setHeritagebyspecieorigins(List<Heritagebyspecieorigin> heritagebyspecieorigins) {
        this.heritagebyspecieorigins = heritagebyspecieorigins;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (origin != null ? origin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Origin)) {
            return false;
        }
        Origin other = (Origin) object;
        if ((this.origin == null && other.origin != null) || (this.origin != null && !this.origin.equals(other.origin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Origin[ origin=" + origin + " ]";
    }
    
}
