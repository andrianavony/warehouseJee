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
    @NamedQuery(name = "Trace.findAll", query = "SELECT t FROM Trace t"),
    @NamedQuery(name = "Trace.findByTrace", query = "SELECT t FROM Trace t WHERE t.trace = :trace"),
    @NamedQuery(name = "Trace.findByTracetype", query = "SELECT t FROM Trace t WHERE t.tracetype = :tracetype"),
    @NamedQuery(name = "Trace.findByQuantity", query = "SELECT t FROM Trace t WHERE t.quantity = :quantity"),
    @NamedQuery(name = "Trace.findByUnite", query = "SELECT t FROM Trace t WHERE t.unite = :unite"),
    @NamedQuery(name = "Trace.findByDescription", query = "SELECT t FROM Trace t WHERE t.description = :description"),
    @NamedQuery(name = "Trace.findByWarehouse", query = "SELECT t FROM Trace t WHERE t.warehouse = :warehouse")
  //,@NamedQuery(name = "Article.findByVersion", query = "SELECT a FROM Article a WHERE a.version = :version")
})
public class Trace implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long trace;
    @Size(max = 50)
    @Column(length = 50)
    private String tracetype;
    @Size(max = 50)
    @Column(length = 50)
    private Double quantity;
    @Size(max = 50)
    @Column(length = 50)
    private String unite;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    @Size(max = 50)
    @Column(length = 50)
    private String warehouse;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Version
    private Timestamp version;*/
    @JoinColumn(name = "COMPANY", referencedColumnName = "COMPANY", insertable=false, updatable=false)
    @ManyToOne
    private Company company;
    @JoinColumn(name = "ARTICLE", referencedColumnName = "ARTICLE")
    @ManyToOne
    private Article article;
    @JoinColumn(name = "BATCHNAME", referencedColumnName = "BATCHNAME")
    @ManyToOne
    private Batch batchname;
    @JoinColumns({    
    	@JoinColumn(name = "WO", referencedColumnName = "WO"),
    	@JoinColumn(name = "COMPANY", referencedColumnName = "COMPANY")
    })
    @ManyToOne
    private Wo wo;
    @OneToMany(mappedBy = "trace")
    private List<Batch> batches;

    public Trace() {
    }

    public Trace(Long trace) {
        this.trace = trace;
    }

    /*public Trace(Long trace, Timestamp version) {
        this.trace = trace;
        this.version = version;
    }*/

    public Long getTrace() {
        return trace;
    }

    public void setTrace(Long trace) {
        this.trace = trace;
    }

    public String getTracetype() {
        return tracetype;
    }

    public void setTracetype(String tracetype) {
        this.tracetype = tracetype;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    /*public Date getVersion() {
        return version;
    }

    public void setVersion(Timestamp version) {
        this.version = version;
    }*/

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Batch getBatchname() {
        return batchname;
    }

    public void setBatchname(Batch batchname) {
        this.batchname = batchname;
    }

    public Wo getWo() {
        return wo;
    }

    public void setWo(Wo wo) {
        this.wo = wo;
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
        hash += (trace != null ? trace.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trace)) {
            return false;
        }
        Trace other = (Trace) object;
        if ((this.trace == null && other.trace != null) || (this.trace != null && !this.trace.equals(other.trace))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Trace[ trace=" + trace + " ]";
    }
    
}
