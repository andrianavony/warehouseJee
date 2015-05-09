/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import java.math.BigInteger;
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
    @NamedQuery(name = "Methodedetail.findAll", query = "SELECT m FROM Methodedetail m"),
    @NamedQuery(name = "Methodedetail.findByMethodedetail", query = "SELECT m FROM Methodedetail m WHERE m.methodedetailPK.methodedetail = :methodedetail"),
    @NamedQuery(name = "Methodedetail.findByMethode", query = "SELECT m FROM Methodedetail m WHERE m.methodedetailPK.methode = :methode"),
    @NamedQuery(name = "Methodedetail.findByLimsmethodename", query = "SELECT m FROM Methodedetail m WHERE m.limsmethodename = :limsmethodename"),
    @NamedQuery(name = "Methodedetail.findByLimsmethodeid", query = "SELECT m FROM Methodedetail m WHERE m.limsmethodeid = :limsmethodeid"),
    @NamedQuery(name = "Methodedetail.findByLimsanalyseid", query = "SELECT m FROM Methodedetail m WHERE m.limsanalyseid = :limsanalyseid"),
    @NamedQuery(name = "Methodedetail.findByLimsmeasureid", query = "SELECT m FROM Methodedetail m WHERE m.limsmeasureid = :limsmeasureid"),
    @NamedQuery(name = "Methodedetail.findByDescription", query = "SELECT m FROM Methodedetail m WHERE m.description = :description"),
    @NamedQuery(name = "Methodedetail.findByDeleted", query = "SELECT m FROM Methodedetail m WHERE m.deleted = :deleted"),
    @NamedQuery(name = "Methodedetail.findByMaingroup", query = "SELECT m FROM Methodedetail m WHERE m.maingroup = :maingroup"),
    @NamedQuery(name = "Methodedetail.findBySubgroup", query = "SELECT m FROM Methodedetail m WHERE m.subgroup = :subgroup"),
    @NamedQuery(name = "Methodedetail.findByModeleanalyse", query = "SELECT m FROM Methodedetail m WHERE m.modeleanalyse = :modeleanalyse"),
    @NamedQuery(name = "Methodedetail.findByMeasuremaster", query = "SELECT m FROM Methodedetail m WHERE m.measuremaster = :measuremaster"),
    @NamedQuery(name = "Methodedetail.findByIsrequired", query = "SELECT m FROM Methodedetail m WHERE m.isrequired = :isrequired"),
    @NamedQuery(name = "Methodedetail.findByDefaultvalue", query = "SELECT m FROM Methodedetail m WHERE m.defaultvalue = :defaultvalue"),
    @NamedQuery(name = "Methodedetail.findByIsvirtual", query = "SELECT m FROM Methodedetail m WHERE m.isvirtual = :isvirtual"),
    @NamedQuery(name = "Methodedetail.findByMeasurevalue", query = "SELECT m FROM Methodedetail m WHERE m.measurevalue = :measurevalue"),
    @NamedQuery(name = "Methodedetail.findByValuemin", query = "SELECT m FROM Methodedetail m WHERE m.valuemin = :valuemin"),
    @NamedQuery(name = "Methodedetail.findByPreferdvaluemin", query = "SELECT m FROM Methodedetail m WHERE m.preferdvaluemin = :preferdvaluemin"),
    @NamedQuery(name = "Methodedetail.findByPreferdvaluemax", query = "SELECT m FROM Methodedetail m WHERE m.preferdvaluemax = :preferdvaluemax"),
    @NamedQuery(name = "Methodedetail.findByValuemax", query = "SELECT m FROM Methodedetail m WHERE m.valuemax = :valuemax"),
    @NamedQuery(name = "Methodedetail.findByValid", query = "SELECT m FROM Methodedetail m WHERE m.valid = :valid"),
    @NamedQuery(name = "Methodedetail.findByRepetition", query = "SELECT m FROM Methodedetail m WHERE m.repetition = :repetition"),
    @NamedQuery(name = "Methodedetail.findBySubrepetition", query = "SELECT m FROM Methodedetail m WHERE m.subrepetition = :subrepetition"),
    @NamedQuery(name = "Methodedetail.findByPrintname", query = "SELECT m FROM Methodedetail m WHERE m.printname = :printname"),
    @NamedQuery(name = "Methodedetail.findByIsprintable", query = "SELECT m FROM Methodedetail m WHERE m.isprintable = :isprintable"),
    @NamedQuery(name = "Methodedetail.findByIsresultat", query = "SELECT m FROM Methodedetail m WHERE m.isresultat = :isresultat")
    //,@NamedQuery(name = "Methodedetail.findByVersion", query = "SELECT m FROM Methodedetail m WHERE m.version = :version")
    })
