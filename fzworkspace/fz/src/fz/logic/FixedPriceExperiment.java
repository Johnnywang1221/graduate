package fz.logic;

import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.PUBLIC_MEMBER;

import fz.bean.Grid;
import fz.bean.TaskDescription;
import fz.bean.User;
import fz.bean.UserParticipateType;
import fz.conf.Configuration;
import fz.dao.GridDao;
import fz.dao.UserDao;
import fz.helper.RandomHelper;

public class FixedPriceExperiment {
	private TaskDescription taskDescription;
	private Configuration configuration;
	private List<Grid> grids;
	private List<User> recruitUsers;
	private int roundNumber;
	public FixedPriceExperiment(){
		this.grids = new ArrayList<>();
		this.recruitUsers = new ArrayList<>();
		this.configuration = Configuration.sharedConfiguration();
		roundNumber = 0;
	}
	void initExperiment(){
		//建立空间布局
		int gridNumber = configuration.getGridNumber();
		for(int i = 0;i < gridNumber;i++){
			for(int j = 0;j < gridNumber;j++){
				Grid grid = new Grid();
				grid.setRow(i+1);
				grid.setCol(j+1);
				grids.add(grid);
			}
		}
		GridDao gridDao = new GridDao();
		gridDao.addGrids(grids);
		//参与者生成
		int userNum = configuration.getRecruitUserNumber();
		//imr为兴趣型用户和功利性用户的数量比值，ir为兴趣型用户占总体比值 
		double imr = configuration.getInterestMoneyRatio();
		double ir = 1-1/(1+imr);
		for(int i = 0;i<userNum;i++){
			
			User user = null;
			double psCost = RandomHelper.nextGaussian();
			int gridIdx = RandomHelper.rollIntBetweenIncludeBoth(0, gridNumber*gridNumber-1);
			if (RandomHelper.rollWithProbability(ir)) {
				//生成兴趣型用户
				user = new User(UserParticipateType.InterestType, psCost, configuration.getRoIThresholdInterestType(), (Grid)grids.get(gridIdx));
				
			}else {
				user = new User(UserParticipateType.MoneyType, psCost, configuration.getRoIThresholdMoneyType(), (Grid)grids.get(gridIdx));
			}
			recruitUsers.add(user);
		}
		//grid 和  user 信息写入数据库

		UserDao userDao = new UserDao();
		userDao.addUsers(recruitUsers);
	}
	
	public void participatorySensing(){
		roundNumber ++;
		List<Grid> gridWithTask = new ArrayList<>(grids);
		List<User> userUnParticipated = new ArrayList<>(recruitUsers);
		
		
		//按grid执行任务
		for (Grid grid : gridWithTask) {
			//查询此grid中的user
			
		}
	}

}
