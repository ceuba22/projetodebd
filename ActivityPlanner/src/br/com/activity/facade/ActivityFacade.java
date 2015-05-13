package br.com.activity.facade;


public class ActivityFacade {
	
	private static ActivityFacade instance;

	public static ActivityFacade getInstance() {
		if (instance == null) {
			instance = new ActivityFacade();
		}
		return instance;
	} 
	
	public ActivityFacade(){}

}
