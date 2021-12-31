package com.abraham.mobilecommunicationplatformtest.services.impl;

import java.io.IOException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abraham.mobilecommunicationplatformtest.constants.Constants;
import com.abraham.mobilecommunicationplatformtest.dtos.MobileCommunicationContainerDto;
import com.abraham.mobilecommunicationplatformtest.entities.Kpis;
import com.abraham.mobilecommunicationplatformtest.entities.Metrics;
import com.abraham.mobilecommunicationplatformtest.entities.MobileCommunicationContainer;
import com.abraham.mobilecommunicationplatformtest.exceptions.BussinessException;
import com.abraham.mobilecommunicationplatformtest.services.MobileCommunicationPlatformTestService;
import com.abraham.mobilecommunicationplatformtest.services.mappers.AuxKpisServiceMapper;
import com.abraham.mobilecommunicationplatformtest.services.mappers.AuxMetricsServiceMapper;

/**
 * MobileCommunicationPlatformTest Service
 * @author Abraham
 *
 */
@Service
public class MobileCommunicationPlatformTestServiceImpl implements MobileCommunicationPlatformTestService{

	@Autowired
	JSonReaderServiceImpl jsonReader;
	
	@Autowired
	AuxMetricsServiceMapper auxMetricsServiceMapper;
	
	@Autowired
	AuxKpisServiceMapper auxKpisServiceMapper;
	
	private MobileCommunicationContainer mobileCommunicationContainer = new MobileCommunicationContainer();
	
	/**
	 * Read a JSON object a process it to an object
	 * @param date
	 * @throws JSONException
	 * @throws IOException
	 * @throws BussinessException
	 */
	@Override
	public MobileCommunicationContainerDto processJson(String date) throws JSONException, IOException, BussinessException {
		
		if (mobileCommunicationContainer.getElapsedMilisecondsPerProcess().get(date) == null) {
			return jsonReader.readJsonFromUrlAndRetrieveInfo(date, mobileCommunicationContainer);
		} else {
			throw new BussinessException(Constants.REST_MESSAGE_JSON_PROCESSED);
		}
		
	}
	
	/**
	 * Retrieve the JSON processed files metrics
	 * @return a given metrics object
	 */
	@Override
	public Metrics retrieveMetrics() {
		
		return auxMetricsServiceMapper.processMetricsInfo(mobileCommunicationContainer);
	}
	
	/**
	 * Retrieve the JSON processed files kpis
	 * @return a given Kpis object
	 */
	@Override
	public Kpis retrieveKpis() {
		
		return auxKpisServiceMapper.processKpisInfo(mobileCommunicationContainer);
	}
}
