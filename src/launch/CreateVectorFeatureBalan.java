package launch;

import java.util.ArrayList;
import java.util.Arrays;

import Util.Utility;
import data.BalanData;
import data.Data;
import data.DataNSW;
import data.NameDomains;
import preprocess.Vocab;

public class CreateVectorFeatureBalan {
	public static void main(String[] agrs) {
		NameDomains namedomain = new NameDomains("ACL2015-Chen-Datasets");
		ArrayList<String> listDomain = namedomain.getListNameDomain();
		for (String s : listDomain) {
			BalanData blData = new BalanData(s);
			Vocab voca = new Vocab(blData);
			//voca.writeVocaToFile();

			// tao vocab
			ArrayList<String> vocab = voca.getWordInVoca();

			//
			ArrayList<String> vectorFeature = new ArrayList<String>();
			StringBuffer sb;
			ArrayList<String> allReview = blData.getReviewBalan();
			//Util.Utility.writerReview(s, allReview);
			ArrayList<String> lables = blData.getLabelsInBalan();
			ArrayList<String> words;
			int i = 0;
			for (String review : allReview) {
				//System.out.println(review);
				sb = new StringBuffer();
				words = new ArrayList<String>(Arrays.asList(review.split(" ")));
				for (String w : vocab) {
					if (words.contains(w))
						sb.append(1);
					else
						sb.append(0);
					sb.append(",");
				}

				vectorFeature.add(sb.toString() + lables.get(i));
				i++;
			}
			Utility.writerDataToARFF(vectorFeature, vocab, s);
			//Utility.writerDataToCSV(vectorFeature, vocab, s);
		}
	
////		BalanData blData = new BalanData(s);
////		Vocab voca = new Vocab(blData);
////		voca.writeVocaToFile();
//		String s = "AlarmClock";
		
	}
}
