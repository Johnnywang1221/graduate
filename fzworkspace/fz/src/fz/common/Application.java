package fz.common;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Application {
	private static Application application = new Application();
	private SessionFactory sessionFactory;
	private Application(){
		try{
	         sessionFactory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
	}
	public static Application sharedApplication(){
		return application;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
