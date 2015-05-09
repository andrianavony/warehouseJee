package session.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import utilities.ArticleFind;
import utilities.ArticleUtility;
import utilities.ForItem;
import entite.Article;
import entite.Generation;
import entite.Specie;
import entite.Stage;
import entite.Variety;

@Stateless
public class ArticleManager {
	
	
	@Inject ArticleUtility articleUtility;
	@Inject ArticleFind articleFind;
	
	public void setArticleFind (ArticleFind articleFind){
		this.articleFind= articleFind;	
	}
	
	public void setArticleUtility (ArticleUtility articleUtility){
		this.articleUtility= articleUtility;
	}
	
	public ArticleManager(){
		
	}
	
	public Article  createOrRetreiveArticle(String idArticle){
		 Article article=null;
		 
		 if (idArticle==null){
	           return null;
	       }
		 
		 article = retreiveArticle(idArticle);
		 if(null == article){		 
			 article=createArticle(idArticle);
		 }
		 
		 return article;
	 }
	
	public Article  retreiveArticle(String idArticle){
		 Article article=null;
		 
		 if (idArticle==null){
	           return null;
	       }
		 
		 List<Article> articles= articleFind.getArticle(idArticle);
		 if(articles.isEmpty()) {		 
			 return null;
		 }else{
			 article=articles.get(0);
		 }
		 
		 return article;
	 }
	 
	public Article createOrRetreiveArticle (String idarticle, String idstage, String idspecie, String idvariety,String idgeneration){
		Article article=null;
		 
		 if (idarticle==null){
	           return null;
	       }
		 
		 article = retreiveArticle(idarticle);
		 if(null != article){		 
			 return article;
		 }
		 
		Variety variety=createOrRetreiveVariety( idspecie ,idvariety);
		 Specie specie =variety.getSpecie();
		 
		 Stage stage=createOrRetreiveStage(idstage);
		 Generation generation=createOrRetreiveGeneration(idgeneration);
		 
		 article = articleUtility.createArticle(idarticle, specie , variety,generation, stage);			 
		return article;
	}
	
	 
	 public Article createArticle(String idArticle) {
			
			String idspecie=ForItem.getIdSpecie(idArticle);
			 String idvariety =ForItem.getIdVariety(idArticle);
			 String idstage = ForItem.getIdStageFromItem(idArticle);
			 String idgeneration = ForItem.getIdGeneration(idArticle);
			 return createOrRetreiveArticle (idArticle, idstage, idspecie, idvariety,idgeneration);
			 
		}
	 

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	 public Specie createSpecie(String idspecie){
		 if (idspecie==null){
	           return null;
	     }
		 return articleUtility.createSpecie(idspecie);		 
	 }
	 
	
	 
	 
	 
	 public Variety createOrRetreiveVariety(String  idspecie ,String idvariety){
		 Variety variety=null;
		 List<Variety>  varieties= articleFind.getArticleBySpecieVariety(idspecie,idvariety);
		 if(varieties.isEmpty()){
			 variety = createVariety(idspecie, idvariety);
		 }else{
			 variety=varieties.get(0);
		 }
		 return variety;
	 }
	 
	 public Variety createVariety(String idspecie, String idvariety) {
			Specie specie=createOrRetreiveSpecie(idspecie);
			return articleUtility.createVariety(specie, idvariety );
		}
	 
	 public Specie createOrRetreiveSpecie(String idspecie){
		 Specie specie=null;
		 List<Specie>  species = articleFind.getSpecie(idspecie);
		 if(species.isEmpty()){
			 specie = createSpecie(idspecie);
		 }else{
			 specie=species.get(0);
		 }
		 return specie;
	 }
	 
	 public Generation createGeneration(String idGeneration){
		 return articleUtility.createGeneration(idGeneration);
	 }
	 
	 public Generation createOrRetreiveGeneration(String idgeneration) {
		 Generation generation=null;
		 List<Generation> generations= articleFind.getGeneration(idgeneration);
		if(generations.isEmpty()){
			generation = createGeneration(idgeneration);
		}else{
			generation=generations.get(0);
		}
		return generation;
	}
	 
	 

	 
	 
	 public Stage createStage(String idstage){
		 return articleUtility.createStage(idstage);	 
	 }
			 
	 public Stage createOrRetreiveStage(String idstage){
		 Stage stage=null;
		 List<Stage>  stages= articleFind.getStage(idstage);
		 if(stages.isEmpty()){
			 stage= createStage(idstage);
		 }else{
			 stage=stages.get(0);
		}
		 return stage;
	 }

	public Article createOrRetreiveArticle(String idspecie, String idvariety,
			String idgeneration, String idstage) {
		String idArticle = ForItem.getIdArticle(idspecie, idvariety, idgeneration, idstage);
		return createOrRetreiveArticle(idArticle);
		
	}
	 
	public Article getArticleByVarietyStageLagel(String idvariety, String stageLabel, String idGeneration) {
	       
        String idStage = ForItem.getIdStageFromStageLabel(stageLabel);
        
        List<Article> articleList = articleFind.findByVarietyStadeGeneration(idvariety,idStage,idGeneration);
        if(articleList.isEmpty()){
            return null;
        }else {
            return articleList.get(0);
        }
                
    }
	 
}
