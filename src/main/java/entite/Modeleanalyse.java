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
    @NamedQuery(name = "Modeleanalyse.findAll", query = "SELECT m FROM Modeleanalyse m"),
    @NamedQuery(name = "Modeleanalyse.findByModeleanalyse", query = "SELECT m FROM Modeleanalyse m WHERE m.modeleanalyse = :modeleanalyse"),
    @NamedQuery(name = "Modeleanalyse.findByIdlims", query = "SELECT m FROM Modeleanalyse m WHERE m.idlims = :idlims"),
    @NamedQuery(name = "Modeleanalyse.findByLimsidanalyse", query = "SELECT m FROM Modeleanalyse m WHERE m.limsidanalyse = :limsidanalyse"),
    @NamedQuery(name = "Modeleanalyse.findByCategory", query = "SELECT m FROM Modeleanalyse m WHERE m.category = :category"),
    @NamedQuery(name = "Modeleanalyse.findByDescription", query = "SELECT m FROM Modeleanalyse m WHERE m.description = :description"),
    @NamedQuery(name = "Modeleanalyse.findByAnalysename", query = "SELECT m FROM Modeleanalyse m WHERE m.analysename = :analysename"),
    @NamedQuery(name = "Modeleanalyse.findByReportname", query = "SELECT m FROM Modeleanalyse m WHERE m.reportname = :reportname"),
    @NamedQuery(name = "Modeleanalyse.findByOfficialname", query = "SELECT m FROM Modeleanalyse m WHERE m.officialname = :officialname"),
    @NamedQuery(name = "Modeleanalyse.findByGroupmeasure", query = "SELECT m FROM Modeleanalyse m WHERE m.groupmeasure = :groupmeasure"),
    @NamedQuery(name = "Modeleanalyse.findByGroupalanalyse", query = "SELECT m FROM Modeleanalyse m WHERE m.groupalanalyse = :groupalanalyse"),
    @NamedQuery(name = "Modeleanalyse.findByOfficialanalyse", query = "SELECT m FROM Modeleanalyse m WHERE m.officialanalyse = :officialanalyse"),
    @NamedQuery(name = "Modeleanalyse.findByInternalanalyse", query = "SELECT m FROM Modeleanalyse m WHERE m.internalanalyse = :internalanalyse"),
    @NamedQuery(name = "Modeleanalyse.findByCanbeherited", query = "SELECT m FROM Modeleanalyse m WHERE m.canbeherited = :canbeherited")
    //,@NamedQuery(name = "Modeleanalyse.findByVersion", query = "SELECT m FROM Modeleanalyse m WHERE m.version = :version")
    })
