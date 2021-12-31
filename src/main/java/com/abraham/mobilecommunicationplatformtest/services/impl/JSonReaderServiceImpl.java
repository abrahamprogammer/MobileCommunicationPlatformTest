package com.abraham.mobilecommunicationplatformtest.services.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abraham.mobilecommunicationplatformtest.constants.Constants;
import com.abraham.mobilecommunicationplatformtest.dtos.MobileCommunicationContainerDto;
import com.abraham.mobilecommunicationplatformtest.entities.Call;
import com.abraham.mobilecommunicationplatformtest.entities.Message;
import com.abraham.mobilecommunicationplatformtest.entities.MobileCommunicationContainer;
import com.abraham.mobilecommunicationplatformtest.services.JSonReaderService;
import com.abraham.mobilecommunicationplatformtest.services.mappers.MobileCommunicationContainerDtoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON Reader Service
 * 
 * @author Abraham
 *
 */
@Service
public class JSonReaderServiceImpl implements JSonReaderService {

	@Autowired
	private MobileCommunicationContainerDtoMapper dtoMapper;

	/**
	 * Read all JSON character and it's merge in a String
	 * 
	 * @param rd
	 * @return
	 * @throws IOException
	 */
	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -Constants.VALUE_ONE) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	/**
	 * Read a JSON from a given URL and retrieve its information into an entity
	 * 
	 * @param date
	 * @param mobileCommunicationContainer
	 * @throws IOException
	 * @throws JSONException
	 */
	@Override
	public MobileCommunicationContainerDto readJsonFromUrlAndRetrieveInfo(String date,
			MobileCommunicationContainer mobileCommunicationContainer) throws IOException, JSONException {
		Instant start = Instant.now();

		InputStream is = new URL(Constants.JSON_RAW_GIT_URL + date + Constants.JSON_EXTENSION).openStream();
		try {
			BufferedReader rd = new BufferedReader(
					new InputStreamReader(is, Charset.forName(Constants.WEB_UTF_CHARSET)));
			String jsonText = readAll(rd);
			List<String> list = Arrays
					.asList(jsonText.split(Character.toString((char) 13) + Character.toString((char) 10)));

			List<Call> actualJSONCalls = new ArrayList<>();
			List<Message> actualJSONMessages = new ArrayList<>();
			List<Call> totalCalls = mobileCommunicationContainer.getCalls();
			List<Message> totalMessages = mobileCommunicationContainer.getMessages();
			Integer totalWrongFieldRows = mobileCommunicationContainer.getTotalWrongFieldRows();
			Integer totalJSONProcessed = mobileCommunicationContainer.getTotalJSONProcessed();
			ObjectMapper mapper = new ObjectMapper();

			for (String object : list) {
				try {
					if (object.contains(Constants.MESSAGE_TYPE_CALL)) {
						actualJSONCalls.add(mapper.readValue(object, Call.class));
					} else if (object.contains(Constants.MESSAGE_TYPE_MSG)) {
						actualJSONMessages.add(mapper.readValue(object, Message.class));
					} else {
						totalWrongFieldRows++;
					}
				} catch (Exception e) {
					totalWrongFieldRows++;
				}
			}
			Instant end = Instant.now();

			// We set the actual JSON Calls / Msgs in order to map to a DTO that will be retrieved by the Service.
			// Then, we will set the Total Call / Msgs list again.
			mobileCommunicationContainer.setCalls(actualJSONCalls);
			mobileCommunicationContainer.setMessages(actualJSONMessages);
			MobileCommunicationContainerDto dto = dtoMapper.fromEntity(mobileCommunicationContainer);
			totalCalls.addAll(actualJSONCalls);
			totalMessages.addAll(actualJSONMessages);

			Long elapsedProcessMiliseconds = Duration.between(start, end).toMillis();
			mobileCommunicationContainer.getElapsedMilisecondsPerProcess().put(date, elapsedProcessMiliseconds);
			mobileCommunicationContainer.setCalls(totalCalls);;
			mobileCommunicationContainer.setMessages(totalMessages);
			mobileCommunicationContainer.setTotalWrongFieldRows(totalWrongFieldRows);
			mobileCommunicationContainer.setTotalJSONProcessed(++totalJSONProcessed);

			return dto;

		} finally {
			is.close();
		}
	}
}
