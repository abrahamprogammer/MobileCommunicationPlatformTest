package com.abraham.mobilecommunicationplatformtest.entities;

import com.abraham.mobilecommunicationplatformtest.enums.CallStatusCodeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Call extends AbstractMobileCommunicationObject{

	@JsonProperty("duration")
	private Integer duration;
	
	@JsonProperty("status_code")
	private CallStatusCodeEnum statusCode;
	
	@JsonProperty("status_description")
	private String statusDescription;

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public CallStatusCodeEnum getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(CallStatusCodeEnum statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
}
