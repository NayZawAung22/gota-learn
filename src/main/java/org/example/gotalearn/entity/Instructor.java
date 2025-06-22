package org.example.gotalearn.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.service.annotation.GetExchange;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Instructor extends User{
    private BigDecimal netWorth;
    private String education;
    @ElementCollection
    private List<String> experties=
            new ArrayList<>();

    public void addExperties(String name){
        this.experties.add(name);
    }
}
