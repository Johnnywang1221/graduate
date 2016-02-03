package fz.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.commons.math3.distribution.NormalDistribution;

import fz.bean.Direction;
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
	public static List<Integer> rollNIntBetweenIncludeBoth(int min, int max, int n){

		List<Integer> integers = new ArrayList<>();
		for(int i = min; i<= max;i++){
			integers.add(i);
		}
		Collections.shuffle(integers);
		List<Integer> result = integers.subList(0, n);
		
		return result;
	}
	public static Direction nextDirection(){
		int directionInt = rollIntBetweenIncludeBoth(0, 4);
		switch (directionInt) {
		case 0:
			return Direction.None;
		case 1:
			return Direction.Top;
		case 2:
			return Direction.Left;
		case 3:
			return Direction.Bottom;
		case 4:
			return Direction.Right;
		default:
			break;
		}
		return Direction.None;
	}

}
