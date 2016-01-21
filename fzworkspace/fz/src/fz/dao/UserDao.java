package fz.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import fz.bean.Grid;
import fz.bean.User;
import fz.common.Application;

public class UserDao {

	public static void addUser(User user){
		Session session = Application.sharedApplication().getSessionFactory().openSession();
		Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         long idInteger = (long)session.save(user);
	         user.setId(idInteger);  
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}
	
	public static void addUsers(List<User> users){
		Session session = Application.sharedApplication().getSessionFactory().openSession();
		Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         for (User user : users) {
		         long idInteger = (long)session.save(user);
		         user.setId(idInteger);  
			}
	         
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}
	public static List<User> listUsersInGrid(Grid grid){
		Session session = Application.sharedApplication().getSessionFactory().openSession();
		Transaction tx = null;
		List<User> users = null;
	      try{
	         tx = session.beginTransaction();
	         Criteria cr = session.createCriteria(User.class);
	         // Add restriction.
	         cr.add(Restrictions.eq("gridId", grid.getId()));
	         users = cr.list();
	         
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return users;
	}
}
