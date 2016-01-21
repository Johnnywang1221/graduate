package fz.logic;

public class FixedPriceEpmtEngine {
	private FixedPriceExperiment experiment;
	public FixedPriceEpmtEngine(){
		this.experiment = new FixedPriceExperiment();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello world");
		FixedPriceEpmtEngine engine = new FixedPriceEpmtEngine();
		engine.experiment.initExperiment();
		

	} 

}
