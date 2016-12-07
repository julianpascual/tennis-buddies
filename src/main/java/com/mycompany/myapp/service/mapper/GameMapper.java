package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.GameDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Game and its DTO GameDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, UserMapper.class, })
public interface GameMapper {

    @Mapping(source = "status.id", target = "statusId")
    @Mapping(source = "status.status", target = "statusStatus")
    @Mapping(source = "idUserRequestor.id", target = "idUserRequestorId")
    @Mapping(source = "idUserRequestor.login", target = "idUserRequestorLogin")
    @Mapping(source = "idUserRequested.id", target = "idUserRequestedId")
    @Mapping(source = "idUserRequested.login", target = "idUserRequestedLogin")
    GameDTO gameToGameDTO(Game game);

    List<GameDTO> gamesToGameDTOs(List<Game> games);

    @Mapping(source = "statusId", target = "status")
    @Mapping(source = "idUserRequestorId", target = "idUserRequestor")
    @Mapping(source = "idUserRequestedId", target = "idUserRequested")
    Game gameDTOToGame(GameDTO gameDTO);

    List<Game> gameDTOsToGames(List<GameDTO> gameDTOs);

    default Game_status game_statusFromId(Long id) {
        if (id == null) {
            return null;
        }
        Game_status game_status = new Game_status();
        game_status.setId(id);
        return game_status;
    }
}
