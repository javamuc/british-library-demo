package com.demoproject.bl.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.demoproject.bl.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NameDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NameDTO.class);
        NameDTO nameDTO1 = new NameDTO();
        nameDTO1.setId(1L);
        NameDTO nameDTO2 = new NameDTO();
        assertThat(nameDTO1).isNotEqualTo(nameDTO2);
        nameDTO2.setId(nameDTO1.getId());
        assertThat(nameDTO1).isEqualTo(nameDTO2);
        nameDTO2.setId(2L);
        assertThat(nameDTO1).isNotEqualTo(nameDTO2);
        nameDTO1.setId(null);
        assertThat(nameDTO1).isNotEqualTo(nameDTO2);
    }
}
