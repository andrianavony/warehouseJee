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
public class VarietyPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String variety;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50, insertable = false, updatable = false)
    private String specie;

    public VarietyPK() {
    }

    public VarietyPK(String variety, String specie) {
        this.variety = variety;
        this.specie = specie;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (variety != null ? variety.hashCode() : 0);
        hash += (specie != null ? specie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VarietyPK)) {
            return false;
        }
        VarietyPK other = (VarietyPK) object;
        if ((this.variety == null && other.variety != null) || (this.variety != null && !this.variety.equals(other.variety))) {
            return false;
        }
        if ((this.specie == null && other.specie != null) || (this.specie != null && !this.specie.equals(other.specie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.VarietyPK[ variety=" + variety + ", specie=" + specie + " ]";
    }
    
}
