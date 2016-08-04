package br.com.system.gestaoConstrucaoCivil.criarObjecto.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GerarInformacao{

	private static GerarInformacao instance;
	private GerarInformacao() {
		// TODO Auto-generated constructor stub
	}
	 public static GerarInformacao getInstance() {
	      if (instance == null)
	         instance = new GerarInformacao();
	      return instance;
	   }
   
	
	
	
	public static String getEmail()
    {
    	
		return "";
    }
	public String getNome()
	{
		return randomNome();
	}
	private String randomNome()
	{
		ArrayList<String> nomes = listNomes();
		
		Random gerador = new Random();
		
		return nomes.get(gerador.nextInt(nomes.size()));
	}
	private ArrayList<String> listNomes()
	{
        ArrayList<String> nomes = new ArrayList<>();  
		try {
			BufferedReader br = new LerArquivo().lerArquivo("nomes.txt");
			String linha = br.readLine();
			while(linha != null) {
				nomes.add(linha);
				linha = br.readLine();
			}
          } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nomes;
	}
	
	
	
	public static String getRg()
	{
		return Util.getInstance().getRg();
	}
	public static String getCnpj()
	{
		return Util.getInstance().getCnpj();
	}
	public static String getCpf()
	{
		return Util.getInstance().getCpf();
	}

}
