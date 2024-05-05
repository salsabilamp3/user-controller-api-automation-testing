package request;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Location;
import model.User;

public class UpdateUserEndpoint {
    public static Response updateOneField(String userId, String fieldName, String fieldValue) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("app-id", "66273be9e26079c76814ec13");
        request.body("{\"" + fieldName + "\":\"" + fieldValue + "\"}");
        return request.put(EndPoint.UPDATE_USER + userId);
    }

    public static Response updateAllField(String userId, User user) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("app-id", "66273be9e26079c76814ec13");
        request.body(user);
        return request.put(EndPoint.UPDATE_USER + userId);
    }

    public static User prepareTestUpdateAll() {
        User dataUser = new User();

        dataUser.setId("60d0fe4f5311236168a10a04");
        dataUser.setTitle("ms");
        dataUser.setFirstName("Titi");
        dataUser.setLastName("Jane");
        dataUser.setPicture("https://example.com/profile_picture_tati.jpg");
        dataUser.setGender("female");
        dataUser.setEmail("konsta.manninen@example.com");
        dataUser.setDateOfBirth("1995-08-15");
        dataUser.setPhone("+62817287187");

        Location location = new Location();
        location.setStreet("123 Oak Street");
        location.setCity("Los Angeles");
        location.setState("California");
        location.setCountry("United States");
        location.setTimezone("-08:00");
        dataUser.setLocation(location);

        dataUser.setRegisterDate("2021-06-21T21:02:17.280Z");
        dataUser.setUpdatedDate("2021-06-21T21:02:17.280Z");

        return dataUser;
    }

    public static User prepareTestUpdateTitle() {
        User dataUser = new User();

        dataUser.setId("60d0fe4f5311236168a10a09");
        dataUser.setTitle("mr");
        dataUser.setFirstName("Clea");
        dataUser.setLastName("Dubois");
        dataUser.setPicture("https://randomuser.me/api/portraits/med/women/11.jpg");
        dataUser.setGender("female");
        dataUser.setEmail("clea.dubois@example.com");
        dataUser.setDateOfBirth("1976-12-19T09:54:30.283Z");
        dataUser.setPhone("05-07-68-29-63");

        Location location = new Location();
        location.setStreet("5348, Rue Barrier");
        location.setCity("Perpignan");
        location.setState("Haut-Rhin");
        location.setCountry("France");
        location.setTimezone("-11:00");
        dataUser.setLocation(location);

        dataUser.setRegisterDate("2021-06-21T21:02:18.060Z");
        dataUser.setUpdatedDate("2021-06-21T21:02:18.060Z");

        return dataUser;
    }

    public static User prepareTestUpdateGender() {
        User dataUser = new User();

        dataUser.setId("60d0fe4f5311236168a10a18");
        dataUser.setTitle("mr");
        dataUser.setFirstName("Jeremy");
        dataUser.setLastName("Morin");
        dataUser.setPicture("https://randomuser.me/api/portraits/med/men/60.jpg");
        dataUser.setGender("");
        dataUser.setEmail("jeremy.morin@example.com");
        dataUser.setDateOfBirth("1951-02-21T09:25:59.841Z");
        dataUser.setPhone("081-702-1111");

        Location location = new Location();
        location.setStreet("1693, Argyle St");
        location.setCity("Flatrock");
        location.setState("Alberta");
        location.setCountry("Canada");
        location.setTimezone("-4:00");
        dataUser.setLocation(location);

        dataUser.setRegisterDate("2021-06-21T21:02:20.521Z");
        dataUser.setUpdatedDate("2021-06-21T21:02:20.521Z");

        return dataUser;
    }
}
