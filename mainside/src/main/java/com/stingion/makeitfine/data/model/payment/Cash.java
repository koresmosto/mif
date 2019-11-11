/*
 * Created in scope of "Make it fine" project
 */

package com.stingion.makeitfine.data.model.payment;

import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created on 12.06.17.
 */
@Entity
@ToString(callSuper = true)
@DiscriminatorValue("Cash")
public class Cash extends Recharge {
}
