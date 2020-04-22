/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 * @author stingion
 */

package com.stingion.makeitfine.controller.it;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class StartUpControllerIT extends ControllerITProvision {

    @Test
    public void index() {
        String[] responseBody = new String[]{getResponseBody("/index"), getResponseBody("/")};

        for (String r : responseBody) {
            assertTrue(r.contains("English"));
            assertTrue(r.contains(" | "));
            assertTrue(r.contains("Русский"));
            assertTrue(r.contains("Make it fine"));
            assertTrue(r.contains("Домашняя страница") || r.contains("Home page"));
        }
    }

    @Test
    public void greeting() {
        assertEquals("Make it fine \"makeitfine\"", getResponseBody("/info"));
        assertEquals("Make it fine \"makeitfine\"", getResponseBody("/info?details=any"));
        assertEquals("Stingion : stingion@gmail.com", getResponseBody("/info?details=author"));
        assertEquals("Social Network for workers", getResponseBody("/info?details=Purpose"));
        assertEquals(
                "Development stage : development milestone", getResponseBody("/info?details=stage"));
    }
}
