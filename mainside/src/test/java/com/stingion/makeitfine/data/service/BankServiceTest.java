package com.stingion.makeitfine.data.service;

import com.stingion.makeitfine.data.model.Bank;
import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

//todo: add testing of user_profile many-to-many relation
public class BankServiceTest extends EntityServiceTestAncestor<Bank> {

    @Ignore//todo: should be fixed (not ignore)
    //Watch cascade deletion in related entities (bank, credit_card, order etc;
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Test
    @Override
    public void deleteTest() {
        super.deleteTest();
    }
}
