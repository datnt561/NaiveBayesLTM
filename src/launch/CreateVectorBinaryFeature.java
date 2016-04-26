package launch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import Util.Utility;
import data.Data;
import data.DataNSW;
import data.DataNoStopWord;
import data.Document;
import data.NameDomains;
import preprocess.Voca;
import preprocess.Vocab;

public class CreateVectorBinaryFeature {
	public static void main(String[] args) {

		NameDomains namedomain = new NameDomains("ACL2015-Chen-Datasets");
		ArrayList<String> listDomain = namedomain.getListNameDomain();
		for (String s : listDomain) {
			Data dataDomain = new Data(s);

			DataNSW datasetNoStopWord = new DataNSW(s);

			datasetNoStopWord.createDataNStopWord();
			Vocab voca = new Vocab(datasetNoStopWord);
			// voca.writeVocaToFile();
			
			// tao vocab
			ArrayList<String> vocab = voca.getWordInVoca();
			
			//
			ArrayList<String> vectorFeature = new ArrayList<String>();
			StringBuffer sb ;
			ArrayList<String> allReview = datasetNoStopWord.getReviewsNewDatasets();
			ArrayList<String> lables = datasetNoStopWord.getLables();
			ArrayList<String> words;
			int i = 0;
			for(String review : allReview){
				sb = new StringBuffer();
				words = new ArrayList<String>(Arrays.asList(review.split(" ")));
				for(String w : vocab){
					if(words.contains(w))
						sb.append(1);
					else
						sb.append(0);
					sb.append(",");
				}
					
				vectorFeature.add(sb.toString() + lables.get(i));
				i++;
			}
			Utility.writerDataToARFF(vectorFeature, vocab, s);
		}
	}
	

}
