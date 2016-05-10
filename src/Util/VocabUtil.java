package Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
	
	public static HashMap<Integer, String> newVocab(ArrayList<String> wordPOS, ArrayList<String> wordNEG , int n){
		HashMap<Integer, String> vocab = new HashMap<Integer, String>();
		
		HashMap<String, int[]> frequencyWord = getFrequencyWord(wordPOS, wordNEG);
		
		HashMap<String, Integer> listWordFrequency = absfrequencyWord(frequencyWord);
		int i = 0;
		for(Entry<String,Integer> entry : listWordFrequency.entrySet()){
			vocab.put(i, entry.getKey());
			i++;
			if( i == n)
				break;
		}
		return vocab;
	}
	
	public static HashMap<String, int[]> getFrequencyWord(ArrayList<String> wordPOS, ArrayList<String> wordNEG){
		Set<String> listWord = new HashSet<String>(wordPOS);
		listWord.addAll(wordNEG);
		int[] timeWord;
		HashMap<String, int[]> frequencyWord = new HashMap<String, int[]>();
		for(String s : listWord){
			timeWord = new int[2];
			timeWord[0] = Collections.frequency(wordPOS, s);
			timeWord[1] = Collections.frequency(wordNEG, s);
			frequencyWord.put(s, timeWord);
		}
		return frequencyWord;
	}
	
	public static HashMap<String, Integer> absfrequencyWord(HashMap<String, int[]> fw){
		HashMap<String, Integer> absfrequencyWord = new HashMap<String, Integer>();
		
		Set<Entry<String, int[]>> setWord = fw.entrySet();
		int[] value;
		for(Entry<String, int[]> entry : fw.entrySet()){
			value = entry.getValue();
			absfrequencyWord.put(entry.getKey(),Math.abs(value[0] - value[1]));
		}
		
		absfrequencyWord = sortHashMapByValue(absfrequencyWord);
		
		return absfrequencyWord;
		
	}
	
	public static HashMap<String, Integer> sortHashMapByValue(HashMap<String, Integer> unsortHashMap){
		List list = new LinkedList(unsortHashMap.entrySet());
		Collections.sort(list, new Comparator() {
	        public int compare(Object o1, Object o2) {
	            return ((Map.Entry<String, Integer>) o2).getValue().compareTo(
	                    ((Map.Entry<String, Integer>) o1).getValue());
	        }
	    });
		HashMap sortedHashMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}
		return sortedHashMap;
	}
	
}
