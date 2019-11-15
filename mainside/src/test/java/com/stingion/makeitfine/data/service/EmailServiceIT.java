/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2019
 *  @author stingion
 */

package com.stingion.makeitfine.data.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
class EmailServiceIT {

  @Autowired
  private EmailService emailService;

  @Test
  void isAdminEmail() {
    assertFalse(emailService.isActiveAdminEmail("some word"));
    assertFalse(emailService.isActiveAdminEmail("any_email@xyz.com"));
    assertFalse(emailService.isActiveAdminEmail("bill@xyz.com"));
    assertFalse(emailService.isActiveAdminEmail("BILL@XYZ.COM"));
    assertFalse(emailService.isActiveAdminEmail("samy@xyz.com"));
    assertFalse(emailService.isActiveAdminEmail("SAMY@XYZ.COM"));
    assertFalse(emailService.isActiveAdminEmail("kenny@xyz.com"));
    assertFalse(emailService.isActiveAdminEmail("nicloe@xyz.com"));

    assertTrue(emailService.isActiveAdminEmail("admin@xxx.xxx"));
    assertTrue(emailService.isActiveAdminEmail("ADMIN@xxx.xxx"));
  }
}