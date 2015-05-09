/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    @NamedQuery(name = "Heritagebygroupofsample.findAll", query = "SELECT h FROM Heritagebygroupofsample h"),
    @NamedQuery(name = "Heritagebygroupofsample.findByGroupofsampleplangroup", query = "SELECT h FROM Heritagebygroupofsample h WHERE h.heritagebygroupofsamplePK.groupofsampleplangroup = :groupofsampleplangroup"),
    @NamedQuery(name = "Heritagebygroupofsample.findByModeleanalyse", query = "SELECT h FROM Heritagebygroupofsample h WHERE h.heritagebygroupofsamplePK.modeleanalyse = :modeleanalyse"),
    @NamedQuery(name = "Heritagebygroupofsample.findByDownupboth", query = "SELECT h FROM Heritagebygroupofsample h WHERE h.heritagebygroupofsamplePK.downupboth = :downupboth"),
    @NamedQuery(name = "Heritagebygroupofsample.findByHeritagebygroupofsamplename", query = "SELECT h FROM Heritagebygroupofsample h WHERE h.heritagebygroupofsamplename = :heritagebygroupofsamplename"),
    @NamedQuery(name = "Heritagebygroupofsample.findByDescription", query = "SELECT h FROM Heritagebygroupofsample h WHERE h.description = :description")
  //,@NamedQuery(name = "Article.findByVersion", query = "SELECT a FROM Article a WHERE a.version = :version")
})
public class Heritagebygroupofsample implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HeritagebygroupofsamplePK heritagebygroupofsamplePK;
    @Size(max = 50)
    @Column(length = 50)
    private String heritagebygroupofsamplename;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Version
    private Timestamp version;*/
    @JoinColumn(name = "MODELEANALYSE", referencedColumnName = "MODELEANALYSE", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Modeleanalyse modeleanalyse;
    @JoinColumn(name = "GROUPOFSAMPLEPLANGROUP", referencedColumnName = "GROUPOFSAMPLEPLANGROUP", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Groupofsampleplangroup groupofsampleplangroup;

    public Heritagebygroupofsample() {
    }

    public Heritagebygroupofsample(HeritagebygroupofsamplePK heritagebygroupofsamplePK) {
        this.heritagebygroupofsamplePK = heritagebygroupofsamplePK;
    }

    /*public Heritagebygroupofsample(HeritagebygroupofsamplePK heritagebygroupofsamplePK, Timestamp version) {
        this.heritagebygroupofsamplePK = heritagebygroupofsamplePK;
        this.version = version;
    }*/

    public Heritagebygroupofsample(long groupofsampleplangroup, long modeleanalyse, short downupboth) {
        this.heritagebygroupofsamplePK = new HeritagebygroupofsamplePK(groupofsampleplangroup, modeleanalyse, downupboth);
    }

    public HeritagebygroupofsamplePK getHeritagebygroupofsamplePK() {
        return heritagebygroupofsamplePK;
    }

    public void setHeritagebygroupofsamplePK(HeritagebygroupofsamplePK heritagebygroupofsamplePK) {
        this.heritagebygroupofsamplePK = heritagebygroupofsamplePK;
    }

    public String getHeritagebygroupofsamplename() {
        return heritagebygroupofsamplename;
    }

    public void setHeritagebygroupofsamplename(String heritagebygroupofsamplename) {
        this.heritagebygroupofsamplename = heritagebygroupofsamplename;
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

    public Modeleanalyse getModeleanalyse() {
        return modeleanalyse;
    }

    public void setModeleanalyse(Modeleanalyse modeleanalyse) {
        this.modeleanalyse = modeleanalyse;
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
        hash += (heritagebygroupofsamplePK != null ? heritagebygroupofsamplePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Heritagebygroupofsample)) {
            return false;
        }
        Heritagebygroupofsample other = (Heritagebygroupofsample) object;
        if ((this.heritagebygroupofsamplePK == null && other.heritagebygroupofsamplePK != null) || (this.heritagebygroupofsamplePK != null && !this.heritagebygroupofsamplePK.equals(other.heritagebygroupofsamplePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Heritagebygroupofsample[ heritagebygroupofsamplePK=" + heritagebygroupofsamplePK + " ]";
    }
    
}
