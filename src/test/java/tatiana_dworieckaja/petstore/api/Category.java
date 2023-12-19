package tatiana_dworieckaja.petstore.api;

public class Category {
    private Integer id;

    private String name;

    public Category(){}//empty constructor needed to let Jackson's Library understand how to create a model, alternatively we can add @JsonProperty("field_name") annotation to each field
    public Category(Integer id, String name) {
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
