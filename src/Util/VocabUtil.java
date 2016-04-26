package Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class VocabUtil {
	
	public static HashMap<Integer, String> readFileVocab(String nameFile){
		HashMap<Integer, String> hm = new HashMap<Integer, String>();
		try{
			FileReader fr = new FileReader("DomainModels/" + nameFile + "/" + nameFile + ".vocab");
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			String[] splitline;
			while((line = br.readLine())!= null){
				splitline = line.split(":");
				hm.put(Integer.valueOf(splitline[0]), splitline[1]);
			}
			
			return hm;
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
