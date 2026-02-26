package com.ankit.assignmentspringboot.utility;

import tools.jackson.databind.util.StdConverter;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class DateSerializer extends StdConverter<String, LocalDate> {

    @Override
    public LocalDate convert(String value) {
        String date = stream(value.split("-")).map(val -> {
            if (val.length() == 1){
                return "0" + val;
            }
            return val;
        }).collect(Collectors.joining("-"));

        return LocalDate.parse(date);
    }
}
