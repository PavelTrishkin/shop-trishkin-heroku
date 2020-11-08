package ru.gb.trishkin.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users_tbl")
public class User {
    private static final String SEQUENCE_NAME = "user_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name_fld")
    private String name;

    @Column(name = "password_fld")
    private String password;

    @Column(name = "email_fld")
    private String email;

    @Column(name = "archive_fld")
    private boolean archive;

    @Column(name = "role_fld")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Bucket bucket;
}
