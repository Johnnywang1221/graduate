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
	private double averageCost;//�Դ�Ϊ��ֵ����̫�ֲ���ÿ���˵ĸ�֪����Ϊ��̫�ֲ�
	private double interestMoneyRatio;//��Ȥ�ͺ͹����Ͳ����ߵı���

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
	            System.out.println("��ȡ�����ļ�--->ʧ�ܣ�- ԭ���ļ�·����������ļ�������");
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            System.out.println("װ���ļ�--->ʧ��!");
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
	            String value = propertie.getProperty(key);//�õ�ĳһ���Ե�ֵ
	            return value;
	        }
	        else 
	            return "";
	    }//end getValue(...)
	public static Configuration sharedConfiguration(){
		return instance;
	}
}
