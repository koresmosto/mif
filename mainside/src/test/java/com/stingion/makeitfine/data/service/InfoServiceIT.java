/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2019
 *  @author stingion
 */

package com.stingion.makeitfine.data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.common.collect.Lists;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("integration_test")
@Transactional
class InfoServiceIT {

  @Autowired
  private InfoService infoService;

  @Test
  void usersAndItsRoles() {
    List<String> expected = Lists.newArrayList(
        "admin : { ADMIN }",
        "bill : { USER }",
        "danny : { USER }",
        "kenny : { ADMIN, DBA }",
        "nicole : { DBA }",
        "sam : { ADMIN }",
        "sudo : { USER, ADMIN, DBA }",
        "tsuser : { USER }"
    );

    assertEquals(expected, infoService.usersAndItsRoles());
    infoService.usersAndItsRoles();
  }
}