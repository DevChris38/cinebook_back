package com.cinebook.cinebookback;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginContollerTests {

    @Autowired
    private MockMvc mvc;

    /*@Test
    public void shouldReturnDefaultMessage() throws Exception {
        mvc.perform(get("/login")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void userLoginTest() throws Exception {
        mvc.perform(formLogin("/login").user("dbuser").password("user")).andExpect(authenticated());
    }

    @Test
    public void userLoginFail() throws Exception {
        mvc.perform(formLogin("/login").user("user").password("wrongpassword")).andExpect(unauthenticated());
    }

    @Test
    @WithMockUser
    public void shouldReturnUserPage() throws Exception {
        mvc.perform(get("/user")).andDo(print()).andExpect(status().isOk());
    }*/
}
