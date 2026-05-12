package cl.duoc.ms_user.service;

import cl.duoc.ms_user.dto.UserRequestDto;
import cl.duoc.ms_user.dto.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRequestDto dto);
    UserResponseDto login(UserRequestDto dto);
}
