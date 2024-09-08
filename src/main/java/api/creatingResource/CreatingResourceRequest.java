package api.creatingResource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "body",
        "userId"
})
public class CreatingResourceRequest {
    @JsonProperty("title")
    private String title;
    @JsonProperty("body")
    private String body;
    @JsonProperty("userId")
    private Integer userId;

    /**
     * No args constructor for use in serialization
     *
     */
    public CreatingResourceRequest() {
    }

    /**
     *
     * @param title
     * @param body
     * @param userId
     */
    public CreatingResourceRequest(String title, String body, Integer userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    @JsonProperty("userId")
    public Integer getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
