/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 * @author stingion
 */

package com.stingion.makeitfine.data.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.stingion.makeitfine.testconfiguration.ServiceTestConfiguration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("integration_test")
@Import(ServiceTestConfiguration.class)
@Transactional
@Disabled
public class EntityServiceITAncestor<T> {

  @Autowired
  protected EntityHelper<T> eH;

  @Autowired
  protected EntityService<T> entityService;

  @Autowired
  protected ServiceTestConfiguration.EntityTestData<T> entityTestData;

  @Test
  public void findAllTest() {
    assertThat(entityTestData.getFindAll()).isEqualTo(entityService.findAll().toString());
  }

  // todo: move test to test from junit 5
  @Test
  public void findByIdTest() {
    assertThat(entityTestData.getFindById())
        .isEqualTo(entityService.findById(entityTestData.getId()).toString());
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
    assertThat(entityBeforeUpdate.toString())
        .isNotEqualTo(entityTestData.getUpdateEntity().toString());
    entityService.update(entityTestData.getUpdateEntity());

    int afterUpdate = eH.getCount();

    T entityAfterUpdate = entityService.findById(entityTestData.getId());

    assertThat(entityTestData.getUpdateEntity().toString()).isEqualTo(entityAfterUpdate.toString());
    assertThat(beforeUpdate == afterUpdate);
  }
}
