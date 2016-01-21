package fz.helper;

import java.util.Random;

import org.apache.commons.math3.distribution.NormalDistribution;

import fz.conf.Configuration;


public class RandomHelper {
	private static NormalDistribution gaussian = new NormalDistribution(Configuration.sharedConfiguration().getAverageCost(), 0.75);
	private static Random random = new Random();
	public static boolean rollWithProbability(double probability){
		Random random = new Random();
		double roll = random.nextDouble();
		
		boolean yesOrNo = (roll<=probability);
		return yesOrNo;
	}
	
	public static double nextGaussian(){
		double sample = gaussian.sample();
		return sample>0?sample:0;
	}
	public static int rollIntBetweenIncludeBoth(int min, int max){

		int randomNum = min + random.nextInt((max - min) + 1);
		return randomNum;
	}

}
