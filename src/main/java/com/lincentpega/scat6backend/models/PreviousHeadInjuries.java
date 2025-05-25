package com.lincentpega.scat6backend.models;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
public class PreviousHeadInjuries {
    private boolean wasDiagnosed;
    @Nullable
    private String additionalInfo;
}