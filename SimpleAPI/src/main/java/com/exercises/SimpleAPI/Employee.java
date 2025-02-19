package com.exercises.SimpleAPI;


import lombok.*;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    enum Gender {
        MALE,
        FEMALE,
        OTHER
    }

    int id;
    String name;
    String dob;
    Gender gender;
    Double salary;

}
