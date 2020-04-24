/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 *  @author stingion
 */

package com.stingion.makeitfine.data.model.utils;

import java.util.Arrays;
import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

/**
 * Created by joe on 11.06.17.
 */
public class AttrConverter {

    @Convert
    public static class StateConvertor implements AttributeConverter<State, String> {

        @Override
        public String convertToDatabaseColumn(State attribute) {
            return attribute.getState();
        }

        @Override
        public State convertToEntityAttribute(String dbData) {
            return Arrays.stream(State.values())
                    .filter(s -> s.getState().equalsIgnoreCase(dbData))
                    .findAny()
                    .orElse(null);
        }
    }
}
