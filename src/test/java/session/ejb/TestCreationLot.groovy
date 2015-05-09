package session.ejb;

 

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.spock.ArquillianSputnik;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

 

import org.jsoup.nodes.Entities.EscapeMode;
import org.junit.Test;
import org.junit.runner.RunWith;

 






import org.mozilla.javascript.tools.idswitch.IdValuePair;

import entite.Article;
import entite.Batch;
import entite.Company;
import entite.Generation;
import entite.Specie;
import entite.Stage;
import entite.Variety;
import error.BatchIdNotFindError;

import javax.inject.Inject; 

import java.util.List;

import spock.lang.Shared;
import spock.lang.Specification;
import spock.lang.Stepwise;
import utilities.BatchFind;
import utilities.ForItem;
import static org.junit.Assert.*;



@Stepwise
@RunWith(ArquillianSputnik)

public class CreationLotTestSpecification extends Specification {
	
	@Inject BatchManager batchManager;
	
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

	@Shared String batchname ;
	@Shared String idarticle ;
	@Shared String idcompany ;
	@Shared BigInteger limsbatchid ;

	@Shared Article article	;
	@Shared Specie espece ;
	@Shared Variety variety;
	@Shared Generation generation ;
	@Shared Stage stage ;
	@Shared Company company;
	@Shared int nbLot=0;

	def setup(){
		batchname = "lotInexistant";
		idarticle = "S0101S10128C04_C";
		idcompany = "lvh";
		limsbatchid = new BigInteger("1");
		
		article	= new Article(idarticle)
		espece = new Specie(ForItem.getIdSpecie(idarticle))
		variety = new Variety (ForItem.getIdVariety(idarticle), ForItem.getIdSpecie(idarticle))
		generation = new Generation(ForItem.getIdGeneration(idarticle))
		stage = new Stage(ForItem.getIdStageFromItem(idarticle))
		
		company = new Company(idcompany)
		
	}
	
	def "Au debut de cet test il n y a pas de lot dans la base de donne"(){
		List<Batch> batchs;
		when :""
			batchs=batchManager.findAllBatch()
		then : ""		
			if (batchs.size()==0) {
				true
			} else {
				Batch b = batchs.get(0)
				b.getBatch()==-1
				b.getBatchname()=="nom_improbable_"
			}
		}
		

	def "Test de creation du lot avec Batch name , article, compagnie "(){
		
		when :"J'enregistre le lot "
			Batch batch= batchManager.createOrRetrieveBatch(batchname, idarticle, idcompany, limsbatchid)
		
		 then :" Alors l article associe au lot est associe au bon espece, a la bonne variete ..."
		 	verificationArticleEspeceVarieteGenerationStade (batch) 
	  		verificationBatchnameEtCompany(batch)
			nbLot++==batch.getBatch()
	}
	
	
	
	def "apres cet test il n y a pas de lot dans la base de donne"(){
		when :""
		List<Batch> batchs= batchManager.findAllBatch()
		then : ""		
		batchs.size()==1
	}
	
	def "Recherche le meme lot avec le code article, batch name, company "(){
		when :" On demande de creer le meme lot deux fois "
		Batch batch= batchManager.createOrRetrieveBatch(batchname, idarticle, idcompany, limsbatchid)
		
		 then :" Alors on retourne le meme lot"
			verificationArticleEspeceVarieteGenerationStade (batch) 
	  		verificationBatchnameEtCompany(batch)
			nbLot==batch.getBatch()
	}

	
	def "Recherche le meme lot avec le batchId "(){
		when :" On cherche le lot avec son identifiant "
		Batch batch= batchManager.retrieveBatchFromBatch(new BigInteger("1")) 
		
		 then :" Alors obtient le meme lot "
			 verificationArticleEspeceVarieteGenerationStade (batch) 
	  		verificationBatchnameEtCompany(batch)
			nbLot==batch.getBatch()
	}
	
	def "Recherche un lot avec un batchId inexistant "(){
		when :" Si on cherche un lot avec un identifiant inexistant "
		Batch batch= batchManager.retrieveBatchFromBatch(new BigInteger("-1"))
		
		 then :" Alors une exception est lance "
		 final BatchIdNotFindError exception = thrown ()
	}
	
	
	def "Test de creation du lot avec le nom espece, variete, stade, generation , Batch name et compagnie "(){
		setup : 
		String idspecie 	="M0101"
		String idvariety 	="M10039"
		String idstage ="B"
		String idgeneration="C04"
		
		idarticle ="M0101M10039C04_B"
		//idspecie+idvariety+ idgeneration +"_" +idstage //ForItem.getIdArticle(idspecie, idvariety, idgeneration, idstage) 
		
		article	= new Article(idarticle)
		espece = new Specie(idspecie) //ForItem.getIdSpecie(idarticle))
		variety = new Variety (idvariety, idspecie) //ForItem.getIdVariety(idarticle), ForItem.getIdSpecie(idarticle))
		generation = new Generation(idgeneration)//ForItem.getIdGeneration(idarticle))
		stage = new Stage(idstage)//ForItem.getIdStageFromItem(idarticle))
		
		
		when :"J'enregistre le lot "
			
			Batch batch= batchManager.createOrRetrieveBatch(idspecie, idvariety, idstage, idgeneration, "prem_"+batchname, idcompany)
		
		 then :" Alors l article associe au lot est associe au bon espece, a la bonne variete ..."
			 //verificationArticleEspeceVarieteGenerationStade (batch)
			  //verificationBatchnameEtCompany(batch)
		 
			nbLot++==batch.getBatch()
	}
	
	
	def "Test de creation du lot avec une variete existante sur un autre stade, generation , Batch name et compagnie "(){
		setup :
		String idspecie 	="M0101"
		String idvariety 	="M10039"
		String idstage 		="C"
		String idgeneration	="C04"
		
		idarticle ="M0101M10039C04_C"
		
		article	= new Article(idarticle)
		espece = new Specie(idspecie) //ForItem.getIdSpecie(idarticle))
		variety = new Variety (idvariety, idspecie) //ForItem.getIdVariety(idarticle), ForItem.getIdSpecie(idarticle))
		generation = new Generation(idgeneration)//ForItem.getIdGeneration(idarticle))
		stage = new Stage(idstage)//ForItem.getIdStageFromItem(idarticle))
		
		when :"J'enregistre le lot "
			
			//Batch batch= batchManager.createOrUpdateBatchByVariety(idvariety, idstage, idgeneration, batchname, idcompany)
		Batch batch= batchManager.createBatch("autre_"+batchname,article, idcompany)
		
		 then :" Alors l article associe au lot est associe au bon espece, a la bonne variete ..."
			 //verificationArticleEspeceVarieteGenerationStade (batch)
			  //verificationBatchnameEtCompany(batch)
			nbLot++==batch.getBatch()
	}
	
	
	
	def Boolean verificationArticleEspeceVarieteGenerationStade (Batch batch){
		article== batch.getArticle() 						&&
		espece==batch.getArticle().getSpecie() 				&&
		variety==batch.getArticle().getVariety() 			&&
		generation==batch.getArticle().getGeneration()		&&
		stage ==batch.getArticle().getStage()
	}
	
	def Boolean verificationBatchnameEtCompany(Batch batch) {
		company==batch.getCompany() 						//&&
		//batchname==batch.getBatchname()
	}
}
