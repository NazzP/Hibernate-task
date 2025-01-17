package org.example.gymcrmsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

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
    @OneToMany(mappedBy = "specialization")
    private List<Trainer> trainers;

    @ToString.Exclude
    @OneToMany(mappedBy = "trainingType")
    private List<Training> trainings;
}
