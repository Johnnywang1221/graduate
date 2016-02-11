package fz.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import fz.bean.Task;
import fz.bean.TaskExecRecord;
import fz.bean.User;
import fz.common.Application;
import fz.conf.Configuration;

public class TaskDao {
	public static void addTask(Task task){
		Session session = Application.sharedApplication().getSessionFactory().openSession();
		Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         long idInteger = (long)session.save(task);
	         task.setId(idInteger);  
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}
	public static void updateTaskInfo(Task task){
		Session session = Application.sharedApplication().getSessionFactory().openSession();
		Transaction tx = null;
		
	      try{
	         tx = session.beginTransaction();
	         session.saveOrUpdate(task);
	         tx.commit();
	         

	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	     
	}
	synchronized public static boolean lockTask(long taskID){
		Session session = Application.sharedApplication().getSessionFactory().openSession();
		Transaction tx = null;
		
	      try{
	         tx = session.beginTransaction();
	         Task task = queryTaskByID(taskID);
	         if(task.getRemainDataNumber()>0){
	        	 task.setRemainDataNumber(task.getRemainDataNumber()-1);
	         }
	         session.saveOrUpdate(task);
	         tx.commit();
	         
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	     
	}
	public static long totalCompletedTasks(){
		Session session = Application.sharedApplication().getSessionFactory().openSession();
	      Transaction tx = null;
	      long totalCompletedTasks = 0;
	      try{
	         tx = session.beginTransaction();
	         Criteria cr = session.createCriteria(Task.class);

	         // To get total row count.
	         cr.add(Restrictions.eq("completed", true));
	         cr.setProjection(Projections.rowCount());
	         List totalCompletedTasksList = cr.list();
	         totalCompletedTasks =  (long)totalCompletedTasksList.get(0) ;

	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return totalCompletedTasks;
	}
	
	public static long totalCompletedTasksInRound(int round){
		Session session = Application.sharedApplication().getSessionFactory().openSession();
	      Transaction tx = null;
	      long totalCompletedTasks = 0;
	      try{
	         tx = session.beginTransaction();
	         Criteria cr = session.createCriteria(Task.class);

	         // To get total row count.
	         cr.add(Restrictions.eq("completed", true));
	         cr.add(Restrictions.eq("roundNumber", round));
	         cr.setProjection(Projections.rowCount());
	         List totalCompletedTasksList = cr.list();
	         totalCompletedTasks =  (long)totalCompletedTasksList.get(0) ;

	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return totalCompletedTasks;
	}
	
	public static long dataItemsInRound(int roundNum){
		Session session = Application.sharedApplication().getSessionFactory().openSession();
	      Transaction tx = null;
	      long dataItems = 0;
	      try{
	         tx = session.beginTransaction();
	         Criteria cr = session.createCriteria(Task.class);

	         // To get total row count.
	         cr.add(Restrictions.eq("roundNumber", roundNum));
	         cr.setProjection(Projections.sum("remainDataNumber"));
	         List totalCompletedTasksList = cr.list();
	         dataItems =  (long)totalCompletedTasksList.get(0);
	         Configuration configuration = Configuration.sharedConfiguration();
	         dataItems = configuration.getDataItemNumber()*configuration.getGridNumber()*configuration.getGridNumber()-dataItems;

	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return dataItems;
	}


}