public class Methodedetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MethodedetailPK methodedetailPK;
    @Size(max = 50)
    @Column(length = 50)
    private String limsmethodename;
    @Size(max = 50)
    @Column(length = 50)
    private String limsmethodeid;
    @Size(max = 50)
    @Column(length = 50)
    private String limsanalyseid;
    @Size(max = 50)
    @Column(length = 50)
    private String limsmeasureid;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    private Boolean deleted;
    @Size(max = 50)
    @Column(length = 50)
    private String maingroup;
    @Size(max = 50)
    @Column(length = 50)
    private String subgroup;
    private BigInteger modeleanalyse;
    @Size(max = 50)
    @Column(length = 50)
    private String measuremaster;
    private Boolean isrequired;
    @Size(max = 50)
    @Column(length = 50)
    private String defaultvalue;
    private Boolean isvirtual;
    @Size(max = 50)
    @Column(length = 50)
    private String measurevalue;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 22)
    private Double valuemin;
    @Column(precision = 22)
    private Double preferdvaluemin;
    @Column(precision = 22)
    private Double preferdvaluemax;
    @Column(precision = 22)
    private Double valuemax;
    private Boolean valid;
    private Short repetition;
    private Short subrepetition;
    @Size(max = 50)
    @Column(length = 50)
    private String printname;
    private Boolean isprintable;
    private Boolean isresultat;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    //private Date version;*/
    @JoinColumn(name = "POSSIBLEVALUE", referencedColumnName = "POSSIBLEVALUE")
    @ManyToOne
    private Possiblevalue possiblevalue;
    @JoinColumn(name = "MEASURE", referencedColumnName = "MEASURE")
    @ManyToOne
    private Measure measure;
    @JoinColumn(name = "METHODE", referencedColumnName = "METHODE", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Methode methode;
    @JoinColumn(name = "SUBMETHODE", referencedColumnName = "METHODE")
    @ManyToOne
    private Methode submethode;

    public Methodedetail() {
    }

    public Methodedetail(MethodedetailPK methodedetailPK) {
        this.methodedetailPK = methodedetailPK;
    }

    /*public Methodedetail(MethodedetailPK methodedetailPK, Date version) {
        this.methodedetailPK = methodedetailPK;
        this.version = version;
    }*/

    public Methodedetail(long methodedetail, long methode) {
        this.methodedetailPK = new MethodedetailPK(methodedetail, methode);
    }

    public MethodedetailPK getMethodedetailPK() {
        return methodedetailPK;
    }

    public void setMethodedetailPK(MethodedetailPK methodedetailPK) {
        this.methodedetailPK = methodedetailPK;
    }

    public String getLimsmethodename() {
        return limsmethodename;
    }

    public void setLimsmethodename(String limsmethodename) {
        this.limsmethodename = limsmethodename;
    }

    public String getLimsmethodeid() {
        return limsmethodeid;
    }

    public void setLimsmethodeid(String limsmethodeid) {
        this.limsmethodeid = limsmethodeid;
    }

    public String getLimsanalyseid() {
        return limsanalyseid;
    }

    public void setLimsanalyseid(String limsanalyseid) {
        this.limsanalyseid = limsanalyseid;
    }

    public String getLimsmeasureid() {
        return limsmeasureid;
    }

    public void setLimsmeasureid(String limsmeasureid) {
        this.limsmeasureid = limsmeasureid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getMaingroup() {
        return maingroup;
    }

    public void setMaingroup(String maingroup) {
        this.maingroup = maingroup;
    }

    public String getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(String subgroup) {
        this.subgroup = subgroup;
    }

    public BigInteger getModeleanalyse() {
        return modeleanalyse;
    }

    public void setModeleanalyse(BigInteger modeleanalyse) {
        this.modeleanalyse = modeleanalyse;
    }

    public String getMeasuremaster() {
        return measuremaster;
    }

    public void setMeasuremaster(String measuremaster) {
        this.measuremaster = measuremaster;
    }

    public Boolean getIsrequired() {
        return isrequired;
    }

    public void setIsrequired(Boolean isrequired) {
        this.isrequired = isrequired;
    }

    public String getDefaultvalue() {
        return defaultvalue;
    }

    public void setDefaultvalue(String defaultvalue) {
        this.defaultvalue = defaultvalue;
    }

    public Boolean getIsvirtual() {
        return isvirtual;
    }

    public void setIsvirtual(Boolean isvirtual) {
        this.isvirtual = isvirtual;
    }

    public String getMeasurevalue() {
        return measurevalue;
    }

    public void setMeasurevalue(String measurevalue) {
        this.measurevalue = measurevalue;
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

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Short getRepetition() {
        return repetition;
    }

    public void setRepetition(Short repetition) {
        this.repetition = repetition;
    }

    public Short getSubrepetition() {
        return subrepetition;
    }

    public void setSubrepetition(Short subrepetition) {
        this.subrepetition = subrepetition;
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

    public Boolean getIsresultat() {
        return isresultat;
    }

    public void setIsresultat(Boolean isresultat) {
        this.isresultat = isresultat;
    }

    /*public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }*/

    public Possiblevalue getPossiblevalue() {
        return possiblevalue;
    }

    public void setPossiblevalue(Possiblevalue possiblevalue) {
        this.possiblevalue = possiblevalue;
    }

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

    public Methode getSubmethode() {
        return submethode;
    }

    public void setSubmethode(Methode submethode) {
        this.submethode = submethode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (methodedetailPK != null ? methodedetailPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Methodedetail)) {
            return false;
        }
        Methodedetail other = (Methodedetail) object;
        if ((this.methodedetailPK == null && other.methodedetailPK != null) || (this.methodedetailPK != null && !this.methodedetailPK.equals(other.methodedetailPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Methodeedetail[ methodedetailPK=" + methodedetailPK + " ]";
    }
    
}
