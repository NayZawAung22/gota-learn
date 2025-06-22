package org.example.gotalearn.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student extends User{

    private String address;
    @Enumerated(EnumType.STRING)
    private Education education;
}
