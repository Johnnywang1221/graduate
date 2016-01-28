package fz.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import fz.bean.TaskExecRecord;
import fz.common.Application;

public class RecordDao {
	public static void addRecord(TaskExecRecord record){
		Session session = Application.sharedApplication().getSessionFactory().openSession();
		Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         long idInteger = (long)session.save(record);
	         record.setId(idInteger);  
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}
	
	public static double totalCost(){
		Session session = Application.sharedApplication().getSessionFactory().openSession();
	      Transaction tx = null;
	      double totalCost = 0;
	      try{
	         tx = session.beginTransaction();
	         Criteria cr = session.createCriteria(TaskExecRecord.class);

	         // To get total row count.
	         cr.setProjection(Projections.sum("cost"));
	         List totalCostList = cr.list();
	         totalCost =  (double)totalCostList.get(0) ;

	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return totalCost;
	}
	public static double totalCostInRound(int roundNum){
		Session session = Application.sharedApplication().getSessionFactory().openSession();
	      Transaction tx = null;
	      double totalCost = 0;
	      try{
	         tx = session.beginTransaction();
	         Criteria cr = session.createCriteria(TaskExecRecord.class);

	         // To get total row count.
	         cr.add(Restrictions.eq("roundNum", roundNum));
	         cr.setProjection(Projections.sum("cost"));
	         List totalCostList = cr.list();
	         totalCost =  (double)totalCostList.get(0) ;

	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return totalCost;
	}
	public static double totalReward(){
		Session session = Application.sharedApplication().getSessionFactory().openSession();
	      Transaction tx = null;
	      double totalReward = 0;
	      try{
	         tx = session.beginTransaction();
	         Criteria cr = session.createCriteria(TaskExecRecord.class);

	         // To get total row count.
	         cr.setProjection(Projections.sum("reward"));
	         List totalRewardList = cr.list();
	         totalReward =  (double)totalRewardList.get(0) ;

	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return totalReward;
	}
	public static double totalRewardInRound(int round){
		Session session = Application.sharedApplication().getSessionFactory().openSession();
	      Transaction tx = null;
	      double totalReward = 0;
	      try{
	         tx = session.beginTransaction();
	         Criteria cr = session.createCriteria(TaskExecRecord.class);

	         // To get total row count.
	         cr.add(Restrictions.eq("roundNum", round));
	         cr.setProjection(Projections.sum("reward"));
	         List totalRewardList = cr.list();
	         totalReward =  (double)totalRewardList.get(0) ;

	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return totalReward;
	}
	
	public static long totalUsersInRound(int round){
		Session session = Application.sharedApplication().getSessionFactory().openSession();
	      Transaction tx = null;
	      long totalReward = 0;
	      try{
	         tx = session.beginTransaction();
	         Criteria cr = session.createCriteria(TaskExecRecord.class);

	         // To get total row count.
	         cr.add(Restrictions.eq("roundNum", round));
	        // cr.add(Restrictions.)
	         cr.setProjection(Projections.countDistinct("userId"));
	         //cr.setProjection(Projections.sum("reward"));
	         List totalRewardList = cr.list();
	         totalReward =  (long)totalRewardList.get(0) ;

	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return totalReward;
	}
	public static long totalUsers(){
		Session session = Application.sharedApplication().getSessionFactory().openSession();
	      Transaction tx = null;
	      long totalReward = 0;
	      try{
	         tx = session.beginTransaction();
	         Criteria cr = session.createCriteria(TaskExecRecord.class);

	         // To get total row count.
	         //cr.add(Restrictions.eq("roundNum", round));
	        // cr.add(Restrictions.)
	         cr.setProjection(Projections.countDistinct("userId"));
	         //cr.setProjection(Projections.sum("reward"));
	         List totalRewardList = cr.list();
	         totalReward =  (long)totalRewardList.get(0) ;

	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return totalReward;
	}
	
	public static List<Double> totalRewardOfUsers(){
		Session session = Application.sharedApplication().getSessionFactory().openSession();
	      Transaction tx = null;
	      List<Double> resuleList = new ArrayList<>();
	      try{
	         tx = session.beginTransaction();
	         Criteria cr = session.createCriteria(TaskExecRecord.class);

	         // To get total row count.
	         //cr.add(Restrictions.eq("roundNum", round));
	        // cr.add(Restrictions.)
	         ProjectionList projectionList = Projections.projectionList();
	         projectionList.add(Projections.groupProperty("userId"));
	         projectionList.add(Projections.sum("reward"));
	         cr.setProjection(projectionList);
	         //cr.setProjection(Projections.sum("reward"));
	         List totalRewardList = cr.list();
	         for(int i = 0;i<totalRewardList.size();i++){
	        	 resuleList.add((Double)totalRewardList.get(i));
	         }

	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return resuleList;
	}
	public static long totalDataItems(){
		Session session = Application.sharedApplication().getSessionFactory().openSession();
	      Transaction tx = null;
	      long totalDataItems = 0;
	      try{
	         tx = session.beginTransaction();
	         Criteria cr = session.createCriteria(TaskExecRecord.class);

	         // To get total row count.
	         cr.setProjection(Projections.rowCount());
	         List totalDataItemList = cr.list();
	         totalDataItems =  (long)totalDataItemList.get(0) ;

	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return totalDataItems;
	}
	

}
