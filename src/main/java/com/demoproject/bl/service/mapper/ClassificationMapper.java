package com.demoproject.bl.service.mapper;

import com.demoproject.bl.domain.Classification;
import com.demoproject.bl.service.dto.ClassificationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Classification} and its DTO {@link ClassificationDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClassificationMapper extends EntityMapper<ClassificationDTO, Classification> {}
