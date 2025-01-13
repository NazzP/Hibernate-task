package org.example.gymcrmsystem.service;

import jakarta.validation.Valid;
import org.example.gymcrmsystem.dto.TrainingDto;
import org.example.gymcrmsystem.dto.TrainingTypeDto;

import java.time.LocalDate;
import java.util.List;

public interface TrainingService {

    /**
     * Creates a new Training session.
     * <p>
     * This method accepts a {@link TrainingDto} object containing the details of the new Training session
     * and passes it to the service layer to handle the creation of the session.
     * <p>
     *
     * @param trainingDto An object containing the details of the new Training session.
     * @return The created {@link TrainingDto} with the assigned ID and relevant information.
     */
    TrainingDto add(@Valid TrainingDto trainingDto);

    /**
     * Retrieves a list of training sessions for a trainee using specified criteria.
     * <p>
     *
     * @param traineeUsername The username of the trainee whose training sessions are to be retrieved.
     * @param fromDate        The start date for filtering training sessions.
     * @param toDate          The end date for filtering training sessions.
     * @param trainerName    The firstName of Trainer whose training sessions are to be retrieved.
     * @param trainingType    The type of training sessions to filter by.
     * @return A list of {@link TrainingDto} objects representing the trainee's training sessions.
     */
    List<TrainingDto> getTraineeTrainingsListCriteria(String traineeUsername, LocalDate fromDate,
                                                      LocalDate toDate, String trainerName, String trainingType);

    /**
     * Retrieves a list of training sessions for a trainer using specified criteria.
     * <p>
     *
     * @param trainerUsername The username of the trainer whose training sessions are to be retrieved.
     * @param fromDate        The start date for filtering training sessions.
     * @param toDate          The end date for filtering training sessions.
     * @param traineeName    The firstName of trainee whose training sessions are to be retrieved.
     * @return A list of {@link TrainingDto} objects representing the trainer's training sessions.
     */
    List<TrainingDto> getTrainerTrainingsListCriteria(String trainerUsername, LocalDate fromDate,
                                                      LocalDate toDate, String traineeName);
}

