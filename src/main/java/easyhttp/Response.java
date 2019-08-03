package easyhttp;

public class Response {
  private final Integer responseCode;
  private final String json;

  public Response(final Integer responseCode, final String json) {
    this.responseCode = responseCode;
    this.json = json;
  }

  public Integer getResponseCode() {
    return responseCode;
  }

  public String getJson() {
    return json;
  }
}
