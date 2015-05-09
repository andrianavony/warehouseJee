/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author S.ANDRIANAVONY
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Traca.findAll", query = "SELECT t FROM Traca t"),
    @NamedQuery(name = "Traca.findByIdcompany", query = "SELECT t FROM Traca t WHERE t.company = :company"),
    @NamedQuery(name = "Traca.findByWarehouse", query = "SELECT t FROM Traca t WHERE t.warehouse = :warehouse"),
    @NamedQuery(name = "Traca.findByIdwo", query = "SELECT t FROM Traca t WHERE t.wo = :wo"),
    @NamedQuery(name = "Traca.findByTracetype", query = "SELECT t FROM Traca t WHERE t.tracetype = :tracetype"),
    @NamedQuery(name = "Traca.findByMgrquantity", query = "SELECT t FROM Traca t WHERE t.mgrquantity = :mgrquantity"),
    @NamedQuery(name = "Traca.findByMgvquantity", query = "SELECT t FROM Traca t WHERE t.mgvquantity = :mgvquantity"),
    @NamedQuery(name = "Traca.findByKgquantity", query = "SELECT t FROM Traca t WHERE t.kgquantity = :kgquantity"),
    @NamedQuery(name = "Traca.findByUnits", query = "SELECT t FROM Traca t WHERE t.units = :units"),
    @NamedQuery(name = "Traca.findByIdarticle", query = "SELECT t FROM Traca t WHERE t.article = :article"),
    @NamedQuery(name = "Traca.findByBatchname", query = "SELECT t FROM Traca t WHERE t.batchname = :batchname"),
    @NamedQuery(name = "Traca.findByYear", query = "SELECT t FROM Traca t WHERE t.year = :year"),
    @NamedQuery(name = "Traca.findByBassin", query = "SELECT t FROM Traca t WHERE t.bassin = :bassin"),
    @NamedQuery(name = "Traca.findByProducteur", query = "SELECT t FROM Traca t WHERE t.producteur = :producteur"),
    @NamedQuery(name = "Traca.findByContrat", query = "SELECT t FROM Traca t WHERE t.contrat = :contrat"),
    @NamedQuery(name = "Traca.findByStatustraca", query = "SELECT t FROM Traca t WHERE t.statustraca = :statustraca"),
    @NamedQuery(name = "Traca.findByIdtraca", query = "SELECT t FROM Traca t WHERE t.idtraca = :idtraca"),
    @NamedQuery(name = "Traca.findTracaLogged", query = "SELECT t FROM Traca t WHERE t.statustraca = 1 ")
    ,@NamedQuery(name = "Traca.findByIdwoTracetype", query = "SELECT t FROM Traca t WHERE t.wo = :wo and t.tracetype = :tracetype")
    ,@NamedQuery(name = "Traca.findByIdwo_Production", query = "SELECT t FROM Traca t WHERE t.wo = :wo and t.company = :company and t.tracetype = 'Production' ")    
})
public class Traca implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 50)
    @Column(length = 50)
    private String company;
    @Size(max = 50)
    @Column(length = 50)
    private String warehouse;
    @ManyToOne()
    @JoinColumns({
        @JoinColumn(name = "WO", referencedColumnName = "WO"),
        @JoinColumn(name = "COMPANY", referencedColumnName = "COMPANY", insertable = false, updatable = false)    
    })
    private Wo wo;
    @Size(max = 50)
    @Column(length = 50)
    private String tracetype;
    @Size(max = 50)
    @Column(length = 50)
    private String mgrquantity;
    @Size(max = 50)
    @Column(length = 50)
    private String mgvquantity;
    @Size(max = 50)
    @Column(length = 50)
    private String kgquantity;
    @Size(max = 50)
    @Column(length = 50)
    private String units;
    @Size(max = 50)
    @Column(length = 50)
    private String article;
    @Size(max = 50)
    @Column(length = 50)
    private String batchname;
    @Size(max = 50)
    @Column(length = 50)
    private String year;
    @Size(max = 50)
    @Column(length = 50)
    private String bassin;
    @Size(max = 50)
    @Column(length = 50)
    private String producteur;
    @Size(max = 50)
    @Column(length = 50)
    private String contrat;
    private Short statustraca;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(nullable = false)
    private BigInteger idtraca;

    public Traca() {
    }

    public Traca(BigInteger idtraca) {
        this.idtraca = idtraca;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public Wo getWo() {
        return wo;
    }

    public void setWo(Wo wo) {
        this.wo = wo;
    }

    public String getTracetype() {
        return tracetype;
    }

    public void setTracetype(String tracetype) {
        this.tracetype = tracetype;
    }

    public String getMgrquantity() {
        return mgrquantity;
    }

    public void setMgrquantity(String mgrquantity) {
        this.mgrquantity = mgrquantity;
    }

    public String getMgvquantity() {
        return mgvquantity;
    }

    public void setMgvquantity(String mgvquantity) {
        this.mgvquantity = mgvquantity;
    }

    public String getKgquantity() {
        return kgquantity;
    }

    public void setKgquantity(String kgquantity) {
        this.kgquantity = kgquantity;
    }

    public String getUnits() {
        return units;
    }

    public void setUnite(String units) {
        this.units = units;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getBatchname() {
        return batchname;
    }

    public void setBatchname(String batchname) {
        this.batchname = batchname;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getBassin() {
        return bassin;
    }

    public void setBassin(String bassin) {
        this.bassin = bassin;
    }

    public String getProducteur() {
        return producteur;
    }

    public void setProducteur(String producteur) {
        this.producteur = producteur;
    }

    public String getContrat() {
        return contrat;
    }

    public void setContrat(String contrat) {
        this.contrat = contrat;
    }

    public Short getStatuttraca() {
        return statustraca;
    }

    public void setStatuttraca(Short statustraca) {
        this.statustraca = statustraca;
    }

    public BigInteger getIdtraca() {
        return idtraca;
    }

    public void setIdtraca(BigInteger idtraca) {
        this.idtraca = idtraca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtraca != null ? idtraca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Traca)) {
            return false;
        }
        Traca other = (Traca) object;
        if ((this.idtraca == null && other.idtraca != null) || (this.idtraca != null && !this.idtraca.equals(other.idtraca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Traca[ idtraca=" + idtraca + " ]";
    }
    
}
