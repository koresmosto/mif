/*
 * Created in scope of "Make it fine" project
 */

/*
 * Created in scope of "Make it fine" project
 */

package com.stingion.makeifine.data.model;

import com.stingion.makeifine.data.model.utils.OrderStatus;
import lombok.AllArgsConstructor;
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

/**
 * Created on 12.06.17.
 */
@Entity
@Table(name = "ORDER")
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "status", "description"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @TableGenerator(
            name = "Order_gen",
            table = "SEQUENCES",
            pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_NUMBER",
            pkColumnValue = "ORDER_SEQUENCE",
            allocationSize = 1)
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Order_gen")
    private Integer id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(columnDefinition = "enum('Pending','Performed','Cancelled')")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "CREDIT_CARD_ID")
    private CreditCard creditCard;

    public Order(String description, OrderStatus status) {
        this.description = description;
        this.status = status;
    }
}
