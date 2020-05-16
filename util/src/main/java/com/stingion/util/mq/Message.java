/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2020
 *  @author stingion
 */

package com.stingion.util.mq;

import java.io.Serializable;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.regex.qual.Regex;
import org.checkerframework.com.google.errorprone.annotations.Immutable;

@NoArgsConstructor
public class Message implements Serializable {

    private static final long serialVersionUID = 540827535458245848L;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy/MM/dd HH:mm:ss.SSS");

    private LocalDateTime localDateTime;

    private static @NonNull Float f = 5.5f;

    @Getter
    @Setter
    private Integer oneOf;

    private @Regex String findNumbers2 = "(\\d*)ab(m)+1+";

    private @Regex(1) String msg;

    public String getMsg() {
        return msg;
    }

    public Message(@NonNull @Regex(1) String msg) {
        this.msg = msg;
        this.localDateTime = LocalDateTime.now();
    }

    public @NonNull String str;

    public String k() {
        if (new Random().nextBoolean()) {
            return Optional.ofNullable(k2()).orElse(">>>");
        }
        throw new UnsupportedOperationException();
    }

    public @Nullable String k2() {
        if (new Random().nextBoolean()) {
            return "sdf";
        }
        return null;
    }

    /**
     * Message fields with {@code yyyy/MM/dd HH:mm:ss.SSS} formatted localDateTime.
     *
     * @return toString
     */
    @Override
    public String toString() {
        return "Message{time = " + DATE_TIME_FORMATTER.format(localDateTime) + ", msg = " + msg + "}";
    }

    private @Nullable Object objPositiveOrNull() {
        return new SecureRandom().nextInt() > 0 ? "positive" : null;
    }

    @Value
    @Immutable
    @AllArgsConstructor
    public static class Okey {

        private Integer i;
    }
}
