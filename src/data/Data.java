package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Data {
	protected ArrayList<Document> data;
	private String nameDomain;

	public Data(String namedomain) {
		this.nameDomain = namedomain;
		String path = "ACL2015-Chen-Datasets/" + namedomain + ".txt";
		data = readDataFromFile(path);
	}

	public ArrayList<Document> readDataFromFile(String nameFile) {
		String line = null;
		ArrayList<Document> dataSet = new ArrayList<Document>();
		try {
			FileReader fileReader = new FileReader(nameFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			Document document = null;

			while ((line = bufferedReader.readLine()) != null) {
				if (!line.contains("Domain") && !line.contains("NEU")) {
					document = this.splitLineData(line);
					dataSet.add(document);
				}
			}

			fileReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + nameFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return dataSet;
	}

	private Document splitLineData(String line) {
		String[] review = new String[4];
		int j = 0;
		for (String s : line.split("\t")) {
			review[j] = s;
			j++;
		}

		Document lineData = new Document(review[1], review[3]);

		return lineData;
	}

	public String getNameDomain() {
		return nameDomain;
	}

	public ArrayList<String> getReviews() {
		ArrayList<String> listReview = new ArrayList<String>();
		for (Document d : data) {
			listReview.add(d.getReview());
		}
		return listReview;
	}

	public void writeDataToFile(String nameFile) {
		FileWriter writer;
		try {
			writer = new FileWriter(nameFile);
			writer.write(nameDomain);
			writer.write("\n");
			for (Document d : data) {
				writer.write(d.getLabel());
				writer.write("\t");
				writer.write(d.getReview());
				writer.write("\n");
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
