package tatiana_dworieckaja.reqres.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tatiana_dworieckaja.reqres.api.SuccessReg;
import tatiana_dworieckaja.reqres.payload.Register;
import tatiana_dworieckaja.reqres.specifications.Specifications;

import static io.restassured.RestAssured.given;

public class PositiveRegisterTest {

    SoftAssert softAssert;
    private final static String URL = "https://reqres.in";
    @BeforeClass
    public void setup(){
        softAssert = new SoftAssert();
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
    }

    @Test
    public void successRegTest(){
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Register user =  new Register("eve.holt@reqres.in", "pistol");
        SuccessReg successReg = given()
                .body(user)
                .when()
                .post("/api/register")
                .then().log().all()
                .extract().as(SuccessReg.class);
        softAssert.assertNotNull(successReg.getId(), "Id is null");
        softAssert.assertNotNull(successReg.getToken(), "Token is null");
        softAssert.assertEquals(successReg.getId(), id,"Id is not valid" );
        softAssert.assertEquals(successReg.getToken(), token,  "Token is not valid" );
    }


}
