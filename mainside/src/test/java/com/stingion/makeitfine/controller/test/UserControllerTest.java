package com.stingion.makeitfine.controller.test;

import com.google.common.collect.Lists;
import com.stingion.makeitfine.controller.UserController;
import com.stingion.makeitfine.data.model.user.User;
import com.stingion.makeitfine.data.service.UserService;
import com.stingion.makeitfine.util.UserPasswordEncoder;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * To run tagged test:
 * mvn -Dgroups="simple" test
 * mvn -DexcludedGroups="other" test
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@ActiveProfiles("test")
@Tag("userController")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserPasswordEncoder passwordEncoder;

    @MockBean
    private UserService userService;

    private static List<User> userList;

    @BeforeAll
    private static void beforeClass() {
        User user1 = new User();
        user1.setId(1);

        User user2 = new User();
        user2.setId(2);

        userList = Lists.newArrayList(user1, user2);
    }

    @BeforeEach
    private void beforeEach() {
        when(userService.findAll()).thenReturn(userList);
        when(userService.findById(1)).thenReturn(userList.get(0));
        when(userService.findById(2)).thenReturn(userList.get(1));

        IllegalArgumentException idShouldBePositiveException = new IllegalArgumentException("id should be positive");

        doThrow(idShouldBePositiveException).when(userService).findById(argThat(new NumberIsNotPositive()));
    }

    @Test
    @Tag("simple")
    void listAllUsers() throws Exception {
        mockMvc.perform(get("/user")).andDo(
                print()
        ).andExpect(status().isOk())
                .andExpect(content().string(Matchers.allOf(containsString("2"), containsString("1"))));
    }

    @Test
    @Tag("simple")
    void getUserOne() throws Exception {
        int id = 1;
        mockMvc.perform(get("/user/" + id)).andDo(
                print()
        ).andExpect(status().isOk())
                .andExpect(content().string(Matchers.allOf(containsString(String.valueOf(id)))));
    }

    @Timeout(value = 250, unit = TimeUnit.MILLISECONDS)
    @Test
    @RepeatedTest(3)
    void getUser() throws Exception {
        int id = 2;
        mockMvc.perform(get("/user/" + id)).andDo(
                print()
        ).andExpect(status().isOk())
                .andExpect(content().string(Matchers.allOf(containsString(String.valueOf(id)))));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5})
    @MethodSource("intNumbs")
    void getUserParam(int id) throws Exception {
        User userById = new User();
        userById.setId(id);
        userList.add(userById);

        when(userService.findById(id)).thenReturn(userList.get(id - 1));

        mockMvc.perform(get("/user/" + id)).andDo(
                print()
        ).andExpect(status().isOk())
                .andExpect(content().string(Matchers.allOf(containsString(String.valueOf(id)))));
    }

    @TestFactory
    public Stream<DynamicTest> translateDynamicTestsFromStream() {
        return Stream.of(-717, -2, -1, 0)
                .map(id ->
                        DynamicTest.dynamicTest("Test negative scenario with no id = " + id, () -> {
                            Throwable excep = assertThrows(IllegalArgumentException.class, () -> userService.findById(id));
                            assertTrue(excep.getMessage().equalsIgnoreCase("id should be positive"));
                        })
                );
    }

    private static final Stream<Integer> intNumbs() {
        return Stream.iterate(6, i -> i + 1).limit(3);
    }

    private class NumberIsNotPositive implements ArgumentMatcher<Integer> {
        @Override
        public boolean matches(Integer argument) {
            return argument < 1;
        }
    }

    static class DisplayNameGen extends DisplayNameGenerator.ReplaceUnderscores {
        @Override
        public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
            return UUID.randomUUID().toString();
        }
    }

    @Nested
    @ExtendWith(TemplateProvider.class)
    @DisplayName("Testing template on userService")
    @DisplayNameGeneration(DisplayNameGen.class)
    public class UserControllerWithTemplateTest {

        @BeforeEach
        private void beforeEach() {
            IllegalArgumentException idShouldBePositiveException = new IllegalArgumentException("id should be positive");
            doReturn(new User()).when(userService).findById(anyInt());
            doThrow(idShouldBePositiveException).when(userService).findById(argThat(new NumberIsNotPositive()));
        }

        @TestTemplate
        void testUserWithHighNumberId(Integer id) {
            assertNotNull(userService.findById(id));
        }
    }

    public static class TemplateProvider implements TestTemplateInvocationContextProvider {

        @Override
        public boolean supportsTestTemplate(ExtensionContext context) {
            return true;
        }

        @Override
        public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(
                ExtensionContext context) {
            return Stream
                    .of(invocationContext(10000), invocationContext(2123434234), invocationContext(1000023423));
        }

        private TestTemplateInvocationContext invocationContext(Integer parameter) {
            return new TestTemplateInvocationContext() {
                @Override
                public String getDisplayName(int invocationIndex) {
                    return "Number: " + parameter;
                }

                @Override
                public List<Extension> getAdditionalExtensions() {
                    return Collections.singletonList(new ParameterResolver() {
                        @Override
                        public boolean supportsParameter(ParameterContext parameterContext,
                                                         ExtensionContext extensionContext) {
                            return parameterContext.getParameter().getType()
                                    .equals(Integer.class);
                        }

                        @Override
                        public Object resolveParameter(ParameterContext parameterContext,
                                                       ExtensionContext extensionContext) {
                            return parameter;
                        }
                    });
                }
            };
        }
    }
}