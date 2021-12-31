package com.abraham.mobilecommunicationplatformtest.entities;

import java.sql.Timestamp;

import com.abraham.mobilecommunicationplatformtest.constants.Constants;
import com.abraham.mobilecommunicationplatformtest.enums.MessageTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public abstract class AbstractMobileCommunicationObject {

	@JsonProperty("message_type")
	private MessageTypeEnum messageType;
	
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@JsonDeserialize(using = FormatTimeStampDeserializer.class)
	@JsonProperty("timestamp")
	private Timestamp timestamp;
	
	@JsonProperty("origin")
	private Long origin;
	
	@JsonProperty("destination")
	private Long destination;

	public MessageTypeEnum getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageTypeEnum messageType) {
		this.messageType = messageType;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Long getOrigin() {
		return Long.parseLong(origin.toString().substring(Constants.VALUE_CERO, Constants.VALUE_THREE));
	}

	public void setOrigin(Long origin) {
		this.origin = origin;
	}

	public Long getDestination() {
		return Long.parseLong(destination.toString().substring(Constants.VALUE_CERO, Constants.VALUE_THREE));
	}

	public void setDestination(Long destination) {
		this.destination = destination;
	}
}
