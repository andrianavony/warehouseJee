/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author S.ANDRIANAVONY
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caliber.findAll", query = "SELECT c FROM Caliber c"),
    @NamedQuery(name = "Caliber.findByCaliber", query = "SELECT c FROM Caliber c WHERE c.caliber = :caliber"),
    @NamedQuery(name = "Caliber.findByCalibername", query = "SELECT c FROM Caliber c WHERE c.calibername = :calibername"),
    @NamedQuery(name = "Caliber.findByDescription", query = "SELECT c FROM Caliber c WHERE c.description = :description")
  //,@NamedQuery(name = "Article.findByVersion", query = "SELECT a FROM Article a WHERE a.version = :version")
})
public class Caliber implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String caliber;
    @Size(max = 50)
    @Column(length = 50)
    private String calibername;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Version
    private Timestamp version;*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caliber")
    private List<Calibrationdiagramline> calibrationdiagramlines;

    public Caliber() {
    }

    public Caliber(String caliber) {
        this.caliber = caliber;
    }

    /*public Caliber(String caliber, Timestamp version) {
        this.caliber = caliber;
        this.version = version;
    }*/

    public String getCaliber() {
        return caliber;
    }

    public void setCaliber(String caliber) {
        this.caliber = caliber;
    }

    public String getCalibername() {
        return calibername;
    }

    public void setCalibername(String calibername) {
        this.calibername = calibername;
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

    @XmlTransient
    public List<Calibrationdiagramline> getCalibrationdiagramlines() {
        return calibrationdiagramlines;
    }

    public void setCalibrationdiagramlines(List<Calibrationdiagramline> calibrationdiagramlines) {
        this.calibrationdiagramlines = calibrationdiagramlines;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (caliber != null ? caliber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caliber)) {
            return false;
        }
        Caliber other = (Caliber) object;
        if ((this.caliber == null && other.caliber != null) || (this.caliber != null && !this.caliber.equals(other.caliber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Caliber[ caliber=" + caliber + " ]";
    }
    
}
