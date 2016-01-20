package fz.logic;

import java.util.ArrayList;
import java.util.List;

import fz.bean.Grid;
import fz.bean.TaskDescription;
import fz.bean.User;

public class FixedPriceExperiment {
	private TaskDescription taskDescription;
	
	private List<Grid> grids;
	private List<User> recruitUsers;
	public FixedPriceExperiment(){
		this.grids = new ArrayList<>();
		this.recruitUsers = new ArrayList<>();
	}
	void initExperiment(){
		//建立空间布局
		int gridNumber = taskDescription.getGridNumber();
		for(int i = 0;i < gridNumber;i++){
			for(int j = 0;j < gridNumber;j++){
				Grid grid = new Grid();
				grid.setRow(i+1);
				grid.setCol(j+1);
				grids.add(grid);
			}
		}
		//参与者生成
		int userNum = taskDescription.getRecruitUserNum();
		
		
		
	}

}
