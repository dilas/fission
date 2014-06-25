package integration.controller;

import config.FissionTestConfig;
import ext.IntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = FissionTestConfig.class)
@TransactionConfiguration(defaultRollback = true)
@Category(IntegrationTest.class)
public class FeedControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void index() throws Exception {
        this.mockMvc.perform(get("/feed"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("feedList"))
                .andExpect(view().name("feed/index"));
    }

    @Test
    public void newFeed() throws Exception {
        this.mockMvc.perform(get("/feed/new"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("feedTypeList"))
                .andExpect(model().attributeExists("feed"))
                .andExpect(view().name("feed/new"));
    }

    @Test
    public void save() throws Exception {
        RequestBuilder request = post("/feed")
                .param("name", "Test Feed")
                .param("feedType", "SIMPLE")
                .param("identifier", "test");

        this.mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isMovedTemporarily())
                .andExpect(model().hasNoErrors());
    }

    @Test
    public void show() throws Exception {
        this.mockMvc.perform(get("/feed/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("feed"))
                .andExpect(view().name("feed/show"));
    }

    @Test
    public void edit() throws Exception {
        this.mockMvc.perform(get("/feed/edit/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("feedTypeList"))
                .andExpect(view().name("feed/edit"));
    }

    @Test
    public void update() throws Exception {
        RequestBuilder request = post("/feed/update")
                .param("id", "1")
                .param("name", "Modified Feed")
                .param("feedType", "SIMPLE")
                .param("identifier", "test");


        this.mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isMovedTemporarily())
                .andExpect(model().hasNoErrors());

    }

    @Test
    public void deleteFeed() throws Exception {
        this.mockMvc.perform(delete("/feed/{id}", 3))
                .andExpect(status().isNoContent());
    }
}
