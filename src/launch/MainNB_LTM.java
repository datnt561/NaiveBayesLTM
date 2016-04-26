package launch;

import java.util.ArrayList;
import java.util.Arrays;

import Util.Utility;
import data.Data;
import data.DataNSW;
import data.Document;
import ltm.DataKnowlLTM;
import preprocess.Vocab;

public class MainNB_LTM {
	
	public static void main(String[] args){
		Data data = new Data("AlarmClock");
		DataNSW dataNSW = new DataNSW("AlarmClock");
		dataNSW.createDataNStopWord();
		Vocab vocab = new Vocab(dataNSW);
		
		DataKnowlLTM dataKnowl = new DataKnowlLTM("AlarmClock", 5, 10);
		ArrayList<ArrayList<String>> listtopicInreviews = dataKnowl.getDataKnowlWord();
		int i = 0;
		for(Document d : dataNSW.getNewDatasets()){
			d.addListWordToReview(listtopicInreviews.get(i));
			i++;
			
		}
		dataNSW.writerDataToFile();
		
		ArrayList<String> vectorFeature = new ArrayList<String>();
		StringBuffer sb ;
		ArrayList<String> allReview = dataNSW.getReviewsNewDatasets();
		ArrayList<String> lables = dataNSW.getLables();
		ArrayList<String> words;
		int i1 = 0;
		ArrayList<String> listwordInVocab = vocab.getWordInVoca();
		for(String review : allReview){
			sb = new StringBuffer();
			words = new ArrayList<String>(Arrays.asList(review.split(" ")));
			for(String w : listwordInVocab){
				if(words.contains(w))
					sb.append(1);
				else
					sb.append(0);
				sb.append(",");
			}
				
			vectorFeature.add(sb.toString() + lables.get(i1));
			i1++;
		}
		Utility.writerDataToARFF(vectorFeature, listwordInVocab, "AlarmClock");
	}
	

}
