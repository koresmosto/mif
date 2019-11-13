/** Created in scope of "Make it fine" project */
package com.stingion.makeitfine.data.service.impl;

import com.stingion.makeitfine.data.model.Ordering;
import com.stingion.makeitfine.data.service.OrderingService;
import org.springframework.stereotype.Service;

@Service
public class OrderingServiceImpl extends EntityServiceImpl<Ordering> implements OrderingService {}
