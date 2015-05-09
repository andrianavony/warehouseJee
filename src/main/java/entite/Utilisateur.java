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
    @NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u"),
    @NamedQuery(name = "Utilisateur.findByUtilisateur", query = "SELECT u FROM Utilisateur u WHERE u.utilisateur = :utilisateur"),
    @NamedQuery(name = "Utilisateur.findByUtilisateurname", query = "SELECT u FROM Utilisateur u WHERE u.utilisateurname = :utilisateurname"),
    @NamedQuery(name = "Utilisateur.findByUtilisateurlims", query = "SELECT u FROM Utilisateur u WHERE u.utilisateurlims = :utilisateurlims"),
    @NamedQuery(name = "Utilisateur.findByShortname", query = "SELECT u FROM Utilisateur u WHERE u.shortname = :shortname")
  //,@NamedQuery(name = "Sample.findByVersion", query = "SELECT s FROM Sample s WHERE s.version = :version")
})
public class Utilisateur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long utilisateur;
    @Size(max = 50)
    @Column(length = 50)
    private String utilisateurname;
    @Size(max = 50)
    @Column(length = 50)
    private String utilisateurlims;
    @Size(max = 50)
    @Column(length = 50)
    private String shortname;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    //private Date version;*/
    @OneToMany(mappedBy = "utilisateur")
    private List<Resultat> resultats;

    public Utilisateur() {
    }

    public Utilisateur(Long utilisateur) {
        this.utilisateur = utilisateur;
    }

    /*public Utilisateur(Long utilisateur, Date version) {
        this.utilisateur = utilisateur;
        this.version = version;
    }*/

    public Long getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Long utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getUtilisateurname() {
        return utilisateurname;
    }

    public void setUtilisateurname(String utilisateurname) {
        this.utilisateurname = utilisateurname;
    }

    public String getUtilisateurlims() {
        return utilisateurlims;
    }

    public void setUtilisateurlims(String utilisateurlims) {
        this.utilisateurlims = utilisateurlims;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    /*public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }*/

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
        hash += (utilisateur != null ? utilisateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.utilisateur == null && other.utilisateur != null) || (this.utilisateur != null && !this.utilisateur.equals(other.utilisateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Utilisateur[ utilisateur=" + utilisateur + " ]";
    }
    
}
