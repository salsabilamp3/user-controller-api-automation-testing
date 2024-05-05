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
        dataUser.setLocation(new Location());
        dataUser.getLocation().setStreet("5348, Rue Barrier");
        dataUser.getLocation().setCity("Perpignan");
        dataUser.getLocation().setState("Haut-Rhin");
        dataUser.getLocation().setCountry("France");
        dataUser.getLocation().setTimezone("-11:00");
        dataUser.setRegisterDate("2021-06-21T21:02:18.060Z");
        dataUser.setUpdatedDate("2021-06-21T21:02:18.060Z");

        return dataUser;
    }
}
