/** Created in scope of "Make it fine" project */
package com.stingion.makeitfine.data.service.impl;

import com.stingion.makeitfine.data.model.CreditCard;
import com.stingion.makeitfine.data.service.CreditCardService;
import org.springframework.stereotype.Service;

@Service
public class CreditCardServiceImpl extends EntityServiceImpl<CreditCard>
    implements CreditCardService {}
