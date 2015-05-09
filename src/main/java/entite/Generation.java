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
    @NamedQuery(name = "Generation.findAll", query = "SELECT g FROM Generation g"),
    @NamedQuery(name = "Generation.findByGeneration", query = "SELECT g FROM Generation g WHERE g.generation = :generation"),
    @NamedQuery(name = "Generation.findByDescription", query = "SELECT g FROM Generation g WHERE g.description = :description"),
    @NamedQuery(name = "Generation.findByGenerationame", query = "SELECT g FROM Generation g WHERE g.generationame = :generationame")
  //,@NamedQuery(name = "Article.findByVersion", query = "SELECT a FROM Article a WHERE a.version = :version")
})
public class Generation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String generation;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    @Size(max = 50)
    @Column(length = 50)
    private String generationame;
    /*@Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Version
    private Timestamp version;*/
    @OneToMany(mappedBy = "generation")
    private List<Batch> batches;
    @OneToMany(mappedBy = "generation")
    private List<Article> articles;

    public Generation() {
    }

    public Generation(String generation) {
        this.generation = generation;
    }

    /*public Generation(String generation, Timestamp version) {
        this.generation = generation;
        this.version = version;
    }*/

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenerationame() {
        return generationame;
    }

    public void setGenerationame(String generationame) {
        this.generationame = generationame;
    }

    /*public Date getVersion() {
        return version;
    }

    public void setVersion(Timestamp version) {
        this.version = version;
    }*/

    @XmlTransient
    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }

    @XmlTransient
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (generation != null ? generation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Generation)) {
            return false;
        }
        Generation other = (Generation) object;
        if ((this.generation == null && other.generation != null) || (this.generation != null && !this.generation.equals(other.generation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Generation[ generation=" + generation + " ]";
    }
    
}
