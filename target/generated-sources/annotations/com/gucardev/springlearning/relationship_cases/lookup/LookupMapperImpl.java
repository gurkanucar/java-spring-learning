package com.gucardev.springlearning.relationship_cases.lookup;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-30T21:47:10+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
public class LookupMapperImpl implements LookupMapper {

    @Override
    public Lookup toEntity(LookupDto dto) {
        if ( dto == null ) {
            return null;
        }

        Lookup lookup = new Lookup();

        Map<String, String> map = dto.getPayloads();
        if ( map != null ) {
            lookup.setPayloads( new HashMap<String, String>( map ) );
        }

        return lookup;
    }

    @Override
    public LookupDto toDto(Lookup entity) {
        if ( entity == null ) {
            return null;
        }

        LookupDto lookupDto = new LookupDto();

        lookupDto.setId( entity.getId() );
        Map<String, String> map = entity.getPayloads();
        if ( map != null ) {
            lookupDto.setPayloads( new HashMap<String, String>( map ) );
        }

        return lookupDto;
    }
}
