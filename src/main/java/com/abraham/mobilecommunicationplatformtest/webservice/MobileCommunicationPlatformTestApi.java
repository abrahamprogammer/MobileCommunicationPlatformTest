package com.abraham.mobilecommunicationplatformtest.webservice;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.abraham.mobilecommunicationplatformtest.constants.Constants;
import com.abraham.mobilecommunicationplatformtest.dtos.MobileCommunicationContainerDto;
import com.abraham.mobilecommunicationplatformtest.entities.Kpis;
import com.abraham.mobilecommunicationplatformtest.entities.Metrics;
import com.abraham.mobilecommunicationplatformtest.exceptions.BussinessException;
import com.abraham.mobilecommunicationplatformtest.services.impl.MobileCommunicationPlatformTestServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * MobileCommunicationPlatformTestApi
 * 
 * @author Abraham
 *
 */
@RestController
@Api(value = "MobileCommunicationPlatformTestApi", tags = { "MobileCommunicationPlatformTestApi" })
public class MobileCommunicationPlatformTestApi {

	@Autowired
	private MobileCommunicationPlatformTestServiceImpl service;

	/**
	 * Process a JSON File
	 * 
	 * @param date
	 * @return String (HTTP Status Message)
	 */
	@ApiOperation(value = "Process JSON File", nickname = "process")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The file has been processed") })
	@PostMapping(value = "/process")
	public ResponseEntity<MobileCommunicationContainerDto> processJSONFile(
			@RequestParam(value = "date", required = true) String date) {

		try {
			return new ResponseEntity<>(service.processJson(date), HttpStatus.OK);
		} catch (FileNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.REST_MESSAGE_JSON_DOESNT_EXIST);
		} catch (BussinessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.REST_MESSAGE_GENERAL_ERROR);
		}

	}

	/**
	 * Get Metrics
	 * 
	 * @return Metrics
	 */
	@ApiOperation(value = "Get Metrics", nickname = "metrics")
	@GetMapping(value = "/metrics", produces = { "application/json" })
	public ResponseEntity<Metrics> getMetrics() {
		return new ResponseEntity<>(service.retrieveMetrics(), HttpStatus.OK);
	}

	/**
	 * Get Kpis
	 * 
	 * @return Kpis
	 */
	@ApiOperation(value = "Get Kpis", nickname = "kpis")
	@GetMapping(value = "/kpis", produces = { "application/json" })
	public ResponseEntity<Kpis> getKpis() {
		return new ResponseEntity<>(service.retrieveKpis(), HttpStatus.OK);
	}

}
