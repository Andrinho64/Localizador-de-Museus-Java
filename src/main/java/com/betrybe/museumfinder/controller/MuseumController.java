package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import com.betrybe.museumfinder.util.ModelDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para gerenciar operações relacionadas a museus.
 */
@RestController
@RequestMapping("/museums")
public class MuseumController {

  private final MuseumServiceInterface museumService;

  @Autowired
    public MuseumController(MuseumServiceInterface museumService) {
    this.museumService = museumService;
  }

  /**
     * Cria um novo museu.
     *
     * @param museumCreationDto os dados para criar o museu
     * @return a resposta com os dados do museu criado
     */
  @PostMapping
    public ResponseEntity<MuseumDto> createMuseum(
      @RequestBody @Validated MuseumCreationDto museumCreationDto) {
    Museum museum = ModelDtoConverter.dtoToModel(museumCreationDto);
    Museum createdMuseum = museumService.createMuseum(museum);
    MuseumDto createdMuseumDto = ModelDtoConverter.modelToDto(createdMuseum);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdMuseumDto);
  }

  /**
   * Obtém o museu mais próximo baseado nas coordenadas fornecidas.
   *
   * @param latitude a latitude da localização
   * @param longitude a longitude da localização
   * @param maxDistance a distância máxima em quilômetros
   * @return a resposta com os dados do museu mais próximo
   */
  @GetMapping("/closest")
    public ResponseEntity<MuseumDto> getClosestMuseum(
        @RequestParam(name = "lat") double latitude,
        @RequestParam(name = "lng") double longitude,
        @RequestParam(name = "max_dist_km") double maxDistance
  ) {
    Coordinate coordinate = new Coordinate(latitude, longitude);
    Museum closestMuseum = museumService.getClosestMuseum(coordinate, maxDistance);
    MuseumDto closestMuseumDto = ModelDtoConverter.modelToDto(closestMuseum);
    return ResponseEntity.ok(closestMuseumDto);
  }
}
