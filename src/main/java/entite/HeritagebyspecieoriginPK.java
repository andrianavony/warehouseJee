/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author S.ANDRIANAVONY
 */
@Embeddable
public class HeritagebyspecieoriginPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String specie;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private long origin;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private long modeleanalyse;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private short downupboth;

    public HeritagebyspecieoriginPK() {
    }

    public HeritagebyspecieoriginPK(String specie, long origin, long modeleanalyse, short downupboth) {
        this.specie = specie;
        this.origin = origin;
        this.modeleanalyse = modeleanalyse;
        this.downupboth = downupboth;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public long getOrigin() {
        return origin;
    }

    public void setOrigin(long origin) {
        this.origin = origin;
    }

    public long getModeleanalyse() {
        return modeleanalyse;
    }

    public void setModeleanalyse(long modeleanalyse) {
        this.modeleanalyse = modeleanalyse;
    }

    public short getDownupboth() {
        return downupboth;
    }

    public void setDownupboth(short downupboth) {
        this.downupboth = downupboth;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (specie != null ? specie.hashCode() : 0);
        hash += (int) origin;
        hash += (int) modeleanalyse;
        hash += (int) downupboth;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HeritagebyspecieoriginPK)) {
            return false;
        }
        HeritagebyspecieoriginPK other = (HeritagebyspecieoriginPK) object;
        if ((this.specie == null && other.specie != null) || (this.specie != null && !this.specie.equals(other.specie))) {
            return false;
        }
        if (this.origin != other.origin) {
            return false;
        }
        if (this.modeleanalyse != other.modeleanalyse) {
            return false;
        }
        if (this.downupboth != other.downupboth) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.HeritagebyspecieoriginPK[ specie=" + specie + ", origin=" + origin + ", modeleanalyse=" + modeleanalyse + ", downupboth=" + downupboth + " ]";
    }
    
}
