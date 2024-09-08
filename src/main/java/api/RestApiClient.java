package api;

import io.restassured.config.EncoderConfig;
import org.apache.http.HttpHeaders;
import utils.TestWrapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import utils.Conf;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public abstract class RestApiClient {
    RestAssured restAssured;
    private String bearerToken = null;
    private Map<String, String> requestHeaders = new HashMap<String, String>(){
        {
            put(HttpHeaders.USER_AGENT, Conf.constant("api.header.userAgentValue"));
        }
    };

    public RestApiClient(String baseUri) {
        this.init(baseUri);
    }

    public void init(String baseUri) {
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.baseURI = baseUri;
        RestAssured.defaultParser = Parser.JSON;
    }

    public RestAssured getRestAssured() {
        return restAssured;
    }

    public Response httpGet(String pathParams) {
        Response response = RestAssured.given()
                .headers(this.requestHeaders)
                .when()
                .get(pathParams);

        return response;
    }

    private Response httpPatch(String pathParams, Object requestModel, String bearerToken, String accessToken) {
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + bearerToken)
                .header("Access-Token", accessToken)
                .contentType(ContentType.JSON)
                .body(requestModel)
                .when()
                .patch(pathParams);
        return response;
    }

    public Response httpPatch(String pathParams, Object requestModel, String bearerToken) {
        return this.httpPatch(pathParams, requestModel, bearerToken, "");
    }

    public Response httpPatch(String pathParams, Object requestModel) {
        return this.httpPatch(pathParams, requestModel, bearerToken);
    }

    private Response httpPut(String pathParams, Object requestModel, String bearerToken, String accessToken) {
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + bearerToken)
                .header("Access-Token", accessToken)
                .contentType(ContentType.JSON)
                .body(requestModel)
                .when()
                .put(pathParams);
        return response;
    }

    public Response httpPut(String pathParams, Object requestModel, String bearerToken) {
        return this.httpPut(pathParams, requestModel, bearerToken, "");
    }

    public Response httpPut(String pathParams, Object requestModel) {
        return this.httpPut(pathParams, requestModel, bearerToken);
    }

    private Response httpPost(String pathParams, Object requestModel, String bearerToken, String accessToken) {
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + bearerToken)
                .header("Access-Token", accessToken)
                .contentType(ContentType.JSON)
                .body(requestModel)
                .when()
                .post(pathParams);
        return response;
    }

    public Response httpPost(String pathParams, Object requestModel, String bearerToken) {
        return this.httpPost(pathParams, requestModel, bearerToken, "");
    }

    public Response httpPost(String pathParams, Object requestModel) {
        return this.httpPost(pathParams, requestModel, bearerToken);
    }

    public boolean checkResponse(Response response, int expectedStatusCode) {
        this.checkStatusCodeResponse(response, expectedStatusCode);
        boolean hasErrorResponse = checkErrorResponse(response, expectedStatusCode);

        return hasErrorResponse;
    }

    public boolean checkValidResponse(Response response) {
        boolean hasErrors = this.checkResponse(response, HttpStatus.SC_OK);

        return !hasErrors;
    }

    private boolean checkErrorResponse(Response response, int validResponse) {
        String responseBody = response.getBody().asString();
        boolean hasError = responseBody.contains("\"success\":false");
        TestWrapper.info("Checking for error response.");
        if (validResponse / 10 != 40) {
            assertFalse(hasError, String.format("Error response is received: %s", responseBody));
        } else {
            assertTrue(hasError,
                    String.format("Response body is: %s \nError response is not received as expected.",
                            responseBody));
        }

        TestWrapper.info("Response is: " + responseBody);

        return hasError;
    }

    public void checkStatusCodeResponse(Response response, int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();

        assertEquals(actualStatusCode, expectedStatusCode,
                String.format("Actual status code is: %s. Expected status code is: %s. \nResponse is received: %s",
                        actualStatusCode, expectedStatusCode,
                        response.getBody().asString()
                                .replace("<meta http-equiv=\"refresh\" content=\"0\"/>", "")));
        TestWrapper.pass(String.format("Response status code is %s as expected!", actualStatusCode));
    }
}