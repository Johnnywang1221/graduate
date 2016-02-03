package fz.logic;

import java.util.Date;

public class FixedPriceEpmtEngine {
	private FixedPriceExperiment experiment;
	public FixedPriceEpmtEngine(){
		this.experiment = new FixedPriceExperiment();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello world");
		FixedPriceEpmtEngine engine = new FixedPriceEpmtEngine();
		FixedPriceExperiment experiment = engine.experiment;
		System.out.println(new Date().toString()+ "experiment starts-----------");
		experiment.initExperiment();
		experiment.participatorySensing();
		System.out.println(new Date().toString()+"experiment ends-----------------");
		

	} 

}
