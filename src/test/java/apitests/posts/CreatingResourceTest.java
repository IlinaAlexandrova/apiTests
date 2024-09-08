package apitests.posts;

import api.FeedApi;
import api.RestApiClient;
import api.creatingResource.CreatingResourceRequest;
import api.creatingResource.CreatingResourceResponse;
import apitests.BaseApiTest;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Conf;
import utils.TestWrapper;

public class CreatingResourceTest extends BaseApiTest {
    private String title = "title";
    private String body = "body";
    private int userId = 11;

    @Test(description = "creatingResource api method",
            groups = {"api"})
    public void creatingResource() {
        TestWrapper.info("Verifying createResource API endpoint:");
        //1. Making a new create request with parameters
        CreatingResourceRequest creatingResourceRequest = new CreatingResourceRequest(title, body, userId);

        //2. Making the POST request
        RestApiClient feedApi = new FeedApi();
        Response response = feedApi.httpPost(Conf.routes("api.posts"), creatingResourceRequest);
        TestWrapper.info(String.format("creatingResource request: request sent to URL: %s.",
                ((RestAssuredResponseImpl) response).getLogRepository().getRequestLog()));
        //3. Validating response code
        feedApi.checkResponse(response, HttpStatus.SC_CREATED);
        //4. Getting the response
        CreatingResourceResponse creatingResourceResponse = response.getBody().as(CreatingResourceResponse.class);

        //5. Validating response parameters
        TestWrapper.info("Validating properties from the API response:");
        Assert.assertEquals(creatingResourceResponse.getTitle(), title, "Title is different than expected.");
        TestWrapper.info("Validating title from the API response passed.");
        Assert.assertEquals(creatingResourceResponse.getBody(), body, "Body is different than expected.");
        TestWrapper.info("Validating body from the API response passed.");
        Assert.assertEquals(creatingResourceResponse.getUserId(), userId, "UserId is different than expected.");
        TestWrapper.info("Validating userId from the API response passed.");
        Assert.assertEquals(creatingResourceResponse.getId(), 101, "Id is different than expected.");
        TestWrapper.info("Validating id from the API response passed.");

        TestWrapper.pass("Validating creatingResponse API passed.");
    }
}
