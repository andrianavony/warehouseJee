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
    @NamedQuery(name = "Treatement.findAll", query = "SELECT t FROM Treatement t"),
    @NamedQuery(name = "Treatement.findByTreatement", query = "SELECT t FROM Treatement t WHERE t.treatement = :treatement"),
    @NamedQuery(name = "Treatement.findByTreatementname", query = "SELECT t FROM Treatement t WHERE t.treatementname = :treatementname"),
    @NamedQuery(name = "Treatement.findByDescription", query = "SELECT t FROM Treatement t WHERE t.description = :description")
  //,@NamedQuery(name = "Article.findByVersion", query = "SELECT a FROM Article a WHERE a.version = :version")
})
public class Treatement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String treatement;
    @Size(max = 50)
    @Column(length = 50)
    private String treatementname;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Version
    private Timestamp version;*/
    @OneToMany(mappedBy = "treatement")
    private List<Batch> batches;

    public Treatement() {
    }

    public Treatement(String treatement) {
        this.treatement = treatement;
    }

    /*public Treatement(String treatement, Timestamp version) {
        this.treatement = treatement;
        this.version = version;
    }*/

    public String getTreatement() {
        return treatement;
    }

    public void setTreatement(String treatement) {
        this.treatement = treatement;
    }

    public String getTreatementname() {
        return treatementname;
    }

    public void setTreatementname(String treatementname) {
        this.treatementname = treatementname;
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
    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (treatement != null ? treatement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Treatement)) {
            return false;
        }
        Treatement other = (Treatement) object;
        if ((this.treatement == null && other.treatement != null) || (this.treatement != null && !this.treatement.equals(other.treatement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Treatement[ treatement=" + treatement + " ]";
    }
    
}
