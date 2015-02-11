package com.ijiaban.u2bplayer.common.bean;
/**
 * 订阅频道资源id
 * @author Adam
 *
 */
public class ResourceId {
public String kind;
public String channelId; //订阅所属频道id
public ResourceId(String kind, String channelId) {
	super();
	this.kind = kind;
	this.channelId = channelId;
}
public String getKind() {
	return kind;
}
public void setKind(String kind) {
	this.kind = kind;
}
public String getChannelId() {
	return channelId;
}
public void setChannelId(String channelId) {
	this.channelId = channelId;
}
public ResourceId() {
	super();
}
}
