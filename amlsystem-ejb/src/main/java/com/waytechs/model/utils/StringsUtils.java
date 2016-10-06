/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.model.utils; 
 
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 *
 * @author JNK
 */ 
public class StringsUtils {
    
    /***
     * comprueba si la cadena es null o vacia con trim()
     * @param cad
     * @return 
     */
    public static Boolean isEmptyTrim(String cad){
        
        if(cad==null) return true;
        
        if(cad.trim().equals("")) return true;
        
        return false;
    }
    
    
    /***
     * Ejecuta trim(), si la cadena es vacia devuelve NULL
     * @param cad
     * @return 
     */
    public static String trimEmptyIsNull(String cad){
        
        if(cad.trim().isEmpty() ) return null;
        
        return cad.trim();
    }
    
    
    /***
     * comprueba si ambas cadenas son iguales con trim(), y ademas que ninguna sea null o vacia
     * @param cad1
     * @param cad2
     * @return 
     */
    public static Boolean eqTrim_NotNullOrEmpty(String cad1, String cad2){
        if( cad1==null || cad2==null) return false;
        if( cad1.trim().equals("") 
                || cad2.trim().equals("") ) return false;
        
        if(cad1.trim().equals(cad2.trim())) return true;
        
        return false;
    }
    
    public static String encloseApost(String cad){
        String res = "'"+cad+"'";

        return res;
    }
    
    public static String removeWhiteSpace(String cadena){
    	String result = "";
    	
    	if(cadena==null) return null;
    	
    	result = cadena.replace(" ", "");
    	
    	return result;
    	
    }
    
     public static String numeroFormateado(Double valor) {
        if (valor != null) {
            String pattern = "###0.00#";
            DecimalFormat df = new DecimalFormat(pattern, DecimalFormatSymbols.getInstance());
            DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
            dfs.setDecimalSeparator('.');
            df.setDecimalFormatSymbols(dfs);
            return df.format(valor);
        }
        return null;
    }    
     
     
         
    
   
    
    

    
    
    
}
