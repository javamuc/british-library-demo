package com.demoproject.bl.service.mapper;

import com.demoproject.bl.domain.Record;
import com.demoproject.bl.service.dto.RecordDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Record} and its DTO {@link RecordDTO}.
 */
@Mapper(componentModel = "spring")
public interface RecordMapper extends EntityMapper<RecordDTO, Record> {}
