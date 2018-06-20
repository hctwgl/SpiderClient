package org.spider.client;

public class MonitorChannelObject {
	String channelId;
	String channelName;
	
	public MonitorChannelObject()
	{
		
	}
	
	public MonitorChannelObject(String channelId, String channelName) {
		super();
		this.channelId = channelId;
		this.channelName = channelName;
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
