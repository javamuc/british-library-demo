package com.demoproject.bl.service.mapper;

import com.demoproject.bl.domain.Name;
import com.demoproject.bl.service.dto.NameDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Name} and its DTO {@link NameDTO}.
 */
@Mapper(componentModel = "spring")
public interface NameMapper extends EntityMapper<NameDTO, Name> {}
