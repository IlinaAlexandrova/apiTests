package apitests.posts1;

import api.FeedApi;
import api.RestApiClient;
import api.patchingResource.PatchingResourceRequest;
import api.patchingResource.PatchingResourceResponse;
import api.updatingResource.UpdatingResourceResponse;
import apitests.BaseApiTest;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Conf;
import utils.TestWrapper;

public class PatchingResourceTest extends BaseApiTest {
    private String title = "title";

    @Test(description = "patchingResource api method",
            groups = {"api"})
    public void patchingResource() {
        TestWrapper.info("Verifying patchResource API endpoint:");
        //1. Making a new create request with parameters
        PatchingResourceRequest patchingResourceRequest = new PatchingResourceRequest(title);

        //2. Making the PATCH request
        RestApiClient feedApi = new FeedApi();
        Response response = feedApi.httpPatch(Conf.routes("api.posts.1"), patchingResourceRequest);
        TestWrapper.info(String.format("patchingResource request: request sent to URL: %s.",
                ((RestAssuredResponseImpl) response).getLogRepository().getRequestLog()));
        //3. Validating response code
        feedApi.checkValidResponse(response);
        //4. Getting the response
        PatchingResourceResponse patchingResourceResponse = response.getBody().as(PatchingResourceResponse.class);

        //5. Validating response parameters
        TestWrapper.info("Validating properties from the API response:");
        Assert.assertEquals(patchingResourceResponse.getTitle(), title, "Title is different than expected.");
        TestWrapper.info("Validating title from the API response passed.");
        Assert.assertFalse(patchingResourceResponse.getBody().isEmpty(), "Body empty.");
        TestWrapper.info("Validating body from the API response passed.");
        Assert.assertEquals(patchingResourceResponse.getUserId(), 1,"UserId is different than expected.");
        TestWrapper.info("Validating userId from the API response passed.");
        Assert.assertEquals(patchingResourceResponse.getId(), 1, "Id is different than expected.");
        TestWrapper.info("Validating id from the API response passed.");

        TestWrapper.pass("Validating updatingResponse API passed.");
    }
}
