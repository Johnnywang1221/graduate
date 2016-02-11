package fz.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fz.bean.Grid;
import fz.bean.TaskDescription;
import fz.common.Application;

public class TaskDescriptionDao {
	public static void addTaskDes(TaskDescription taskDescription){
		Session session = Application.sharedApplication().getSessionFactory().openSession();
		Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         long id = (long)session.save(taskDescription);
	         taskDescription.setId(id);   
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}
	public static TaskDescription queryTaskDes(long taskDescriptionID){
		return null;
	}
//	public static void addGrids(List<Grid> grids){
//		Session session = Application.sharedApplication().getSessionFactory().openSession();
//		Transaction tx = null;
//	      try{
//	         tx = session.beginTransaction();
//	         for (Grid grid : grids) {
//		         long idInteger = (long)session.save(grid);
//		         grid.setId(idInteger);
//			}
//	         
//	         tx.commit();
//	      }catch (HibernateException e) {
//	         if (tx!=null) tx.rollback();
//	         e.printStackTrace(); 
//	      }finally {
//	         session.close(); 
//	      }
//	}

}
