package com.abraham.mobilecommunicationplatformtest.services.mappers;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.abraham.mobilecommunicationplatformtest.entities.Kpis;
import com.abraham.mobilecommunicationplatformtest.entities.MobileCommunicationContainer;

/**
 * AuxKpisServiceMapper
 * @author Abraham
 *
 */
@Service
public class AuxKpisServiceMapper {
	
	private Kpis kpis;
	
	/**
	 * Map mobileCommunicationContainer into a new Kpi Object, witch contains call / msg info
	 * @param mobileCommunicationContainer
	 * @return processed Kpi
	 */
	public Kpis processKpisInfo(MobileCommunicationContainer mobileCommunicationContainer) {

		kpis = new Kpis();
		kpis.setTotalProcessedJSONFiles(mobileCommunicationContainer.getTotalJSONProcessed());
		kpis.setTotalRows(mobileCommunicationContainer.getCalls().size() + mobileCommunicationContainer.getMessages().size());
		kpis.setTotalCalls(mobileCommunicationContainer.getCalls().size());
		kpis.setTotalMessages(mobileCommunicationContainer.getMessages().size());
		kpis.setElapsedMilisecondsPerJSONDateFile(mobileCommunicationContainer.getElapsedMilisecondsPerProcess());
		
		List<Long> originCountryCalls = mobileCommunicationContainer.getCalls().stream().map(call -> call.getOrigin()).distinct().collect(Collectors.toList());
		List<Long> originCountryMessages = mobileCommunicationContainer.getMessages().stream().map(message -> message.getOrigin()).distinct().collect(Collectors.toList());
		Integer totalDifferentOriginCountryCodes = Stream.of(originCountryCalls, originCountryMessages).distinct().flatMap(Collection::stream).collect(Collectors.toList()).size();
		kpis.setTotalDifferentOriginCountryCodes(totalDifferentOriginCountryCodes);
		
		List<Long> destinationCountryCalls = mobileCommunicationContainer.getCalls().stream().map(call -> call.getDestination()).distinct().collect(Collectors.toList());
		List<Long> destinationCountryMessages = mobileCommunicationContainer.getMessages().stream().map(message -> message.getDestination()).distinct().collect(Collectors.toList());
		Integer totalDifferentDestinationCountryCodes = Stream.of(destinationCountryCalls, destinationCountryMessages).distinct().flatMap(Collection::stream).collect(Collectors.toList()).size();
		kpis.setTotalDifferentDestinationCountryCodes(totalDifferentDestinationCountryCodes);
		
		return kpis;
	}
}
