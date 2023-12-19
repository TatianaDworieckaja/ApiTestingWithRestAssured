package tatiana_dworieckaja.reqres.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tatiana_dworieckaja.reqres.payload.UserUpdateRequest;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserResponse extends UserUpdateRequest {

    private String updatedAt;

}
