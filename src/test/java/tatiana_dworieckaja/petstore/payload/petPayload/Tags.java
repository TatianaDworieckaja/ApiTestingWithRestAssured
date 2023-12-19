package tatiana_dworieckaja.petstore.payload.petPayload;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class Tags {
    private Integer id;
    private String name;

    Random rnd = new Random();

    public Integer getId() {
        return id;
    }
    public void setId() {
        this.id = rnd.nextInt(5);
    }
    public String getName() {
        return name;
    }
    public void setName() {
        this.name = RandomStringUtils.randomAlphabetic(7);
    }
}
