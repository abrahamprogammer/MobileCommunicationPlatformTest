package com.abraham.mobilecommunicationplatformtest.services.mappers;

import org.mapstruct.Mapper;

import com.abraham.mobilecommunicationplatformtest.dtos.MobileCommunicationContainerDto;
import com.abraham.mobilecommunicationplatformtest.entities.MobileCommunicationContainer;

@Mapper(componentModel = "spring")
public interface MobileCommunicationContainerDtoMapper {

    /**
     * Entity to Dto
     * @param entity
     * @return Dto
     */
    MobileCommunicationContainerDto fromEntity(MobileCommunicationContainer entity);
}
