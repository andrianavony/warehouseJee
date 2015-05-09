package utilities;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import entite.*;

@Stateless
public class ArticleUtility {
	@PersistenceContext ()
    protected EntityManager em;
	
	//@Inject ArticleFind articleFind;

	/*
	public Article createArticle(Specie specie , String articlename) {
		//String idspecie = ForItem.getIdSpecie(articlename);
        String idvariety = ForItem.getIdVariety(articlename);
        String idstage = ForItem.getIdStageFromItem(articlename);
        String idgeneration=ForItem.getIdGeneration(articlename);
        return createOrUpdateArticle ( articlename,  idstage,  specie,  idvariety,idgeneration);		 
 }
 */
// public Article createArticle (String idarticle, String idstage, Specie specie , String idvariety,String idgeneration){
	
	
	public Article createArticle (String idarticle, Specie specie , Variety variety,Generation generation, Stage stage){	 
        Article a = new Article (idarticle);
        a.setSpecie(specie);
        a.setVariety(variety);
        a.setStage(stage);
        a.setGeneration(generation);
        em.persist(a);
        return a;
    }

	

	public Specie createSpecie(String idspecie) {
		Specie specie= new Specie(idspecie);
		 em.persist(specie);
		 em.flush();
		 System.out.println(" Creation Espece faite "+specie);
		 return specie;
	}

	

	public Generation createGeneration(String idGeneration) {
		Generation generation=new Generation(idGeneration);
		 em.persist(generation);
		 return generation;
	}

	public Stage createStage(String idstage) {
		Stage stage = new Stage(idstage);
		 em.persist(stage);
		 return stage;
		
	}



	public Variety createVariety(Specie specie, String idvariety) {
		Variety variety = new Variety(idvariety, specie.getSpecie());
		 variety.setSpecie(specie); 
		 em.persist(variety);
		 return variety;
	}
	
	
	
	
	
	
	
	

}
