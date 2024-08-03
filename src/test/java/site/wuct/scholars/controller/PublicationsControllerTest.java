package site.wuct.scholars.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import site.wuct.scholars.model.Publication;
import site.wuct.scholars.service.impl.PublicationsServiceImpl;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PublicationsController.class)
public class PublicationsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublicationsServiceImpl publicationsService;

    /**
     * Test for getting all publications
     * 
     * @throws Exception if the request fails
     */
    @Test
    void updatePublicationAndVerifyChanges() throws Exception {
        Long pubId = 1L;
        Publication existingPublication = new Publication();
        existingPublication.setPubid(pubId);
        existingPublication.setDoi("10.1000/original");

        Publication updatedPublication = new Publication();
        updatedPublication.setPubid(pubId);
        updatedPublication.setDoi("10.1000/updated");

        when(publicationsService.findById(pubId)).thenReturn(existingPublication);
        when(publicationsService.save(any(Publication.class))).thenReturn(updatedPublication);

        mockMvc.perform(put("/api/pub/{id}", pubId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"doi\":\"10.1000/updated\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.doi").value("10.1000/updated"));

        verify(publicationsService).save(any(Publication.class));
    }

    /**
     * Test for deleting a publication
     * 
     * @throws Exception if the request fails
     */
    @Test
    void deletePublicationAndVerifyRemoval() throws Exception {
        Long pubId = 1L;

        mockMvc.perform(delete("/api/pub/{id}", pubId))
                .andExpect(status().isOk());

        verify(publicationsService).deleteById(pubId);
    }

    /**
     * Test for updating a non-existent publication
     * 
     * @throws Exception if the request fails
     */
    @Test
    void updateNonExistentPublication() throws Exception {
        Long nonExistentId = 999L;

        when(publicationsService.findById(nonExistentId)).thenReturn(null);

        mockMvc.perform(put("/api/pub/{id}", nonExistentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"doi\":\"10.1000/nonexistent\"}"))
                .andExpect(status().isNotFound());

        verify(publicationsService, never()).save(any(Publication.class));
    }
}