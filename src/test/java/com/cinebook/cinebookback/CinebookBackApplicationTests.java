package com.cinebook.cinebookback;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cinebook.cinebookback.controller.LoginController;

@SpringBootTest
class CinebookBackApplicationTests {

    @Autowired
    private LoginController loginController;

    /*@Test
    void contextLoads() throws Exception {
        assertThat(loginController).isNotNull();
    }*/

}
