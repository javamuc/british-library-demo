package com.demoproject.bl.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NameMapperTest {

    private NameMapper nameMapper;

    @BeforeEach
    public void setUp() {
        nameMapper = new NameMapperImpl();
    }
}
