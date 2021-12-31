package com.abraham.mobilecommunicationplatformtest.entities;

import java.util.HashMap;
import java.util.Map;

import com.abraham.mobilecommunicationplatformtest.constants.Constants;

public class Kpis {

	private Integer totalProcessedJSONFiles;
	
	private Integer totalRows;
	
	private Integer totalCalls;
	
	private Integer totalMessages;
	
	private Integer totalDifferentOriginCountryCodes;
	
	private Integer totalDifferentDestinationCountryCodes;
	
	private Map<String, Long> elapsedMilisecondsPerJSONDateFile;
	
	public Kpis() {
		this.totalProcessedJSONFiles = Constants.VALUE_CERO;
		this.totalRows = Constants.VALUE_CERO;
		this.totalCalls = Constants.VALUE_CERO;
		this.totalMessages = Constants.VALUE_CERO;
		this.totalDifferentOriginCountryCodes = Constants.VALUE_CERO;
		this.totalDifferentOriginCountryCodes = Constants.VALUE_CERO;
		this.elapsedMilisecondsPerJSONDateFile = new HashMap<>();
	}

	public Integer getTotalProcessedJSONFiles() {
		return totalProcessedJSONFiles;
	}

	public void setTotalProcessedJSONFiles(Integer totalProcessedJSONFiles) {
		this.totalProcessedJSONFiles = totalProcessedJSONFiles;
	}

	public Integer getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

	public Integer getTotalCalls() {
		return totalCalls;
	}

	public void setTotalCalls(Integer totalCalls) {
		this.totalCalls = totalCalls;
	}

	public Integer getTotalMessages() {
		return totalMessages;
	}

	public void setTotalMessages(Integer totalMessages) {
		this.totalMessages = totalMessages;
	}

	public Integer getTotalDifferentOriginCountryCodes() {
		return totalDifferentOriginCountryCodes;
	}

	public void setTotalDifferentOriginCountryCodes(Integer totalDifferentOriginCountryCodes) {
		this.totalDifferentOriginCountryCodes = totalDifferentOriginCountryCodes;
	}

	public Integer getTotalDifferentDestinationCountryCodes() {
		return totalDifferentDestinationCountryCodes;
	}

	public void setTotalDifferentDestinationCountryCodes(Integer totalDifferentDestinationCountryCodes) {
		this.totalDifferentDestinationCountryCodes = totalDifferentDestinationCountryCodes;
	}

	public Map<String, Long> getElapsedMilisecondsPerJSONDateFile() {
		return elapsedMilisecondsPerJSONDateFile;
	}

	public void setElapsedMilisecondsPerJSONDateFile(Map<String, Long> elapsedMilisecondsPerJSONDateFile) {
		this.elapsedMilisecondsPerJSONDateFile = elapsedMilisecondsPerJSONDateFile;
	}

}
