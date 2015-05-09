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
public class SpecificationdetailPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private long specification;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private long measure;

    public SpecificationdetailPK() {
    }

    public SpecificationdetailPK(long specification, long measure) {
        this.specification = specification;
        this.measure = measure;
    }

    public long getSpecification() {
        return specification;
    }

    public void setSpecification(long specification) {
        this.specification = specification;
    }

    public long getMeasure() {
        return measure;
    }

    public void setMeasure(long measure) {
        this.measure = measure;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) specification;
        hash += (int) measure;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpecificationdetailPK)) {
            return false;
        }
        SpecificationdetailPK other = (SpecificationdetailPK) object;
        if (this.specification != other.specification) {
            return false;
        }
        if (this.measure != other.measure) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.SpecificationdetailPK[ specification=" + specification + ", measure=" + measure + " ]";
    }
    
}
