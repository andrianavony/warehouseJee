/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

/**
 *
 * @author S.ANDRIANAVONY
 * 
 * S0101S10096C04_A
 * 

 */
public class ForItem {
    public static String getIdSpecie(String IdArticle ){
        if (null==IdArticle){
            return "";
        }
        return IdArticle.substring(0,5);
    }
    
    public static String getIdVariety(String IdArticle ){
        if (null==IdArticle){
            return "";
        }
        return IdArticle.substring(5,11);
    }
    
    public static String getIdGeneration(String IdArticle ){
        if (null==IdArticle){
            return "";
        }
        return IdArticle.substring(11,14);
    }
    
    public static String getIdStageFromItem(String IdArticle ){
        if (null==IdArticle){
            return "";
        }
        if(IdArticle.length()==14){
        return "COM";
        }
        
        if(IdArticle.endsWith("_KG")){
        return "COM";
        }
        
        return IdArticle.substring(15,IdArticle.length());
    }
    
    
    public static String getIdStageFromStageLabel(String stageLabel ){
        if (null==stageLabel){
            return "COM";
        }
        
        
        if(stageLabel.equalsIgnoreCase("A")){
        return "A";
        }
        
        if(stageLabel.equalsIgnoreCase("C")){
        return "C";
        }
        
        if(stageLabel.equalsIgnoreCase("_KG")){
        return "COM";
        }
        
        if(stageLabel.equalsIgnoreCase("Shelled")){
        return "B";
        }
        
        if(stageLabel.equalsIgnoreCase("B")){
        return "B";
        }
        return null;
    }

    /**
     * On verifie juste la logeur des champs
     */
    public static String getIdArticle(String idspecie, String idvariety, String idgeneration, String idstageOuLabel) {
        if(idspecie!=null && idvariety!=null && idgeneration!= null){
            String  idstage =getIdStageFromStageLabel(idstageOuLabel);
            if (idstage==null){
                return idspecie+ idvariety + idgeneration;
            }else {
                return idspecie+ idvariety + idgeneration+"_"+idstage;
            }
            /*
            if (idstage==null){
                return idspecie+ idvariety + idgeneration;
            }
            if (idstage.equalsIgnoreCase("COM")){
                return idspecie+ idvariety + idgeneration;
            }
            if (idstage.equalsIgnoreCase("_Kg")){
                return idspecie+ idvariety + idgeneration+idstage;
            }
            if (idstage.equalsIgnoreCase("Kg")){
                return idspecie+ idvariety + idgeneration+"_"+idstage;
            }
            
            //Shelled
            if (idstage.equalsIgnoreCase("Shelled")){
                return idspecie+ idvariety + idgeneration+"B";
            }
                    */

        } 
        return null;
    }

}
