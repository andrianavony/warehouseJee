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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Wo.findAll", query = "SELECT w FROM Wo w"),
    @NamedQuery(name = "Wo.findByWo", query = "SELECT w FROM Wo w WHERE w.woPK.wo = :wo"),
    @NamedQuery(name = "Wo.findByCompany", query = "SELECT w FROM Wo w WHERE w.woPK.company = :company"),
    @NamedQuery(name = "Wo.findByDescription", query = "SELECT w FROM Wo w WHERE w.description = :description")
  //,@NamedQuery(name = "Article.findByVersion", query = "SELECT a FROM Article a WHERE a.version = :version")
})
public class Wo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WoPK woPK;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Version
    private Timestamp version;*/
    @OneToMany(mappedBy = "wo")
    private List<Trace> traces;
    @JoinColumn(name = "COMPANY", referencedColumnName = "COMPANY", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Company company;
    @JoinColumn(name = "DIAGRAM", referencedColumnName = "DIAGRAM")
    @ManyToOne
    private Calibrationdiagram diagram;
    @OneToMany(mappedBy = "wo")
    private List<Batch> batches;

    public Wo() {
    }

    public Wo(WoPK woPK) {
        this.woPK = woPK;
    }

    /*public Wo(WoPK woPK, Timestamp version) {
        this.woPK = woPK;
        this.version = version;
    }*/

    public Wo(String wo, String company) {
        this.woPK = new WoPK(wo, company);
    }

    public WoPK getWoPK() {
        return woPK;
    }

    public void setWoPK(WoPK woPK) {
        this.woPK = woPK;
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
    public List<Trace> getTraces() {
        return traces;
    }

    public void setTraces(List<Trace> traces) {
        this.traces = traces;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Calibrationdiagram getDiagram() {
        return diagram;
    }

    public void setDiagram(Calibrationdiagram diagram) {
        this.diagram = diagram;
    }

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
        hash += (woPK != null ? woPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wo)) {
            return false;
        }
        Wo other = (Wo) object;
        if ((this.woPK == null && other.woPK != null) || (this.woPK != null && !this.woPK.equals(other.woPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Wo[ woPK=" + woPK + " ]";
    }
    
}
