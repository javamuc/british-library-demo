package com.demoproject.bl.service.mapper;

import com.demoproject.bl.domain.Topic;
import com.demoproject.bl.service.dto.TopicDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Topic} and its DTO {@link TopicDTO}.
 */
@Mapper(componentModel = "spring")
public interface TopicMapper extends EntityMapper<TopicDTO, Topic> {}
