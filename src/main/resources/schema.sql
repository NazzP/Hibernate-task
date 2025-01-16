CREATE TABLE training_types
(
    training_type_id   BIGINT PRIMARY KEY,
    training_type_name VARCHAR(255) NOT NULL UNIQUE,
    CONSTRAINT check_training_type_name CHECK (training_type_name IN ('Yoga', 'Cardio', 'Strength Training', 'CrossFit'))
);

CREATE TABLE users
(
    user_id    BIGINT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    username   VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    is_active  BOOLEAN      NOT NULL
);

CREATE TABLE trainees
(
    trainee_id    BIGINT PRIMARY KEY,
    date_of_birth DATE,
    address       VARCHAR(255),
    user_id       BIGINT NOT NULL UNIQUE,
    CONSTRAINT fk_trainee_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE trainers
(
    trainer_id     BIGINT PRIMARY KEY,
    user_id        BIGINT NOT NULL UNIQUE,
    specialization BIGINT NOT NULL,
    CONSTRAINT fk_trainer_user FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT fk_trainer_specialization FOREIGN KEY (specialization) REFERENCES training_types (training_type_id)
);

CREATE TABLE trainings
(
    training_id      BIGINT PRIMARY KEY,
    trainee_id       BIGINT,
    trainer_id       BIGINT,
    training_name    VARCHAR(255) NOT NULL,
    training_type_id BIGINT,
    date             DATE         NOT NULL,
    duration         INTEGER      NOT NULL,
    CONSTRAINT fk_training_trainee FOREIGN KEY (trainee_id) REFERENCES trainees (trainee_id),
    CONSTRAINT fk_training_trainer FOREIGN KEY (trainer_id) REFERENCES trainers (trainer_id),
    CONSTRAINT fk_training_type FOREIGN KEY (training_type_id) REFERENCES training_types (training_type_id)
);

CREATE TABLE trainee_trainer
(
    trainee_id BIGINT NOT NULL,
    trainer_id BIGINT NOT NULL,
    PRIMARY KEY (trainee_id, trainer_id),
    CONSTRAINT fk_trainee_trainer_trainee FOREIGN KEY (trainee_id) REFERENCES trainees (trainee_id),
    CONSTRAINT fk_trainee_trainer_trainer FOREIGN KEY (trainer_id) REFERENCES trainers (trainer_id)
);
