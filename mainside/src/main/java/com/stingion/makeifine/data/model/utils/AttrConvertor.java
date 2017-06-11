package com.stingion.makeifine.data.model.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import java.util.Arrays;

/**
 * Created by joe on 11.06.17.
 */
public class AttrConvertor {

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
