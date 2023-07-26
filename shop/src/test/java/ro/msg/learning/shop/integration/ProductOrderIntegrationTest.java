package ro.msg.learning.shop.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ro.msg.learning.shop.ShopApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = ShopApplication.class)
@AutoConfigureMockMvc
public class ProductOrderIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @BeforeAll
    public void inializeTestData() {

    }

    @Test
    public void testCreateOrderSuccessfully() throws Exception {
        String customerId = "8e936d7d-76d8-47fe-80be-0993e348f877";
        ObjectMapper objectMapper = new ObjectMapper();

        JSONArray objectDetailArray = new JSONArray();
        objectDetailArray.put(new JSONObject().put("productId", "a0b3fba7-9c98-4673-82dd-14f9f5b7db28").put("quantity", 1));
        objectDetailArray.put(new JSONObject().put("productId", "db91de3d-2a7f-42e6-8bfc-d7b244132c55").put("quantity", 1));

        String jsonString = new JSONObject()
                .put("orderDetailDtoList", objectDetailArray)
                .put("country", "test_country")
                .put("city", "test_city")
                .put("streetAddress", "test_streetAddress").toString();
        try {
            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/orders/" + customerId)
                            .content(jsonString)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andReturn();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void testFailMissingStock() throws Exception {
        String customerId = "8e936d7d-76d8-47fe-80be-0993e348f877";
        ObjectMapper objectMapper = new ObjectMapper();

        JSONArray objectDetailArray = new JSONArray();
        objectDetailArray.put(new JSONObject().put("productId", "a0b3fba7-9c98-4673-82dd-14f9f5b7db28").put("quantity", 10000));
        objectDetailArray.put(new JSONObject().put("productId", "db91de3d-2a7f-42e6-8bfc-d7b244132c55").put("quantity", 10000));

        String jsonString = new JSONObject()
                .put("orderDetailDtoList", objectDetailArray)
                .put("country", "test_country")
                .put("city", "test_city")
                .put("streetAddress", "test_streetAddress").toString();
        try {

            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/orders/" + customerId)
                            .content(jsonString)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andReturn();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
