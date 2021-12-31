package com.abraham.mobilecommunicationplatformtest.entities;

import java.util.HashMap;
import java.util.Map;

import com.abraham.mobilecommunicationplatformtest.constants.Constants;

public class Metrics {
	
	private Integer totalMissingFieldsRows;
	
	private Integer totalNoContentMessages;
	
	private Integer totalErrorFieldsRow;
	
	private Map<Long, Long> totalgroupedCalls;
	
	private Integer totalOKCalls;

	private Integer totalKOCalls;
	
	private Map<Long, Double> totalAverageCalls;
	
	private Map<Integer, String> wordOcurrenceRanking;
	
	public Metrics() {
		this.totalMissingFieldsRows = Constants.VALUE_CERO;
		this.totalNoContentMessages = Constants.VALUE_CERO;
		this.totalErrorFieldsRow = Constants.VALUE_CERO;
		this.totalgroupedCalls = new HashMap<>();
		this.totalOKCalls = Constants.VALUE_CERO;
		this.totalKOCalls = Constants.VALUE_CERO;
		this.totalAverageCalls = new HashMap<>();
		this.wordOcurrenceRanking = new HashMap<>();
	}

	public Integer getTotalMissingFieldsRows() {
		return totalMissingFieldsRows;
	}

	public void setTotalMissingFieldsRows(Integer totalMissingFieldsRows) {
		this.totalMissingFieldsRows = totalMissingFieldsRows;
	}

	public Integer getTotalNoContentMessages() {
		return totalNoContentMessages;
	}

	public void setTotalNoContentMessages(Integer totalNoContentMessages) {
		this.totalNoContentMessages = totalNoContentMessages;
	}

	public Integer getTotalErrorFieldsRow() {
		return totalErrorFieldsRow;
	}

	public void setTotalErrorFieldsRow(Integer totalErrorFieldsRow) {
		this.totalErrorFieldsRow = totalErrorFieldsRow;
	}

	public Map<Long, Long> getTotalgroupedCalls() {
		return totalgroupedCalls;
	}

	public void setTotalgroupedCalls(Map<Long, Long> totalgroupedCalls) {
		this.totalgroupedCalls = totalgroupedCalls;
	}

	public Integer getTotalOKCalls() {
		return totalOKCalls;
	}

	public void setTotalOKCalls(Integer totalOKCalls) {
		this.totalOKCalls = totalOKCalls;
	}

	public Integer getTotalKOCalls() {
		return totalKOCalls;
	}

	public void setTotalKOCalls(Integer totalKOCalls) {
		this.totalKOCalls = totalKOCalls;
	}

	public Map<Long, Double> getTotalAverageCalls() {
		return totalAverageCalls;
	}

	public void setTotalAverageCalls(Map<Long, Double> totalAverageCalls) {
		this.totalAverageCalls = totalAverageCalls;
	}

	public Map<Integer, String> getWordOcurrenceRanking() {
		return wordOcurrenceRanking;
	}

	public void setWordOcurrenceRanking(Map<Integer, String> wordOcurrenceRanking) {
		this.wordOcurrenceRanking = wordOcurrenceRanking;
	}

}
