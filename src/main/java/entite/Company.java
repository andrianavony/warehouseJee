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
import javax.persistence.CascadeType;
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
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c"),
    @NamedQuery(name = "Company.findByCompany", query = "SELECT c FROM Company c WHERE c.company = :company"),
    @NamedQuery(name = "Company.findByCompanyname", query = "SELECT c FROM Company c WHERE c.companyname = :companyname"),
    @NamedQuery(name = "Company.findByDescription", query = "SELECT c FROM Company c WHERE c.description = :description")
  //,@NamedQuery(name = "Article.findByVersion", query = "SELECT a FROM Article a WHERE a.version = :version")
})
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String company;
    @Size(max = 50)
    @Column(length = 50)
    private String companyname;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Version
    private Timestamp version;*/
    @OneToMany(mappedBy = "company")
    private List<Trace> traces;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<Wo> wos;
    @OneToMany(mappedBy = "company")
    private List<Batch> batches;

    public Company() {
    }

    public Company(String company) {
        this.company = company;
    }

    /*public Company(String company, Timestamp version) {
        this.company = company;
        this.version = version;
    }*/

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
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

    @XmlTransient
    public List<Wo> getWos() {
        return wos;
    }

    public void setWos(List<Wo> wos) {
        this.wos = wos;
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
        hash += (company != null ? company.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.company == null && other.company != null) || (this.company != null && !this.company.equals(other.company))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Company[ company=" + company + " ]";
    }
    
}
