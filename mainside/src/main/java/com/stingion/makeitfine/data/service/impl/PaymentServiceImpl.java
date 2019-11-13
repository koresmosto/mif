package com.stingion.makeitfine.data.service.impl;

import com.stingion.makeitfine.data.model.payment.Payment;
import com.stingion.makeitfine.data.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl extends EntityServiceImpl<Payment> implements PaymentService {}
