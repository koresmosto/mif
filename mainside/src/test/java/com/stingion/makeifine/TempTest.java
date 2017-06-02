package com.stingion.makeifine;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by joe on 02.06.17.
 */
public class TempTest {

    @Test
    public void get5() throws Exception {
        assertEquals(new Temp().get5(), 5);
    }
}