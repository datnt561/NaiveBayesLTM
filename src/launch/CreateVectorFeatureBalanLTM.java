package launch;

import java.util.ArrayList;
import java.util.Arrays;

import Util.Utility;
import data.BalanData;
import data.DataNSW;
import data.Document;
import data.NameDomains;
import ltm.DataKnowlLTM;
import preprocess.DataNumber;
import preprocess.Vocab;

public class CreateVectorFeatureBalanLTM {

	public static void main(String[] args) {
		NameDomains namedomain = new NameDomains("ACL2015-Chen-Datasets");
		ArrayList<String> listDomain = namedomain.getListNameDomain();
		for (String s : listDomain) {
			BalanData bldata = new BalanData(s);
			Vocab vocab = new Vocab(bldata);
			vocab.writeVocaToFile();
			DataNumber dataNumber = new DataNumber(bldata, vocab);
			dataNumber.writeReviewNoLableBalanData();

		}

	}

}
