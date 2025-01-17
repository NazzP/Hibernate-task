package org.example.gymcrmsystem.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trainees")
public class Trainee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainee_id", nullable = false, unique = true)
    private Long id;

    @ToString.Exclude
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Basic(fetch = FetchType.LAZY)
    @ToString.Exclude
    @Column(name = "address")
    private String address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ToString.Exclude
    @OneToMany(mappedBy = "trainer", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Training> trainings;

    @ToString.Exclude
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "trainee_trainer",
            joinColumns = @JoinColumn(name = "trainee_id", referencedColumnName = "trainee_id"),
            inverseJoinColumns = @JoinColumn(name = "trainer_id", referencedColumnName = "trainer_id")
    )
    private List<Trainer> trainers = new ArrayList<>();
}
