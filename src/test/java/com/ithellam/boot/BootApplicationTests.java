package com.ithellam.boot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import com.ithellam.boot.model.Product;
import io.restassured.RestAssured;
import io.restassured.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BootApplication.class}, webEnvironment = WebEnvironment.DEFINED_PORT)
public class BootApplicationTests {
    private static final String API_ROOT = "http://localhost:8081/api/products";

    @Test
    public void contextLoads() {
    }

    private Product createProduct() {
        Product product = Product.builder()
            .withName(RandomStringUtils.randomAlphabetic(10))
            .withColor(RandomStringUtils.randomAlphabetic(25))
            .build();

        return product;
    }

    private String createProductAsUri(Product product) {
        Response response = RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(product)
            .post(API_ROOT);

        return API_ROOT + "/" + response.jsonPath().get("productId");
    }

    @Test
    public void whenGetAllProducts_thenOK() {
        Response response = RestAssured.get(API_ROOT);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenGetProductsByTitle_thenOK() {
        Product product = createProduct();
        createProductAsUri(product);
        Response response = RestAssured.get(API_ROOT + "/name/" + product.getName());

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertTrue(response.as(List.class).size() > 0);
    }

    @Test
    public void whenGetCreatedProductById_thenOK() {
        Product product = createProduct();
        final String location = createProductAsUri(product);
        Response response = RestAssured.get(location);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(product.getName(), response.jsonPath().get("name"));
    }
}
