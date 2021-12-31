package com.abraham.mobilecommunicationplatformtest.services;

import java.io.IOException;

import org.json.JSONException;

import com.abraham.mobilecommunicationplatformtest.dtos.MobileCommunicationContainerDto;
import com.abraham.mobilecommunicationplatformtest.entities.Kpis;
import com.abraham.mobilecommunicationplatformtest.entities.Metrics;
import com.abraham.mobilecommunicationplatformtest.exceptions.BussinessException;

/**
 * MobileCommunicationPlatformTest Interface
 * @author Abraham
 *
 */
public interface MobileCommunicationPlatformTestService {

	/**
	 * Read a JSON object a process it to an object
	 * @param date
	 * @return MobileCommunicationContainerDto
	 * @throws JSONException
	 * @throws IOException
	 * @throws BussinessException
	 */
	MobileCommunicationContainerDto processJson(String date) throws JSONException, IOException, BussinessException;

	/**
	 * Retrieve the JSON processed files metrics
	 * @return a given metrics object
	 */
	Metrics retrieveMetrics();

	/**
	 * Retrieve the JSON processed files kpis
	 * @return a given Kpis object
	 */
	public Kpis retrieveKpis();
}
