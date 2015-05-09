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
public class PossiblevaluesdetailPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private long measure;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private long possiblevalue;

    public PossiblevaluesdetailPK() {
    }

    public PossiblevaluesdetailPK(long measure, long possiblevalue) {
        this.measure = measure;
        this.possiblevalue = possiblevalue;
    }

    public long getMeasure() {
        return measure;
    }

    public void setMeasure(long measure) {
        this.measure = measure;
    }

    public long getPossiblevalue() {
        return possiblevalue;
    }

    public void setPossiblevalue(long possiblevalue) {
        this.possiblevalue = possiblevalue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) measure;
        hash += (int) possiblevalue;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PossiblevaluesdetailPK)) {
            return false;
        }
        PossiblevaluesdetailPK other = (PossiblevaluesdetailPK) object;
        if (this.measure != other.measure) {
            return false;
        }
        if (this.possiblevalue != other.possiblevalue) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.PossiblevaluesdetailPK[ measure=" + measure + ", possiblevalue=" + possiblevalue + " ]";
    }
    
}
