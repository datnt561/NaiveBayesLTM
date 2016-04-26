package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class DataNSW extends Data {
	private ArrayList<Document> newDatasets;
	public DataNSW(String namedomain) {
		super(namedomain);
		createDataNStopWord();
	}

	

	public ArrayList<Document> getNewDatasets() {
		return newDatasets;
	}

	public void createDataNStopWord() {
		newDatasets = new ArrayList<Document>();
		int i = 0;
		ArrayList<String> reviewNStopWord = removeStopWords();
		if (reviewNStopWord.isEmpty()) {
			System.out.println("ReviewNStopWord is Null");
		} else {

			for (Document d : data) {
				newDatasets.add(new Document(d.label, reviewNStopWord.get(i)));
				i++;
			}
		}

	}

	private ArrayList<String> readFileStopWords(String nameFile) {
		nameFile += ".txt";
		ArrayList<String> stopWords = new ArrayList<String>();
		String line = null;
		try {
			FileReader fileReader = new FileReader(nameFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				stopWords.add(line);
			}

			fileReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + nameFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return stopWords;
	}

	public void writerDataToFile() {
		try {
			File file = new File("word" + super.getNameDomain() + ".docs");

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fileWritter = new FileWriter(file.getName(), true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWritter);

			for (Document d : newDatasets) {

				bufferedWriter.write(d.getLabel() + "," + d.getReview() + "\n");
			}
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> removeStopWords() {
		ArrayList<String> stopWords = readFileStopWords("stopwords");
		ArrayList<String> reviews = super.getReviews();
		ArrayList<String> reviewsNoStopWord = new ArrayList<String>();
		ArrayList<String> words;
		String reviewWord;
		for (String s : reviews) {
			reviewWord = "";
			words = Util.Utility.splitReviewtoWords(s);
			words.removeAll(stopWords);
			for (String s1 : words) {
				reviewWord += s1 + " ";
			}

			reviewsNoStopWord.add(reviewWord);
		}

		return reviewsNoStopWord;
	}

	public ArrayList<String> getReviewsNewDatasets() {
		ArrayList<String> reviewsNewData = new ArrayList<String>();
		for (Document d : newDatasets) {
			reviewsNewData.add(d.review.trim());
		}
		return reviewsNewData;
	}

	public ArrayList<String> getLables() {
		ArrayList<String> lables = new ArrayList<String>();
		for (Document d : newDatasets) {
			lables.add(d.getLabel());
		}
		return lables;
	}
	
	public void printDataNSW(){
		for (Document d : newDatasets) {
			System.out.println(d.label + "," + d.review);
		}
	}
}
