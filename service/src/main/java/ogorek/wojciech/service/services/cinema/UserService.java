package ogorek.wojciech.service.services.cinema;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.favourite.Favourite;
import ogorek.wojciech.domain.model.favourite.dto.CreateFavDto;
import ogorek.wojciech.domain.model.favourite.dto.GetFavDto;
import ogorek.wojciech.domain.model.favourite.dto.GetFavUserGenreDto;
import ogorek.wojciech.domain.model.favourite.dto.validator.CreateFavouriteDtoValidator;
import ogorek.wojciech.domain.model.favourite.repository.FavouritesRepository;
import ogorek.wojciech.domain.model.favourite.views.FavouriteUserGenre;
import ogorek.wojciech.domain.model.user.User;
import ogorek.wojciech.domain.model.user.UserFunctors;
import ogorek.wojciech.domain.model.user.dto.CreateUserDto;
import ogorek.wojciech.domain.model.user.dto.GetUserDto;
import ogorek.wojciech.domain.model.user.dto.GetUserHistoryDto;
import ogorek.wojciech.domain.model.user.dto.GetUserWithTicketDto;
import ogorek.wojciech.domain.model.user.dto.validator.CreateUserDtoValidator;
import ogorek.wojciech.domain.model.user.repository.UserRepository;
import ogorek.wojciech.domain.model.user.views.UserHistory;
import ogorek.wojciech.domain.model.user.views.UserWithTicket;
import ogorek.wojciech.service.services.exceptions.AppServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final FavouritesRepository favouritesRepository;


    public List<GetUserDto> findAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(User::toGetUserDto)
                .collect(Collectors.toList());
    }

    public GetUserDto findUserById(Long id) {
        return userRepository
                .findById(id)
                .map(User::toGetUserDto)
                .orElseThrow();
    }

    public GetUserDto registerUser(CreateUserDto createUserDto) {
        Validator.validate(new CreateUserDtoValidator(), createUserDto);

        if (userRepository.getUserByUsername(createUserDto.getUsername()).isPresent()) {
            throw new AppServiceException("Fail to register user. Username has already been taken " + createUserDto.getUsername());
        }
        var user = createUserDto.toUser();
        user.setPassword(passwordEncoder);
        return userRepository
                .add(user)
                .map(User::toGetUserDto)
                .orElseThrow();

    }

    public Long updateUser(Long id, CreateUserDto createUserDto) {
        Validator.validate(new CreateUserDtoValidator(), createUserDto);
        if(!createUserDto.getPassword().equals(createUserDto.getPasswordConfirmation())){
            throw new AppServiceException("Updating user password confirmation failed");
        }

        var userToUpdate = User
                .builder()
                .id(id)
                .name(createUserDto.getName())
                .surname(createUserDto.getSurname())
                .username(createUserDto.getUsername())
                .email(createUserDto.getEmail())
                .role(createUserDto.getRole())
                .password(createUserDto.getPassword())
                .build();

        userToUpdate.setPassword(passwordEncoder);
        return userRepository
                .update(userToUpdate)
                .map(UserFunctors.toId)
                .orElseThrow();
    }

    public GetUserDto deleteUser(Long id) {
        return userRepository
                .delete(id)
                .map(User::toGetUserDto)
                .orElseThrow();
    }

    public boolean deleteAllUsers() {
        return userRepository
                .deleteAll();
    }


    public List<GetUserHistoryDto> findUserHistory(Long id) {
        return userRepository
                .getUserHistory(id)
                .stream()
                .map(UserHistory::toGetUserHistoryDto)
                .collect(Collectors.toList());
    }

    public GetUserDto findUserByNameAndSurname(String name, String surname) {
        return userRepository
                .getUserByNameAndSurname(name, surname)
                .map(User::toGetUserDto)
                .orElseThrow();
    }

    public List<GetUserWithTicketDto> findUserWithTicket(Long id) {
        return userRepository
                .getUserWithTickets(id)
                .stream()
                .map(UserWithTicket::toGetUserWithTicketDto)
                .collect(Collectors.toList());
    }

    public GetUserDto findUserIdByUsername(String username) {
        return userRepository
                .getUserByUsername(username)
                .map(User::toGetUserDto)
                .orElseThrow(() -> new IllegalArgumentException("There is no such username in Db"));
    }


    //---------------------------FAVOURITES-------------------------------


    public List<GetFavDto> findUserFavourites(Long id) {
        return favouritesRepository
                .getUserFavourites(id)
                .stream()
                .map(Favourite::toGetFavDto)
                .collect(Collectors.toList());
    }

    public List<GetFavUserGenreDto> findFavouritesUserGenre(Long id) {
        return favouritesRepository
                .findFavouritesUserGenre(id)
                .stream()
                .map(FavouriteUserGenre::toGetFavUserGenreDto)
                .collect(Collectors.toList());
    }

    public GetFavDto addFavourite(CreateFavDto createFavDto) {
        Validator.validate(new CreateFavouriteDtoValidator(), createFavDto);
        var favourite = createFavDto.toFavourite();
        return favouritesRepository
                .add(favourite)
                .map(Favourite::toGetFavDto)
                .orElseThrow();
    }


}
