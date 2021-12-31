package com.abraham.mobilecommunicationplatformtest.dtos;

import java.util.ArrayList;
import java.util.List;

import com.abraham.mobilecommunicationplatformtest.entities.Call;
import com.abraham.mobilecommunicationplatformtest.entities.Message;

public class MobileCommunicationContainerDto {

	private List<Call> calls;
	
	private List<Message> messages;
	
	public MobileCommunicationContainerDto() {
		this.calls = new ArrayList<>();
		this.messages = new ArrayList<>();

	}

	public List<Call> getCalls() {
		return calls;
	}

	public void setCalls(List<Call> calls) {
		this.calls = calls;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
