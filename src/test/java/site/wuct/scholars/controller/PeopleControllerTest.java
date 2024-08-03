package site.wuct.scholars.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import site.wuct.scholars.service.impl.PeopleServiceImpl;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PeopleController.class)
public class PeopleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeopleServiceImpl peopleService;

    /**
     * Test for the addNewPerson method in PeopleController
     * 
     * @throws Exception if the request fails
     */
    @Test
    void addNewPerson() throws Exception {
        mockMvc.perform(post("/api/scholar/add")
                .param("name", "Chentian Wu")
                .param("major", "Math")
                .param("hindex", "1")
                .param("location", "University of Wisconsin-Madison"))
                .andExpect(status().isOk())
                .andExpect(content().string("Person added successfully"));

        verify(peopleService).addNewPerson("Chentian Wu", "Math", 1, "University of Wisconsin-Madison");
    }

    /**
     * Test for the getPersonProfile method in PeopleController
     * @throws Exception if the request fails
     */
    @Test
    void getPersonProfile() throws Exception {
        Long personId = 185026L;
        Map<String, Object> profile = new HashMap<>();
        profile.put("name", "Chia Y. Cho");
        profile.put("major", "computerscience");
        profile.put("hindex", 20);
        when(peopleService.getPersonProfile(personId)).thenReturn(profile);

        mockMvc.perform(get("/api/scholar/{personId}/profile", personId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Chia Y. Cho"))
                .andExpect(jsonPath("$.major").value("computerscience"))
                .andExpect(jsonPath("$.hindex").value(20));

        verify(peopleService).getPersonProfile(personId);
    }

}