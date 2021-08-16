package com.playmatch.bbb.mapper;

import com.playmatch.bbb.model.Applicant;
import com.playmatch.bbb.resources.model.ApplicantDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-16T12:12:47-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
public class ApplicantMapperImpl implements ApplicantMapper {

    @Override
    public ApplicantDTO toDTO(Applicant applicant) {
        if ( applicant == null ) {
            return null;
        }

        ApplicantDTO applicantDTO = new ApplicantDTO();

        applicantDTO.setId( applicant.getId() );
        applicantDTO.setStatus( statusToDto( applicant.getStatus() ) );
        applicantDTO.setName( applicant.getName() );
        applicantDTO.setEmail( applicant.getEmail() );
        applicantDTO.setPhone( applicant.getPhone() );
        applicantDTO.setCpf( applicant.getCpf() );

        return applicantDTO;
    }

    @Override
    public Applicant toEntity(ApplicantDTO applicant) {
        if ( applicant == null ) {
            return null;
        }

        Applicant applicant1 = new Applicant();

        applicant1.setId( applicant.getId() );
        applicant1.setStatus( statusToEntity( applicant.getStatus() ) );
        applicant1.setName( applicant.getName() );
        applicant1.setEmail( applicant.getEmail() );
        applicant1.setPhone( applicant.getPhone() );
        applicant1.setCpf( applicant.getCpf() );

        return applicant1;
    }

    @Override
    public List<ApplicantDTO> toDTOs(List<Applicant> list) {
        if ( list == null ) {
            return null;
        }

        List<ApplicantDTO> list1 = new ArrayList<ApplicantDTO>( list.size() );
        for ( Applicant applicant : list ) {
            list1.add( toDTO( applicant ) );
        }

        return list1;
    }
}
