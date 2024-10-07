package com.betrybe.museumfinder.exception;

/**
 * Exceção lançada quando as coordenadas do museu são inválidas.
 */

public class InvalidCoordinateException extends RuntimeException {
  public InvalidCoordinateException(String message) {
    super(message);
  }
}
