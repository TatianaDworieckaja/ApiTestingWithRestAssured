package tatiana_dworieckaja.reqres.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tatiana_dworieckaja.reqres.api.ColorsData;
import tatiana_dworieckaja.reqres.specifications.Specifications;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ColorsDataSortTest {
    private static final String URL = "https://reqres.in";

    @BeforeClass
    public void setup(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
    }

    @Test
    public void colorDataSortedByYear(){
        List<ColorsData> data = given()
                .when()
                .get("/api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ColorsData.class);
        List<Integer> years = data.stream().map(ColorsData::getYear).collect(Collectors.toList());
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(years, sortedYears);
        System.out.println(years);
        System.out.println(sortedYears);
    }



}
