package Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class LtmUtil {

	public static HashMap<Integer, Double> sortListDoubleByID(HashMap<Integer, Double> proba) {

		List list = new LinkedList(proba.entrySet());
		// Defined Custom Comparator here
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
			}
		});

		// Here I am copying the sorted list in HashMap
		// using LinkedHashMap to preserve the insertion order
		HashMap sortedHashMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}
		return sortedHashMap;

	}

	public static ArrayList<ArrayList<Integer>> getNTopID(ArrayList<HashMap<Integer, Double>> dtopic, int n) {
		ArrayList<ArrayList<Integer>> listNTopID = new ArrayList<ArrayList<Integer>>();
		HashMap<Integer, Double> sortedHashMap;
		ArrayList<Integer> ids = null;
		int index;
		for (HashMap<Integer, Double> hm : dtopic) {
			sortedHashMap = sortListDoubleByID(hm);
			if (n <= sortedHashMap.size()) {
				ids = new ArrayList<Integer>(sortedHashMap.keySet());
				index = n;
				while (index < ids.size()) {
					ids.remove(index);
				}
			} else {
				System.out.println("Document just has " + sortedHashMap.size());
			}
			listNTopID.add(ids);
		}

		return listNTopID;
	}
	public static ArrayList<ArrayList<Integer>> getNTopIDBySign(ArrayList<HashMap<Integer, Double>> dtopic, double sign) {
		ArrayList<ArrayList<Integer>> listNTopID = new ArrayList<ArrayList<Integer>>();
		HashMap<Integer, Double> sortedHashMap;
		ArrayList<Integer> ids = null;
		for (HashMap<Integer, Double> hm : dtopic) {
			sortedHashMap = sortListDoubleByID(hm);
			ids = new ArrayList<Integer>();
			for(Entry<Integer, Double> e : sortedHashMap.entrySet()){
				if(e.getValue() > sign){
					ids.add(e.getKey());
				}
				else{
					break;
				}
			}
			
			listNTopID.add(ids);
		}

		return listNTopID;
	}

	public static ArrayList<HashMap<Integer, Double>> readFileData(String nameFile) {
		HashMap<Integer, Double> topicdocumet;
		ArrayList<HashMap<Integer, Double>> dtopicdist = new ArrayList<HashMap<Integer, Double>>();
		String line = null;
		try {
			FileReader fr = new FileReader(nameFile);
			BufferedReader br = new BufferedReader(fr);
			int i;
			String[] probaTopic;
			int j = 0;
			while ((line = br.readLine()) != null) {
				i = 1;
				topicdocumet = new HashMap<Integer, Double>();
				probaTopic = line.split(" ");
				if(probaTopic.length == 0){
					System.out.println("\n\nnnasdgfdreg  ");
					break;
				}
				else{
					System.out.println("Length" + probaTopic.length);
				}
					
				for (String s : probaTopic) {
					topicdocumet.put(new Integer(i), Double.valueOf(s));
					i++;
				}
				dtopicdist.add(topicdocumet);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtopicdist;
	}

}
