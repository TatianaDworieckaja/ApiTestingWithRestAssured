package tatiana_dworieckaja.petstore.api;

public class Tags {
    private Integer id;
    private String name;
    public Tags(){}

    public Tags(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}


