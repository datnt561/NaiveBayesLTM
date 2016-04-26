package launch;

import java.util.ArrayList;

import ltm.DataLTM;

public class TestDataLTM {

	public static void main(String[] args) {

		DataLTM topicdist = new DataLTM("DomainModels/AlarmClock/AlarmClock.dtopicdist", 5);
		ArrayList<ArrayList<Integer>> topNtopic = topicdist.getNData();
		System.out.println("bcv");
		for(ArrayList<Integer> ntopic : topNtopic){
			System.out.println(ntopic.toString());
		}
	}

}
