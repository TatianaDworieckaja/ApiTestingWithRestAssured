package tatiana_dworieckaja.reqres.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateRequest {
    private String name;
    private String job;

    public UserUpdateRequest(String name, String job) {
        this.name = name;
        this.job = job;
    }
}
