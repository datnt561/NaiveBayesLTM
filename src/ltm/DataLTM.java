package ltm;

import java.util.ArrayList;
import java.util.HashMap;

import Util.LtmUtil;

public class DataLTM {
	private String domain;
	private ArrayList<HashMap<Integer, Double>> dataDist;
	private int sizeDocs;
	private int numberTopic;
	private ArrayList<ArrayList<Integer>> ndata;

	public DataLTM(String nameFile, int n) {
		// n is number topic
		domain = nameFile;
		dataDist = Util.LtmUtil.readFileData(nameFile);
		sizeDocs = dataDist.size();
		numberTopic = dataDist.get(0).size();
		ndata = LtmUtil.getNTopID(dataDist, n);
	}
	
	public DataLTM(String nameFile, double anpha){
		domain = nameFile;
		dataDist = Util.LtmUtil.readFileData(nameFile);
		sizeDocs = dataDist.size();
		numberTopic = dataDist.get(0).size();
		ndata = LtmUtil.getNTopIDBySign(dataDist, anpha);
	}

	public int getSizeDocument() {
		return sizeDocs;
	}

	public int getNumberTopic() {
		return numberTopic;
	}

	public ArrayList<ArrayList<Integer>> getNData() {
		return ndata;
	}
	
	public String getDomain(){
		return domain;
	}

}
