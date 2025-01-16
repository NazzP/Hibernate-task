package org.example.gymcrmsystem.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.example.gymcrmsystem.config.AppConfig;
import org.example.gymcrmsystem.entity.Trainee;
import org.example.gymcrmsystem.entity.User;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
class TraineeRepositoryTest {

    @Autowired
    private TraineeRepository traineeRepository;

    private Trainee sampleTrainee;

    @BeforeEach
    void setUp() {
        sampleTrainee = Trainee.builder()
                .user(User.builder()
                        .firstName("FirstName")
                        .lastName("LastName")
                        .username("FirstName.LastName")
                        .password("password")
                        .isActive(true)
                        .build())
                .dateOfBirth(LocalDate.of(2000, 1, 1))
                .address("123 Main St")
                .build();
    }

    @Test
    void saveTraineeSuccess() {
        Trainee savedTrainee = traineeRepository.save(sampleTrainee);
        assertNotNull(savedTrainee);
        assertEquals("FirstName", savedTrainee.getUser().getFirstName());
        assertEquals(sampleTrainee.getUser().getUsername(), savedTrainee.getUser().getUsername());
    }

    @Test
    void saveTraineeWithExistingId() {
        sampleTrainee.setId(1L);
        Trainee savedTrainee = traineeRepository.save(sampleTrainee);
        assertNotNull(savedTrainee);
        assertEquals("FirstName", savedTrainee.getUser().getFirstName());
        assertEquals(sampleTrainee.getUser().getUsername(), savedTrainee.getUser().getUsername());
    }

    @Test
    void saveTraineeWithNullId() {
        sampleTrainee.setId(null);
        Trainee savedTrainee = traineeRepository.save(sampleTrainee);
        assertNotNull(savedTrainee);
        assertNotNull(savedTrainee.getId());
    }

    @Test
    void findByUsernameSuccess() {
        Trainee savedTrainee = traineeRepository.save(sampleTrainee);
        Optional<Trainee> foundTrainee = traineeRepository.findByUsername(savedTrainee.getUser().getUsername());
        assertTrue(foundTrainee.isPresent());
        assertEquals(savedTrainee.getUser().getFirstName(), foundTrainee.get().getUser().getFirstName());
    }

    @Test
    void findByUsernameNoResult() {
        Optional<Trainee> foundTrainee = traineeRepository.findByUsername("nonexistentUsername");
        assertFalse(foundTrainee.isPresent());
    }

    @Test
    void deleteByUsernameSuccess() {
        Trainee savedTrainee = traineeRepository.save(sampleTrainee);
        Optional<Trainee> foundTrainee = traineeRepository.findByUsername(savedTrainee.getUser().getUsername());
        assertTrue(foundTrainee.isPresent());
        traineeRepository.deleteByUsername(savedTrainee.getUser().getUsername());
        foundTrainee = traineeRepository.findByUsername(savedTrainee.getUser().getUsername());
        assertFalse(foundTrainee.isPresent());
    }

    @Test
    void deleteByUsernameDoesNotExist() {
        traineeRepository.deleteByUsername("nonexistentUsername");
        Optional<Trainee> foundTrainee = traineeRepository.findByUsername("nonexistentUsername");
        assertFalse(foundTrainee.isPresent());
    }
}
