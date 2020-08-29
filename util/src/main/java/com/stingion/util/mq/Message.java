/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2020
 *  @author stingion
 */

package com.stingion.util.mq;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.nullness.qual.NonNull;

@NoArgsConstructor
@Getter
public class Message implements Serializable {

    private static final long serialVersionUID = 540827535458245848L;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");

    private String msg;

    private LocalDateTime localDateTime;

    public Message(@NonNull String msg) {
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
}
