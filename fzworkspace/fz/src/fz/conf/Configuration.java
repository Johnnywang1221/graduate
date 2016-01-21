package fz.conf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
	
    private Properties propertie;
    private FileInputStream inputFile;
    private FileOutputStream outputFile;
	
	private int recruitUserNumber;
	private double averageCost;//以此为均值做正太分布，每个人的感知开销为正太分布
	private double interestMoneyRatio;//兴趣型和功利型参与者的比例

	private static final Configuration instance = new Configuration();
	
	private Configuration () {
		 propertie = new Properties();
	        try {
	            inputFile = new FileInputStream("src/fixedPriceExperiment.properties");
	            propertie.load(inputFile);
	            inputFile.close();
	            averageCost =  Double.parseDouble(getValue("averageCost"));
	            interestMoneyRatio = Double.parseDouble(getValue("interestMoneyRatio"));
	            recruitUserNumber = Integer.parseInt(getValue("recruitUserNumber"));
	            
	        } catch (FileNotFoundException ex) {
	            System.out.println("读取属性文件--->失败！- 原因：文件路径错误或者文件不存在");
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            System.out.println("装载文件--->失败!");
	            ex.printStackTrace();
	        }
	}
	 public Properties getPropertie() {
		return propertie;
	}
	public void setPropertie(Properties propertie) {
		this.propertie = propertie;
	}
	public FileInputStream getInputFile() {
		return inputFile;
	}
	public void setInputFile(FileInputStream inputFile) {
		this.inputFile = inputFile;
	}
	public FileOutputStream getOutputFile() {
		return outputFile;
	}
	public void setOutputFile(FileOutputStream outputFile) {
		this.outputFile = outputFile;
	}
	public int getRecruitUserNumber() {
		return recruitUserNumber;
	}
	public void setRecruitUserNumber(int recruitUserNumber) {
		this.recruitUserNumber = recruitUserNumber;
	}
	public double getAverageCost() {
		return averageCost;
	}
	public void setAverageCost(double averageCost) {
		this.averageCost = averageCost;
	}
	public double getInterestMoneyRatio() {
		return interestMoneyRatio;
	}
	public void setInterestMoneyRatio(double interestMoneyRatio) {
		this.interestMoneyRatio = interestMoneyRatio;
	}
	public static Configuration getInstance() {
		return instance;
	}
	public String getValue(String key)
	    {
	        if(propertie.containsKey(key)){
	            String value = propertie.getProperty(key);//得到某一属性的值
	            return value;
	        }
	        else 
	            return "";
	    }//end getValue(...)
	public static Configuration sharedConfiguration(){
		return instance;
	}
}
