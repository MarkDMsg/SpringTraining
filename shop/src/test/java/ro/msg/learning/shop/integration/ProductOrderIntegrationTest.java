package ro.msg.learning.shop.integration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductOrderIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateOrderSuccessfully() throws Exception {
        String customerId = "8e936d7d-76d8-47fe-80be-0993e348f877";
        String requestBody = "{\n" +
                "  \"orderDetailDtoList\": [\n" +
                "    {\n" +
                "      \"productId\": \"a0b3fba7-9c98-4673-82dd-14f9f5b7db28\",\n" +
                "      \"quantity\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"productId\": \"db91de3d-2a7f-42e6-8bfc-d7b244132c55\",\n" +
                "      \"quantity\": 1\n" +
                "    }\n" +
                "  ],\n" +
                "  \"country\": \"test_country\",\n" +
                "  \"city\": \"test_city\",\n" +
                "  \"county\": \"test_county\",\n" +
                "  \"streetAddress\": \"test_streetAddress\"\n" +
                "}";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/orders/" + customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();


        String responseContent = mvcResult.getResponse().getContentAsString();

        JsonNode responseJsonNode = objectMapper.readTree(responseContent);

        String responseCustomerId = responseJsonNode.get("customerId").asText();
        String responseCountry = responseJsonNode.get("country").asText();
        String responseCity = responseJsonNode.get("city").asText();
        String responseCounty = responseJsonNode.get("county").asText();
        String responseStreetAddress = responseJsonNode.get("streetAddress").asText();

        assertEquals(customerId, responseCustomerId);
        assertEquals("test_county", responseCounty);
        assertEquals("test_country", responseCountry);
        assertEquals("test_city", responseCity);
        assertEquals("test_streetAddress", responseStreetAddress);
    }

    @Test
    public void testFailMissingStock() throws Exception {
        String customerId = "8e936d7d-76d8-47fe-80be-0993e348f877";
        //TODO:Delete Strings
        String requestBody = "{\n" +
                "  \"orderDetailDtoList\": [\n" +
                "    {\n" +
                "      \"productId\": \"a0df1fd6-6cd1-4b87-a75b-7656976a8249\",\n" +
                "      \"quantity\": 10000\n" +
                "    },\n" +
                "    {\n" +
                "      \"productId\": \"3fd0a290-33da-4a0a-b3f5-93bc5f337faf\",\n" +
                "      \"quantity\": 100000\n" +
                "    }\n" +
                "  ],\n" +
                "  \"country\": \"test_country\",\n" +
                "  \"city\": \"test_city\",\n" +
                "  \"county\": \"test_county\",\n" +
                "  \"streetAddress\": \"test_streetAddress\"\n" +
                "}";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/orders/" + customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

    }
}
