package com.jhd.dotime.tasktime.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AllocationType {
    DAY("day"),
    WEEK("week"),
    MONTH("month"),
    YEAR("year")
    ;
    private String type;

    @JsonCreator
    public static AllocationType from(String sub){
        for (AllocationType type : AllocationType.values()){
            if(type.getType().equals(sub)){
                return type;
            }
        }
        return null;
    }
}
