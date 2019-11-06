package com.stingion.makeitfine.data.service;

import com.stingion.makeitfine.data.repository.EntityHelper;
import com.stingion.makeitfine.testconfiguration.MajorTestConfiguration;
import com.stingion.makeitfine.testconfiguration.ServiceTestConfiguration;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Import(MajorTestConfiguration.class)
@Transactional
@Disabled
public class EntityServiceTestAncestor<T> {

    @Autowired
    protected EntityHelper<T> eH;

    @Autowired
    protected EntityService<T> entityService;

    @Autowired
    protected ServiceTestConfiguration.EntityTestData<T> entityTestData;

    @Test
    public void findAllTest() {
        assertThat(entityService.findAll().toString()).isEqualTo(entityTestData.getFindAll());
    }

    //todo: move test to test from junit 5
    @Test
    public void findByIdTest() {
        assertThat(entityService.findById(entityTestData.getId()).toString()).isEqualTo(entityTestData.getFindById());
    }

    @DisplayName("Delete entity from storage")
    @Test
    public void deleteTest() {
        int beforeDelete = eH.getCount();
        T entity = entityService.findById(entityTestData.getId());
        entityService.delete(entity);
        int afterDelete = eH.getCount();

        assertThat(beforeDelete == afterDelete + 1);
        assertThat(!eH.isExist(entity));
    }

    @DisplayName("Insert entity into storage")
    @Test
    public void saveTest() {
        int beforeSave = eH.getCount();
        entityService.save(entityTestData.getSavedEntity());
        int afterSave = eH.getCount();

        assertThat(beforeSave + 1 == afterSave);
        assertThat(eH.isExist(entityTestData.getSavedEntity()));
    }

    @Test
    public void updateTest() {
        int beforeUpdate = eH.getCount();

        T entityBeforeUpdate = entityService.findById(entityTestData.getId());
        assertThat(entityBeforeUpdate.toString()).isNotEqualTo(entityTestData.getUpdateEntity().toString());
        entityService.update(entityTestData.getUpdateEntity());

        int afterUpdate = eH.getCount();

        T entityAfterUpdate = entityService.findById(entityTestData.getId());

        assertThat(entityAfterUpdate.toString()).isEqualTo(entityTestData.getUpdateEntity().toString());
        assertThat(beforeUpdate == afterUpdate);
    }
}