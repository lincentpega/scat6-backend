package com.lincentpega.scat6backend.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Document(collection = "sportsmans")
@Getter
@Setter
@NoArgsConstructor
public class Sportsman {
    @Id
    private Long id;
    private String fullName;
    private LocalDate birthDate;
    private String passport;
    private Gender gender;
    private LeadingHand leadingHand;
    private String otherInfo;
    private String sportType;
    private int yearOfStudy;
    private int completedYears;
    private String nativeLanguage;
    private String spokenLanguage;
}
