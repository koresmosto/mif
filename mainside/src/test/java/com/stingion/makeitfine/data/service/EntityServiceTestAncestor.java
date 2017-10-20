package com.stingion.makeitfine.data.service;

import com.stingion.makeitfine.ItIsTestConfiguration;
import com.stingion.makeitfine.ServiceTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Import(ItIsTestConfiguration.class)
public class EntityServiceTestAncestor<T> {

    @Autowired
    protected EntityService<T> entityService;

    @Autowired
    protected ServiceTestConfiguration.EntityTestData<T> entityTestData;

    @Test
    public void findAllTest() {
        assertEquals(entityTestData.getFindAll(), entityService.findAll().toString());
    }

    @Test
    public void findByIdTest() {
        assertEquals(entityTestData.getFindById(), entityService.findById(entityTestData.getId()).toString());
    }

    @Test
    @Rollback//todo:solve issue with rollback after db changes in tests
    public void deleteTest() {
        T entity = entityService.findById(entityTestData.getId());
        entityService.delete(entity);
        assertEquals(entityTestData.getDelete(), entityService.findAll().toString());
    }
}