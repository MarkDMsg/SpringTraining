package ro.msg.learning.shop.integration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNodeFactory nodeFactory = objectMapper.getNodeFactory();

        // Create the order detail objects
        ObjectNode orderDetail1 = nodeFactory.objectNode();
        orderDetail1.put("productId", "a0b3fba7-9c98-4673-82dd-14f9f5b7db28");
        orderDetail1.put("quantity", 1);

        ObjectNode orderDetail2 = nodeFactory.objectNode();
        orderDetail2.put("productId", "db91de3d-2a7f-42e6-8bfc-d7b244132c55");
        orderDetail2.put("quantity", 1);

        // Create the orderDetailDtoList array node and add order detail objects
        ArrayNode orderDetailDtoList = nodeFactory.arrayNode();
        orderDetailDtoList.add(orderDetail1);
        orderDetailDtoList.add(orderDetail2);

        // Create the main object and add properties
        ObjectNode mainObject = nodeFactory.objectNode();
        mainObject.set("orderDetailDtoList", orderDetailDtoList);
        mainObject.put("country", "test_country");
        mainObject.put("city", "test_city");
        mainObject.put("county", "test_county");
        mainObject.put("streetAddress", "test_streetAddress");


        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/orders/" + customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mainObject.asText()))
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
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNodeFactory nodeFactory = objectMapper.getNodeFactory();

        // Create the order detail objects
        ObjectNode orderDetail1 = nodeFactory.objectNode();
        orderDetail1.put("productId", "a0b3fba7-9c98-4673-82dd-14f9f5b7db28");
        orderDetail1.put("quantity", 10000);

        ObjectNode orderDetail2 = nodeFactory.objectNode();
        orderDetail2.put("productId", "db91de3d-2a7f-42e6-8bfc-d7b244132c55");
        orderDetail2.put("quantity", 100000);

        // Create the orderDetailDtoList array node and add order detail objects
        ArrayNode orderDetailDtoList = nodeFactory.arrayNode();
        orderDetailDtoList.add(orderDetail1);
        orderDetailDtoList.add(orderDetail2);

        // Create the main object and add properties
        ObjectNode mainObject = nodeFactory.objectNode();
        mainObject.set("orderDetailDtoList", orderDetailDtoList);
        mainObject.put("country", "test_country");
        mainObject.put("city", "test_city");
        mainObject.put("county", "test_county");
        mainObject.put("streetAddress", "test_streetAddress");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/orders/" + customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mainObject.asText()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

    }
}
