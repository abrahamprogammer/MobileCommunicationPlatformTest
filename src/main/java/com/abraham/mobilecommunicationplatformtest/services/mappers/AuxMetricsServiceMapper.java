package com.abraham.mobilecommunicationplatformtest.services.mappers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.abraham.mobilecommunicationplatformtest.constants.Constants;
import com.abraham.mobilecommunicationplatformtest.entities.Call;
import com.abraham.mobilecommunicationplatformtest.entities.Message;
import com.abraham.mobilecommunicationplatformtest.entities.Metrics;
import com.abraham.mobilecommunicationplatformtest.entities.MobileCommunicationContainer;
import com.abraham.mobilecommunicationplatformtest.enums.CallStatusCodeEnum;

/**
 * AuxMetricsServiceMapper
 * @author Abraham
 *
 */
@Service
public class AuxMetricsServiceMapper {

	private Metrics metrics;

	/**
	 * Map mobileCommunicationContainer into a new Metrics Object, witch contains call / msg info
	 * @param mobileCommunicationContainer
	 * @return processed Metrics
	 */
	public Metrics processMetricsInfo(MobileCommunicationContainer mobileCommunicationContainer) {

		metrics = new Metrics();
		countMissingFieldsRows(mobileCommunicationContainer);
		countNoContentMessages(mobileCommunicationContainer);
		countErrorFieldsRows(mobileCommunicationContainer);
		groupCallsByCountryCode(mobileCommunicationContainer);
		countOkKoCalls(mobileCommunicationContainer);
		groupAverageCallsDurationByCountryCode(mobileCommunicationContainer);
		setWordOcurrenceRanking(mobileCommunicationContainer);
		return metrics;
	}

	/**
	 * Count all lines that has at least one missing field
	 * @param mobileCommunicationContainer
	 */
	private void countMissingFieldsRows(MobileCommunicationContainer mobileCommunicationContainer) {

		List<Call> calls = mobileCommunicationContainer.getCalls();
		List<Message> messages = mobileCommunicationContainer.getMessages();

		calls.stream().forEach(call -> {
			if (checkCallMissingFields(call)) {
				metrics.setTotalMissingFieldsRows(metrics.getTotalMissingFieldsRows() + Constants.VALUE_ONE);
			}
		});

		messages.stream().forEach(message -> {
			if (checkMessageMissingFields(message)) {
				metrics.setTotalMissingFieldsRows(metrics.getTotalMissingFieldsRows() + Constants.VALUE_ONE);
			}
		});

	}

	/**
	 * Count all messages wihout content
	 * @param mobileCommunicationContainer
	 */
	private void countNoContentMessages(MobileCommunicationContainer mobileCommunicationContainer) {

		List<Message> messages = mobileCommunicationContainer.getMessages();
		Integer noContentMessages = messages.stream().filter(message -> Constants.EMPTY_STRING.equals(message.getMessageContent()))
				.collect(Collectors.toList()).size();
		metrics.setTotalNoContentMessages(noContentMessages);
	}

	/**
	 * Map the lines that had at least one field with errors (format, input, etc)
	 * @param mobileCommunicationContainer
	 */
	private void countErrorFieldsRows(MobileCommunicationContainer mobileCommunicationContainer) {

		metrics.setTotalErrorFieldsRow(mobileCommunicationContainer.getTotalWrongFieldRows());
	}

	/**
	 * Group all calls by CountryCode
	 * @param mobileCommunicationContainer
	 */
	private void groupCallsByCountryCode(MobileCommunicationContainer mobileCommunicationContainer) {

		List<Call> calls = mobileCommunicationContainer.getCalls();
		List<Long> destinationValues = calls.stream().map(call -> call.getDestination()).collect(Collectors.toList());
		List<Long> originValues = calls.stream().map(call -> call.getOrigin()).collect(Collectors.toList());
		List<Long> totalValues = Stream.concat(destinationValues.stream(), originValues.stream())
				.collect(Collectors.toList());
		Map<Long, Long> groupedCalls = totalValues.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		metrics.setTotalgroupedCalls(groupedCalls);
	}

	/**
	 * Count all KO / OK calls
	 * @param mobileCommunicationContainer
	 */
	private void countOkKoCalls(MobileCommunicationContainer mobileCommunicationContainer) {

		List<Call> calls = mobileCommunicationContainer.getCalls();
		Integer okCalls = calls.stream().filter(call -> CallStatusCodeEnum.OK.equals(call.getStatusCode()))
				.collect(Collectors.toList()).size();
		Integer koCalls = calls.stream().filter(call -> CallStatusCodeEnum.KO.equals(call.getStatusCode()))
				.collect(Collectors.toList()).size();

		metrics.setTotalOKCalls(okCalls);
		metrics.setTotalKOCalls(koCalls);
	}

	/**
	 * Group the average call duration by CountryCode
	 * @param mobileCommunicationContainer
	 */
	private void groupAverageCallsDurationByCountryCode(MobileCommunicationContainer mobileCommunicationContainer) {

		List<Call> calls = mobileCommunicationContainer.getCalls();
		Map<Long, Double> averageDurationMap = calls.stream().filter(call -> call.getDuration() != null)
				.collect(Collectors.groupingBy(Call::getDestination, Collectors.averagingDouble(Call::getDuration)));

		metrics.setTotalAverageCalls(averageDurationMap);

	}

	/**
	 * Set word ocurrence ranking
	 * @param mobileCommunicationContainer
	 */
	private void setWordOcurrenceRanking(MobileCommunicationContainer mobileCommunicationContainer) {

		List<Message> messages = mobileCommunicationContainer.getMessages();
		Map<String, Long> wordRanking = messages.stream().map(message -> message.getMessageContent())
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		LinkedHashMap<String, Long> sortedWordRanking = wordRanking.entrySet()
	            .stream()
	            .filter(entry -> !Constants.EMPTY_STRING.equals(entry.getKey()))
	            .sorted((value2,value1) -> Long.compare(value1.getValue(), value2.getValue()))
	            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (value1, value2) -> value1, LinkedHashMap::new));
		
		Map<Integer, String> rankedMap = new HashMap<Integer, String>();
		Integer position = Constants.VALUE_ONE;
		for (Entry<String, Long> entry : sortedWordRanking.entrySet()) {
			rankedMap.put(position++, entry.getKey());
		}
		
		metrics.setWordOcurrenceRanking(rankedMap);
	}

	/**
	 * Check if a Call Object has null fields
	 * @param call
	 * @return True if has missing fields, False if not
	 */
	private Boolean checkCallMissingFields(Call call) {
		if (null == call.getDestination() || null == call.getDuration() || null == call.getMessageType()
				|| null == call.getOrigin() || null == call.getStatusCode() || null == call.getStatusDescription()
				|| null == call.getTimestamp()) {
			return true;
		}
		return false;
	}

	/**
	 * Check if a Message Object has null fields
	 * @param message
	 * @return True if has missing fields, False if not
	 */
	private Boolean checkMessageMissingFields(Message message) {
		if (null == message.getDestination() || null == message.getMessageType() || null == message.getOrigin()
				|| null == message.getMessageStatus() || null == message.getMessageContent()
				|| null == message.getTimestamp()) {
			return true;
		}
		return false;
	}
}
