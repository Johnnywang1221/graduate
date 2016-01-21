package fz.logic;

import java.util.ArrayList;
import java.util.List;

import fz.bean.Grid;
import fz.bean.TaskDescription;
import fz.bean.User;
import fz.conf.Configuration;

public class FixedPriceExperiment {
	private TaskDescription taskDescription;
	private Configuration configuration;
	private List<Grid> grids;
	private List<User> recruitUsers;
	public FixedPriceExperiment(){
		this.grids = new ArrayList<>();
		this.recruitUsers = new ArrayList<>();
		this.configuration = Configuration.sharedConfiguration();
	}
	void initExperiment(){
		//�����ռ䲼��
		int gridNumber = taskDescription.getGridNumber();
		for(int i = 0;i < gridNumber;i++){
			for(int j = 0;j < gridNumber;j++){
				Grid grid = new Grid();
				grid.setRow(i+1);
				grid.setCol(j+1);
				grids.add(grid);
			}
		}
		//����������
		int userNum = configuration.getRecruitUserNumber();
		
		
		
		
		
	}

}
