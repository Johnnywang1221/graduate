package fz.logic;

import java.util.Date;

public class BiddingExperimentEngine {
	private BiddingExperiment experiment;
	public BiddingExperimentEngine(){
		this.experiment = new BiddingExperiment();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello world");
		BiddingExperimentEngine engine = new BiddingExperimentEngine();
		BiddingExperiment experiment = engine.experiment;
		System.out.println(new Date().toString()+ "experiment starts-----------");
		experiment.initExperiment();
		experiment.participatorySensing();
		System.out.println(new Date().toString()+"experiment ends-----------------");
		

	} 

}
