package tatiana_dworieckaja.petstore.payload.petPayload;

import org.apache.commons.lang3.RandomStringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RootPet {
        private Integer id;
        private Category category;
        private String name;
        private List<String> photoUrls;
        private List<Tags> tags;
        private String status;

        public RootPet() {
                photoUrls = new ArrayList<String>();
                category = new Category();
                tags = new ArrayList<>();
        }

        public Integer getId() {
                return id;
        }
        public void setId(Integer id) {
                this.id = id;
        }
        public Category getCategory() {
                return category;
        }
        public void setCategory() {
                category.setName();
        }
        public String getName() {
                return name;
        }
        public void setName(String name) {
                this.name = name;
        }
        public List<String> getPhotoUrls() {
                return photoUrls;
        }
        public void setPhotoUrls(int numberOfUrls) {
                for (int i=0; i<numberOfUrls; i++){
                        photoUrls.add("https://" + RandomStringUtils.randomAlphabetic(20));
                }
        }
        public List<Tags> getTags() {
                return this.tags;
        }
        public void setTags(int numberOfTags) {
                for (int i=0; i<numberOfTags; i++) {
                        Tags newTag = new Tags();
                        newTag.setId();
                        newTag.setName();
                        tags.add(newTag);
                }
        }
        public String getStatus() {
                return status;
        }
        public void setStatus() {
                String[] statusArr = {"available", "pending", "sold"};
                Random rnd = new Random();
                this.status = statusArr[rnd.nextInt(statusArr.length)];
        }
}

