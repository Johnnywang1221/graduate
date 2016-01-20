package fz.bean;

public class UserFactory {
	User createUser(UserParticipateType userParticipateType){
		switch (userParticipateType) {
		case InterestType:
			//生成兴趣型用户
			break;
		case MoneyType:
			//生成功利型用户
			break;
		default:
			break;
		}
		return null;
	}

}
