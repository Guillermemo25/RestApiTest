package com.chakray.userapi.mapper;

import com.chakray.userapi.models.Address;
import com.chakray.userapi.models.User;
import com.chakray.userapi.dto.AddressRequest;
import com.chakray.userapi.dto.AddressResult;
import com.chakray.userapi.dto.UserRequest;
import com.chakray.userapi.dto.UserResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Map of AddressRequest to Address
    Address addressRequestToAddress(AddressRequest addressRequest);
    // Map of Address to AddressRequest
    AddressResult addressToAddressResult(Address address);

    List<Address> addressesRequestToAddresses(List<AddressRequest> addressesRequest);
    // Map of List of Address to List of AddressResult
    List<AddressResult> addressesToAddressResults(List<Address> addresses);


    // Map of UserResult to User
    @Mapping(target = "addresses", source = "addresses")
    User userRequestToUser(UserRequest userRequest);
    // Map of User to UserResult
    UserResult userToUserResult(User user);

    // Map of List of UserResult to List of User
    List<UserResult> usersToUsersResults(List<User> users);
}
