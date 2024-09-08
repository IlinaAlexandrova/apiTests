package api.creatingResource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "body",
        "userId",
        "id"
})
public class CreatingResourceResponse {
    @JsonProperty("title")
    private String title;
    @JsonProperty("body")
    private String body;
    @JsonProperty("userId")
    private Integer userId;
    @JsonProperty("id")
    private Integer id;

    /**
     * No args constructor for use in serialization
     *
     */
    public CreatingResourceResponse() {
    }

    /**
     *
     * @param id
     * @param title
     * @param body
     * @param userId
     */
    public CreatingResourceResponse(String title, String body, Integer userId, Integer id) {
        super();
        this.title = title;
        this.body = body;
        this.userId = userId;
        this.id = id;
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

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }
}