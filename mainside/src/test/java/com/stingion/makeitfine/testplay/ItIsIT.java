package com.stingion.makeitfine.testplay;

import com.stingion.makeitfine.testconfiguration.MajorTestConfiguration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("it")
@Import(MajorTestConfiguration.class)
@Disabled
public class ItIsIT {
    @Autowired
    private JdbcTemplate template;

    @Test
    public void testDefaultSettings() throws Exception {
        assertThat(this.template).isNotNull();
        assertThat(this.template.queryForObject("SELECT COUNT(*) from BANK",
                Integer.class)).isEqualTo(5);
    }
}
