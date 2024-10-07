package com.betrybe.museumfinder.service;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.util.CoordinateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável por operações relacionadas a museus.
 */
@Service
public class MuseumService implements MuseumServiceInterface {

  private final MuseumFakeDatabase museumDatabase;

  @Autowired
  public MuseumService(MuseumFakeDatabase museumDatabase) {
    this.museumDatabase = museumDatabase;
  }

  @Override
  public Museum createMuseum(Museum museum) {
    if (!isCoordinateValid(museum.getCoordinate())) {
      throw new InvalidCoordinateException("As coordenadas fornecidas são inválidas.");
    }
    
    return museumDatabase.saveMuseum(museum);
  }

  /**
   * Verifica se a coordenada é válida utilizando o utilitário CoordinateUtil.
   *
   * @param coordinate objeto do tipo Coordinate
   * @return true se a coordenada for válida, false caso contrário
   */
  private boolean isCoordinateValid(Coordinate coordinate) {
    return CoordinateUtil.isCoordinateValid(coordinate);
  }

  /**
   * Método para obter o museu mais próximo com base na coordenada e distância máxima.
   *
   * @param coordinate a coordenada de referência
   * @param maxDistance a distância máxima permitida em quilômetros
   * @return o museu mais próximo dentro da distância especificada
   * @throws InvalidCoordinateException se a coordenada for inválida
   * @throws MuseumNotFoundException se nenhum museu for encontrado dentro da distância
   */
  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double maxDistance) {
    // Valida a coordenada antes de realizar a busca
    if (!isCoordinateValid(coordinate)) {
      throw new InvalidCoordinateException("Coordenada inválida fornecida.");
    }

    // Busca o museu mais próximo usando o banco de dados falso
    return museumDatabase.getClosestMuseum(coordinate, maxDistance)
        // Caso nenhum museu seja encontrado, lançar exceção
        .orElseThrow(() -> new MuseumNotFoundException(
          "Nenhum museu encontrado dentro da distância especificada."));
  }

  @Override
  public Museum getMuseum(Long id) {
    return null;  // Implementação futura
  }
}
