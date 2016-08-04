package br.com.system.gestaoConstrucaoCivil.criarObjecto.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class LerArquivo {

  
  public BufferedReader lerArquivo(String arquivo) throws IOException
  {
	 FileInputStream stream;
	 BufferedReader br;	 
	 try {
		stream = new FileInputStream("../gestaoConstrucaoCivil/src/test/java/br/com/system/gestaoConstrucaoCivil/criarObjecto/util/"+arquivo);
	
	 InputStreamReader reader = new InputStreamReader(stream);
	  br = new BufferedReader(reader);
	  return br;
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 return null;
  }
 
}



