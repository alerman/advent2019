package com.alerman.advent2019.day14;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.HashCodeExclude;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Ingredient {
    @HashCodeExclude
    @EqualsExclude
    private int required;
    private String name;
}
