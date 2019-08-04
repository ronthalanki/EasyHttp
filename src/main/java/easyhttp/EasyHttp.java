package easyhttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;

public class EasyHttp {
  private static final String BLANK_JSON_EXCEPTION_MESSAGE = "JSON is empty, null, or blank";
  private static final String BLANK_PATH_EXCEPTION_MESSAGE = "Path is empty, null, or blank";
  private static final String BLANK_EXCEPTION_MESSAGE = "String is empty, null, or blank";

  private final String baseUrl;

  public EasyHttp(String baseUrl) {
    Objects.requireNonNull(baseUrl);
    requireNotBlank(baseUrl, BLANK_EXCEPTION_MESSAGE);

    this.baseUrl = baseUrl;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public Response get(final URL url) throws IOException {
    Objects.requireNonNull(url);

    final HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");

    final Response response = getResponse(con);
    con.disconnect();
    return response;
  }

  public Response post(final URL url) throws IOException {
    Objects.requireNonNull(url);

    final HttpURLConnection con = (HttpURLConnection) url.openConnection();

    final Response response = getResponse(con);
    con.disconnect();
    return response;
  }

  public Response post(final URL url, final String payload) throws IOException {
    Objects.requireNonNull(url);
    Objects.requireNonNull(payload);
    requireNotBlank(payload, BLANK_JSON_EXCEPTION_MESSAGE);

    final HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("POST");
    setupPayload(con, payload);

    final Response response = getResponse(con);
    con.disconnect();
    return response;
  }

  public Response put(final URL url) throws IOException {
    Objects.requireNonNull(url);

    final HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("PUT");

    final Response response = getResponse(con);
    con.disconnect();
    return response;
  }

  public Response put(final URL url, final String payload) throws IOException {
    Objects.requireNonNull(url);
    Objects.requireNonNull(payload);
    requireNotBlank(payload, BLANK_JSON_EXCEPTION_MESSAGE);

    final HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("PUT");
    setupPayload(con, payload);

    final Response response = getResponse(con);
    con.disconnect();
    return response;
  }

  public Response delete(final URL url) throws IOException {
    Objects.requireNonNull(url);

    final HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("DELETE");

    final Response response = getResponse(con);
    con.disconnect();
    return response;
  }

  private void setupPayload(final HttpURLConnection con, final String payload) throws IOException {
    Objects.requireNonNull(con);
    Objects.requireNonNull(payload);
    requireNotBlank(payload, BLANK_JSON_EXCEPTION_MESSAGE);

    con.setRequestProperty("Content-Type", "application/json");
    final OutputStream os = con.getOutputStream();
    byte[] input = payload.getBytes("utf-8");
    os.write(input, 0, input.length);
  }

  private Response getResponse(final HttpURLConnection con) throws IOException {
    Objects.requireNonNull(con);

    con.setRequestProperty("Accept", "application/json");
    con.setDoOutput(true);
    final BufferedReader bufferedReader =
        new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));

    final StringBuilder response = new StringBuilder();
    String responseLine = null;
    while ((responseLine = bufferedReader.readLine()) != null) {
      response.append(responseLine.trim());
    }

    return new Response(con.getResponseCode(), response.toString());
  }

  public URL url(final String path) throws URISyntaxException, MalformedURLException {
    return url(path, Collections.emptyMap());
  }

  public URL url(final String path, final Map<String, Object> queryParams)
      throws URISyntaxException, MalformedURLException {
    Objects.requireNonNull(path);
    requireNotBlank(path, BLANK_PATH_EXCEPTION_MESSAGE);
    Objects.requireNonNull(queryParams);

    final List<NameValuePair> nvps = new ArrayList<>();
    for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
      final BasicNameValuePair nameValuePair =
          new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()));
      nvps.add(nameValuePair);
    }

    return new URIBuilder(baseUrl).setPath(path).setParameters(nvps).build().toURL();
  }

  private static void requireNotBlank(final String str, final String message) {
    if (StringUtils.isBlank(str)) {
      throw new IllegalArgumentException(message);
    }
  }
}
