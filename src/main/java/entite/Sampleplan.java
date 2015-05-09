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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author S.ANDRIANAVONY
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sampleplan.findAll", query = "SELECT s FROM Sampleplan s"),
    @NamedQuery(name = "Sampleplan.findBySampleplan", query = "SELECT s FROM Sampleplan s WHERE s.sampleplan = :sampleplan"),
    @NamedQuery(name = "Sampleplan.findBySampleplanname", query = "SELECT s FROM Sampleplan s WHERE s.sampleplanname = :sampleplanname"),
    @NamedQuery(name = "Sampleplan.findByDescription", query = "SELECT s FROM Sampleplan s WHERE s.description = :description"),
    @NamedQuery(name = "Sampleplan.findBySampleplanidlims", query = "SELECT s FROM Sampleplan s WHERE s.sampleplanidlims = :sampleplanidlims")
  //,@NamedQuery(name = "Article.findByVersion", query = "SELECT a FROM Article a WHERE a.version = :version")
})
public class Sampleplan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long sampleplan;
    @Size(max = 50)
    @Column(length = 50)
    private String sampleplanname;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    private BigInteger sampleplanidlims;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Version
    private Timestamp version;*/
    @JoinColumn(name = "SAMPLEPLANGROUP", referencedColumnName = "SAMPLEPLANGROUP")
    @ManyToOne
    private Sampleplangroup sampleplangroup;

    public Sampleplan() {
    }

    public Sampleplan(Long sampleplan) {
        this.sampleplan = sampleplan;
    }

    /*public Sampleplan(Long sampleplan, Timestamp version) {
        this.sampleplan = sampleplan;
        this.version = version;
    }*/

    public Long getSampleplan() {
        return sampleplan;
    }

    public void setSampleplan(Long sampleplan) {
        this.sampleplan = sampleplan;
    }

    public String getSampleplanname() {
        return sampleplanname;
    }

    public void setSampleplanname(String sampleplanname) {
        this.sampleplanname = sampleplanname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigInteger getSampleplanidlims() {
        return sampleplanidlims;
    }

    public void setSampleplanidlims(BigInteger sampleplanidlims) {
        this.sampleplanidlims = sampleplanidlims;
    }

    /*public Date getVersion() {
        return version;
    }

    public void setVersion(Timestamp version) {
        this.version = version;
    }*/

    public Sampleplangroup getSampleplangroup() {
        return sampleplangroup;
    }

    public void setSampleplangroup(Sampleplangroup sampleplangroup) {
        this.sampleplangroup = sampleplangroup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sampleplan != null ? sampleplan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sampleplan)) {
            return false;
        }
        Sampleplan other = (Sampleplan) object;
        if ((this.sampleplan == null && other.sampleplan != null) || (this.sampleplan != null && !this.sampleplan.equals(other.sampleplan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Sampleplan[ sampleplan=" + sampleplan + " ]";
    }
    
}
