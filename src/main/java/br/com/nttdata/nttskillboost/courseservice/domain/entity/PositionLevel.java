package br.com.nttdata.nttskillboost.courseservice.domain.entity;

import lombok.Getter;

@Getter
public enum PositionLevel {
    JUNIOR("JUNIOR"),
    PLENARY("PLENARY"),
    SENIOR("SENIOR"),
    TECH_LEAD("TECH_LEAD"),
    CES("CES");

    private final String description;

    private PositionLevel(String description) {
        this.description = description;
    }

    public static PositionLevel getPositionLevel(String status) {
        for (PositionLevel positionLevel : PositionLevel.values()) {
            if (positionLevel.name().equalsIgnoreCase(status)) {
                return positionLevel;
            }
        }
        throw new IllegalArgumentException("No constant with text " + status + " found");
    }
}
