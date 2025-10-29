package com.chakray.userapi.mapper;

import com.chakray.userapi.models.User;
import com.chakray.userapi.dto.UserResult;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Mapeo de un único User a UserResult
    UserResult userToUserResult(User user);

    // Mapeo de una lista de Users a una lista de UserResults
    List<UserResult> usersToUsersResults(List<User> users);
}