public class Modeleanalyse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(nullable = false)
    private BigInteger modeleanalyse;
    @Size(max = 50)
    @Column(length = 50)
    private String idlims;
    private BigInteger limsidanalyse;
    @Size(max = 50)
    @Column(length = 50)
    private String category;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    @Size(max = 50)
    @Column(length = 50)
    private String analysename;
    @Size(max = 50)
    @Column(length = 50)
    private String reportname;
    @Size(max = 50)
    @Column(length = 50)
    private String officialname;
    @Size(max = 50)
    @Column(length = 50)
    private String groupmeasure;
    @Size(max = 50)
    @Column(length = 50)
    private String groupalanalyse;
    private Boolean officialanalyse;
    private Boolean internalanalyse;
    private Boolean canbeherited;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    //private Date version;*/
    @OneToMany(mappedBy = "mothermodeleanalyse")
    private List<Modeleanalyse> modeleanalyses;
    @JoinColumn(name = "MOTHERMODELEANALYSE", referencedColumnName = "MODELEANALYSE")
    @ManyToOne
    private Modeleanalyse mothermodeleanalyse;
    @OneToMany(mappedBy = "modeleanalyse")
    private List<Analyse> analyses;
    @OneToMany(mappedBy = "modeleanalyse")
    private List<Methode> methodes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modeleanalyse")
    private List<Heritagebygroupofsample> heritagebygroupofsamples;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modeleanalyse")
    private List<Heritagebyspecieorigin> heritagebyspecieorigins;
    @OneToMany(mappedBy = "modeleanalyse")
    private List<Resultat> resultats;

    public Modeleanalyse() {
    }

    public Modeleanalyse(BigInteger modeleanalyse) {
        this.modeleanalyse = modeleanalyse;
    }

    /*public Modeleanalyse(Long modeleanalyse, Date version) {
        this.modeleanalyse = modeleanalyse;
        this.version = version;
    }*/

    public BigInteger getModeleanalyse() {
        return modeleanalyse;
    }

    public void setModeleanalyse(BigInteger modeleanalyse) {
        this.modeleanalyse = modeleanalyse;
    }

    public String getIdlims() {
        return idlims;
    }

    public void setIdlims(String idlims) {
        this.idlims = idlims;
    }

    public BigInteger getLimsidanalyse() {
        return limsidanalyse;
    }

    public void setLimsidanalyse(BigInteger limsidanalyse) {
        this.limsidanalyse = limsidanalyse;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnalysename() {
        return analysename;
    }

    public void setAnalysename(String analysename) {
        this.analysename = analysename;
    }

    public String getReportname() {
        return reportname;
    }

    public void setReportname(String reportname) {
        this.reportname = reportname;
    }

    public String getOfficialname() {
        return officialname;
    }

    public void setOfficialname(String officialname) {
        this.officialname = officialname;
    }

    public String getGroupmeasure() {
        return groupmeasure;
    }

    public void setGroupmeasure(String groupmeasure) {
        this.groupmeasure = groupmeasure;
    }

    public String getGroupalanalyse() {
        return groupalanalyse;
    }

    public void setGroupalanalyse(String groupalanalyse) {
        this.groupalanalyse = groupalanalyse;
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

    public Boolean getCanbeherited() {
        return canbeherited;
    }

    public void setCanbeherited(Boolean canbeherited) {
        this.canbeherited = canbeherited;
    }

    /*public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }*/

    @XmlTransient
    public List<Modeleanalyse> getModeleanalyses() {
        return modeleanalyses;
    }

    public void setModeleanalyses(List<Modeleanalyse> modeleanalyses) {
        this.modeleanalyses = modeleanalyses;
    }

    public Modeleanalyse getMothermodeleanalyse() {
        return mothermodeleanalyse;
    }

    public void setMothermodeleanalyse(Modeleanalyse mothermodeleanalyse) {
        this.mothermodeleanalyse = mothermodeleanalyse;
    }

    @XmlTransient
    public List<Analyse> getAnalyses() {
        return analyses;
    }

    public void setAnalyses(List<Analyse> analyses) {
        this.analyses = analyses;
    }

    @XmlTransient
    public List<Methode> getMethodes() {
        return methodes;
    }

    public void setMethodes(List<Methode> methodes) {
        this.methodes = methodes;
    }

    @XmlTransient
    public List<Heritagebygroupofsample> getHeritagebygroupofsamples() {
        return heritagebygroupofsamples;
    }

    public void setHeritagebygroupofsamples(List<Heritagebygroupofsample> heritagebygroupofsamples) {
        this.heritagebygroupofsamples = heritagebygroupofsamples;
    }

    @XmlTransient
    public List<Heritagebyspecieorigin> getHeritagebyspecieorigins() {
        return heritagebyspecieorigins;
    }

    public void setHeritagebyspecieorigins(List<Heritagebyspecieorigin> heritagebyspecieorigins) {
        this.heritagebyspecieorigins = heritagebyspecieorigins;
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
        hash += (modeleanalyse != null ? modeleanalyse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modeleanalyse)) {
            return false;
        }
        Modeleanalyse other = (Modeleanalyse) object;
        if ((this.modeleanalyse == null && other.modeleanalyse != null) || (this.modeleanalyse != null && !this.modeleanalyse.equals(other.modeleanalyse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Modeleanalyse[ modeleanalyse=" + modeleanalyse + " ]";
    }
    
}
