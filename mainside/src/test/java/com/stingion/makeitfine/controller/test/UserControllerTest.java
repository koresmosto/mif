package com.stingion.makeitfine.controller.test;

import com.google.common.collect.Lists;
import com.stingion.makeitfine.controller.UserController;
import com.stingion.makeitfine.data.model.User;
import com.stingion.makeitfine.data.service.UserService;
import com.stingion.makeitfine.testconfiguration.IgnoreSecurityConfiguration;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
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
    }

    @Test
    void listAllUsers() throws Exception {
        mockMvc.perform(get("/user")).andDo(
                print()
        ).andExpect(status().isOk())
                .andExpect(content().string(Matchers.allOf(containsString("2"), containsString("1"))));
    }

    @Test
    void getUser() throws Exception {
        mockMvc.perform(get("/user/2")).andDo(
                print()
        ).andExpect(status().isOk())
                .andExpect(content().string(Matchers.allOf(containsString("2"))));
    }
}