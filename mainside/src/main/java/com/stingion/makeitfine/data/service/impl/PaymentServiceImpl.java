/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 * @author stingion
 */
package com.stingion.makeitfine.data.service.impl;

import com.stingion.makeitfine.data.model.payment.Payment;
import com.stingion.makeitfine.data.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl extends EntityServiceImpl<Payment> implements PaymentService {}
