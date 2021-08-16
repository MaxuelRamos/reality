package com.playmatch.bbb.mapper;

import com.google.common.base.Enums;
import com.playmatch.bbb.model.Applicant;
import com.playmatch.bbb.model.ApplicantStatus;
import com.playmatch.bbb.resources.model.ApplicantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ApplicantMapper {

    ApplicantMapper INSTANCE = Mappers.getMapper( ApplicantMapper.class );

    ApplicantDTO toDTO(Applicant applicant);

    Applicant toEntity(ApplicantDTO applicant);

    default com.playmatch.bbb.resources.model.ApplicantStatus statusToDto(ApplicantStatus status) {
        String statusStr = status == null ? "" : status.toString();
        return Enums.getIfPresent(com.playmatch.bbb.resources.model.ApplicantStatus.class, statusStr).orNull();
    }

    default ApplicantStatus statusToEntity(com.playmatch.bbb.resources.model.ApplicantStatus status) {
        String statusStr = status == null ? "" : status.toString();
        return Enums.getIfPresent(ApplicantStatus.class, statusStr).orNull();
    }

    List<ApplicantDTO> toDTOs(List<Applicant> list);
}