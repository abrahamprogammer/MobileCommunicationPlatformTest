package com.abraham.mobilecommunicationplatformtest.unittest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.abraham.mobilecommunicationplatformtest.constants.Constants;
import com.abraham.mobilecommunicationplatformtest.dtos.MobileCommunicationContainerDto;
import com.abraham.mobilecommunicationplatformtest.entities.MobileCommunicationContainer;
import com.abraham.mobilecommunicationplatformtest.exceptions.BussinessException;
import com.abraham.mobilecommunicationplatformtest.services.impl.JSonReaderServiceImpl;
import com.abraham.mobilecommunicationplatformtest.services.impl.MobileCommunicationPlatformTestServiceImpl;
import com.abraham.mobilecommunicationplatformtest.services.mappers.AuxKpisServiceMapper;
import com.abraham.mobilecommunicationplatformtest.services.mappers.AuxMetricsServiceMapper;

@RunWith(PowerMockRunner.class)
@SpringBootTest
public class MobileCommunicationPlatformTestServiceTest {

	private final static String DATE = "20180131";

	@InjectMocks
	private MobileCommunicationPlatformTestServiceImpl service;

	@Mock
	JSonReaderServiceImpl jsonReader;

	@Mock
	AuxMetricsServiceMapper auxMetricsServiceMapper;

	@Mock
	AuxKpisServiceMapper auxKpisServiceMapper;

	@Mock
	private MobileCommunicationContainer mockedMobileCommunication;


	@Test
	public void shouldProcessJson() throws IOException, JSONException, BussinessException {

		when(jsonReader.readJsonFromUrlAndRetrieveInfo(DATE, mockedMobileCommunication)).thenReturn(null);
		
		MobileCommunicationContainerDto expected = service.processJson(DATE);
		
		assertThat(expected).isNull();
	}
	
	@Test
	public void shouldProcessJsonException() throws IOException, JSONException, BussinessException {

		Map<String, Long> map = new HashMap<>();
		map.put(DATE, 1L);
		Mockito.doReturn(map).when(mockedMobileCommunication).getElapsedMilisecondsPerProcess();
		
		final Throwable thrown = catchThrowable(() -> service.processJson(DATE));
		
		assertThat(thrown).isExactlyInstanceOf(BussinessException.class);
        assertThat(((BussinessException) thrown).getMessage()).isEqualTo(Constants.REST_MESSAGE_JSON_PROCESSED);
	}
	
	@Test
	public void shouldRetrieveMetrics() {
		
		service.retrieveMetrics();
		verify(auxMetricsServiceMapper).processMetricsInfo(mockedMobileCommunication);
	}
	
	@Test
	public void shouldRetrieveKpis() {
		
		service.retrieveKpis();
		verify(auxKpisServiceMapper).processKpisInfo(mockedMobileCommunication);
	}
	
}
