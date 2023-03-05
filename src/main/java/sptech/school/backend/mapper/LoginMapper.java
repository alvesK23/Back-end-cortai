package sptech.school.backend.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sptech.school.backend.entities.BarberShopEntity;
import sptech.school.backend.entities.UserEntity;
import sptech.school.backend.request.LoginRequest;
import sptech.school.backend.response.BarberResponse;
import sptech.school.backend.entities.BarberEntity;
import sptech.school.backend.response.BarberShopResponse;
import sptech.school.backend.response.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LoginMapper {

    private final ModelMapper mapper;

    public BarberEntity toEntityBarber(LoginRequest request) {
        return mapper.map(request, BarberEntity.class);
    }

    public BarberShopEntity toEntityBarberShop(LoginRequest request) {
        return mapper.map(request, BarberShopEntity.class);
    }

    public UserEntity toEntityUser(LoginRequest request) {
        return mapper.map(request, UserEntity.class);
    }

    public BarberResponse toResponseBarber(BarberEntity entity) {
        return mapper.map(entity, BarberResponse.class);
    }

    public BarberShopResponse toResponseBarberShop(BarberShopEntity entity) {
        return mapper.map(entity, BarberShopResponse.class);
    }

    public UserResponse toResponseUser(UserEntity entity) {
        return mapper.map(entity, UserResponse.class);
    }

    public List<BarberResponse> toResponseList(List<BarberEntity> list) {
        return list.stream()
                .map(this::toResponseBarber)
                .collect(Collectors.toList());
    }

    public List<UserResponse> toResponseListUser(List<UserEntity> list) {
        return list.stream()
                .map(this::toResponseUser)
                .collect(Collectors.toList());
    }
}
