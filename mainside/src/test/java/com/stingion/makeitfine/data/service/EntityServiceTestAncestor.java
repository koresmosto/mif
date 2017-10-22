package com.stingion.makeitfine.data.service;

import com.stingion.makeitfine.ItIsTestConfiguration;
import com.stingion.makeitfine.ServiceTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

//Todo:
//1) Write fucntion to get "select count(*) for entities"
//2) Optimize tests
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Import(ItIsTestConfiguration.class)
@Transactional
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
    public void deleteTest() {
        int beforeDelete = entityService.findAll().size();
        T entity = entityService.findById(entityTestData.getId());
        entityService.delete(entity);
        int afterDelete = entityService.findAll().size();

        assertEquals(beforeDelete, afterDelete + 1);
        assertThat(entityService.findAll()).doesNotContain(entity);
    }

    @Test
    public void saveTest() {
        int beforeSave = entityService.findAll().size();
        entityService.save(entityTestData.getSavedEntity());
        int afterSave = entityService.findAll().size();

        assertEquals(beforeSave + 1, afterSave);
        assertThat(entityService.findAll()).contains(entityTestData.getSavedEntity());
    }

    @Test
    public void updateTest() {
        int beforeUpdate = entityService.findAll().size();

        T entityBeforeUpdate = entityService.findById(entityTestData.getId());
        assertThat(entityTestData.getUpdateEntity().toString()).isNotEqualTo(entityBeforeUpdate.toString());

        entityService.update(entityTestData.getUpdateEntity());

        int afterUpdate = entityService.findAll().size();

        T entityAfterUpdate = entityService.findById(entityTestData.getId());

        assertThat(entityTestData.getUpdateEntity().toString()).isEqualTo(entityAfterUpdate.toString());
        assertEquals(beforeUpdate, afterUpdate);
    }
}