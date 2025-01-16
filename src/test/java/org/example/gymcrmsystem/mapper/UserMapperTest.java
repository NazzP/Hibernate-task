package org.example.gymcrmsystem.mapper;

import org.example.gymcrmsystem.config.AppConfig;
import org.example.gymcrmsystem.dto.UserDto;
import org.example.gymcrmsystem.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserMapperTest {

    private static UserMapper userMapper;
    private static AnnotationConfigApplicationContext context;

    @BeforeAll
    public static void setUp() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        userMapper = context.getBean(UserMapper.class);
    }

    @Test
    void convertToDto() {
        User user = User.builder()
                .firstName("FirstName")
                .lastName("LastName")
                .username("FirstName.LastName")
                .password("password")
                .isActive(true)
                .build();

        UserDto userDto = userMapper.convertToDto(user);

        assertNotNull(userDto);
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getPassword(), userDto.getPassword());
        assertEquals(user.getIsActive(), userDto.getIsActive());
    }

    @Test
    void convertToDtoWithNullTrainee() {
        UserDto userDto = userMapper.convertToDto(null);
        assertNull(userDto, "Expected convertToDto to return null when input is null");
    }

    @Test
    void convertToEntity() {
        UserDto userDto = UserDto.builder()
                .firstName("FirstName")
                .lastName("LastName")
                .password("password")
                .username("FirstName.LastName")
                .isActive(true)
                .build();

        User user = userMapper.convertToEntity(userDto);

        assertNotNull(user);
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(userDto.getLastName(), user.getLastName());
        assertEquals(userDto.getUsername(), user.getUsername());
        assertEquals(userDto.getPassword(), user.getPassword());
        assertEquals(userDto.getIsActive(), user.getIsActive());

    }

    @Test
    void convertToEntityWithNullTraineeDto() {
        User user = userMapper.convertToEntity(null);
        assertNull(user, "Expected convertToEntity to return null when input is null");
    }
}
