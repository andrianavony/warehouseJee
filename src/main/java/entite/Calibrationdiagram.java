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
    @NamedQuery(name = "Calibrationdiagram.findAll", query = "SELECT c FROM Calibrationdiagram c"),
    @NamedQuery(name = "Calibrationdiagram.findByDiagram", query = "SELECT c FROM Calibrationdiagram c WHERE c.diagram = :diagram"),
    @NamedQuery(name = "Calibrationdiagram.findByDescription", query = "SELECT c FROM Calibrationdiagram c WHERE c.description = :description")
  //,@NamedQuery(name = "Article.findByVersion", query = "SELECT a FROM Article a WHERE a.version = :version")
})
public class Calibrationdiagram implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String diagram;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Version
    private Timestamp version;*/
    @OneToMany(mappedBy = "diagram")
    private List<Wo> wos;

    public Calibrationdiagram() {
    }

    public Calibrationdiagram(String diagram) {
        this.diagram = diagram;
    }

    /*public Calibrationdiagram(String diagram, Timestamp version) {
        this.diagram = diagram;
        this.version = version;
    }*/

    public String getDiagram() {
        return diagram;
    }

    public void setDiagram(String diagram) {
        this.diagram = diagram;
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
    public List<Wo> getWos() {
        return wos;
    }

    public void setWos(List<Wo> wos) {
        this.wos = wos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diagram != null ? diagram.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calibrationdiagram)) {
            return false;
        }
        Calibrationdiagram other = (Calibrationdiagram) object;
        if ((this.diagram == null && other.diagram != null) || (this.diagram != null && !this.diagram.equals(other.diagram))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Calibrationdiagram[ diagram=" + diagram + " ]";
    }
    
}
