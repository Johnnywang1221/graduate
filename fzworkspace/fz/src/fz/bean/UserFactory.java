package fz.bean;

public class UserFactory {
	User createUser(UserParticipateType userParticipateType){
		switch (userParticipateType) {
		case InterestType:
			//������Ȥ���û�
			break;
		case MoneyType:
			//���ɹ������û�
			break;
		default:
			break;
		}
		return null;
	}

}
