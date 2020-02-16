package com.nouhoun.springboot.jwt.integration;

import io.restassured.response.Response;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetCitiesTest {
    Logger log = LoggerFactory.getLogger(GetCitiesTest.class);

    private static String accessToken;
    private static final String BASE_URI = "http://localhost:8080";

    @Test
    public void getAuthorizationToken() throws JSONException {

        String CLIENT_ID = "testjwtclientid";
        String CLIENT_SECRET = "XY7kmzoNzl100";
        String USER_NAME = "john.doe";
        String PASSWORD = "jwtpass";
        String GRANT_TYPE = "password";
        Response response =
                given()
                        .auth().preemptive().basic(CLIENT_ID, CLIENT_SECRET)
                        .contentType("application/x-www-form-urlencoded").log().all()
                        .formParam("grant_type", GRANT_TYPE)
                        .formParam("username", USER_NAME)
                        .formParam("password", PASSWORD)
                        .when()
                        .post(BASE_URI + "/oauth/token")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .response()
                        ;

        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        log.debug("\n"+jsonObject.toString(4));
        accessToken = jsonObject.get("access_token").toString();
        String tokenType = jsonObject.get("token_type").toString();
        log.debug("Oauth Token with type : " + tokenType + "\n-->" + accessToken + "<--");

    }


    @Test
    public void getCities() throws JSONException {
        Response response =
            given().auth().oauth2(accessToken)
                .when()
                .get(BASE_URI + "/springjwt/cities")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .response()
                ;
        JSONArray jsonArray = new JSONArray(response.getBody().asString());
        log.debug("\n"+jsonArray.toString(4));
        //log.debug("\n"+response);
    }
}
