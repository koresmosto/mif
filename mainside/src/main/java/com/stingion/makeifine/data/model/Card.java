/*
 * Created in scope of "Make it fine" project
 */

package com.stingion.makeifine.data.model;

import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created on 12.06.17.
 */
@Entity
@ToString(callSuper = true)
@DiscriminatorValue("Card")
public class Card extends Recharge {
}
