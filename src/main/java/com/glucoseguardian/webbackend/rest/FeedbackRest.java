package com.glucoseguardian.webbackend.rest;

import com.glucoseguardian.webbackend.application.service.feedback.AbstractFeedbackService;
import com.glucoseguardian.webbackend.application.service.feedback.FeedbackServiceInterface;
import com.glucoseguardian.webbackend.exceptions.EntityNotFoundException;
import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.DottoreDto;
import com.glucoseguardian.webbackend.storage.dto.FeedbackDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.PazienteDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import com.glucoseguardian.webbackend.storage.entity.Feedback;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import com.glucoseguardian.webbackend.storage.entity.Utente;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
/**
 * Feedback Rest implementation.
 */

@RestController
@RequestMapping("feedback")
public class FeedbackRest {

  @Autowired
  @Qualifier("finalFeedbackService")
  private AbstractFeedbackService feedbackService;

  /**
   * Metodo che gestisce il servizio Feedback findById.
   */
  @PostMapping(value = "/getById", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getFeedback(
      @RequestBody FeedbackDto input) throws UserNotFoundException {
    ResponseEntity<RisultatoDto> response;
    try {
      FeedbackDto dto = getService().findById(input.getId());
      response = new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (Exception ex) {
      response = new ResponseEntity<>(new RisultatoDto("Errore durante la ricerca del feedback"),
          HttpStatus.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }
    return CompletableFuture.completedFuture(response);
  }
  /**
   * Metodo che gestisce il servizio Feedback findByPaziente.
   */

  @PostMapping(value = "/getByPaziente", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getFeedbackByPaziente(
      @RequestBody PazienteDto input) throws Exception {
    ListDto<FeedbackDto> dto = getService().findByPaziente(input.getCodiceFiscale());
    ResponseEntity<RisultatoDto> response = new ResponseEntity<>(dto, HttpStatus.OK);
    return CompletableFuture.completedFuture(response);
  }
  /**
   * Metodo che gestisce il servizio Feedback findByDottore.
   */

  @PostMapping(value = "/getByDottore", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> getFeedbackByDottore(
      @RequestBody DottoreDto input) throws Exception {
    ListDto<FeedbackDto> dto = getService().findByDottore(input.getCodiceFiscale());
    ResponseEntity<RisultatoDto> response = new ResponseEntity<>(dto, HttpStatus.OK);
    return CompletableFuture.completedFuture(response);
  }
  /**
   * Metodo che gestisce il servizio save Feedback.
   */

  @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDto>> saveFeedback(
      @RequestBody FeedbackDto input) {

    // TODO: Add custom checks (es. length, null etc..)

    boolean result = false;
    try {
      result = getService().send(input.getStatoSalute(), input.getOreSonno(), input.getDolori(),
          input.getSvenimenti(), input.getIdPaziente());
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    if (result) {
      return CompletableFuture.completedFuture(
          new ResponseEntity<>(
              new RisultatoDto("Feedback inserito correttamente"),
              HttpStatus.OK
          )
      );
    } else {
      return CompletableFuture.completedFuture(
          new ResponseEntity<>(new RisultatoDto("Errore durante l'inserimento del Feedback"),
              HttpStatus.INTERNAL_SERVER_ERROR));
    }
  }

  private FeedbackServiceInterface getService() {
    return feedbackService.getImplementation();
  }

  private Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }
}