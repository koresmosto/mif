/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2020
 *  @author stingion
 */

package com.stingion.intro.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "key")
@Document(collection = "info")
public class Info {

  @Id
  private String id;

  @Indexed(unique = true)
  private String key;
  private String details;
}
