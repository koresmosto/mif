package com.stingion.makeitfine.data.service;

import com.stingion.makeitfine.data.model.User;
import org.junit.Test;

public class UserServiceTest extends EntityServiceTestAncestor<User> {

    @Test
    public void show() {
        System.out.println(entityService);
    }
}
