package com.backend.makemyimage.domain.user.controller;

import com.backend.makemyimage.domain.user.dto.request.JoinRequest;
import com.backend.makemyimage.domain.user.entity.User;
import com.backend.makemyimage.domain.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc= MockMvcBuilders.webAppContextSetup(context)
                .build();
        userRepository.deleteAll();
    }

    @DisplayName("join: 회원가입에 성공한다.")
    @Test
    public void join() throws Exception {
        //given
        final String url= "/join";
        final String name="준형이";
        final String password="wnsgud0000";
        final String email="jh981109@naver.com";
        final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        final JoinRequest joinRequest=new JoinRequest(name,password,email);
        final String requestBody = objectMapper.writeValueAsString(joinRequest);


        //when
        ResultActions result = mockMvc.perform(post(url).
                contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

//        then
        result.andExpect(status().isCreated());

        List<User> users = userRepository.findAll();

        assertThat(users.size()).isEqualTo(1);
        assertThat(users.get(0).getName()).isEqualTo(name);
        assertThat(users.get(0).getPassword()).isEqualTo(bCryptPasswordEncoder.encode(password));

    }




}