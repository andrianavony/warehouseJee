/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import java.math.BigInteger;
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
    @NamedQuery(name = "Methode.findAll", query = "SELECT m FROM Methode m"),
    @NamedQuery(name = "Methode.findByMethode", query = "SELECT m FROM Methode m WHERE m.methode = :methode"),
    @NamedQuery(name = "Methode.findByLimsmethodeid", query = "SELECT m FROM Methode m WHERE m.limsmethodeid = :limsmethodeid"),
    @NamedQuery(name = "Methode.findByLimsanalyseid", query = "SELECT m FROM Methode m WHERE m.limsanalyseid = :limsanalyseid"),
    @NamedQuery(name = "Methode.findByDepartement", query = "SELECT m FROM Methode m WHERE m.departement = :departement"),
    @NamedQuery(name = "Methode.findByService", query = "SELECT m FROM Methode m WHERE m.service = :service"),
    @NamedQuery(name = "Methode.findByMethodename", query = "SELECT m FROM Methode m WHERE m.methodename = :methodename"),
    @NamedQuery(name = "Methode.findByDuration", query = "SELECT m FROM Methode m WHERE m.duration = :duration"),
    @NamedQuery(name = "Methode.findByDescription", query = "SELECT m FROM Methode m WHERE m.description = :description"),
    @NamedQuery(name = "Methode.findByOfficialname", query = "SELECT m FROM Methode m WHERE m.officialname = :officialname"),
    @NamedQuery(name = "Methode.findByIsvirtual", query = "SELECT m FROM Methode m WHERE m.isvirtual = :isvirtual"),
    @NamedQuery(name = "Methode.findByIsdefault", query = "SELECT m FROM Methode m WHERE m.isdefault = :isdefault"),
    @NamedQuery(name = "Methode.findByOfficialanalyse", query = "SELECT m FROM Methode m WHERE m.officialanalyse = :officialanalyse"),
    @NamedQuery(name = "Methode.findByInternalanalyse", query = "SELECT m FROM Methode m WHERE m.internalanalyse = :internalanalyse")
    //,@NamedQuery(name = "Methode.findByVersion", query = "SELECT m FROM Methode m WHERE m.version = :version")
    })
public class Methode implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(nullable = false)
    private BigInteger methode;
    private BigInteger limsmethodeid;
    private BigInteger limsanalyseid;
    @Size(max = 50)
    @Column(length = 50)
    private String departement;
    @Size(max = 50)
    @Column(length = 50)
    private String service;
    @Size(max = 50)
    @Column(length = 50)
    private String methodename;
    @Size(max = 50)
    @Column(length = 50)
    private String duration;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    @Size(max = 50)
    @Column(length = 50)
    private String officialname;
    private Boolean isvirtual;
    private Boolean isdefault;
    private Boolean officialanalyse;
    private Boolean internalanalyse;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    //private Date version;*/
    @OneToMany(mappedBy = "methode")
    private List<Specificationdetail> specificationdetails;
    @OneToMany(mappedBy = "methode")
    private List<Analyse> analyses;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "methode")
    private List<Methodedetail> methodedetails;
    @OneToMany(mappedBy = "submethode")
    private List<Methodedetail> methodedetailsSubmethode;
    @OneToMany(mappedBy = "mother")
    private List<Methode> methodes;
    @JoinColumn(name = "MOTHER", referencedColumnName = "METHODE")
    @ManyToOne
    private Methode mother;
    @JoinColumn(name = "MODELEANALYSE", referencedColumnName = "MODELEANALYSE")
    @ManyToOne
    private Modeleanalyse modeleanalyse;
    @OneToMany(mappedBy = "methode")
    private List<Resultat> resultats;

    public Methode() {
    }

    public Methode(BigInteger methode) {
        this.methode = methode;
    }

    /*public Methode(Long methode, Date version) {
        this.methode = methode;
        this.version = version;
    }
*/
    public BigInteger getMethode() {
        return methode;
    }

    public void setMethode(BigInteger methode) {
        this.methode = methode;
    }

    public BigInteger getLimsmethodeid() {
        return limsmethodeid;
    }

    public void setLimsmethodeid(BigInteger limsmethodeid) {
        this.limsmethodeid = limsmethodeid;
    }

    public BigInteger getLimsanalyseid() {
        return limsanalyseid;
    }

    public void setLimsanalyseid(BigInteger limsanalyseid) {
        this.limsanalyseid = limsanalyseid;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMethodename() {
        return methodename;
    }

    public void setMethodename(String methodename) {
        this.methodename = methodename;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

    public Boolean getIsvirtual() {
        return isvirtual;
    }

    public void setIsvirtual(Boolean isvirtual) {
        this.isvirtual = isvirtual;
    }

    public Boolean getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Boolean isdefault) {
        this.isdefault = isdefault;
    }

    public Boolean getOfficialanalyse() {
        return officialanalyse;
    }

    public void setOfficialanalyse(Boolean officialanalyse) {
        this.officialanalyse = officialanalyse;
    }

    public Boolean getInternalanalyse() {
        return internalanalyse;
    }

    public void setInternalanalyse(Boolean internalanalyse) {
        this.internalanalyse = internalanalyse;
    }

    /*public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
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
    public List<Analyse> getAnalyses() {
        return analyses;
    }

    public void setAnalyses(List<Analyse> analyses) {
        this.analyses = analyses;
    }

    @XmlTransient
    public List<Methodedetail> getMethodedetails() {
        return methodedetails;
    }

    public void setMethodedetails(List<Methodedetail> methodedetails) {
        this.methodedetails = methodedetails;
    }

    @XmlTransient
    public List<Methodedetail> getMethodedetailsSubmethode() {
        return methodedetailsSubmethode;
    }

    public void setMethodedetailsSubmethode(List<Methodedetail> methodedetailsSubmethode) {
        this.methodedetailsSubmethode = methodedetailsSubmethode;
    }

    @XmlTransient
    public List<Methode> getMethodes() {
        return methodes;
    }

    public void setMethodes(List<Methode> methodes) {
        this.methodes = methodes;
    }

    public Methode getMother() {
        return mother;
    }

    public void setMother(Methode mother) {
        this.mother = mother;
    }

    public Modeleanalyse getModeleanalyse() {
        return modeleanalyse;
    }

    public void setModeleanalyse(Modeleanalyse modeleanalyse) {
        this.modeleanalyse = modeleanalyse;
    }

    @XmlTransient
    public List<Resultat> getResultats() {
        return resultats;
    }

    public void setResultats(List<Resultat> resultats) {
        this.resultats = resultats;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (methode != null ? methode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Methode)) {
            return false;
        }
        Methode other = (Methode) object;
        if ((this.methode == null && other.methode != null) || (this.methode != null && !this.methode.equals(other.methode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Methodee[ methode=" + methode + " ]";
    }
    
}
