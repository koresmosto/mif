/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 * @author stingion
 */

package com.stingion.makeitfine.data.service.impl;

import com.stingion.makeitfine.data.model.user.Contact;
import com.stingion.makeitfine.data.service.ContactService;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl extends EntityServiceImpl<Contact> implements ContactService {}
