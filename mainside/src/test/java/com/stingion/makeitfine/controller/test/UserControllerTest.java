package com.stingion.makeitfine.controller.test;

import com.google.common.collect.Lists;
import com.stingion.makeitfine.controller.UserController;
import com.stingion.makeitfine.data.model.User;
import com.stingion.makeitfine.data.service.UserService;
import com.stingion.makeitfine.testconfiguration.IgnoreSecurityConfiguration;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@ActiveProfiles("test")
@Import(IgnoreSecurityConfiguration.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
    void listAllUsers() throws Exception {
        mockMvc.perform(get("/user")).andDo(
                print()
        ).andExpect(status().isOk())
                .andExpect(content().string(Matchers.allOf(containsString("2"), containsString("1"))));
    }

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

    public static class NumberIsNotPositive implements ArgumentMatcher<Integer> {
        @Override
        public boolean matches(Integer argument) {
            return argument < 1;
        }
    }
}