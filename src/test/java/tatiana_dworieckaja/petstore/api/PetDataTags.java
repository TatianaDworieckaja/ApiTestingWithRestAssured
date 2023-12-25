package tatiana_dworieckaja.petstore.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(fluent=true)
public class PetDataTags {
    private Integer id;
    private String name;
}


