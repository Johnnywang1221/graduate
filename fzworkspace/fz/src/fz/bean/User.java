package fz.bean;



public class User implements Comparable {

	private long id;
	private Grid gridBelongto;
	private long gridId;
	public long getGridId() {
		return gridId;
	}
	public void setGridId(long gridId) {
		this.gridId = gridId;
	}
	private boolean quit;
	private double psCost;
	private UserParticipateType participateType;
	private double irrThreshold;//退出时的投资回报率阈值
	@Override
	public int compareTo(Object user) {
        double compareCost = ((User)user).getPsCost();
        /* For Ascending order*/
        return Double.compare(this.psCost, compareCost);

        /* For Descending order do like this */
        //return compareage-this.studentage;
    }
	
	public User() {
	
	}
	public User(UserParticipateType type, double psCost, double th,Grid grid) {
		this.participateType = type;
		this.psCost = psCost;
		this.quit = false;
		this.irrThreshold = th;
		this.gridBelongto = grid;
		this.gridId = grid.getId();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Grid getGridBelongto() {
		return gridBelongto;
	}
	public void setGridBelongto(Grid gridBelongto) {
		this.gridBelongto = gridBelongto;
	}

	public boolean isQuit() {
		return quit;
	}
	public void setQuit(boolean quit) {
		this.quit = quit;
	}
	public double getPsCost() {
		return psCost;
	}
	public void setPsCost(double psCost) {
		this.psCost = psCost;
	}
	public UserParticipateType getParticipateType() {
		return participateType;
	}
	public void setParticipateType(UserParticipateType participateType) {
		this.participateType = participateType;
	}
	public double getIrrThreshold() {
		return irrThreshold;
	}
	public void setIrrThreshold(double irrThreshold) {
		this.irrThreshold = irrThreshold;
	}
	



}
