package com.keyin;

import com.keyin.mainservice.TreeController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TreeController.class)
public class ServiceTest {
    // Mock beans for any dependencies required by BSTController (if any)
    @Autowired
    private MockMvc mockMvc;



    @BeforeEach
    void setUp() {
        // setup code if needed
    }


    @Test
    void testPreviousTreesEmptyList() throws Exception {
        String expectedJson = "[]";

        mockMvc.perform(get("/treeify/previous-trees"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedJson));
    }

    @Test
    void testNumberProcessing() throws Exception {
        String numbers = "3 1 2";

        mockMvc.perform(post("/treeify/process-numbers")
                        .param("numbers", numbers))
                .andExpect(status().isOk());
    }



}

