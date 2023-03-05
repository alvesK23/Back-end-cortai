package sptech.school.backend.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sptech.school.backend.request.UserRequest;
import sptech.school.backend.response.UserResponse;
import sptech.school.backend.entities.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;

    public UserEntity toEntity(UserRequest request) {
        return mapper.map(request, UserEntity.class);
    }

    public UserResponse toResponse(UserEntity entity) {
        return mapper.map(entity, UserResponse.class);
    }

    public List<UserResponse> toResponseList(List<UserEntity> list) {
        return list.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
