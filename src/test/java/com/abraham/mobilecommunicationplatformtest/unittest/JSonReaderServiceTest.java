package com.abraham.mobilecommunicationplatformtest.unittest;

import java.io.IOException;

import org.json.JSONException;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import org.junit.jupiter.api.Test;
import org.powermock.modules.junit4.PowerMockRunner;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.abraham.mobilecommunicationplatformtest.entities.MobileCommunicationContainer;
import com.abraham.mobilecommunicationplatformtest.services.impl.JSonReaderServiceImpl;
import com.abraham.mobilecommunicationplatformtest.services.mappers.MobileCommunicationContainerDtoMapper;

@RunWith(PowerMockRunner.class)
@SpringBootTest
public class JSonReaderServiceTest {
	
	private final static String DATE = "20180131";

	@InjectMocks
	private JSonReaderServiceImpl service;

	@Mock
	private MobileCommunicationContainerDtoMapper dtoMapper;

	@Test
	public void shouldReadJsonFromUrlAndRetrieveInfo() throws IOException, JSONException {

		service.readJsonFromUrlAndRetrieveInfo(DATE, new MobileCommunicationContainer());
		verify(dtoMapper).fromEntity(any());
	}
}
