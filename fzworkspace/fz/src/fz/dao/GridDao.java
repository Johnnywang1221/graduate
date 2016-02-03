package fz.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import fz.bean.Grid;
import fz.bean.User;
import fz.common.Application;

public class GridDao  {
	public static void addGrid(Grid grid){
		Session session = Application.sharedApplication().getSessionFactory().openSession();
		Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         long idInteger = (long)session.save(grid);
	         grid.setId(idInteger);  
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}
	
	public static void addGrids(List<Grid> grids){
		Session session = Application.sharedApplication().getSessionFactory().openSession();
		Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         for (Grid grid : grids) {
		         long idInteger = (long)session.save(grid);
		         grid.setId(idInteger);
			}
	         
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}	
	
	public static Grid listGridByGridId(long gridId){
		Session session = Application.sharedApplication().getSessionFactory().openSession();
		Transaction tx = null;
		Grid grid = null;
	      try{
	         tx = session.beginTransaction();
	         Criteria cr = session.createCriteria(Grid.class);
	         // Add restriction.
	         cr.add(Restrictions.eq("id", gridId));
	         List<Grid> grids= cr.list();
	         if(grids != null && grids.size()!= 0){
	        	 grid = grids.get(0);
	         }
	         
	         
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return grid;
	}
	
	public static List<Grid> listAllGrids(){
		Session session = Application.sharedApplication().getSessionFactory().openSession();
		Transaction tx = null;
		 List<Grid> grids = new ArrayList<>();
	      try{
	         tx = session.beginTransaction();
	         Criteria cr = session.createCriteria(Grid.class);
	         // Add restriction.
	         //cr.add(Restrictions.eq("id", gridId));
	         grids= cr.list();
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return grids;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello world");

	}

}
