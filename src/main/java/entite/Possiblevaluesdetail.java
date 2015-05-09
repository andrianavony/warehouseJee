/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
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
    @NamedQuery(name = "Possiblevaluesdetail.findAll", query = "SELECT p FROM Possiblevaluesdetail p"),
    @NamedQuery(name = "Possiblevaluesdetail.findByMeasure", query = "SELECT p FROM Possiblevaluesdetail p WHERE p.possiblevaluesdetailPK.measure = :measure"),
    @NamedQuery(name = "Possiblevaluesdetail.findByPossiblevalue", query = "SELECT p FROM Possiblevaluesdetail p WHERE p.possiblevaluesdetailPK.possiblevalue = :possiblevalue"),
    @NamedQuery(name = "Possiblevaluesdetail.findByRawresultat", query = "SELECT p FROM Possiblevaluesdetail p WHERE p.rawresultat = :rawresultat"),
    @NamedQuery(name = "Possiblevaluesdetail.findByPossiblevaluename", query = "SELECT p FROM Possiblevaluesdetail p WHERE p.possiblevaluename = :possiblevaluename"),
    @NamedQuery(name = "Possiblevaluesdetail.findByDescription", query = "SELECT p FROM Possiblevaluesdetail p WHERE p.description = :description"),
    @NamedQuery(name = "Possiblevaluesdetail.findByDefaultstatut", query = "SELECT p FROM Possiblevaluesdetail p WHERE p.defaultstatut = :defaultstatut")
    //,@NamedQuery(name = "Possiblevaluesdetail.findByVersion", query = "SELECT p FROM Possiblevaluesdetail p WHERE p.version = :version")
    })
public class Possiblevaluesdetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PossiblevaluesdetailPK possiblevaluesdetailPK;
    @Size(max = 50)
    @Column(length = 50)
    private String rawresultat;
    @Size(max = 50)
    @Column(length = 50)
    private String possiblevaluename;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    private Short defaultstatut;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)*/
    //private Date version;
    @JoinColumn(name = "POSSIBLEVALUE", referencedColumnName = "POSSIBLEVALUE", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Possiblevalue possiblevalue;
    @JoinColumn(name = "MEASURE", referencedColumnName = "MEASURE", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Measure measure;
    //@OneToMany(mappedBy = "possiblevaluesdetails")
    private List<Measure> measures;

    public Possiblevaluesdetail() {
    }

    public Possiblevaluesdetail(PossiblevaluesdetailPK possiblevaluesdetailPK) {
        this.possiblevaluesdetailPK = possiblevaluesdetailPK;
    }

    /*public Possiblevaluesdetail(PossiblevaluesdetailPK possiblevaluesdetailPK, Date version) {
        this.possiblevaluesdetailPK = possiblevaluesdetailPK;
        this.version = version;
    }*/

    public Possiblevaluesdetail(long measure, long possiblevalue) {
        this.possiblevaluesdetailPK = new PossiblevaluesdetailPK(measure, possiblevalue);
    }

    public PossiblevaluesdetailPK getPossiblevaluesdetailPK() {
        return possiblevaluesdetailPK;
    }

    public void setPossiblevaluesdetailPK(PossiblevaluesdetailPK possiblevaluesdetailPK) {
        this.possiblevaluesdetailPK = possiblevaluesdetailPK;
    }

    public String getRawresultat() {
        return rawresultat;
    }

    public void setRawresultat(String rawresultat) {
        this.rawresultat = rawresultat;
    }

    public String getPossiblevaluename() {
        return possiblevaluename;
    }

    public void setPossiblevaluename(String possiblevaluename) {
        this.possiblevaluename = possiblevaluename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getDefaultstatut() {
        return defaultstatut;
    }

    public void setDefaultstatut(Short defaultstatut) {
        this.defaultstatut = defaultstatut;
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

    @XmlTransient
    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (possiblevaluesdetailPK != null ? possiblevaluesdetailPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Possiblevaluesdetail)) {
            return false;
        }
        Possiblevaluesdetail other = (Possiblevaluesdetail) object;
        if ((this.possiblevaluesdetailPK == null && other.possiblevaluesdetailPK != null) || (this.possiblevaluesdetailPK != null && !this.possiblevaluesdetailPK.equals(other.possiblevaluesdetailPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Possiblevaluesdetail[ possiblevaluesdetailPK=" + possiblevaluesdetailPK + " ]";
    }
    
}
