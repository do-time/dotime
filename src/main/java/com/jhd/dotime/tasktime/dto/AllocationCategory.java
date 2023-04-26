package com.jhd.dotime.tasktime.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AllocationCategory {
    DAY("day"),
    WEEK("week"),
    MONTH("month"),
    YEAR("year")
    ;
    private String value;

    @JsonCreator
    public static AllocationCategory from(String sub){
        for (AllocationCategory type : AllocationCategory.values()){
            if(type.getValue().equals(sub)){
                return type;
            }
        }
        return null;
    }
}
