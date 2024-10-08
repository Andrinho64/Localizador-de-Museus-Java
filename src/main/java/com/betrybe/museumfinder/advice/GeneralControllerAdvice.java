package com.betrybe.museumfinder.advice;

import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe responsável por tratar exceções e gerar respostas apropriadas.
 */
@ControllerAdvice
public class GeneralControllerAdvice {

  /**
   * Trata exceções do tipo InvalidCoordinateException.
   *
   * @param ex a exceção lançada
   * @return ResponseEntity com status 400 e mensagem de erro
   */
  @ExceptionHandler(InvalidCoordinateException.class)
  public ResponseEntity<String> handleInvalidCoordinateException(InvalidCoordinateException ex) {
    return new ResponseEntity<>("Coordenada inválida!", HttpStatus.BAD_REQUEST);
  }

  /**
   * Trata exceções do tipo MuseumNotFoundException.
   *
   * @param ex a exceção lançada
   * @return ResponseEntity com status 404 e mensagem de erro
   */
  @ExceptionHandler(MuseumNotFoundException.class)
  public ResponseEntity<String> handleMuseumNotFoundException(MuseumNotFoundException ex) {
    return new ResponseEntity<>("Museu não encontrado!", HttpStatus.NOT_FOUND);
  }

  /**
   * Trata qualquer outra exceção genérica.
   *
   * @param ex a exceção lançada
   * @return ResponseEntity com status 500 e mensagem de erro
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGenericException(Exception ex) {
    return new ResponseEntity<>("Erro interno!", HttpStatus.INTERNAL_SERVER_ERROR);
  }
}