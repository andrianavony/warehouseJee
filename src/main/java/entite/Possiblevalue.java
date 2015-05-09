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
    @NamedQuery(name = "Possiblevalue.findAll", query = "SELECT p FROM Possiblevalue p"),
    @NamedQuery(name = "Possiblevalue.findByPossiblevalue", query = "SELECT p FROM Possiblevalue p WHERE p.possiblevalue = :possiblevalue"),
    @NamedQuery(name = "Possiblevalue.findByDescription", query = "SELECT p FROM Possiblevalue p WHERE p.description = :description")
    //,@NamedQuery(name = "Methodedetail.findByVersion", query = "SELECT m FROM Methodedetail m WHERE m.version = :version")
    })
public class Possiblevalue implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(nullable = false)
    private BigInteger possiblevalue;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    //private Date version;*/
    @OneToMany(mappedBy = "possiblevalue")
    private List<Methodedetail> methodedetails;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "possiblevalue")
    private List<Possiblevaluesdetail> possiblevaluesdetails;

    public Possiblevalue() {
    }

    public Possiblevalue(BigInteger possiblevalue) {
        this.possiblevalue = possiblevalue;
    }

    /*public Possiblevalue(BigInteger possiblevalue, Date version) {
        this.possiblevalue = possiblevalue;
        this.version = version;
    }*/

    public BigInteger getPossiblevalue() {
        return possiblevalue;
    }

    public void setPossiblevalue(BigInteger possiblevalue) {
        this.possiblevalue = possiblevalue;
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

    public void setVersion(Date version) {
        this.version = version;
    }*/

    @XmlTransient
    public List<Methodedetail> getMethodedetails() {
        return methodedetails;
    }

    public void setMethodedetails(List<Methodedetail> methodedetails) {
        this.methodedetails = methodedetails;
    }

    @XmlTransient
    public List<Possiblevaluesdetail> getPossiblevaluesdetails() {
        return possiblevaluesdetails;
    }

    public void setPossiblevaluesdetails(List<Possiblevaluesdetail> possiblevaluesdetails) {
        this.possiblevaluesdetails = possiblevaluesdetails;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (possiblevalue != null ? possiblevalue.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Possiblevalue)) {
            return false;
        }
        Possiblevalue other = (Possiblevalue) object;
        if ((this.possiblevalue == null && other.possiblevalue != null) || (this.possiblevalue != null && !this.possiblevalue.equals(other.possiblevalue))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Possiblevalues[ possiblevalue=" + possiblevalue + " ]";
    }
    
}
