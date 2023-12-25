package tatiana_dworieckaja.petstore.test.petTests;

import com.github.javafaker.Faker;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tatiana_dworieckaja.petstore.api.PetDataMain;
import tatiana_dworieckaja.petstore.endpoints.PetEndPoints;
import org.apache.logging.log4j.Logger;
import tatiana_dworieckaja.petstore.payload.petPayload.RootPet;
import tatiana_dworieckaja.petstore.specifications.Specifications;

import static io.restassured.RestAssured.given;

public class PetTests {

    public RootPet petPayLoad;
    public Faker faker;
    public Logger logger;

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
    }

    @Test(priority = 1)
    public void postPetTest() {
        logger.info("******* Creating pet *******");
        PetEndPoints.createPet(petPayLoad);
        logger.info("******* Pet has been created *******");
    }


    @Test(priority = 2)
    public void getPetById() {
        PetDataMain petdata = given()
                .when()
                //.contentType(ContentType.JSON)
                .get("https://petstore.swagger.io/v2/pet/" + petPayLoad.getId())
                .then().log().all()
                .extract().body().as(PetDataMain.class);
        Assert.assertEquals(petdata.getStatus(), petPayLoad.getStatus());
    }

  // @Test
  // public void getPetsByStatusTest() {
  //     List<PetDataMain> pets = given()
  //             //.contentType(ContentType.JSON)
  //             .when()
  //             .queryParam("status", "available")
  //             .get("https://petstore.swagger.io/v2/pet/findByStatus")
  //             .then().log().all()
  //             .extract().body().jsonPath().getList("$.", PetDataMain.class);

  //     SoftAssert softAssert = new SoftAssert();
  //     pets.stream().forEach(x -> softAssert.assertTrue(x.getStatus().matches("available"), "Status is incorrect"));
  //     softAssert.assertFalse(pets.stream().allMatch(x -> x.getName().isEmpty()), "For one or more pets name is displayed incorrectly or does not exist");

  //     List<String> statuses = pets.stream().map(PetDataMain::getStatus).collect(Collectors.toList());
  //     List<String> ids = pets.stream().map(x -> x.getId().toString()).collect(Collectors.toList());
  //     for (int i = 0; i < ids.size(); i++) {
  //         softAssert.assertTrue(ids.get(i).matches("\\d+"));
  //     }

  //     final String[] standardStatus = {"available", "sold", "pending"};
  //     for (int i = 0; i < statuses.size(); i++) {
  //         softAssert.assertTrue(Arrays.stream(standardStatus).anyMatch(statuses.get(i)::matches));
  //     }

  //     softAssert.assertAll();
  // }
}
