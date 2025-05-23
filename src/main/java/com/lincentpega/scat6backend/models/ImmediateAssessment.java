package com.lincentpega.scat6backend.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "immediate_assessments")
public class ImmediateAssessment {
    @Id
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long sportsmanId;
    private ObservableSigns observableSigns;
    private NeckSpineAssessment neckSpineAssessment;
    private GlasgowScale glasgowScale;
    private CoordinationEyeMovement coordinationEyeMovement;
    private MaddocksQuestions maddocksQuestions;

    @Getter
    @Setter
    public static class ObservableSigns {
        private boolean immobile;
        private boolean unprotectedFall;
        private boolean unsteadyGait;
        private boolean disorientation;
        private boolean vacantStare;
        private boolean facialInjury;
        private boolean seizure;
        private boolean highRiskMechanism;
    }

    @Getter
    @Setter
    public static class NeckSpineAssessment {
        private boolean painAtRest;
        private boolean tenderness;
        private boolean fullActiveMovement;
        private boolean normalStrengthSensation;
    }

    @Getter
    @Setter
    public static class GlasgowScale {
        private String eye;
        private String verbal;
        private String motor;
    }

    @Getter
    @Setter
    public static class CoordinationEyeMovement {
        private boolean coordination;
        private boolean eyeMovement;
        private boolean normalEyeMovement;
    }

    @Getter
    @Setter
    public static class MaddocksQuestions {
        private boolean event;
        private boolean period;
        private boolean lastScorer;
        private boolean teamLastWeek;
        private boolean teamWin;
    }
}
