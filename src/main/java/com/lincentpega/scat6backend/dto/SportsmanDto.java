package com.lincentpega.scat6backend.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import com.lincentpega.scat6backend.models.Gender;
import com.lincentpega.scat6backend.models.LeadingHand;
import com.lincentpega.scat6backend.models.PreviousHeadInjuries;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class SportsmanDto {
    private String id;
    private String fullName;
    private LocalDate birthDate;
    private String passport;
    private Gender gender;
    private LeadingHand leadingHand;
    private String otherInfo;
    private String sportType;
    private Integer yearOfStudy;
    private Integer completedYears;
    private String nativeLanguage;
    private String spokenLanguage;
    private LocalDate inspectionDate;
    private LocalDate injuryDate;
    private LocalTime injuryTime;
    
    private PreviousHeadInjuries previousHeadInjuries;
    private Boolean migraines;
    private Boolean learningDisabilities;
    private Boolean adhd;
    private Boolean depressionAnxiety;
    private String currentMedications;
    private Integer numberOfBrainInjuries;
    private LocalDate lastBrainInjuryDate;
    private String brainInjurySymptoms;
    private Integer daysOfRecovery;
}
