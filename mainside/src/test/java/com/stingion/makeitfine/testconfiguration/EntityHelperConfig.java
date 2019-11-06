package com.stingion.makeitfine.testconfiguration;

import com.stingion.makeitfine.data.model.Bank;
import com.stingion.makeitfine.data.model.Contact;
import com.stingion.makeitfine.data.model.CreditCard;
import com.stingion.makeitfine.data.model.Item;
import com.stingion.makeitfine.data.model.Ordering;
import com.stingion.makeitfine.data.model.Recharge;
import com.stingion.makeitfine.data.model.User;
import com.stingion.makeitfine.data.model.UserProfile;
import com.stingion.makeitfine.data.repository.EntityHelper;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class EntityHelperConfig {

    @Component
    public static class UserHelper extends EntityHelper<User> {
    }

    @Component
    public static class UserProfileHelper extends EntityHelper<UserProfile> {
    }

    @Component
    public static class BankHelper extends EntityHelper<Bank> {
    }

    @Component
    public static class CreditCardHelper extends EntityHelper<CreditCard> {
    }

    @Component
    public static class ContactHelper extends EntityHelper<Contact> {
    }

    @Component
    public static class ItemHelper extends EntityHelper<Item> {
    }

    @Component
    public static class OrderingHelper extends EntityHelper<Ordering> {
    }

    @Component
    public static class RechargeHelper extends EntityHelper<Recharge> {
    }
}
