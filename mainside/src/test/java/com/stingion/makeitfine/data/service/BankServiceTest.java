package com.stingion.makeitfine.data.service;

import com.stingion.makeitfine.data.model.Bank;
import org.junit.Ignore;

//todo: add testing of user_profile many-to-many relation
public class BankServiceTest extends EntityServiceTestAncestor<Bank> {

    @Ignore//todo: should be fixed (not ignore)
    @Override
    public void deleteTest() {
        super.deleteTest();
    }
}
