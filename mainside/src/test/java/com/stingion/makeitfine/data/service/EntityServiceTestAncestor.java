package com.stingion.makeitfine.data.service;

import com.stingion.makeitfine.ItIsTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Import(ItIsTestConfiguration.class)
public class EntityServiceTestAncestor<T> {

    @Autowired
    protected EntityService<T> entityService;

    @Autowired
    protected String any;

    @Test
    public void findAll() {
    }
}