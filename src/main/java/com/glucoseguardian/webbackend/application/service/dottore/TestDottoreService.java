package com.glucoseguardian.webbackend.application.service.dottore;

import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class DottoreService for testing.
 */
@Service
public class TestDottoreService extends AbstractDottoreService {


  @Override
  public DottoreServiceInterface getImplementation() {
    return new DottoreServiceConcrete();
  }
}