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
public class MethodedetailPK implements Serializable {
    @Basic(optional = false)
    @Column(nullable = false)
    private long methodedetail;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private long methode;

    public MethodedetailPK() {
    }

    public MethodedetailPK(long methodedetail, long methode) {
        this.methodedetail = methodedetail;
        this.methode = methode;
    }

    public long getMethodedetail() {
        return methodedetail;
    }

    public void setMethodedetail(long methodedetail) {
        this.methodedetail = methodedetail;
    }

    public long getMethode() {
        return methode;
    }

    public void setMethode(long methode) {
        this.methode = methode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) methodedetail;
        hash += (int) methode;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MethodedetailPK)) {
            return false;
        }
        MethodedetailPK other = (MethodedetailPK) object;
        if (this.methodedetail != other.methodedetail) {
            return false;
        }
        if (this.methode != other.methode) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.MethodeedetailPK[ methodedetail=" + methodedetail + ", methode=" + methode + " ]";
    }
    
}
