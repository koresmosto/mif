/**
 * Created in scope of "Make it fine" project
 */
package com.stingion.makeitfine.data.service.impl;

import com.stingion.makeitfine.data.model.user.Contact;
import com.stingion.makeitfine.data.service.ContactService;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl extends EntityServiceImpl<Contact> implements ContactService {
}
