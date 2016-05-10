package launch;

import java.util.ArrayList;
import java.util.Arrays;

import Util.Utility;
import data.BalanData;
import data.DataNSW;
import data.Document;
import data.NameDomains;
import ltm.DataKnowlLTM;
import preprocess.Vocab;

public class CreateBalanLTM {

	public static void main(String[] args) {
		NameDomains namedomain = new NameDomains("ACL2015-Chen-Datasets");
		ArrayList<String> listDomain = namedomain.getListNameDomain();
		for (String s : listDomain) {
			// Data data = new Data(s);
			BalanData bldata = new BalanData(s);
			// dataNSW.createDataNStopWord();
			Vocab vocab = new Vocab(bldata);

			DataKnowlLTM dataKnowl = new DataKnowlLTM(s, 0.04, 0.0001);
			ArrayList<ArrayList<String>> listtopicInreviews = dataKnowl.getDataKnowlWord();
			int i = 0;
			for (Document d : bldata.getBalanDatasets()) {
				d.addListWordToReview(listtopicInreviews.get(i));
				i++;

			}
			ArrayList<String> vectorFeature = new ArrayList<String>();
			StringBuffer sb;
			ArrayList<String> allReview = bldata.getReviewBalan();
			ArrayList<String> lables = bldata.getLabelsInBalan();
			ArrayList<String> words;
			int i1 = 0;
			ArrayList<String> listwordInVocab = vocab.getWordInVoca();
			for (String review : allReview) {
				sb = new StringBuffer();
				words = new ArrayList<String>(Arrays.asList(review.split(" ")));
				for (String w : listwordInVocab) {
					if (words.contains(w))
						sb.append(1);
					else
						sb.append(0);
					sb.append(",");
				}

				vectorFeature.add(sb.toString() + lables.get(i1));
				i1++;
			}
			Utility.writerDataToARFF(vectorFeature, listwordInVocab, s);

		}
	}

}
