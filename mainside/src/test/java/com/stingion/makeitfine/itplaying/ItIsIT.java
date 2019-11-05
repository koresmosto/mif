package com.stingion.makeitfine.itplaying;

import com.stingion.makeitfine.testconfiguration.MajorTestConfiguration;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("it")
@Import(MajorTestConfiguration.class)
@Ignore
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
