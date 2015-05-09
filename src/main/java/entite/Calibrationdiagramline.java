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
    @NamedQuery(name = "Calibrationdiagramline.findAll", query = "SELECT c FROM Calibrationdiagramline c"),
    @NamedQuery(name = "Calibrationdiagramline.findByDiagram", query = "SELECT c FROM Calibrationdiagramline c WHERE c.calibrationdiagramlinePK.diagram = :diagram"),
    @NamedQuery(name = "Calibrationdiagramline.findByCaliber", query = "SELECT c FROM Calibrationdiagramline c WHERE c.calibrationdiagramlinePK.caliber = :caliber"),
    @NamedQuery(name = "Calibrationdiagramline.findByQuantity", query = "SELECT c FROM Calibrationdiagramline c WHERE c.quantity = :quantity"),
    @NamedQuery(name = "Calibrationdiagramline.findByUnite", query = "SELECT c FROM Calibrationdiagramline c WHERE c.unite = :unite"),
    @NamedQuery(name = "Calibrationdiagramline.findByDescription", query = "SELECT c FROM Calibrationdiagramline c WHERE c.description = :description")
    //,@NamedQuery(name = "Calibrationdiagramline.findByVersion", query = "SELECT c FROM Calibrationdiagramline c WHERE c.version = :version")
    })
public class Calibrationdiagramline implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CalibrationdiagramlinePK calibrationdiagramlinePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 22)
    private Double quantity;
    @Size(max = 50)
    @Column(length = 50)
    private String unite;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Version
    private Timestamp version;*/
    @JoinColumn(name = "CALIBER", referencedColumnName = "CALIBER", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Caliber caliber;

    public Calibrationdiagramline() {
    }

    public Calibrationdiagramline(CalibrationdiagramlinePK calibrationdiagramlinePK) {
        this.calibrationdiagramlinePK = calibrationdiagramlinePK;
    }

    /*public Calibrationdiagramline(CalibrationdiagramlinePK calibrationdiagramlinePK, Timestamp version) {
        this.calibrationdiagramlinePK = calibrationdiagramlinePK;
        this.version = version;
    }*/

    public Calibrationdiagramline(String diagram, String caliber) {
        this.calibrationdiagramlinePK = new CalibrationdiagramlinePK(diagram, caliber);
    }

    public CalibrationdiagramlinePK getCalibrationdiagramlinePK() {
        return calibrationdiagramlinePK;
    }

    public void setCalibrationdiagramlinePK(CalibrationdiagramlinePK calibrationdiagramlinePK) {
        this.calibrationdiagramlinePK = calibrationdiagramlinePK;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
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

    public Caliber getCaliber() {
        return caliber;
    }

    public void setCaliber(Caliber caliber) {
        this.caliber = caliber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (calibrationdiagramlinePK != null ? calibrationdiagramlinePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calibrationdiagramline)) {
            return false;
        }
        Calibrationdiagramline other = (Calibrationdiagramline) object;
        if ((this.calibrationdiagramlinePK == null && other.calibrationdiagramlinePK != null) || (this.calibrationdiagramlinePK != null && !this.calibrationdiagramlinePK.equals(other.calibrationdiagramlinePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Calibrationdiagramline[ calibrationdiagramlinePK=" + calibrationdiagramlinePK + " ]";
    }
    
}
