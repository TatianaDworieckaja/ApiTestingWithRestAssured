package tatiana_dworieckaja.petstore.test.userTests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import tatiana_dworieckaja.petstore.endpoints.UserEndPoints;
import tatiana_dworieckaja.petstore.payload.userPayLoad.User;
import tatiana_dworieckaja.petstore.utilities.DataProviders;

public class DDTests {


    @Test(dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String id, String	userName, String firstName, String lastName, String userStatus, String email) {
        User userPayLoad = new User();
        userPayLoad.setId(Integer.valueOf(id));
        userPayLoad.setUsername(userName);
        userPayLoad.setFirstName(firstName);
        userPayLoad.setLastName(lastName);
        userPayLoad.setUserStatus(Integer.valueOf(userStatus));
        userPayLoad.setEmail(email);

        Response response = UserEndPoints.createUser(userPayLoad);
        response.then().statusCode(200);
    }

        @Test(dataProvider = "UserNames", dataProviderClass = DataProviders.class)
        public void testGetUser(String userName){
            Response response = UserEndPoints.getUser(userName);
            response.then().statusCode(200).log().all();
        }

}
