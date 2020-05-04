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
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.regex.qual.Regex;
import org.checkerframework.com.google.errorprone.annotations.Immutable;

//@AllArgsConstructor
//@Data
@NoArgsConstructor
public class Message implements Serializable {

    private static final long serialVersionUID = 540827535458245848L;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy/MM/dd HH:mm:ss.SSS");

    private LocalDateTime localDateTime;

    private static @NonNull Float f = 5.5f;

    @Getter(onMethod_ = {@Nullable})
    @Setter(onParam_ = {@Nullable})
    private Integer oneOf;

    //    private static @Regex String findNumbers = "(\\d*)";

    private @Regex String findNumbers2 = "(\\d*)ab(m)+1+";

    //    private static @Regex(1) String FIND_NUMBERS = "\\d*";

    private @Regex(1) String msg;

    public String getMsg() {
        return msg;
    }

    //    private @MonotonicNonNull Integer m;

    public Message(@NonNull @Regex(1) String msg) {
        this.msg = msg;
        this.localDateTime = LocalDateTime.now();
    }

    //    public @Regex String parenthesize(@Regex String regex) {
    //        return "(" + regex + ")"; // Even though the parentheses are not @Regex Strings,
    //        // the whole expression is a @Regex String
    //    }


    public @NonNull String str;

    void realError(@MonotonicNonNull Object p) {
        //:: error: (monotonic.type.incompatible)
        var x = p;
        //:: error: (monotonic.type.incompatible)
        var x1 = new Random().nextBoolean() ? new Object() : null;
        @MonotonicNonNull Object k = x1;
        k = new Object();
        var k2 = k();
        k = k();
        // It would be nice not to raise the following
        // error.
        //:: error: (monotonic.type.incompatible)
    }

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

    public String formattedTime() {
        return DATE_TIME_FORMATTER.format(localDateTime);
    }

    public @Nullable Object someForCheckerFramework() {
        Okey okey = new Okey(5);
        okey.getI();
        //parenthesize("(a)bc");
        //okey.setI(55);
        okey = new Okey(52);
        Integer k = 5;
        //findNumbers = ("(" + "abc" + ")");
        Object o = objPositiveOrNull();
        //getMsg().equals(new Object()); // if msg not set it gives exception
        // return Optional.ofNullable(o).orElse(5).equals(new Object());
        return o;
    }

    private @Nullable Object objPositiveOrNull() {
        return new SecureRandom().nextInt() > 0 ? "positive" : null;
    }

    @Value
    @Immutable
    @AllArgsConstructor
//    @Setter
    public static class Okey {

        private Integer i;
    }
}
