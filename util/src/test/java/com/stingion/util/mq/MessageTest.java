/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2020
 *  @author stingion
 */

package com.stingion.util.mq;

import static org.junit.Assert.assertNotEquals;

import java.security.SecureRandom;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;

@Getter(onMethod_ = {@Nullable})
@Setter(onMethod_ = {@Nullable})
@ToString
public class MessageTest {

    private Integer i;

    private String k = "For test success";

    public void setI(Integer i) {
        this.i = i;
    }

    @Test
    public void someTest() {
        assertNotEquals(new Object().toString(), someForCheckerframework().toString());
    }

    @Nonnull //by default all method return is nonnull (so Nunnull annotation is just mark)
    public Object someForCheckerframework() {
        k.toCharArray();
        Optional.ofNullable(getK()).orElse("").toLowerCase();
        setK(objForCheckerframework() == null ? "no" : "yes");
        return Optional.ofNullable(getI()).orElse(125);
    }

    @Nullable
    private Object objForCheckerframework() {
        return new SecureRandom().nextInt() < 0 ? null : 61;
    }
}