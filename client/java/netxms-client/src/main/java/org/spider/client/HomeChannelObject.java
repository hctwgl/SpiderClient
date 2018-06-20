package org.spider.client;

public class HomeChannelObject {
	public String getGoogleUser() {
		return googleUser;
	}

	public void setGoogleUser(String googleUser) {
		this.googleUser = googleUser;
	}

	String channelId;
	String channelName;
	String googleUser;
	
	public HomeChannelObject()
	{
		
	}
	
	public HomeChannelObject(String channelId, String channelName, String googleUser)
	{
		this.channelId = channelId;
		this.channelName = channelName;
		this.googleUser = googleUser;
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
	
}
