/*
 * Created in scope of "Make it fine" project
 */

package com.stingion.makeifine.data.model;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "WORKER")
@PrimaryKeyJoinColumn(name = "ID")
@ToString(callSuper = true)
public class Worker extends Contact {
}
