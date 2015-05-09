/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import java.math.BigInteger;
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
    @NamedQuery(name = "Specificationdetail.findAll", query = "SELECT s FROM Specificationdetail s"),
    @NamedQuery(name = "Specificationdetail.findBySpecification", query = "SELECT s FROM Specificationdetail s WHERE s.specificationdetailPK.specification = :specification"),
    @NamedQuery(name = "Specificationdetail.findByMeasure", query = "SELECT s FROM Specificationdetail s WHERE s.specificationdetailPK.measure = :measure"),
    @NamedQuery(name = "Specificationdetail.findByMethodedetail", query = "SELECT s FROM Specificationdetail s WHERE s.methodedetail = :methodedetail"),
    @NamedQuery(name = "Specificationdetail.findByDescription", query = "SELECT s FROM Specificationdetail s WHERE s.description = :description"),
    @NamedQuery(name = "Specificationdetail.findByValuemin", query = "SELECT s FROM Specificationdetail s WHERE s.valuemin = :valuemin"),
    @NamedQuery(name = "Specificationdetail.findByPreferdvaluemin", query = "SELECT s FROM Specificationdetail s WHERE s.preferdvaluemin = :preferdvaluemin"),
    @NamedQuery(name = "Specificationdetail.findByPreferdvaluemax", query = "SELECT s FROM Specificationdetail s WHERE s.preferdvaluemax = :preferdvaluemax"),
    @NamedQuery(name = "Specificationdetail.findByValuemax", query = "SELECT s FROM Specificationdetail s WHERE s.valuemax = :valuemax"),
    @NamedQuery(name = "Specificationdetail.findByStatut", query = "SELECT s FROM Specificationdetail s WHERE s.statut = :statut")
  //,@NamedQuery(name = "Article.findByVersion", query = "SELECT a FROM Article a WHERE a.version = :version")
})
public class Specificationdetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SpecificationdetailPK specificationdetailPK;
    private BigInteger methodedetail;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 22)
    private Double valuemin;
    @Column(precision = 22)
    private Double preferdvaluemin;
    @Column(precision = 22)
    private Double preferdvaluemax;
    @Column(precision = 22)
    private Double valuemax;
    private Short statut;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Version
    private Timestamp version;*/
    @JoinColumn(name = "MEASURE", referencedColumnName = "MEASURE", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Measure measure;
    @JoinColumn(name = "METHODE", referencedColumnName = "METHODE")
    @ManyToOne
    private Methode methode;
    @JoinColumn(name = "SPECIFICATION", referencedColumnName = "SPECIFICATION", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Specification specification;

    public Specificationdetail() {
    }

    public Specificationdetail(SpecificationdetailPK specificationdetailPK) {
        this.specificationdetailPK = specificationdetailPK;
    }

    /*public Specificationdetail(SpecificationdetailPK specificationdetailPK, Timestamp version) {
        this.specificationdetailPK = specificationdetailPK;
        this.version = version;
    }*/

    public Specificationdetail(long specification, long measure) {
        this.specificationdetailPK = new SpecificationdetailPK(specification, measure);
    }

    public SpecificationdetailPK getSpecificationdetailPK() {
        return specificationdetailPK;
    }

    public void setSpecificationdetailPK(SpecificationdetailPK specificationdetailPK) {
        this.specificationdetailPK = specificationdetailPK;
    }

    public BigInteger getMethodedetail() {
        return methodedetail;
    }

    public void setMethodedetail(BigInteger methodedetail) {
        this.methodedetail = methodedetail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValuemin() {
        return valuemin;
    }

    public void setValuemin(Double valuemin) {
        this.valuemin = valuemin;
    }

    public Double getPreferdvaluemin() {
        return preferdvaluemin;
    }

    public void setPreferdvaluemin(Double preferdvaluemin) {
        this.preferdvaluemin = preferdvaluemin;
    }

    public Double getPreferdvaluemax() {
        return preferdvaluemax;
    }

    public void setPreferdvaluemax(Double preferdvaluemax) {
        this.preferdvaluemax = preferdvaluemax;
    }

    public Double getValuemax() {
        return valuemax;
    }

    public void setValuemax(Double valuemax) {
        this.valuemax = valuemax;
    }

    public Short getStatut() {
        return statut;
    }

    public void setStatut(Short statut) {
        this.statut = statut;
    }

    /*public Date getVersion() {
        return version;
    }

    public void setVersion(Timestamp version) {
        this.version = version;
    }*/

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }

    public Methode getMethode() {
        return methode;
    }

    public void setMethode(Methode methode) {
        this.methode = methode;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (specificationdetailPK != null ? specificationdetailPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Specificationdetail)) {
            return false;
        }
        Specificationdetail other = (Specificationdetail) object;
        if ((this.specificationdetailPK == null && other.specificationdetailPK != null) || (this.specificationdetailPK != null && !this.specificationdetailPK.equals(other.specificationdetailPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Specificationdetail[ specificationdetailPK=" + specificationdetailPK + " ]";
    }
    
}
