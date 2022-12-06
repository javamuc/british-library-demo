package com.demoproject.bl.service.mapper;

import com.demoproject.bl.domain.Title;
import com.demoproject.bl.service.dto.TitleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Title} and its DTO {@link TitleDTO}.
 */
@Mapper(componentModel = "spring")
public interface TitleMapper extends EntityMapper<TitleDTO, Title> {}
