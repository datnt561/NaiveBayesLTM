package ltm;

import java.util.ArrayList;

import preprocess.Vocab;

public class DataKnowlLTM {
	ArrayList<ArrayList<Integer>> dataKnowl;
	ArrayList<ArrayList<String>> dataKnowlWord;
	

	DataLTM ntwdist;
	DataLTM dtopicdist;
	
	public DataKnowlLTM(String domain, int ntopic, int nwordInTopic){
		Vocab vocab = new Vocab(domain);
		dtopicdist = new DataLTM("DomainModels/" + domain + "/" + domain + ".dtopicdist", ntopic);
		ntwdist = new DataLTM("DomainModels/" + domain + "/" + domain + ".twdist", nwordInTopic);
		ArrayList<ArrayList<Integer>> nTopicInDocuments = dtopicdist.getNData();
		ArrayList<ArrayList<Integer>> nWordTopic = ntwdist.getNData();
		
		dataKnowl = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> idsWordInDocument;
		for(ArrayList<Integer> listIDsTopic : nTopicInDocuments){
			idsWordInDocument = new ArrayList<Integer>();
			for(Integer idword : listIDsTopic){
				idsWordInDocument.addAll(nWordTopic.get(idword-1));
			}
			dataKnowl.add(idsWordInDocument);
		}
		convertdataNumToWord(vocab);
		
	}
	public DataKnowlLTM(String domain, double signtopic, double signwordInTopic){
		Vocab vocab = new Vocab(domain);
		dtopicdist = new DataLTM("DomainModels/" + domain + "/" + domain + ".dtopicdist", signtopic);
		ntwdist = new DataLTM("DomainModels/" + domain + "/" + domain + ".twdist", signwordInTopic);
		ArrayList<ArrayList<Integer>> nTopicInDocuments = dtopicdist.getNData();
		ArrayList<ArrayList<Integer>> nWordTopic = ntwdist.getNData();
		
		dataKnowl = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> idsWordInDocument;
		for(ArrayList<Integer> listIDsTopic : nTopicInDocuments){
			idsWordInDocument = new ArrayList<Integer>();
			for(Integer idword : listIDsTopic){
				idsWordInDocument.addAll(nWordTopic.get(idword-1));
			}
			dataKnowl.add(idsWordInDocument);
		}
		convertdataNumToWord(vocab);
		
	}
	
	public ArrayList<ArrayList<Integer>> getDataKnol(){
		return dataKnowl;
	}
	public ArrayList<ArrayList<String>> getDataKnowlWord() {
		return dataKnowlWord;
	}
	
	private void convertdataNumToWord(Vocab vocab){
		dataKnowlWord = new ArrayList<ArrayList<String>>();
		String word;
		ArrayList<String> lineTopicWord;
		for(ArrayList<Integer> listID : dataKnowl){
			lineTopicWord = new ArrayList<String>();
			for(Integer id : listID){
				word = vocab.searchWordByID(id);
				if(word != null)
					lineTopicWord.add(word);
					
			}
			dataKnowlWord.add(lineTopicWord);
		}
	}
	
	public void printDataLTM(){
		for(ArrayList<String> listIDsDocument : dataKnowlWord)
			System.out.println(listIDsDocument);
	}
	
}
