package fz.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fz.bean.Grid;
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
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello world");

	}

}
