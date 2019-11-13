/*
 * Created in scope of "Make it fine" project
 */

package com.stingion.makeitfine.data.model.user;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER")
@PrimaryKeyJoinColumn(name = "ID")
@ToString(callSuper = true)
public class Customer extends Contact {}
