package com.abraham.mobilecommunicationplatformtest.entities;

import com.abraham.mobilecommunicationplatformtest.enums.MessageStatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Message extends AbstractMobileCommunicationObject {

	@JsonProperty("message_content")
	private String messageContent;
	
	@JsonProperty("message_status")
	private MessageStatusEnum messageStatus;

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public MessageStatusEnum getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(MessageStatusEnum messageStatus) {
		this.messageStatus = messageStatus;
	}
}
