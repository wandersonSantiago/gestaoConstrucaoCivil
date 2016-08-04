package br.com.system.gestaoConstrucaoCivil.criarObjecto.util;



import java.util.Random;

/** 
 * Classe utilitaria de validacao e geracao de numeros de CPF CNPJ,RG para testes  
 *  
 * @author Evaldo Junior
 *
 */
public class Util  {

	/**
	 * Gera numeros de CPF aleatorios 
	 * @return String
	 */
	
	public static Util instance;
	
	private Util()
	{
		
	}

	 public static Util getInstance() {
	      if (instance == null)
	         instance = new Util();
	      return instance;
	   }
    
	protected static String getRg()
    {
    	String  nDigResult;
        String numerosContatenados;
        String numeroGerado;
        Random numeroAleatorio = new Random();
         
        int n1 = numeroAleatorio.nextInt(10);
        int n2 = numeroAleatorio.nextInt(10);
        int n3 = numeroAleatorio.nextInt(10);
        int n4 = numeroAleatorio.nextInt(10);
        int n5 = numeroAleatorio.nextInt(10);
        int n6 = numeroAleatorio.nextInt(10);
        int n7 = numeroAleatorio.nextInt(10);
        int n8 = numeroAleatorio.nextInt(10);
        int n9 = numeroAleatorio.nextInt(10);
         
        numerosContatenados = String.valueOf(n1) + String.valueOf(n2) + String.valueOf(n3)  + String.valueOf(n4) +
                              String.valueOf(n5) + String.valueOf(n6) + String.valueOf(n7) +String.valueOf(n8)  +
                              String.valueOf(n9);
        numeroGerado = numerosContatenados;
        return numeroGerado;
    }
	protected static String getCpf() {
	
	      Random random = new Random();
	      
	      int n = 9;
	      int n1 = random.nextInt(n);
	      int n2 = random.nextInt(n);
	      int n3 = random.nextInt(n);
	      int n4 = random.nextInt(n);
	      int n5 = random.nextInt(n);
	      int n6 = random.nextInt(n);
	      int n7 = random.nextInt(n);
	      int n8 = random.nextInt(n);
	      int n9 = random.nextInt(n);
	      int d1 = n9*2+n8*3+n7*4+n6*5+n5*6+n4*7+n3*8+n2*9+n1*10;
	      
	      d1 = 11 - (d1%11);
	      
	      if (d1>=10){
	    	  d1 = 0;
	      }  
	      
	      int d2 = d1*2+n9*3+n8*4+n7*5+n6*6+n5*7+n4*8+n3*9+n2*10+n1*11;	      
	      
	      d2 = 11 - (d2%11);
	      
	      if (d2>=10){
	    	  d2 = 0;
	      }  
	      
	      StringBuilder result = new StringBuilder();
	      result.append(n1);
	      result.append(n2);
	      result.append(n3);
	      result.append(".");
	      result.append(n4);
	      result.append(n5);
	      result.append(n6);
	      result.append(".");
	      result.append(n7);
	      result.append(n8);
	      result.append(n9);
	      result.append("-");
	      result.append(d1);
	      result.append(d2);
	      
	      return result.toString();
	
	}

	/**
	 * Gera numeros de CNPJ aleatorios 
	 * @return String
	 */
	protected static String getCnpj() {
	
	      Random random = new Random();
	      
	      int n = 9;
	      int n1 = random.nextInt(n);
	      int n2 = random.nextInt(n);
	      int n3 = random.nextInt(n);
	      int n4 = random.nextInt(n);
	      int n5 = random.nextInt(n);
	      int n6 = random.nextInt(n);
	      int n7 = random.nextInt(n);
	      int n8 = random.nextInt(n);
	      int n9 = 0; 
	      int n10 = 0;
	      int n11 = 0;
	      int n12 = 1;
	      int d1 = n12*2+n11*3+n10*4+n9*5+n8*6+n7*7+n6*8+n5*9+n4*2+n3*3+n2*4+n1*5;
	      
	      d1 = 11 - (d1%11);
	      
	      if (d1>=10){
	    	  d1 = 0;
	      }	  
	      
	      int d2 = d1*2+n12*3+n11*4+n10*5+n9*6+n8*7+n7*8+n6*9+n5*2+n4*3+n3*4+n2*5+n1*6;
	      d2 = 11 - (d2%11);
	      
	      if (d2>=10){
	    	  d2 = 0;
	      }
	      
	      StringBuilder result = new StringBuilder();
	      result.append(n1);
	      result.append(n2);
	      result.append(".");
	      result.append(n3);
	      result.append(n4);
	      result.append(n5);
	      result.append(".");
	      result.append(n6);
	      result.append(n7);
	      result.append(n8); 
	      result.append("/");
	      result.append(n9);
	      result.append(n10);
	      result.append(n11);
	      result.append(n12);
	      result.append("-");
	      result.append(d1);
	      result.append(d2);
	       
	      return result.toString();
	
	}
}