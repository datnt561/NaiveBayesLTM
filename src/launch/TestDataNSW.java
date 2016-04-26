package launch;

import data.Data;
import data.DataNSW;


public class TestDataNSW {

	public static void main(String[] args) {
		Data dataDomain = new Data("AlarmClock");
		DataNSW datasetNoStopWord = new DataNSW("AlarmClock");
		datasetNoStopWord.createDataNStopWord();
		datasetNoStopWord.writerDataToFile();

	}

}
