package org.example.gotalearn.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Admin extends User{
    private BigDecimal platformShare;
}
