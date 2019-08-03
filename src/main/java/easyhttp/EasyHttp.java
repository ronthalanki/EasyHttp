package easyhttp;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;

public class EasyHttp {
  private final String baseUrl;

  public EasyHttp(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  public Response get(final URL url) {
    return null;
  }

  public Response post(final URL url) {
    return null;
  }

  public Response post(final URL url, final String payload) {
    return null;
  }

  public Response put(final URL url) {
    return null;
  }

  public Response put(final URL url, final String payload) {
    return null;
  }

  public Response delete(final URL url) {
    return null;
  }

  public URL url(final String path) throws URISyntaxException, MalformedURLException {
    return url(path, Collections.emptyMap());
  }

  public URL url(final String path, final Map<String, Object> queryParams)
      throws URISyntaxException, MalformedURLException {
    final List<NameValuePair> nvps = new ArrayList<>();
    for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
      final BasicNameValuePair nameValuePair =
          new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()));
      nvps.add(nameValuePair);
    }

    return new URIBuilder(baseUrl).setPath(path).setParameters(nvps).build().toURL();
  }
}
