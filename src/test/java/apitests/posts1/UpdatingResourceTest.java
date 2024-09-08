package apitests.posts1;

import api.FeedApi;
import api.RestApiClient;
import api.updatingResource.UpdatingResourceRequest;
import api.updatingResource.UpdatingResourceResponse;
import apitests.BaseApiTest;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Conf;
import utils.TestWrapper;

public class UpdatingResourceTest extends BaseApiTest {
    private int id = 1;
    private String title = "title";
    private String body = "body";
    private int userId = 11;

    @Test(description = "updatingResource api method",
            groups = {"api"})
    public void updatingResource() {
        TestWrapper.info("Verifying updateResource API endpoint:");
        //1. Making a new create request with parameters
        UpdatingResourceRequest updatingResourceRequest = new UpdatingResourceRequest(id, title, body, userId);

        //2. Making the PUT request
        RestApiClient feedApi = new FeedApi();
        Response response = feedApi.httpPut(Conf.routes("api.posts.1"), updatingResourceRequest);
        TestWrapper.info(String.format("updatingResource request: request sent to URL: %s.",
                ((RestAssuredResponseImpl) response).getLogRepository().getRequestLog()));
        //3. Validating response code
        feedApi.checkValidResponse(response);
        //4. Getting the response
        UpdatingResourceResponse updatingResourceResponse = response.getBody().as(UpdatingResourceResponse.class);

        //5. Validating response parameters
        TestWrapper.info("Validating properties from the API response:");
        Assert.assertEquals(updatingResourceResponse.getTitle(), title, "Title is different than expected.");
        TestWrapper.info("Validating title from the API response passed.");
        Assert.assertEquals(updatingResourceResponse.getBody(), body, "Body  is different than expected.");
        TestWrapper.info("Validating body from the API response passed.");
        Assert.assertEquals(updatingResourceResponse.getUserId(), userId,"UserId is different than expected.");
        TestWrapper.info("Validating userId from the API response passed.");
        Assert.assertEquals(updatingResourceResponse.getId(), id, "Id is different than expected.");
        TestWrapper.info("Validating id from the API response passed.");

        TestWrapper.pass("Validating updatingResponse API passed.");
    }
}
