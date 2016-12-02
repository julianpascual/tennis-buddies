package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.Game_statusDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Game_status and its DTO Game_statusDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Game_statusMapper {

    Game_statusDTO game_statusToGame_statusDTO(Game_status game_status);

    List<Game_statusDTO> game_statusesToGame_statusDTOs(List<Game_status> game_statuses);

    Game_status game_statusDTOToGame_status(Game_statusDTO game_statusDTO);

    List<Game_status> game_statusDTOsToGame_statuses(List<Game_statusDTO> game_statusDTOs);
}
