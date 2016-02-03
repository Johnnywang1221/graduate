package fz.logic;

import java.util.Date;

public class DBAExperimentEngine {
	private  DBAExperiment experiment;
	public  DBAExperimentEngine(){
		this.experiment = new  DBAExperiment();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello world");
		 DBAExperimentEngine engine = new  DBAExperimentEngine();
		 DBAExperiment experiment = engine.experiment;
		System.out.println(new Date().toString()+ "experiment starts-----------");
		experiment.initExperiment();
		experiment.participatorySensing();
		System.out.println(new Date().toString()+"experiment ends-----------------");
		

	} 


}
