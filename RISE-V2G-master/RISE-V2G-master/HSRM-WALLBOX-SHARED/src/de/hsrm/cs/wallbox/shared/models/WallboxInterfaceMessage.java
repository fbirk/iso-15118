package de.hsrm.cs.wallbox.shared.models;

import de.hsrm.cs.wallbox.shared.enums.MessageType;

public class WallboxInterfaceMessage {

	private MessageType messageType;
	private String title;
	private String value;

	public WallboxInterfaceMessage(String title, String value, MessageType messageType) {
		this.title = title;
		this.value = value;
		this.messageType = messageType;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
