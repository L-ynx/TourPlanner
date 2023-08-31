package org.fancylynx.application.DAL.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Repository;

// 2do
//, uniqueConstraints = {
//        @UniqueConstraint(name = "tour_unique_name", columnNames = "test_name2")
//})
@Entity(name = "tour")
@Table(name = "tour")
@Data
@Repository
public class Tour {
    @Id
    @SequenceGenerator(name = "tour_sequence", sequenceName = "tour_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tour_sequence")
    @Column(name = "id", unique = true, updatable = false, nullable = false, columnDefinition = "BIGINT")
    private long id; // note: best practice to use long for id, not really necessary regarding the scope of this project

    @Column(name = "name", unique = true, nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "origin", columnDefinition = "TEXT")
    private String origin;

    @Column(name = "destination", columnDefinition = "TEXT")
    private String destination;

    @Column(name = "transport_type", columnDefinition = "TEXT")
    private String transportType; // 2do: make enum?

    @Column(name = "distance", columnDefinition = "TEXT")
    private String distance; // 2do: float?

    @Column(name = "estimated_time", columnDefinition = "TEXT")
    private String estimatedTime; // 2do: time?

    @Column(name = "image_path", columnDefinition = "TEXT")
    private String imagePath;
}

