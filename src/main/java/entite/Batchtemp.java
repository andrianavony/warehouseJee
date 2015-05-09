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
import javax.persistence.Entity;
import javax.persistence.Id;
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
    @NamedQuery(name = "Batchtemp.findAll", query = "SELECT b FROM Batchtemp b"),
    @NamedQuery(name = "Batchtemp.findByBatch", query = "SELECT b FROM Batchtemp b WHERE b.batch = :batch"),
    @NamedQuery(name = "Batchtemp.findByBatchname", query = "SELECT b FROM Batchtemp b WHERE b.batchname = :batchname"),
    @NamedQuery(name = "Batchtemp.findByDescription", query = "SELECT b FROM Batchtemp b WHERE b.description = :description"),
    @NamedQuery(name = "Batchtemp.findByArticle", query = "SELECT b FROM Batchtemp b WHERE b.article = :article"),
    @NamedQuery(name = "Batchtemp.findByStage", query = "SELECT b FROM Batchtemp b WHERE b.stage = :stage"),
    @NamedQuery(name = "Batchtemp.findBySpecie", query = "SELECT b FROM Batchtemp b WHERE b.specie = :specie"),
    @NamedQuery(name = "Batchtemp.findByVariety", query = "SELECT b FROM Batchtemp b WHERE b.variety = :variety"),
    @NamedQuery(name = "Batchtemp.findByGeneration", query = "SELECT b FROM Batchtemp b WHERE b.generation = :generation"),
    @NamedQuery(name = "Batchtemp.findByProdgroup", query = "SELECT b FROM Batchtemp b WHERE b.prodgroup = :prodgroup"),
    @NamedQuery(name = "Batchtemp.findByPea", query = "SELECT b FROM Batchtemp b WHERE b.pea = :pea"),
    @NamedQuery(name = "Batchtemp.findByOrigin1", query = "SELECT b FROM Batchtemp b WHERE b.origin1 = :origin1"),
    @NamedQuery(name = "Batchtemp.findByOrigin2", query = "SELECT b FROM Batchtemp b WHERE b.origin2 = :origin2"),
    @NamedQuery(name = "Batchtemp.findByDiagram", query = "SELECT b FROM Batchtemp b WHERE b.diagram = :diagram"),
    @NamedQuery(name = "Batchtemp.findByCaliber", query = "SELECT b FROM Batchtemp b WHERE b.caliber = :caliber"),
    @NamedQuery(name = "Batchtemp.findByWo", query = "SELECT b FROM Batchtemp b WHERE b.wo = :wo"),
    @NamedQuery(name = "Batchtemp.findByQuantity", query = "SELECT b FROM Batchtemp b WHERE b.quantity = :quantity"),
    @NamedQuery(name = "Batchtemp.findByUnits", query = "SELECT b FROM Batchtemp b WHERE b.units = :units"),
    @NamedQuery(name = "Batchtemp.findByLimsbatchid", query = "SELECT b FROM Batchtemp b WHERE b.limsbatchid = :limsbatchid"),
    @NamedQuery(name = "Batchtemp.findByCompany", query = "SELECT b FROM Batchtemp b WHERE b.company = :company"),
    @NamedQuery(name = "Batchtemp.findByContract", query = "SELECT b FROM Batchtemp b WHERE b.contract = :contract"),
    @NamedQuery(name = "Batchtemp.findByTreatement", query = "SELECT b FROM Batchtemp b WHERE b.treatement = :treatement"),
    @NamedQuery(name = "Batchtemp.findByLimsfolderno", query = "SELECT b FROM Batchtemp b WHERE b.limsfolderno = :limsfolderno"),
    @NamedQuery(name = "Batchtemp.findByNumdemandelims", query = "SELECT b FROM Batchtemp b WHERE b.numdemandelims = :numdemandelims")
  //,@NamedQuery(name = "Article.findByVersion", query = "SELECT a FROM Article a WHERE a.version = :version")
})
public class Batchtemp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long batch;
    @Size(max = 50)
    @Column(length = 50)
    private String batchname;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    @Size(max = 50)
    @Column(length = 50)
    private String article;
    @Size(max = 50)
    @Column(length = 50)
    private String stage;
    @Size(max = 50)
    @Column(length = 50)
    private String specie;
    @Size(max = 50)
    @Column(length = 50)
    private String variety;
    @Size(max = 50)
    @Column(length = 50)
    private String generation;
    @Size(max = 50)
    @Column(length = 50)
    private String prodgroup;
    @Size(max = 50)
    @Column(length = 50)
    private String pea;
    @Size(max = 50)
    @Column(length = 50)
    private String origin1;
    @Size(max = 50)
    @Column(length = 50)
    private String origin2;
    @Size(max = 50)
    @Column(length = 50)
    private String diagram;
    @Size(max = 50)
    @Column(length = 50)
    private String caliber;
    @Size(max = 50)
    @Column(length = 50)
    private String wo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 22)
    private Double quantity;
    @Size(max = 50)
    @Column(length = 50)
    private String units;
    private BigInteger limsbatchid;
    @Size(max = 50)
    @Column(length = 50)
    private String company;
    @Size(max = 50)
    @Column(length = 50)
    private String contract;
    @Size(max = 50)
    @Column(length = 50)
    private String treatement;
    @Size(max = 50)
    @Column(length = 50)
    private String limsfolderno;
    private Short numdemandelims;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Version
    private Timestamp version;*/

    public Batchtemp() {
    }

    public Batchtemp(Long batch) {
        this.batch = batch;
    }

    /*public Batchtemp(Long batch, Timestamp version) {
        this.batch = batch;
        this.version = version;
    }*/

    public Long getBatch() {
        return batch;
    }

    public void setBatch(Long batch) {
        this.batch = batch;
    }

    public String getBatchname() {
        return batchname;
    }

    public void setBatchname(String batchname) {
        this.batchname = batchname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getProdgroup() {
        return prodgroup;
    }

    public void setProdgroup(String prodgroup) {
        this.prodgroup = prodgroup;
    }

    public String getPea() {
        return pea;
    }

    public void setPea(String pea) {
        this.pea = pea;
    }

    public String getOrigin1() {
        return origin1;
    }

    public void setOrigin1(String origin1) {
        this.origin1 = origin1;
    }

    public String getOrigin2() {
        return origin2;
    }

    public void setOrigin2(String origin2) {
        this.origin2 = origin2;
    }

    public String getDiagram() {
        return diagram;
    }

    public void setDiagram(String diagram) {
        this.diagram = diagram;
    }

    public String getCaliber() {
        return caliber;
    }

    public void setCaliber(String caliber) {
        this.caliber = caliber;
    }

    public String getWo() {
        return wo;
    }

    public void setWo(String wo) {
        this.wo = wo;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnits() {
        return units;
    }

    public void setUnite(String units) {
        this.units = units;
    }

    public BigInteger getLimsbatchid() {
        return limsbatchid;
    }

    public void setLimsbatchid(BigInteger limsbatchid) {
        this.limsbatchid = limsbatchid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getTreatement() {
        return treatement;
    }

    public void setTreatement(String treatement) {
        this.treatement = treatement;
    }

    public String getLimsfolderno() {
        return limsfolderno;
    }

    public void setLimsfolderno(String limsfolderno) {
        this.limsfolderno = limsfolderno;
    }

    public Short getNumdemandelims() {
        return numdemandelims;
    }

    public void setNumdemandelims(Short numdemandelims) {
        this.numdemandelims = numdemandelims;
    }

    /*public Date getVersion() {
        return version;
    }

    public void setVersion(Timestamp version) {
        this.version = version;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (batch != null ? batch.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Batchtemp)) {
            return false;
        }
        Batchtemp other = (Batchtemp) object;
        if ((this.batch == null && other.batch != null) || (this.batch != null && !this.batch.equals(other.batch))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Batchtemp[ batch=" + batch + " ]";
    }
    
}
