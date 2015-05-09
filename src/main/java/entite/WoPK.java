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
public class WoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String wo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String company;

    public WoPK() {
    }

    public WoPK(String wo, String company) {
        this.wo = wo;
        this.company = company;
    }

    public String getWo() {
        return wo;
    }

    public void setWo(String wo) {
        this.wo = wo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wo != null ? wo.hashCode() : 0);
        hash += (company != null ? company.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WoPK)) {
            return false;
        }
        WoPK other = (WoPK) object;
        if ((this.wo == null && other.wo != null) || (this.wo != null && !this.wo.equals(other.wo))) {
            return false;
        }
        if ((this.company == null && other.company != null) || (this.company != null && !this.company.equals(other.company))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.WoPK[ wo=" + wo + ", company=" + company + " ]";
    }
    
}
