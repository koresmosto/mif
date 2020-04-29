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
import javax.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Message implements Serializable {

    private static final long serialVersionUID = 540827535458245848L;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy/MM/dd HH:mm:ss.SSS");

    private LocalDateTime localDateTime;
    private String msg;

    public Message(String msg) {
        this.msg = msg;
        this.localDateTime = LocalDateTime.now();
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

    @Nullable
    public Object someForCheckerFramework() {
        Object o = objPositiveOrNull();
        getMsg().equals(new Object()); // if msg not set it gives exception
        // return Optional.ofNullable(o).orElse(5).equals(new Object());
        return o;
    }

    @Nullable
    private Object objPositiveOrNull() {
        return new SecureRandom().nextInt() > 0 ? "positive" : null;
    }
}
