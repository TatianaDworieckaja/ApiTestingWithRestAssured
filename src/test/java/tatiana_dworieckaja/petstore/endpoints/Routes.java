package tatiana_dworieckaja.petstore.endpoints;

public class Routes {

    public static String BASE_USER_URL = "https://petstore.swagger.io/v2";

    public static String CREATE_USER_URL = BASE_USER_URL+"/user";
    public static String GET_USER_URL = BASE_USER_URL+"/user/{username}";
    public static String UPDATE_USER_URL = BASE_USER_URL+"/user/{username}";
    public static String DELETE_USER_URL = BASE_USER_URL+"/user/{username}";
    public static String LOGIN_USER_URL = BASE_USER_URL+"/user/login";
    public static String LOGOUT_USER_URL = BASE_USER_URL+"/user/logout";
    public static String CREATE_LIST_OF_USERS_URL = BASE_USER_URL+"/user/createWithArray";



}
