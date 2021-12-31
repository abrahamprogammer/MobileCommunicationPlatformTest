package com.abraham.mobilecommunicationplatformtest.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.abraham.mobilecommunicationplatformtest.constants.Constants;

public class MobileCommunicationContainer {

	private List<Call> calls;
	
	private List<Message> messages;
	
	private Integer totalJSONProcessed;
	
	private Integer totalWrongFieldRows;
	
	private Map<String, Long> elapsedMilisecondsPerProcess;

	public MobileCommunicationContainer() {
		this.calls = new ArrayList<>();
		this.messages = new ArrayList<>();
		this.totalJSONProcessed = Constants.VALUE_CERO;
		this.totalWrongFieldRows = Constants.VALUE_CERO;
		this.elapsedMilisecondsPerProcess = new HashMap<>();
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
	
	public Integer getTotalJSONProcessed() {
		return totalJSONProcessed;
	}

	public void setTotalJSONProcessed(Integer totalJSONProcessed) {
		this.totalJSONProcessed = totalJSONProcessed;
	}

	public Integer getTotalWrongFieldRows() {
		return totalWrongFieldRows;
	}

	public void setTotalWrongFieldRows(Integer totalWrongFieldRows) {
		this.totalWrongFieldRows = totalWrongFieldRows;
	}
	
	public Map<String, Long> getElapsedMilisecondsPerProcess() {
		return elapsedMilisecondsPerProcess;
	}

	public void setElapsedMilisecondsPerProcess(Map<String, Long> elapsedMilisecondsPerProcess) {
		this.elapsedMilisecondsPerProcess = elapsedMilisecondsPerProcess;
	}
	
}
