package org.spider.client;

public class HomeChannelObject {
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	String channelId;
	String channelName;
	String googleUser;
	int videoNumber;
	int viewNumber;
	int subcriber;
	long dateCreated;
	int status;
	String note;
	
	public HomeChannelObject()
	{
		
	}
	
	public HomeChannelObject(String channelId, String channelName, String googleUser, int videoNumber,
			int viewNumber, int subscriber, long dateCreated, int status, String note)
	{
		this.channelId = channelId;
		this.channelName = channelName;
		this.googleUser = googleUser;
		this.videoNumber = videoNumber;
		this.viewNumber = viewNumber;
		this.subcriber = subscriber;
		this.dateCreated = dateCreated;
		this.status = status;
		this.note = note;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getGoogleUser() {
		return googleUser;
	}

	public void setGoogleUser(String googleUser) {
		this.googleUser = googleUser;
	}
	public int getVideoNumber() {
		return videoNumber;
	}

	public void setVideoNumber(int videoNumber) {
		this.videoNumber = videoNumber;
	}

	public int getViewNumber() {
		return viewNumber;
	}

	public void setViewNumber(int viewNumber) {
		this.viewNumber = viewNumber;
	}

	public int getSubcriber() {
		return subcriber;
	}

	public void setSubcriber(int subcriber) {
		this.subcriber = subcriber;
	}

	public long getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(long dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
