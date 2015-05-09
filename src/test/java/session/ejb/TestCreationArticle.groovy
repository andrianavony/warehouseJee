package session.ejb;

 

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.spock.ArquillianSputnik;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

 

import org.junit.Test;
import org.junit.runner.RunWith;

 






import org.mozilla.javascript.tools.idswitch.IdValuePair;

import entite.Article;
import entite.Generation;
import entite.Specie;
import entite.Stage;
import entite.Variety;

import javax.inject.Inject;

 










import java.util.List;

 










import spock.lang.Specification;
import utilities.ArticleUtility;
import utilities.ForItem;
import static org.junit.Assert.*;




@RunWith(ArquillianSputnik)

public class JpaCriteriaTestSpecification extends Specification {
	
	/*
	@Inject ArticleManager articleManager;
	
	@Deployment
	public static WebArchive createDeployment() {

		WebArchive war = ShrinkWrap.create(WebArchive.class)

			.addPackage("entite")
			.addPackage("session.ejb")
			.addPackage("utilities")
			.addPackage("error")

			.addAsResource("META-INF/persistence.xml")

			.addAsResource("META-INF/create.sql")

			.addAsResource("META-INF/drop.sql")

			.addAsResource("META-INF/load.sql");

		System.out.println(war.toString(true));

		return war;

	}

	
	def "Test de création de l espece"(){
		
		given :"J'ai un code espece"
					 
			String idspecie="M0102"
			Specie specie=new Specie(idspecie);
			
					 
		when :"J'enregistre l espece"
			Specie specieTmp= articleManager.createOrRetreiveSpecie(idspecie)
		
		 then :" Alors la Variete est crée"
			 specie== specieTmp
	}
	
	def "Test de création de Variete "(){
		
		given :"J'ai un code Variete"
					 
			String idspecie="M0101"
			Specie specie=new Specie(idspecie);
					 
			String idvariety="M10095"
			Variety variety=new Variety(idvariety, idspecie);

					 
		when :"J'enregistre le Variete"
			Variety varietyTmp= articleManager.createOrRetreiveVariety( idspecie,idvariety)
		
		 then :" Alors la Variete est crée"
			 variety== varietyTmp
			 specie==varietyTmp.getSpecie()
	}
	
	def "Test de création de stade "(){
		
		given :"J'ai un code Stade"
					 
			String idstage="COMME";
			Stage  stage = new Stage(idstage);
					 
		when :"J'enregistre le Stade"
			Stage  stageTmp= articleManager.createOrRetreiveStage(idstage)
		
		 then :" Alors le stade est crée"
		 	stage == stageTmp
	}
	
	def "Test de creation de Generation "(){
		
		given :"J'ai un code Generation"
					 
			String idGeneration="C04"
			Generation generation= new Generation(idGeneration);
					 
		when :"J'enregistre le Generation"
			Generation generationTmp= articleManager.createOrRetreiveGeneration(idGeneration)
		
		 then :" Alors la Generation est crée"
			 generation== generationTmp
	}
	
	def "La création de l'article crée aussi l'espece, la variété, stade et la génération"(){
		
		given :"J'ai un code article "
					 
					 String idspecie="S0101"
					 Specie specie=new Specie(idspecie);
					 
					 String idvariety="S10095"
					 Variety variety=new Variety(idvariety, idspecie);
					 
					 String idGeneration="C03"
					 Generation generation= new Generation(idGeneration);
					 
					 String idstage="A";
					 Stage  stage = new Stage(idstage);
					 
					 
					 
					 String nomArticle =ForItem.getIdArticle(idspecie, idvariety, idGeneration, idstage)
					 
		
		when :"J'enregistre l' article "
					 Article article = articleManager.createOrRetreiveArticle(nomArticle)
		
		then :" Alors l'article ainsi que l'espece la variete,  Code Generation et le stade sont crées"
					 nomArticle == article.getArticle()
					 
					 specie==article.getSpecie()
					 variety==article.getVariety()
					 stage==article.getStage()
					 generation==article.getGeneration()
					 
	}
	
	def "La création de l'article Kg crée un article au stade COM"(){
		
		given :"J'ai un code article "
					 
					 String idspecie="S0101"
					 Specie specie=new Specie(idspecie);
					 
					 String idvariety="S10095"
					 Variety variety=new Variety(idvariety, idspecie);
					 
					 String idGeneration="C03"
					 Generation generation= new Generation(idGeneration);
					 
					 String stageLabel="Kg";
					 Stage  stage = new Stage(stageLabel);
					 
					 
					 
					 String nomArticle =ForItem.getIdArticle(idspecie, idvariety, idGeneration, stageLabel)
					 
		
		when :"J'enregistre l' article "
					 Article article = articleManager.createOrRetreiveArticle(nomArticle)
		
		then :" Alors l'article ainsi que l'espece la variete,  Code Generation et le stade sont crées"
					 nomArticle == article.getArticle()
					 
					 specie==article.getSpecie()
					 variety==article.getVariety()
					 "COM"==article.getStage().stage
					 generation==article.getGeneration()
					 
	}
	
	
						 
    def "La création de l'article avec un espece existant "(){
		given :"Enregistrement une autre variete avec le meme espece"
						 
					String idspecie="S0102"
					Specie specie=new Specie(idspecie)
					Specie specieTmp= articleManager.createSpecie(idspecie)
				
					String idvariety="S10094"
					Variety variety=new Variety(idvariety, idspecie);
					
					String idGeneration="C02"
					Generation generation= new Generation(idGeneration);
					
					String idstage="A";
					Stage  stage = new Stage(idstage);
						 
					String nomArticle =ForItem.getIdArticle(idspecie, idvariety, idGeneration, idstage)
					 
		when : "Enregistre nouvelle variete "
					Article article = articleManager.createOrRetreiveArticle(nomArticle)
		
		then :" Alors la nouvelle la variete,  est crées avec le meme espece "
					 nomArticle == article.getArticle()
					 
					 specie==article.getSpecie()
					 variety==article.getVariety()
					 stage==article.getStage()
					 generation==article.getGeneration()
		
					 
	}
	

	def "La création deux fois le meme article donne le meme resultat"(){
		given :"un article "
						 
					String idspecie="S0106"
					Specie specie=new Specie(idspecie)
					Specie specieTmp= articleManager.createSpecie(idspecie)
				
					String idvariety="S10099"
					Variety variety=new Variety(idvariety, idspecie);
					
					String idGeneration="C02"
					Generation generation= new Generation(idGeneration);
					
					String idstage="A";
					Stage  stage = new Stage(idstage);
						 
					String nomArticle =ForItem.getIdArticle(idspecie, idvariety, idGeneration, idstage)
					 
		when : "Enregistre 2 fois l article variete "
					Article article = articleManager.createOrRetreiveArticle(nomArticle)
					
					Article article2 = articleManager.createOrRetreiveArticle(nomArticle)
		
		then :" Alors les deux articles sont identiques "
		
					 nomArticle == article.getArticle()
					 
					 specie==article.getSpecie()
					 variety==article.getVariety()
					 stage==article.getStage()
					 generation==article.getGeneration()
					 article==article2
		
					 
	}
	


	  */
	   
	   
}
