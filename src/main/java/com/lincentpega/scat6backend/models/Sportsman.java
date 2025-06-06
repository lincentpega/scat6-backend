package com.lincentpega.scat6backend.models;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Document(collection = "sportsmans")
@Getter
@Setter
@NoArgsConstructor
public class Sportsman {
    @Id
    private String id;
    private String fullName;
    private LocalDate birthDate;
    @Nullable
    private String passport;
    private Gender gender;
    private LeadingHand leadingHand;
    @Nullable
    private String otherInfo;
    private String sportType;
    @Nullable
    private Integer yearOfStudy;
    @Nullable
    private Integer completedYears;
    @Nullable
    private String nativeLanguage;
    @Nullable
    private String spokenLanguage;
    @Nullable
    private LocalDate inspectionDate;
    @Nullable
    private LocalDate injuryDate;
    @Nullable
    private LocalTime injuryTime;
    
    // New fields from frontend interface
    @Nullable
    private PreviousHeadInjuries previousHeadInjuries;
    @Nullable
    private Boolean migraines;
    @Nullable
    private Boolean learningDisabilities;
    @Nullable
    private Boolean adhd;
    @Nullable
    private Boolean depressionAnxiety;
    @Nullable
    private String currentMedications;
    @Nullable
    private Integer numberOfBrainInjuries;
    @Nullable
    private LocalDate lastBrainInjuryDate;
    @Nullable
    private String brainInjurySymptoms;
    @Nullable
    private Integer daysOfRecovery;
}
