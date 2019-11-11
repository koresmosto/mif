/*
 * Created in scope of "Make it fine" project
 */

package com.stingion.makeitfine.data.model;

import com.stingion.makeitfine.data.model.payment.Recharge;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created on 12.06.17.
 */
@Entity
@ToString(callSuper = true)
@DiscriminatorValue("Card")
public class Card extends Recharge {
}
