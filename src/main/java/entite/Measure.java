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
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
    @NamedQuery(name = "Measure.findAll", query = "SELECT m FROM Measure m"),
    @NamedQuery(name = "Measure.findByMeasure", query = "SELECT m FROM Measure m WHERE m.measure = :measure"),
    @NamedQuery(name = "Measure.findByLimsmeasureid", query = "SELECT m FROM Measure m WHERE m.limsmeasureid = :limsmeasureid"),
    @NamedQuery(name = "Measure.findByLimsanalyseid", query = "SELECT m FROM Measure m WHERE m.limsanalyseid = :limsanalyseid"),
    @NamedQuery(name = "Measure.findByMeasurename", query = "SELECT m FROM Measure m WHERE m.measurename = :measurename"),
    @NamedQuery(name = "Measure.findByDescription", query = "SELECT m FROM Measure m WHERE m.description = :description"),
    @NamedQuery(name = "Measure.findByOfficialname", query = "SELECT m FROM Measure m WHERE m.officialname = :officialname"),
    @NamedQuery(name = "Measure.findByPrintname", query = "SELECT m FROM Measure m WHERE m.printname = :printname"),
    @NamedQuery(name = "Measure.findByIsprintable", query = "SELECT m FROM Measure m WHERE m.isprintable = :isprintable"),
    @NamedQuery(name = "Measure.findByDefaultvalue", query = "SELECT m FROM Measure m WHERE m.defaultvalue = :defaultvalue")
  //,@NamedQuery(name = "Article.findByVersion", query = "SELECT a FROM Article a WHERE a.version = :version")
})
public class Measure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long measure;
    private BigInteger limsmeasureid;
    private BigInteger limsanalyseid;
    @Size(max = 50)
    @Column(length = 50)
    private String measurename;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    @Size(max = 50)
    @Column(length = 50)
    private String officialname;
    @Size(max = 50)
    @Column(length = 50)
    private String printname;
    private Boolean isprintable;
    @Size(max = 50)
    @Column(length = 50)
    private String defaultvalue;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Version
    private Timestamp version;*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "measure")
    private List<Specificationdetail> specificationdetails;
    @OneToMany(mappedBy = "measure")
    private List<Methodedetail> methodedetails;
    @OneToMany(mappedBy = "measure")
    private List<Resultat> resultats;
    //@ManyToOne(cascade = CascadeType.ALL, mappedBy = "measure")
    private List<Possiblevaluesdetail> possiblevaluesdetails;
    @JoinColumns({
    	@JoinColumn(name = "POSSIBLEVALUE", referencedColumnName = "POSSIBLEVALUE"),
    	@JoinColumn(name = "MEASURE", referencedColumnName = "MEASURE", updatable=false, insertable=false)
    })
    @ManyToOne
    private Possiblevaluesdetail possiblevaluesdetail;

    public Measure() {
    }

    public Measure(Long measure) {
        this.measure = measure;
    }

    /*public Measure(Long measure, Timestamp version) {
        this.measure = measure;
        this.version = version;
    }*/

    public Long getMeasure() {
        return measure;
    }

    public void setMeasure(Long measure) {
        this.measure = measure;
    }

    public BigInteger getLimsmeasureid() {
        return limsmeasureid;
    }

    public void setLimsmeasureid(BigInteger limsmeasureid) {
        this.limsmeasureid = limsmeasureid;
    }

    public BigInteger getLimsanalyseid() {
        return limsanalyseid;
    }

    public void setLimsanalyseid(BigInteger limsanalyseid) {
        this.limsanalyseid = limsanalyseid;
    }

    public String getMeasurename() {
        return measurename;
    }

    public void setMeasurename(String measurename) {
        this.measurename = measurename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOfficialname() {
        return officialname;
    }

    public void setOfficialname(String officialname) {
        this.officialname = officialname;
    }

    public String getPrintname() {
        return printname;
    }

    public void setPrintname(String printname) {
        this.printname = printname;
    }

    public Boolean getIsprintable() {
        return isprintable;
    }

    public void setIsprintable(Boolean isprintable) {
        this.isprintable = isprintable;
    }

    public String getDefaultvalue() {
        return defaultvalue;
    }

    public void setDefaultvalue(String defaultvalue) {
        this.defaultvalue = defaultvalue;
    }

    /*public Date getVersion() {
        return version;
    }

    public void setVersion(Timestamp version) {
        this.version = version;
    }*/

    @XmlTransient
    public List<Specificationdetail> getSpecificationdetails() {
        return specificationdetails;
    }

    public void setSpecificationdetails(List<Specificationdetail> specificationdetails) {
        this.specificationdetails = specificationdetails;
    }

    @XmlTransient
    public List<Methodedetail> getMethodedetails() {
        return methodedetails;
    }

    public void setMethodedetails(List<Methodedetail> methodedetails) {
        this.methodedetails = methodedetails;
    }

    @XmlTransient
    public List<Resultat> getResultats() {
        return resultats;
    }

    public void setResultats(List<Resultat> resultats) {
        this.resultats = resultats;
    }

    @XmlTransient
    public List<Possiblevaluesdetail> getPossiblevaluesdetails() {
        return possiblevaluesdetails;
    }

    public void setPossiblevaluesdetails(List<Possiblevaluesdetail> possiblevaluesdetails) {
        this.possiblevaluesdetails = possiblevaluesdetails;
    }

    public Possiblevaluesdetail getPossiblevaluesdetail() {
        return possiblevaluesdetail;
    }

    public void setPossiblevalue(Possiblevaluesdetail possiblevaluesdetail) {
        this.possiblevaluesdetail = possiblevaluesdetail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (measure != null ? measure.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Measure)) {
            return false;
        }
        Measure other = (Measure) object;
        if ((this.measure == null && other.measure != null) || (this.measure != null && !this.measure.equals(other.measure))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Measure[ measure=" + measure + " ]";
    }
    
}
