/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2020
 *  @author stingion
 */

package com.stingion.intro.service;

import java.util.List;

public interface EntityService<T> {

  List<T> findAll();
}
