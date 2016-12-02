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
    @Mapping(source = "id_user_requestor.id", target = "id_user_requestorId")
    @Mapping(source = "id_user_requestor.login", target = "id_user_requestorLogin")
    @Mapping(source = "id_user_requested.id", target = "id_user_requestedId")
    @Mapping(source = "id_user_requested.login", target = "id_user_requestedLogin")
    GameDTO gameToGameDTO(Game game);

    List<GameDTO> gamesToGameDTOs(List<Game> games);

    @Mapping(source = "statusId", target = "status")
    @Mapping(source = "id_user_requestorId", target = "id_user_requestor")
    @Mapping(source = "id_user_requestedId", target = "id_user_requested")
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
