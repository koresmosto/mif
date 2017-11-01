package com.stingion.makeitfine.data.repository.util;

import com.stingion.makeitfine.data.model.Bank;
import com.stingion.makeitfine.data.model.CreditCard;
import com.stingion.makeitfine.data.model.User;
import com.stingion.makeitfine.data.model.UserProfile;
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
}
