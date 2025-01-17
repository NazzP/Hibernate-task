package org.example.gymcrmsystem.entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trainers")
public class Trainer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_id", nullable = false, unique = true)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialization", referencedColumnName = "training_type_name", nullable = false)
    private TrainingType specialization;

    @ToString.Exclude
    @OneToMany(mappedBy = "trainer", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Training> trainings;

    @ToString.Exclude
    @ManyToMany(mappedBy = "trainers", fetch = FetchType.LAZY)
    private List<Trainee> trainees = new ArrayList<>();
}
