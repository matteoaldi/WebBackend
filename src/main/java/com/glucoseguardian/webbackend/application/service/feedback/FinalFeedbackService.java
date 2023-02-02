package com.glucoseguardian.webbackend.application.service.feedback;

import com.glucoseguardian.webbackend.application.service.farmaco.FarmacoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class.
 */
@Service
public class FinalFeedbackService extends AbstractFeedbackService {
  @Autowired
  @Qualifier("FeedbackServiceConcrete")
  FeedbackServiceInterface feedbackService;

  @Override
  public FeedbackServiceInterface getImplementation() {
    return feedbackService;
  }
}
