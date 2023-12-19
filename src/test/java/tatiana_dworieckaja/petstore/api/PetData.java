package tatiana_dworieckaja.petstore.api;

import java.util.ArrayList;
import java.util.List;

public class PetData {
    private Long id;
    private Category category;
    private String name;
    private List<String> photoUrls = new ArrayList<>();
    private List<Tags> tags = new ArrayList<Tags>();
    private String status;

    public PetData(){}

    public PetData(Long id, Category category, String name, List<String> photoUrls, List<Tags> tags, String status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public String getStatus() {
        return status;
    }
}
