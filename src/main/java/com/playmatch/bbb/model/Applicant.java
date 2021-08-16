package com.playmatch.bbb.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Applicant {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private String id;

    @Column
    @Enumerated(EnumType.STRING)
    private ApplicantStatus status = ApplicantStatus.PENDING_REVIEW;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(name = "video_path")
    private String videoPath;
}
