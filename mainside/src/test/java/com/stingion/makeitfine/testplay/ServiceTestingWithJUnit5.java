package com.stingion.makeitfine.testplay;

import com.stingion.makeitfine.data.model.Bank;
import com.stingion.makeitfine.data.model.Contact;
import com.stingion.makeitfine.data.model.CreditCard;
import com.stingion.makeitfine.data.model.Worker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServiceTestingWithJUnit5 {

    private static final List<String> annotationChecking = Arrays.asList(null, "", "abc");

    private static Stream<Class<?>> entities() {
        return Stream.of(Contact.class, Worker.class);
    }

    @ParameterizedTest
    @ValueSource(classes = {Bank.class, Contact.class, CreditCard.class})
    @MethodSource("entities")
    public void checkServiceFindAllIsNotEmpty(Class<?> clazz) {
        assertEquals("com.stingion.makeitfine.data.model", clazz.getPackage().getName());
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void checkAnnotations(String word) {
        assertTrue(annotationChecking.contains(word));
    }
}
