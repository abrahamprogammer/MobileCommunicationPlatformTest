package com.abraham.mobilecommunicationplatformtest.unittest;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.abraham.mobilecommunicationplatformtest.entities.Call;
import com.abraham.mobilecommunicationplatformtest.entities.Message;
import com.abraham.mobilecommunicationplatformtest.entities.Metrics;
import com.abraham.mobilecommunicationplatformtest.entities.MobileCommunicationContainer;
import com.abraham.mobilecommunicationplatformtest.enums.CallStatusCodeEnum;
import com.abraham.mobilecommunicationplatformtest.enums.MessageStatusEnum;
import com.abraham.mobilecommunicationplatformtest.enums.MessageTypeEnum;
import com.abraham.mobilecommunicationplatformtest.services.mappers.AuxMetricsServiceMapper;

@RunWith(PowerMockRunner.class)
@SpringBootTest
public class AuxMetricsServiceMapperTest {

	@InjectMocks
	private AuxMetricsServiceMapper service;

	private MobileCommunicationContainer mobileCommunicationContainer;

	@BeforeEach
	public void init() {
		mobileCommunicationContainer = new MobileCommunicationContainer();

		Call call1 = new Call();
		call1.setDestination(1000L);
		call1.setOrigin(1000L);
		call1.setDuration(100);
		call1.setMessageType(MessageTypeEnum.CALL);
		call1.setStatusCode(CallStatusCodeEnum.OK);
		call1.setStatusDescription("Desc");
		call1.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
		Call call2 = new Call();
		call2.setDestination(1000L);
		call2.setOrigin(1000L);
		call2.setDuration(150);
		call2.setMessageType(MessageTypeEnum.CALL);
		call2.setStatusCode(CallStatusCodeEnum.OK);
		call2.setStatusDescription("Desc");
		call2.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
		mobileCommunicationContainer.setCalls(List.of(call1, call2));

		Message msg1 = new Message();
		msg1.setDestination(1000L);
		msg1.setOrigin(1000L);
		msg1.setMessageContent("Hello");
		msg1.setMessageType(MessageTypeEnum.MSG);
		msg1.setMessageStatus(MessageStatusEnum.SEEN);
		msg1.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
		Message msg2 = new Message();
		msg2.setDestination(1000L);
		msg2.setOrigin(1000L);
		msg2.setMessageContent("Hello");
		msg2.setMessageType(MessageTypeEnum.MSG);
		msg2.setMessageStatus(MessageStatusEnum.SEEN);
		msg2.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
		mobileCommunicationContainer.setMessages(List.of(msg1, msg2));

		Map<String, Long> elapsedMiliseconds = new HashMap<>();
		elapsedMiliseconds.put("1", 111L);
		elapsedMiliseconds.put("2", 222L);
		mobileCommunicationContainer.setElapsedMilisecondsPerProcess(elapsedMiliseconds);

		mobileCommunicationContainer.setTotalJSONProcessed(5);
		mobileCommunicationContainer.setTotalWrongFieldRows(3);
	}

	@Test
	public void shouldProcessMetricsInfo() {

		Metrics metrics = service.processMetricsInfo(mobileCommunicationContainer);

		Double averageCallDuration = (double) ((mobileCommunicationContainer.getCalls().get(0).getDuration()
				+ mobileCommunicationContainer.getCalls().get(1).getDuration())
				/ mobileCommunicationContainer.getCalls().size());
		assertThat(metrics.getTotalAverageCalls()).isEqualTo(Map.of(100L, averageCallDuration));
		assertThat(metrics.getTotalErrorFieldsRow()).isEqualTo(3);
		assertThat(metrics.getTotalgroupedCalls()).isEqualTo(Map.of(100L, 4L));
		assertThat(metrics.getTotalKOCalls()).isEqualTo(0);
		assertThat(metrics.getTotalMissingFieldsRows()).isEqualTo(0);
		assertThat(metrics.getTotalNoContentMessages()).isEqualTo(0);
		assertThat(metrics.getTotalOKCalls()).isEqualTo(2);
		assertThat(metrics.getWordOcurrenceRanking()).isEqualTo(Map.of(1, "Hello"));
	}

}
