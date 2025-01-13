package org.example.gymcrmsystem.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.example.gymcrmsystem.config.AppConfig;
import org.example.gymcrmsystem.dto.UserDto;
import org.example.gymcrmsystem.entity.Trainee;
import org.example.gymcrmsystem.dto.TraineeDto;
import org.example.gymcrmsystem.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

class TraineeMapperTest {

    private TraineeMapper traineeMapper;

    @BeforeEach
    public void setUp() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        traineeMapper = context.getBean(TraineeMapper.class);
    }

    @Test
    void convertToDto() {
        Trainee trainee = Trainee.builder()
                .user(User.builder()
                        .firstName("FirstName")
                        .lastName("LastName")
                        .username("FirstName.LastName")
                        .password("password")
                        .isActive(true).build())
                .dateOfBirth(LocalDate.of(2020, 1, 1))
                .address("123 Main St")
                .build();

        TraineeDto traineeDto = traineeMapper.convertToDto(trainee);

        assertNotNull(traineeDto);
        assertEquals(trainee.getUser().getFirstName(), traineeDto.getUser().getFirstName());
        assertEquals(trainee.getUser().getLastName(), traineeDto.getUser().getLastName());
        assertEquals(trainee.getUser().getUsername(), traineeDto.getUser().getUsername());
        assertEquals(trainee.getUser().getIsActive(), traineeDto.getUser().getIsActive());
        assertEquals(trainee.getDateOfBirth(), traineeDto.getDateOfBirth());
        assertEquals(trainee.getAddress(), traineeDto.getAddress());
    }

    @Test
    void convertToDtoWithNullTrainee() {
        TraineeDto traineeDto = traineeMapper.convertToDto(null);
        assertNull(traineeDto, "Expected convertToDto to return null when input is null");
    }

    @Test
    void convertToEntity() {
        TraineeDto traineeDto = TraineeDto.builder()
                .user(UserDto.builder()
                        .firstName("FirstName")
                        .lastName("LastName")
                        .username("FirstName.LastName")
                        .isActive(true)
                        .build())
                .dateOfBirth(LocalDate.of(2020, 1, 1))
                .address("123 Main St")
                .build();

        Trainee trainee = traineeMapper.convertToEntity(traineeDto);

        assertNotNull(trainee);
        assertEquals(traineeDto.getUser().getFirstName(), trainee.getUser().getFirstName());
        assertEquals(traineeDto.getUser().getLastName(), trainee.getUser().getLastName());
        assertEquals(traineeDto.getUser().getUsername(), trainee.getUser().getUsername());
        assertEquals(traineeDto.getUser().getIsActive(), trainee.getUser().getIsActive());
        assertEquals(traineeDto.getDateOfBirth(), trainee.getDateOfBirth());
        assertEquals(traineeDto.getAddress(), trainee.getAddress());
    }

    @Test
    void convertToEntityWithNullTraineeDto() {
        Trainee trainee = traineeMapper.convertToEntity(null);
        assertNull(trainee, "Expected convertToEntity to return null when input is null");
    }
}