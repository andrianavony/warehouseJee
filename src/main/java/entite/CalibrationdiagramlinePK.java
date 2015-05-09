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
public class CalibrationdiagramlinePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String diagram;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String caliber;

    public CalibrationdiagramlinePK() {
    }

    public CalibrationdiagramlinePK(String diagram, String caliber) {
        this.diagram = diagram;
        this.caliber = caliber;
    }

    public String getDiagram() {
        return diagram;
    }

    public void setDiagram(String diagram) {
        this.diagram = diagram;
    }

    public String getCaliber() {
        return caliber;
    }

    public void setCaliber(String caliber) {
        this.caliber = caliber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diagram != null ? diagram.hashCode() : 0);
        hash += (caliber != null ? caliber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalibrationdiagramlinePK)) {
            return false;
        }
        CalibrationdiagramlinePK other = (CalibrationdiagramlinePK) object;
        if ((this.diagram == null && other.diagram != null) || (this.diagram != null && !this.diagram.equals(other.diagram))) {
            return false;
        }
        if ((this.caliber == null && other.caliber != null) || (this.caliber != null && !this.caliber.equals(other.caliber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.CalibrationdiagramlinePK[ diagram=" + diagram + ", caliber=" + caliber + " ]";
    }
    
}
