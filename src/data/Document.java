package data;

import java.util.ArrayList;

public class Document {
	String label;
	String review;

	public Document(String label, String review) {
		this.label = label;
		this.review = review;
	}

	public String getLabel() {
		return label;
	}

	public String getReview() {
		return review;
	}
	
	public void addListWordToReview(ArrayList<String> listtopic){
		for(String topic : listtopic){
			review += topic + " ";
		}
	}

}
