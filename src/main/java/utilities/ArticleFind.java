/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import entite.Article;
import entite.Generation;
import entite.Specie;
import entite.Stage;
import entite.Variety;
import entite.VarietyPK;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author S.ANDRIANAVONY
 */
@Stateless
public class ArticleFind {

    @PersistenceContext
    protected EntityManager em;

    public List<Article> findByVarietyStadeGeneration(String variety, String Stage, String Generation) {
        TypedQuery<Article> query = em.createNamedQuery("Article.findByVarietyStadeGeneration", Article.class);
            query.setParameter("variety", variety);
            query.setParameter("stage", Stage);
            query.setParameter("generation", Generation);
            
        return query.getResultList();
    }
    
    public List<Variety> getArticleByVariety(String variety) {
        VarietyPK varietyPK = new VarietyPK();
        varietyPK.setVariety(variety);
        TypedQuery<Variety> query = em.createNamedQuery("Variety.findByVariety", Variety.class);
            query.setParameter("variety", variety);
        return  query.getResultList();
        }
    
    public List<Variety>  getArticleBySpecieVariety(String specie, String variety) {
        TypedQuery<Variety> query = em.createNamedQuery("Variety.findBySpecieVariety", Variety.class);
            query.setParameter("specie", specie);
            query.setParameter("variety", variety);
            return query.getResultList();
    }
     
    public List<Specie>  getSpecie(String specie) {
        TypedQuery<Specie> query = em.createNamedQuery("Specie.findBySpecie", Specie.class);
            query.setParameter("specie", specie);
            return query.getResultList();
    }
    
    public List<Stage>  getStage(String stage) {
        TypedQuery<Stage> query = em.createNamedQuery("Stage.findByStage", Stage.class);
            query.setParameter("stage", stage);
            return query.getResultList();
    }

    public List<Generation>  getGeneration(String generation) {
        TypedQuery<Generation> query = em.createNamedQuery("Generation.findByGeneration", Generation.class);
            query.setParameter("generation", generation);
            return query.getResultList();
    }

	public List<Article> getArticle(String article) {
		TypedQuery<Article> query = em.createNamedQuery("Article.findByArticle", Article.class);
        query.setParameter("article", article);
        return query.getResultList();
}
    
}
