package tatiana_dworieckaja.petstore.test.userTests;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tatiana_dworieckaja.petstore.endpoints.UserEndPoints;
import tatiana_dworieckaja.petstore.payload.userPayLoad.User;

public class UserTests {
    User userPayLoad;
    Faker faker;
    public Logger logger;

    @BeforeClass
    public void setup(){
        faker = new Faker();
        userPayLoad  = new User();

        logger = LogManager.getLogger(this.getClass());

        logger.info("******* Initial testdata is creating *******");

        userPayLoad.setUsername(faker.name().username());
        userPayLoad.setUserStatus(faker.number().numberBetween(0,1));
        userPayLoad.setEmail(faker.internet().safeEmailAddress());
        userPayLoad.setFirstName(faker.name().firstName());
        userPayLoad.setId(faker.idNumber().hashCode());
        userPayLoad.setLastName(faker.name().lastName());
        userPayLoad.setPassword(faker.internet().password());
        userPayLoad.setPhone(faker.phoneNumber().cellPhone());

        logger.info("******* Initial testdata was created *******");
    }

    @Test(priority = 1)
    public void testPostUser(){

        logger.info("******* Creating user *******");
        Response response = UserEndPoints.createUser(userPayLoad);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getContentType(), "application/json");
        Assert.assertEquals((response.then().extract().path("type")), "unknown");
        logger.info("******* User was created *******");
    }

    @Test (priority = 2)
    public void testGetUserByName(){
    logger.info("******* Reading user *******");
    Response response = UserEndPoints.getUser(userPayLoad.getUsername());
    response.then().log().all();
    Assert.assertEquals(response.getStatusCode(), 200);
    logger.info("******* User info is displayed *******");
    }

    @Test(priority = 3)
    public void testUpdateUser(){
        logger.info("******* Updating user *******");
        userPayLoad.setFirstName(faker.name().firstName());
        Response response = UserEndPoints.updateUser(userPayLoad.getFirstName(), userPayLoad );
        response.then().log().body().statusCode(200);
        logger.info("******* User was updated *******");
    }

    @Test(priority=4)
    public void testDeleteUser(){
        logger.info("******* Deleting user *******");
        Response response = UserEndPoints.deleteUser(userPayLoad.getUsername());
        response.then().log().body().statusCode(200);
        logger.info("******* User was deleted *******");
    }
}
