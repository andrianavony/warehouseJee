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

/**
 *
 * @author S.ANDRIANAVONY
 */
@Embeddable
public class HeritagebygroupofsamplePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private long groupofsampleplangroup;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private long modeleanalyse;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private short downupboth;

    public HeritagebygroupofsamplePK() {
    }

    public HeritagebygroupofsamplePK(long groupofsampleplangroup, long modeleanalyse, short downupboth) {
        this.groupofsampleplangroup = groupofsampleplangroup;
        this.modeleanalyse = modeleanalyse;
        this.downupboth = downupboth;
    }

    public long getGroupofsampleplangroup() {
        return groupofsampleplangroup;
    }

    public void setGroupofsampleplangroup(long groupofsampleplangroup) {
        this.groupofsampleplangroup = groupofsampleplangroup;
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
        hash += (int) groupofsampleplangroup;
        hash += (int) modeleanalyse;
        hash += (int) downupboth;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HeritagebygroupofsamplePK)) {
            return false;
        }
        HeritagebygroupofsamplePK other = (HeritagebygroupofsamplePK) object;
        if (this.groupofsampleplangroup != other.groupofsampleplangroup) {
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
        return "entite.HeritagebygroupofsamplePK[ groupofsampleplangroup=" + groupofsampleplangroup + ", modeleanalyse=" + modeleanalyse + ", downupboth=" + downupboth + " ]";
    }
    
}
