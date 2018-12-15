package com.niit.BlogBackEnd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Notification_Detail")
@SequenceGenerator(name="notificationIDSeq", sequenceName="Notification_seq")
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="notificationIDSeq")
	private int notificationID;
	private String blogTitle;
	private String emailId;
	private String approvalStatus;
	private String rejectionReason;
	private boolean viewed;
	
	
	public int getNotificationID() {
		return notificationID;
	}
	public void setNotificationID(int notificationID) {
		this.notificationID = notificationID;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getRejuctionReason() {
		return rejectionReason;
	}
	public void setRejuctionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
	public boolean isViewed() {
		return viewed;
	}
	public void setViewed(boolean viewed) {
		this.viewed = viewed;
	}
	
	
	
	
	

}
