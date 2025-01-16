package org.example.gymcrmsystem.mapper;

import org.example.gymcrmsystem.config.AppConfig;
import org.example.gymcrmsystem.dto.TrainingTypeDto;
import org.example.gymcrmsystem.entity.TrainingType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class TrainingTypeMapperTest {

    private static TrainingTypeMapper trainingTypeMapper;
    private static AnnotationConfigApplicationContext context;

    @BeforeAll
    public static void setUp() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        trainingTypeMapper = context.getBean(TrainingTypeMapper.class);
    }

    @AfterAll
    public static void tearDown() {
        if (context != null) {
            context.close();
        }
    }

    @Test
    void convertToDto() {

        TrainingType trainingType = TrainingType.builder()
                .trainingTypeName("Yoga")
                .build();

        TrainingTypeDto trainingTypeDto = trainingTypeMapper.convertToDto(trainingType);

        assertNotNull(trainingTypeDto);
        assertEquals(trainingType.getTrainingTypeName(), trainingTypeDto.getTrainingTypeName());
    }

    @Test
    void convertToDtoWithNullTrainee() {
        TrainingTypeDto trainingTypeDto = trainingTypeMapper.convertToDto(null);
        assertNull(trainingTypeDto, "Expected convertToDto to return null when input is null");
    }

    @Test
    void convertToEntity() {
        TrainingTypeDto trainingTypeDto = TrainingTypeDto.builder()
                .trainingTypeName("Yoga")
                .build();

        TrainingType trainingType = trainingTypeMapper.convertToEntity(trainingTypeDto);

        assertNotNull(trainingType);
        assertEquals(trainingTypeDto.getTrainingTypeName(), trainingType.getTrainingTypeName());
    }

    @Test
    void convertToEntityWithNullTraineeDto() {
        TrainingType trainingType = trainingTypeMapper.convertToEntity(null);
        assertNull(trainingType, "Expected convertToEntity to return null when input is null");
    }
}
