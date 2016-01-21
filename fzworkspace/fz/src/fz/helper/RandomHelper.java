package fz.helper;

import java.util.Random;

public class RandomHelper {
	public static boolean rollWithProbability(double probability){
		Random random = new Random();
		double roll = random.nextDouble();
		
		boolean yesOrNo = (roll<=probability);
		return yesOrNo;
	}

}
