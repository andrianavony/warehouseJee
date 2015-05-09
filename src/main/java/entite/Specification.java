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
    @NamedQuery(name = "Specification.findAll", query = "SELECT s FROM Specification s"),
    @NamedQuery(name = "Specification.findBySpecification", query = "SELECT s FROM Specification s WHERE s.specification = :specification"),
    @NamedQuery(name = "Specification.findByModeleanalyse", query = "SELECT s FROM Specification s WHERE s.modeleanalyse = :modeleanalyse"),
    @NamedQuery(name = "Specification.findByDescription", query = "SELECT s FROM Specification s WHERE s.description = :description")
  //,@NamedQuery(name = "Article.findByVersion", query = "SELECT a FROM Article a WHERE a.version = :version")
})
public class Specification implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long specification;
    private BigInteger modeleanalyse;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Version
    private Timestamp version;*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "specification")
    private List<Specificationdetail> specificationdetails;
    @OneToMany(mappedBy = "specification")
    private List<Analyse> analyses;
    @OneToMany(mappedBy = "specification")
    private List<Resultat> resultats;

    public Specification() {
    }

    public Specification(Long specification) {
        this.specification = specification;
    }

    /*public Specification(Long specification, Timestamp version) {
        this.specification = specification;
        this.version = version;
    }*/

    public Long getSpecification() {
        return specification;
    }

    public void setSpecification(Long specification) {
        this.specification = specification;
    }

    public BigInteger getModeleanalyse() {
        return modeleanalyse;
    }

    public void setModeleanalyse(BigInteger modeleanalyse) {
        this.modeleanalyse = modeleanalyse;
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
    public List<Resultat> getResultats() {
        return resultats;
    }

    public void setResultats(List<Resultat> resultats) {
        this.resultats = resultats;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (specification != null ? specification.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Specification)) {
            return false;
        }
        Specification other = (Specification) object;
        if ((this.specification == null && other.specification != null) || (this.specification != null && !this.specification.equals(other.specification))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Specification[ specification=" + specification + " ]";
    }
    
}
