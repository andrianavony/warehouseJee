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
    @NamedQuery(name = "Heritagebyspecieorigin.findAll", query = "SELECT h FROM Heritagebyspecieorigin h"),
    @NamedQuery(name = "Heritagebyspecieorigin.findBySpecie", query = "SELECT h FROM Heritagebyspecieorigin h WHERE h.heritagebyspecieoriginPK.specie = :specie"),
    @NamedQuery(name = "Heritagebyspecieorigin.findByOrigin", query = "SELECT h FROM Heritagebyspecieorigin h WHERE h.heritagebyspecieoriginPK.origin = :origin"),
    @NamedQuery(name = "Heritagebyspecieorigin.findByModeleanalyse", query = "SELECT h FROM Heritagebyspecieorigin h WHERE h.heritagebyspecieoriginPK.modeleanalyse = :modeleanalyse"),
    @NamedQuery(name = "Heritagebyspecieorigin.findByDownupboth", query = "SELECT h FROM Heritagebyspecieorigin h WHERE h.heritagebyspecieoriginPK.downupboth = :downupboth"),
    @NamedQuery(name = "Heritagebyspecieorigin.findByHeritagebyspecieoriginname", query = "SELECT h FROM Heritagebyspecieorigin h WHERE h.heritagebyspecieoriginname = :heritagebyspecieoriginname"),
    @NamedQuery(name = "Heritagebyspecieorigin.findByDescription", query = "SELECT h FROM Heritagebyspecieorigin h WHERE h.description = :description")
  //,@NamedQuery(name = "Article.findByVersion", query = "SELECT a FROM Article a WHERE a.version = :version")
})
public class Heritagebyspecieorigin implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HeritagebyspecieoriginPK heritagebyspecieoriginPK;
    @Size(max = 50)
    @Column(length = 50)
    private String heritagebyspecieoriginname;
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
    @JoinColumn(name = "ORIGIN", referencedColumnName = "ORIGIN", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Origin origin1;
    @JoinColumn(name = "SPECIE", referencedColumnName = "SPECIE", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Specie specie;

    public Heritagebyspecieorigin() {
    }

    public Heritagebyspecieorigin(HeritagebyspecieoriginPK heritagebyspecieoriginPK) {
        this.heritagebyspecieoriginPK = heritagebyspecieoriginPK;
    }

    /*public Heritagebyspecieorigin(HeritagebyspecieoriginPK heritagebyspecieoriginPK, Timestamp version) {
        this.heritagebyspecieoriginPK = heritagebyspecieoriginPK;
        this.version = version;
    }*/

    public Heritagebyspecieorigin(String specie, long origin, long modeleanalyse, short downupboth) {
        this.heritagebyspecieoriginPK = new HeritagebyspecieoriginPK(specie, origin, modeleanalyse, downupboth);
    }

    public HeritagebyspecieoriginPK getHeritagebyspecieoriginPK() {
        return heritagebyspecieoriginPK;
    }

    public void setHeritagebyspecieoriginPK(HeritagebyspecieoriginPK heritagebyspecieoriginPK) {
        this.heritagebyspecieoriginPK = heritagebyspecieoriginPK;
    }

    public String getHeritagebyspecieoriginname() {
        return heritagebyspecieoriginname;
    }

    public void setHeritagebyspecieoriginname(String heritagebyspecieoriginname) {
        this.heritagebyspecieoriginname = heritagebyspecieoriginname;
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

    public Origin getOrigin1() {
        return origin1;
    }

    public void setOrigin1(Origin origin1) {
        this.origin1 = origin1;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (heritagebyspecieoriginPK != null ? heritagebyspecieoriginPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Heritagebyspecieorigin)) {
            return false;
        }
        Heritagebyspecieorigin other = (Heritagebyspecieorigin) object;
        if ((this.heritagebyspecieoriginPK == null && other.heritagebyspecieoriginPK != null) || (this.heritagebyspecieoriginPK != null && !this.heritagebyspecieoriginPK.equals(other.heritagebyspecieoriginPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Heritagebyspecieorigin[ heritagebyspecieoriginPK=" + heritagebyspecieoriginPK + " ]";
    }
    
}
