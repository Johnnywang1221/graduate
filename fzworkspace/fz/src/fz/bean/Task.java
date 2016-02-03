package fz.bean;

import fz.bean.Location;

public class Task implements Comparable {
	private long id;
	private long gridId;
	private long taskDescriptionId;
	
	private int roundNumber;//µÚ¼¸ÂÖ
	private double subBudget;
	private int dataItemNumber;
	private int remainDataNumber;
	private double remainSubBudget;
	private int currentTimeSlot;
	private Grid gridBelongTo;
	private boolean completed;
	public double remainBudget4DataItem(){
		return remainSubBudget/remainDataNumber;
	}
	@Override
	public int compareTo(Object task) {
        double compareBudget = ((Task)task).remainBudget4DataItem();
        /* For Ascending order*/
        return Double.compare(compareBudget,this.remainBudget4DataItem());

        /* For Descending order do like this */
        //return compareage-this.studentage;
    }
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public Grid getGridBelongTo() {
		return gridBelongTo;
	}
	public void setGridBelongTo(Grid gridBelongTo) {
		this.gridBelongTo = gridBelongTo;
	}
	public int getCurrentTimeSlot() {
		return currentTimeSlot;
	}
	public void setCurrentTimeSlot(int currentTimeSlot) {
		this.currentTimeSlot = currentTimeSlot;
	}
	public Task(long gridId, long taskDescriptionId, int roundNumber, double subBudget, int dataItemNumber) {
		super();
		this.gridId = gridId;
		this.taskDescriptionId = taskDescriptionId;
		this.roundNumber = roundNumber;
		this.subBudget = subBudget;
		this.dataItemNumber = dataItemNumber;
	}
	public int getRemainDataNumber() {
		return remainDataNumber;
	}
	public void setRemainDataNumber(int remainDataNumber) {
		this.remainDataNumber = remainDataNumber;
	}
	public double getRemainSubBudget() {
		return remainSubBudget;
	}
	public void setRemainSubBudget(double remainSubBudget) {
		this.remainSubBudget = remainSubBudget;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Task() {
	}
	public long getGridId() {
		return gridId;
	}
	public void setGridId(long gridId) {
		this.gridId = gridId;
	}
	public long getTaskDescriptionId() {
		return taskDescriptionId;
	}
	public void setTaskDescriptionId(long taskDescriptionId) {
		this.taskDescriptionId = taskDescriptionId;
	}
	public int getRoundNumber() {
		return roundNumber;
	}
	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}
	public double getSubBudget() {
		return subBudget;
	}
	public void setSubBudget(double subBudget) {
		this.subBudget = subBudget;
	}
	public int getDataItemNumber() {
		return dataItemNumber;
	}
	public void setDataItemNumber(int dataItemNumber) {
		this.dataItemNumber = dataItemNumber;
	}
	
	
	

}
