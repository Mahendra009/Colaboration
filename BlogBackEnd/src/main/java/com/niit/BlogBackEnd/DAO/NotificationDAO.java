package com.niit.BlogBackEnd.DAO;

import java.util.List;

import com.niit.BlogBackEnd.model.Notification;

public interface NotificationDAO {
	
	public boolean addNotification(Notification notification);
	public boolean updateNotification(int notificationID);
	public boolean deleteNotification(Notification notification);
	public Notification getNotification(int notificationID);
	public List<Notification> listNotificationNotViewed(String emailId);
	
	
	

}
