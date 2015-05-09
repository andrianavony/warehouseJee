/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objet.metier.excel;

import session.ejb.ArticleManager;
import utilities.ArticleUtility;
import entite.Article;
import entite.Batch;
import entite.Company;
import entite.Trace;
import entite.Wo;
import entite.WoPK;
import entite.Traca;

import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author S.ANDRIANAVONY
 */
@Stateless
@LocalBean
public class TracaBean {
    String CompanyId;
    String Warehouse_Id;
    String Reference_Id;
    String Transaction_Type;
    Double MGR_Unit_Quantity;
    Double MGV_Unit_Quantity;
    Double KG_Unit_Quantity;
    String Item_Number;
    String Batch_Number;
    String Year;
    String Bassin;
    String Producteur;
    String Contract;
    

    @PersistenceContext
    protected EntityManager em;
    
    @Inject
    ArticleManager articleManager;
    
    
    
    Trace trace;

     Wo wo;
    
    public TracaBean() {
        
    }

    public TracaBean(String CompanyId, String Warehouse_Id, String Reference_Id, String Transaction_Type, Double MGR_Unit_Quantity, Double MGV_Unit_Quantity, Double KG_Unit_Quantity, String Item_Number, String Batch_Number, String Year, String Bassin, String Producteur, String Contract) {
        this.CompanyId = CompanyId;
        this.Warehouse_Id = Warehouse_Id;
        this.Reference_Id = Reference_Id;
        this.Transaction_Type = Transaction_Type;
        this.MGR_Unit_Quantity = MGR_Unit_Quantity;
        this.MGV_Unit_Quantity = MGV_Unit_Quantity;
        this.KG_Unit_Quantity = KG_Unit_Quantity;
        this.Item_Number = Item_Number;
        this.Batch_Number = Batch_Number;
        this.Year = Year;
        this.Bassin = Bassin;
        this.Producteur = Producteur;
        this.Contract = Contract;
    }
    
    
    
    
    public void setInfo(String CompanyId, String Warehouse_Id, String Reference_Id, String Transaction_Type, Double MGR_Unit_Quantity, Double MGV_Unit_Quantity, Double KG_Unit_Quantity, String Item_Number, String Batch_Number, String Year, String Bassin, String Producteur, String Contract) {
        this.CompanyId=CompanyId ;
        this.Warehouse_Id=Warehouse_Id ;
        this.Reference_Id=Reference_Id ;
        this.Transaction_Type=Transaction_Type ;
        this.MGR_Unit_Quantity= MGR_Unit_Quantity;
        this.MGV_Unit_Quantity=MGV_Unit_Quantity ;
        this.KG_Unit_Quantity= KG_Unit_Quantity; 
        this.Item_Number= Item_Number;
        this.Batch_Number= Batch_Number;
        this.Year= Year; 
        this.Bassin= Bassin;
        this.Producteur= Producteur;
        this.Contract=Contract;        
    }
        
    public void createTraca(){    
        //createOrUpdateArticle.createOrUpdateArticle("S0101S10031C04_C");
        
        //createOrUpdateArticle = new  ArticleUtility();
        System.out.println(" dans create Traca "+articleManager);
        System.out.println(" dans create Traca this.Item_Number ============================================= "+this.Item_Number);
        Article idarticle = articleManager.createOrRetreiveArticle(this.Item_Number);
        
        
        trace = new Trace();
        trace.setWarehouse(Warehouse_Id);
        trace.setArticle(idarticle);
        Company company = new Company(CompanyId);
        trace.setCompany(company);
        
        wo = createWo();// CompanyId,  Warehouse_Id,  Reference_Id,  Transaction_Type, MGR_Unit_Quantity,  MGV_Unit_Quantity,  KG_Unit_Quantity,  Item_Number,  Batch_Number, Year, Bassin, Producteur, Contract); 
        trace.setWo(wo);
        
        createBatch(idarticle, company);// CompanyId,  Warehouse_Id,  Reference_Id,  Transaction_Type, MGR_Unit_Quantity,  MGV_Unit_Quantity,  KG_Unit_Quantity,  Item_Number,  Batch_Number, Year, Bassin, Producteur, Contract);
        
        setTraceQuantity();
        em.merge(trace);
        
    }

    public Wo createWo(){//String CompanyId, String Warehouse_Id, String Reference_Id, String Transaction_Type, Double MGR_Unit_Quantity, Double MGV_Unit_Quantity, Double KG_Unit_Quantity, String Item_Number, String Batch_Number, String Year, String Bassin, String Producteur, String Contract) {
        
        WoPK woPK =new WoPK(Reference_Id, CompanyId);
        wo = new Wo(woPK);
        return wo;
    }
    
    public void setTraceQuantity(){
        trace.setQuantity(this.KG_Unit_Quantity);
        trace.setUnite("Kg");
        trace.setTracetype(Transaction_Type);
    }
    public void createBatch(Article idarticle,Company company ){//String CompanyId, String Warehouse_Id, String Reference_Id, String Transaction_Type, Double MGR_Unit_Quantity, Double MGV_Unit_Quantity, Double KG_Unit_Quantity, String Item_Number, String Batch_Number, String Year, String Bassin, String Producteur, String Contract) {
        Batch batch= new Batch();
        batch.setBatchname(Batch_Number);
        batch.setArticle(idarticle);
        batch.setSpecie(idarticle.getSpecie());
        batch.setStage(idarticle.getStage());
        batch.setCompany(company);
        batch.setWo(trace.getWo());
        batch.setContract(Contract);
    
        trace.setBatchname(batch);
        //return trace;
    }

    

    
    public void setInfo(Traca traca ) {
        this.CompanyId = traca.getCompany();
        this.Warehouse_Id = traca.getWarehouse();
        this.Reference_Id = traca.getWo().getWoPK().getWo();
        this.Transaction_Type = traca.getTracetype();
        String mgr = traca.getMgrquantity();
        if (mgr!=null){
            this.MGR_Unit_Quantity = Double.parseDouble(traca.getMgrquantity());
        }
        String mgv = traca.getMgvquantity();
        if (mgv!=null){
            this.MGV_Unit_Quantity = Double.parseDouble(traca.getMgvquantity());
        }
        String kg = traca.getKgquantity();
        if (kg!=null){
            this.KG_Unit_Quantity = Double.parseDouble(traca.getKgquantity());
        }
        
        this.Item_Number = traca.getArticle();
        System.out.println("dans création TracaBean(Traca ************************************************ "+this.Item_Number );
        this.Batch_Number = traca.getBatchname();
        this.Year = traca.getYear();
        this.Bassin = traca.getBassin();
        this.Producteur = traca.getProducteur();
        this.Contract = traca.getContrat();
    }

    public void setDone(Traca tracaLogged) {
        short statusDone=0;
        tracaLogged.setStatuttraca(statusDone);
        em.merge(tracaLogged);
    }
    
}
