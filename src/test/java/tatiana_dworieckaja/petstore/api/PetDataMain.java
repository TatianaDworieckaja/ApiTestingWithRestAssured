package tatiana_dworieckaja.petstore.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(fluent=true)
public class PetDataMain {
    private Integer id;
    private PetaDataCategory category;
    private String name;
    private List<String> photoUrls = new ArrayList<>();
    private List<PetDataTags> tags = new ArrayList<>();
    private String status;

    public String getStatus() {
        return status;
    }
}
