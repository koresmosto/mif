/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2020
 *  @author stingion
 */

package com.stingion.intro.service;

import com.stingion.intro.domain.Info;
import com.stingion.intro.repository.InfoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService {

  private final InfoRepository repository;

  @Override
  public List<Info> findAll() {
    return repository.findAll();
  }
}
