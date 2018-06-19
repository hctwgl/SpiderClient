package org.spider.client;

public class HomeChannelObject {
	int id;
	String channelId;
	String channelName;
	int googleAccountId;
	
	public HomeChannelObject()
	{
		
	}
	
	public HomeChannelObject( int id, String channelId, String channelName, int googleAccountId)
	{
		this.id = id;
		this.channelId = channelId;
		this.channelName = channelName;
		this.googleAccountId = googleAccountId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public int getGoogleAccountId() {
		return googleAccountId;
	}

	public void setGoogleAccountId(int googleAccountId) {
		this.googleAccountId = googleAccountId;
	}
}
