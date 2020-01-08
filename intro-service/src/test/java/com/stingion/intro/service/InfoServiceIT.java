/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2020
 *  @author stingion
 */

package com.stingion.intro.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.stingion.intro.domain.Info;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("integration_test")
class InfoServiceIT {

  private static final Info info1 = new Info("key1", "details1");
  private static final Info info2 = new Info("key2", "details2");
  private static final Info info3 = new Info("key3", "details3");

  @Autowired
  private InfoService infoService;

  @BeforeEach
  public void setup() {
    infoService.insert(info1);
    infoService.insert(info2);
    infoService.insert(info3);
  }

  @Test
  @Disabled//todo: should be fixed
  public void findAll() {
    List<Info> findAll = infoService.findAll();

    assertThat(findAll.size()).isEqualTo(3);
    assertThat(findAll).contains(info1);
    assertThat(findAll).contains(info2);
    assertThat(findAll).contains(info3);
  }

  @Test
  public void findByKey() {
    assertThat(infoService.findByKey("key2").getDetails()).isEqualTo("details2");
  }

  @Test
  public void findByNotExistingKey() {
    Throwable exception =
        assertThrows(NoSuchElementException.class, () -> infoService.findByKey("key4"));
    assertTrue(exception.getMessage().equalsIgnoreCase("No value present"));
  }

  @Test
  public void insert() {
    Info insertedInfo = new Info("insertedKey", "insertedDetails");
    infoService.insert(insertedInfo);
    List<Info> findAll = infoService.findAll();

    assertThat(findAll.size()).isEqualTo(4);
    assertThat(findAll).contains(insertedInfo);
  }

  @Test
  public void insertWithExistingKey() {
    assertThrows(DuplicateKeyException.class, () -> infoService.insert(new Info("key2", "any")));
  }

  @Test
  public void delete() {
    infoService.delete(info3);
    List<Info> findAll = infoService.findAll();

    assertThat(findAll.size()).isEqualTo(2);
    assertThat(findAll).doesNotContain(info3);
  }

  @Test
  public void deleteByNotExistingKey() {
    Throwable exception =
        assertThrows(IllegalStateException.class,
            () ->
                infoService.delete(new Info("any", "any"))
        );
    assertTrue(exception.getMessage().startsWith("Could not obtain identifier"));
  }

  @Test
  public void findById() {
    Info foundByKey = infoService.findByKey("key2");
    Info foundByIdInfo = infoService.findById(foundByKey.getId());

    assertThat(foundByIdInfo).isEqualTo(foundByKey);
    assertThat(foundByIdInfo.getId()).isEqualTo(foundByKey.getId());
  }

  @Test
  @Disabled//todo: should be fixed
  public void update() {
    Info updateDetailsInfo = infoService.findByKey("key1");
    updateDetailsInfo.setDetails("details1New");
    infoService.update(updateDetailsInfo);
    assertThat(infoService.findByKey("key1").getDetails()).isEqualTo("details1New");

    Info updateKeyInfo = infoService.findByKey("key3");
    updateKeyInfo.setKey("key3New");
    infoService.update(updateKeyInfo);
    assertThat(infoService.findByKey("key3New").getDetails()).isEqualTo("details3");

    assertThat(infoService.findAll().size()).isEqualTo(3);
  }

  @Test
  public void updateToExistingKey() {
    assertThrows(DuplicateKeyException.class, () -> infoService.update(new Info("key2", "any")));
  }
}