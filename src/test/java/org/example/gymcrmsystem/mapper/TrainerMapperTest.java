package org.example.gymcrmsystem.mapper;

import org.example.gymcrmsystem.config.AppConfig;
import org.example.gymcrmsystem.dto.TrainerDto;
import org.example.gymcrmsystem.dto.TrainingTypeDto;
import org.example.gymcrmsystem.dto.UserDto;
import org.example.gymcrmsystem.entity.Trainer;
import org.example.gymcrmsystem.entity.TrainingType;
import org.example.gymcrmsystem.entity.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class TrainerMapperTest {

    private static TrainerMapper trainerMapper;
    private static AnnotationConfigApplicationContext context;

    @BeforeAll
    public static void setUp() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        trainerMapper = context.getBean(TrainerMapper.class);
    }

    @AfterAll
    public static void tearDown() {
        if (context != null) {
            context.close();
        }
    }

    @Test
    void convertToDto() {
        Trainer trainer = Trainer.builder()
                .user(User.builder()
                        .firstName("FirstName")
                        .lastName("LastName")
                        .username("FirstName.LastName")
                        .password("password")
                        .isActive(true)
                        .build())
                .specialization(TrainingType.builder().id(1L).trainingTypeName("Yoga").build())
                .build();

        TrainerDto trainerDto = trainerMapper.convertToDto(trainer);

        assertNotNull(trainerDto);
        assertEquals(trainer.getUser().getFirstName(), trainerDto.getUser().getFirstName());
        assertEquals(trainer.getUser().getLastName(), trainerDto.getUser().getLastName());
        assertEquals(trainer.getUser().getUsername(), trainerDto.getUser().getUsername());
        assertEquals(trainer.getUser().getIsActive(), trainerDto.getUser().getIsActive());
        assertEquals(trainer.getSpecialization().getTrainingTypeName(), trainerDto.getSpecialization().getTrainingTypeName());
    }

    @Test
    void convertToDtoWithNullTrainee() {
        TrainerDto trainerDto = trainerMapper.convertToDto(null);
        assertNull(trainerDto, "Expected convertToDto to return null when input is null");
    }

    @Test
    void convertToEntity() {
        TrainerDto trainerDto = TrainerDto.builder()
                .user(UserDto.builder()
                        .firstName("FirstName")
                        .lastName("LastName")
                        .username("FirstName.LastName")
                        .isActive(true)
                        .build())
                .specialization(TrainingTypeDto.builder().trainingTypeName("Yoga").build())
                .build();

        Trainer trainer = trainerMapper.convertToEntity(trainerDto);

        assertNotNull(trainer);
        assertEquals(trainerDto.getUser().getFirstName(), trainer.getUser().getFirstName());
        assertEquals(trainerDto.getUser().getLastName(), trainer.getUser().getLastName());
        assertEquals(trainerDto.getUser().getUsername(), trainer.getUser().getUsername());
        assertEquals(trainerDto.getUser().getIsActive(), trainer.getUser().getIsActive());
        assertEquals(trainerDto.getSpecialization().getTrainingTypeName(), trainer.getSpecialization().getTrainingTypeName());
    }

    @Test
    void convertToEntityWithNullTraineeDto() {
        Trainer trainer = trainerMapper.convertToEntity(null);
        assertNull(trainer, "Expected convertToEntity to return null when input is null");
    }
}
