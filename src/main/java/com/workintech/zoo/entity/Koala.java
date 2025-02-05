package com.workintech.zoo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Koala {
    private Integer id;
    private String name;
    private Double weight;
    private Double sleepHour;
    private String gender;
}
