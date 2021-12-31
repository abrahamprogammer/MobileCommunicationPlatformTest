package com.abraham.mobilecommunicationplatformtest.services;

import java.io.IOException;

import org.json.JSONException;

import com.abraham.mobilecommunicationplatformtest.dtos.MobileCommunicationContainerDto;
import com.abraham.mobilecommunicationplatformtest.entities.MobileCommunicationContainer;

/**
 * JSONReader Interface
 * @author Abraham
 *
 */
public interface JSonReaderService {

	/**
	 * Read a JSON from a given URL and retrieve its information into an entity
	 * @param date
	 * @param mobileCommunicationContainer
	 * @throws IOException
	 * @throws JSONException
	 */
	MobileCommunicationContainerDto readJsonFromUrlAndRetrieveInfo(String date, MobileCommunicationContainer mobileCommunicationContainer) throws IOException, JSONException;
}
