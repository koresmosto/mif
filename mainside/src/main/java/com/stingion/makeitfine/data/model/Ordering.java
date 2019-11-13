/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 * @author stingion
 */
package com.stingion.makeitfine.data.model;

import com.stingion.makeitfine.data.model.utils.OrderingStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/** Created on 12.06.17. */
@Entity
@Table(name = "ORDERING")
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"item", "creditCard"})
@Getter
@Setter
@NoArgsConstructor
public class Ordering {

  @TableGenerator(
      name = "Ordering_gen",
      table = "SEQUENCES",
      pkColumnName = "SEQ_NAME",
      valueColumnName = "SEQ_NUMBER",
      pkColumnValue = "ORDERING_SEQUENCE",
      allocationSize = 1)
  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "Ordering_gen")
  private Integer id;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(columnDefinition = "enum('Pending','Performed','Cancelled')")
  @Enumerated(EnumType.STRING)
  private OrderingStatus status;

  @ManyToOne
  @JoinColumn(name = "ITEM_ID")
  private Item item;

  @ManyToOne
  @JoinColumn(name = "CREDIT_CARD_ID")
  private CreditCard creditCard;

  // If you tell Hibernate to generate your primary key values, you need to use a fixed hash code,
  // https://developer.jboss.org/wiki/EqualsAndHashCode?_sscc=t
  // https://thoughts-on-java.org/ultimate-guide-to-implementing-equals-and-hashcode-with-hibernate/
  @Override
  public int hashCode() {
    return 31;
  }
}
