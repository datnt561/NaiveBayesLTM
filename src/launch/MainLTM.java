package launch;

import java.util.ArrayList;
import java.util.Scanner;

import data.Data;
import data.DataNSW;
import data.Document;
import ltm.DataKnowlLTM;
import ltm.DataLTM;

public class MainLTM {
	public static void main(String[] args) {
		DataKnowlLTM data = new DataKnowlLTM("AlarmClock", 5, 10);
		
		data.printDataLTM();
		System.out.println(data.toString());
		
		ArrayList<ArrayList<String>> listtopicInreviews = data.getDataKnowlWord();
		Data dataDomain = new Data("AlarmClock");
		DataNSW datasetNoStopWord = new DataNSW("AlarmClock");
		datasetNoStopWord.createDataNStopWord();
		int i = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("nhap");
		int c = sc.nextInt();
		for(Document d : datasetNoStopWord.getNewDatasets()){
			d.addListWordToReview(listtopicInreviews.get(i));
			i++;
			
		}
		
		datasetNoStopWord.writerDataToFile();
		datasetNoStopWord.printDataNSW();
	}

}
