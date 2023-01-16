package net.becquerel.springboot.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_id")
    private String  emailId;

    @Column(name = "fileName")
    private String  fileName;

    @Column(name="url")
    private String url;

    @Column(name="url2")
    private String url2;

    /*@Column(name="logo",nullable = true)
    private byte[] logo;*/









}
