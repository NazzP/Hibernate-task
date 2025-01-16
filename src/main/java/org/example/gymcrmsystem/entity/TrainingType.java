package org.example.gymcrmsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "training_types")
@Immutable
public class TrainingType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_type_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "training_type_name", nullable = false, unique = true)
    private String trainingTypeName;

    @ToString.Exclude
    @OneToMany(mappedBy = "specialization", fetch = FetchType.EAGER)
    private List<Trainer> trainers;

    @ToString.Exclude
    @OneToMany(mappedBy = "trainingType")
    private List<Training> trainings;
}
