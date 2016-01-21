package fz.bean;

public class PSReport {
	
	public PSReport() {
		
	}
	private long id;
	private User user;
	private boolean noTask;
	private Grid grid;
	private double invest;
	private double reward;
	private int roundNumber;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isNoTask() {
		return noTask;
	}
	public void setNoTask(boolean noTask) {
		this.noTask = noTask;
	}
	public Grid getGrid() {
		return grid;
	}
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	public double getInvest() {
		return invest;
	}
	public void setInvest(double invest) {
		this.invest = invest;
	}
	public double getReward() {
		return reward;
	}
	public void setReward(double reward) {
		this.reward = reward;
	}
	public int getRoundNumber() {
		return roundNumber;
	}
	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}

}
