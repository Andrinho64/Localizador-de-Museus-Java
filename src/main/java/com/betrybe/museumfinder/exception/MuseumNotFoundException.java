package com.betrybe.museumfinder.exception;

/**
 * Exceção lançada quando nenhum museu é encontrado dentro da distância especificada.
 */
public class MuseumNotFoundException extends RuntimeException {
  public MuseumNotFoundException() {
    super();
  }

  public MuseumNotFoundException(String message) {
    super(message);
  }
}