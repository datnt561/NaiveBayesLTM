package data;

import java.util.ArrayList;

public class BalanData  {
	private ArrayList<Document> balanDatasets;
	private String domain;
	private int size;

	public BalanData(String domain) {
		DataNSW dataNSW = new DataNSW(domain);
		this.domain = dataNSW.getNameDomain();
		ArrayList<Document> documentNEG = dataNSW.filterDocumentByLabel("NEG");
		ArrayList<Document> documentPOS = dataNSW.filterDocumentByLabel("POS");
		size = documentNEG.size();

		balanDatasets = new ArrayList<Document>();

		balanDatasets.addAll(documentNEG);
		for (int i = 0; i < size; i++) {
			System.out.println(documentPOS.get(i));
			balanDatasets.add(new Document(documentPOS.get(i).label, documentPOS.get(i).review));
		}
	}


	public ArrayList<String> getReviewBalan() {
		ArrayList<String> reviewInBalan = new ArrayList<String>();

		for (Document d : balanDatasets) {
			reviewInBalan.add(d.getReview());
		}

		return reviewInBalan;
	}

	public ArrayList<String> getLabelsInBalan() {
		ArrayList<String> labelInBalan = new ArrayList<String>();

		for (Document d : balanDatasets) {
			labelInBalan.add(d.getLabel());
		}

		return labelInBalan;
	}

	public ArrayList<Document> getBalanDatasets() {
		return balanDatasets;
	}

	public String getDomain() {
		return domain;
	}

	public int getSize() {
		return size;
	}
}
