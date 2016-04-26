package preprocess;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import data.DataNSW;

public class Vocab {
	private HashMap<Integer, String> vocab;
	private String domain;

	public Vocab(String nameFile) {
		domain = nameFile;
		vocab = Util.VocabUtil.readFileVocab(nameFile);
	}

	public Vocab(DataNSW d) {
		domain = d.getNameDomain();
		vocab = new HashMap<Integer, String>();
		Set<String> reviews = createVoca(d);
		for (String s : reviews) {
			if (!s.isEmpty())
				addWord(s);
		}
	}

	public String searchWordByID(Integer id) {
		if (vocab.containsKey(id))
			return vocab.get(id);
		else
			return null;
	}

	public int getIndexWord(String word) throws Exception {
		Set<Entry<Integer, String>> listWord = vocab.entrySet();
		System.out.println(listWord.toString());
		for (Entry<Integer, String> e : listWord) {
			System.out.println(e.getValue());
			if (e.getValue().equals(word))
				return e.getKey();
		}
		System.out.println("Error" + word + "sd");
		throw new Exception();

	}

	public Set<Entry<Integer, String>> entrySet() {
		return vocab.entrySet();
	}

	public void addWord(String word) {
		if (!vocab.containsValue(word)) {
			vocab.put(nextKey(), word);
		}
	}

	private int nextKey() {
		if (vocab.isEmpty())
			return 0;
		return vocab.size();
	}

	public ArrayList<String> getWordInVoca() {
		ArrayList<String> wordInVoca = new ArrayList<String>();
		for (Entry<Integer, String> e : vocab.entrySet()) {
			wordInVoca.add(e.getValue());
		}
		return wordInVoca;
	}

	public Set<String> createVoca(DataNSW d) {
		ArrayList<String> reviews = d.getReviewsNewDatasets();

		Set<String> wordInReviews = new HashSet<String>();
		String[] words;
		for (String s : reviews) {
			words = s.split(" ");
			wordInReviews.addAll(Arrays.asList(words));
		}

		return wordInReviews;
	}

	public void writeVocaToFile() {

		FileWriter writer;
		try {
			writer = new FileWriter(domain + ".vocab");
			List<Integer> ids = new ArrayList<Integer>(vocab.keySet());
			Collections.sort(ids);
			String review = "";
			for (Integer i : ids) {
				review += i + ":" + vocab.get(i) + "\n";
				System.out.println(review);

			}
			writer.write(review);

			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
