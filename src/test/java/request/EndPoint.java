package request;

public class EndPoint {
    public static final String BASE_URL = "https://dummyapi.io/data/v1/";

    // Get user by id >> GET: /user/:id
    public static final String GET_USER_BY_ID = BASE_URL + "user/";

    // Create new user >> POST: /user/create
    public static final String CREATE_USER = BASE_URL + "user/create";

    // Update exisiting user >> PUT: /user/:id
    public static final String UPDATE_USER = BASE_URL + "user/";

    // Delete user by id >> DELETE: /user/:id
    public static final String DELETE_USER = BASE_URL + "user/";
}
