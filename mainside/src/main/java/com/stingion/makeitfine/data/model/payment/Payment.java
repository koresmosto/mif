/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 * @author stingion
 */
package com.stingion.makeitfine.data.model.payment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/** Created on 12.06.17. */
@Entity
@Getter
@Setter
@ToString
@Table(name = "PAYMENT")
@EqualsAndHashCode(of = {"id"})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PAYMENT_TYPE", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Undefined")
public class Payment {
  @TableGenerator(
      name = "Payment_gen",
      table = "SEQUENCES",
      pkColumnName = "SEQ_NAME",
      valueColumnName = "SEQ_NUMBER",
      pkColumnValue = "PAYMENT_SEQUENCE",
      allocationSize = 1)
  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "Payment_gen")
  private int id;

  @Column(name = "AMOUNT")
  private Double amount;

  // See: Ordering hashcode method comment
  @Override
  public int hashCode() {
    return 37;
  }
}
