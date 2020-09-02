/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2020
 */

package com.stingion.kafka.event;

import com.stingion.util.mq.Message;
import java.io.Serializable;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MessageEvent extends Message implements Serializable {

    private static final long serialVersionUID = -5647687076938924957L;

    public MessageEvent(String message) {
        super(message);
    }
}