package tatiana_dworieckaja.petstore.test;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tatiana_dworieckaja.petstore.api.PetData;
import tatiana_dworieckaja.petstore.endpoints.PetEndPoints;
import org.apache.logging.log4j.Logger;

import tatiana_dworieckaja.petstore.payload.petPayload.Category;
import tatiana_dworieckaja.petstore.payload.petPayload.RootPet;
import tatiana_dworieckaja.petstore.specifications.Specifications;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class PetTests {

    public RootPet petPayLoad;
    public Faker faker;
    public Logger logger;
   // private final static String URL = ""

    @BeforeClass
    public void setup() {
        faker = new Faker();
        petPayLoad = new RootPet();
        logger = LogManager.getLogger(this.getClass());
        logger.info("******* Initial testdata is creating *******");

        petPayLoad.setId(faker.idNumber().hashCode());
        petPayLoad.setCategory();
        petPayLoad.setName(faker.name().firstName());
        petPayLoad.setPhotoUrls(2);
        petPayLoad.setStatus();
        petPayLoad.setTags(2);

        logger.info("******* Initial testdata was created *******");
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK200());
    }

    @Test(priority = 1)
    public void postPetTest() {
        logger.info("******* Creating pet *******");
        Response response = PetEndPoints.createPet(petPayLoad);
        response.then().log().all();
    }


    @Test(priority = 2)
    public void getPetById() {
        PetData petdata = given()
                .when()
                //.contentType(ContentType.JSON)
                .get("https://petstore.swagger.io/v2/pet/" + petPayLoad.getId())
                .then().log().all()
                .extract().body().as(PetData.class);
        Assert.assertEquals(petdata.getStatus(), petPayLoad.getStatus());
    }

    @Test
    public void getPetsByStatusTest() {
        List<PetData> pets = given()
                //.contentType(ContentType.JSON)
                .when()
                .queryParam("status", "available")
                .get("https://petstore.swagger.io/v2/pet/findByStatus")
                .then().log().all()
                .extract().body().jsonPath().getList("$.", PetData.class);

        SoftAssert softAssert = new SoftAssert();
        pets.stream().forEach(x -> softAssert.assertTrue(x.getStatus().matches("available"), "Status is incorrect"));
        softAssert.assertFalse(pets.stream().allMatch(x -> x.getName().isEmpty()), "For one or more pets name is displayed incorrectly or does not exist");

        List<String> statuses = pets.stream().map(PetData::getStatus).collect(Collectors.toList());
        List<String> ids = pets.stream().map(x -> x.getId().toString()).collect(Collectors.toList());
        for (int i = 0; i < ids.size(); i++) {
            softAssert.assertTrue(ids.get(i).matches("\\d+"));
        }

        final String[] standardStatus = {"available", "sold", "pending"};
        for (int i = 0; i < statuses.size(); i++) {
            softAssert.assertTrue(Arrays.stream(standardStatus).anyMatch(statuses.get(i)::matches));
        }

        softAssert.assertAll();
    }
}
