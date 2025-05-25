package com.lincentpega.scat6backend.models;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;

@Getter
@Setter
@Document(collection = "medical_office_assessments")
public class MedicalOfficeAssessment {
    @Id
    private String id;
    private String sportsmanId;
    private SportsmanInfo sportsmanInfo;
    private Symptoms symptoms;
    private CognitiveFunctions cognitiveFunctions;
    private List<ShortTermMemory> shortTermMemory;
    private List<ConcentrationNumbers> concentrationNumbers;
    private ConcentrationMonths concentrationMonths;
    private Mbess mbess;
    private TandemWalk tandemWalk;
    private DeferredMemory deferredMemory;
    private boolean wasKnownBefore;
    private boolean differsFromKnownBefore;

    @Getter
    @Setter
    public static class SportsmanInfo {
        private PreviousHeadInjuries previousHeadInjuries;
        private boolean migraines;
        private boolean learningDisabilities;
        private boolean adhd;
        private boolean depressionAnxiety;
        private String currentMedications;

        @Getter
        @Setter
        public static class PreviousHeadInjuries {
            private boolean wasDiagnosed;
            private String additionalInfo;
        }
    }

    @Getter
    @Setter
    public static class Symptoms {
        private short headache;
        private short headPressure;
        private short neckPain;
        private short nausea;
        private short dizziness;
        private short blurredVision;
        private short balance;
        private short lightSensitivity;
        private short noiseSensitivity;
        private short slowness;
        private short foggy;
        private short notRight;
        private short concentration;
        private short memory;
        private short fatigue;
        private short confusion;
        private short drowsiness;
        private short emotionality;
        private short irritability;
        private short depression;
        private short anxiety;
        private short sleepIssues;
        private boolean worseAfterPhysicalActivity;
        private boolean worseAfterMentalActivity;
        private double wellnessPercent;
    }

    @Getter
    @Setter
    public static class CognitiveFunctions {
        private short month;
        private short date;
        private short weekday;
        private short year;
        private short time;
    }

    @Getter
    @Setter
    public static class ShortTermMemory {
        private short trial;
        private int score;
        private LocalDateTime testFinishTime;
    }

    @Getter
    @Setter
    public static class ConcentrationNumbers {
        private short numberList;
        private int score;
    }

    @Getter
    @Setter
    public static class ConcentrationMonths {
        private int errors;
        private int time; // seconds
    }

    @Getter
    @Setter
    public static class Mbess {
        private short legTested;
        private String surface;
        private String footwear;
        private Casually casually;
        private Styrofoam styrofoam;

        @Getter
        @Setter
        public static class Casually {
            private int standsOnBothFeet;
            private int tandemPosition;
            private int standsOnOneFeet;
        }

        @Getter
        @Setter
        public static class Styrofoam {
            private int standsOnBothFeet;
            private int tandemPosition;
            private int standsOnOneFeet;
        }
    }

    @Getter
    @Setter
    public static class TandemWalk {
        private IsolatedTask isolatedTask;
        private Dual dual;
        private boolean failedTrials;
        private String failReason;

        @Getter
        @Setter
        public static class IsolatedTask {
            private List<Integer> trials; // e.g., [10, 22, 33] (seconds)
            private int avgResult;
            private int bestResult;
        }

        @Getter
        @Setter
        public static class Dual {
            private Practice practice;
            private Cognitive cognitive;

            @Getter
            @Setter
            public static class Practice {
                private int errors;
                private int time; // seconds
            }

            @Getter
            @Setter
            public static class Cognitive {
                private List<Trial> trials;

                @Getter
                @Setter
                public static class Trial {
                    private int id;
                    private int errors;
                    private int time; // seconds
                }
            }
        }
    }

    @Getter
    @Setter
    public static class DeferredMemory {
        private LocalDateTime startTime;
        private String list;
        private int result;
    }
}
