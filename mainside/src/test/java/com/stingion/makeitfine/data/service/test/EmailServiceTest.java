/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2019
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.junit.jupiter.api.Test;

public class EmailServiceTest extends ServiceTestProvision {

    private Integer v1;
    private Integer v2;

    public EmailServiceTest(Integer v1, Integer v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public EmailServiceTest() {
    }

    public void setV1(Integer v1) {
        this.v1 = v1;
    }

    public void setV2(Integer v2) {
        this.v2 = v2;
    }

    @Test
    public void ssoIdEmails() {
        Map<String, String> expected = new HashMap<>();
        expected.put("user1", "user1@any.xxxmail");
        expected.put("user2", "user2@any.xyz");
        expected.put("user3", "user3@any.xxxmail");

        Map<String, String> actual = emailService.ssoIdEmails();
        assertEquals(expected, actual);
    }

    //todo: remove method
    @Test
    public void test() {
        Object oo = 2 > 5 ? null : 1;
        assertNull(oo);
        //Object o = max(new Object(), "abc");
        Object o2 = max(new Object(), null);
        //        assertNotNull(o);
        //        assertNotNull(o2);
        //        assertNotNull(String.valueOf(o));
        Object o3 = String.valueOf(o2);
        //Object o3 = o2;
        SecureRandom random = new SecureRandom();
        if (random.nextInt() > 3) {
            o3 = v1;
        } else if (random.nextInt() < 5) {
            o3 = v2;
        }
        v1 = 3;
        v2 = 5;
        assertNotNull(one(o3));
        assertNull(one(null));
    }

    @RequiresNonNull({"v1", "this.v1"})
    @EnsuresNonNull({"v1"})
    public @PolyNull Object one(@PolyNull Object o) {
        int v1 = this.v1;
        int v2 = this.v2;
        return v1 > v2 ? v1 : o;
    }

    public static <T> @PolyNull T max(@PolyNull T x, @PolyNull T y) {
        return x;
    }

    public static <T> @PolyNull T max2(@PolyNull T x, @PolyNull T y) {
        return y;
    }
}
