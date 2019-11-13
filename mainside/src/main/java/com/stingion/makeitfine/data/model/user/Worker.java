package com.stingion.makeitfine.data.model.user;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "WORKER")
@PrimaryKeyJoinColumn(name = "ID")
@ToString(callSuper = true)
public class Worker extends Contact {}
