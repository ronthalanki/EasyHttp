package easyhttp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(UnitTests.class)
public class EasyHttpTest {
  private static final String BASE_URL = "http://www.google.com";
  private static final String PAYLOAD = "{\"key\":\"value\"}";
  private static final String PATH = "/list";
  private static final Map<String, Object> PARAMS = new HashMap<>();
  private static final EasyHttp http = new EasyHttp(BASE_URL);

  private final URL url;

  public EasyHttpTest() throws MalformedURLException {
    this.url = new URL(BASE_URL);
    PARAMS.put("key", 10);
  }

  @Test
  public void test_init() {
    final EasyHttp testHttp = new EasyHttp(BASE_URL);

    assertThat(BASE_URL).isEqualTo(testHttp.getBaseUrl());
  }

  @Test
  public void test_init_nullBaseUrl() {
    assertThatNullPointerException()
        .isThrownBy(
            () -> {
              new EasyHttp(null);
            });
  }

  @Test
  public void test_init_blankBaseUrl() {
    assertThatIllegalArgumentException()
        .isThrownBy(
            () -> {
              new EasyHttp(" ");
            });
  }

  @Test
  public void test_get_nullUrl() {
    assertThatNullPointerException()
        .isThrownBy(
            () -> {
              http.get(null);
            });
  }

  @Test
  public void test_postNoPayload_nullUrl() {
    assertThatNullPointerException()
        .isThrownBy(
            () -> {
              http.post(null);
            });
  }

  @Test
  public void test_post_nullUrl() {
    assertThatNullPointerException()
        .isThrownBy(
            () -> {
              http.post(null, PAYLOAD);
            });
  }

  @Test
  public void test_post_nullPayload() {
    assertThatNullPointerException()
        .isThrownBy(
            () -> {
              http.post(url, null);
            });
  }

  @Test
  public void test_post_blankPayload() {
    assertThatIllegalArgumentException()
        .isThrownBy(
            () -> {
              http.post(url, " ");
            });
  }

  @Test
  public void test_putNoPayload_nullUrl() {
    assertThatNullPointerException()
        .isThrownBy(
            () -> {
              http.put(null);
            });
  }

  @Test
  public void test_put_nullPayload() {
    assertThatNullPointerException()
        .isThrownBy(
            () -> {
              http.put(url, null);
            });
  }

  @Test
  public void test_put_blankPayload() {
    assertThatIllegalArgumentException()
        .isThrownBy(
            () -> {
              http.put(url, " ");
            });
  }

  @Test
  public void test_delete_nullUrl() {
    assertThatNullPointerException()
        .isThrownBy(
            () -> {
              http.delete(null);
            });
  }

  @Test
  public void test_urlNoParams_nullPath() {
    assertThatNullPointerException()
        .isThrownBy(
            () -> {
              http.url(null);
            });
  }

  @Test
  public void test_urlNoParams_blankPath() {
    assertThatIllegalArgumentException()
        .isThrownBy(
            () -> {
              http.url(" ");
            });
  }

  @Test
  public void test_url_nullPath() {
    assertThatNullPointerException()
        .isThrownBy(
            () -> {
              http.url(null, PARAMS);
            });
  }

  @Test
  public void test_url_blankPath() {
    assertThatIllegalArgumentException()
        .isThrownBy(
            () -> {
              http.url(" ", PARAMS);
            });
  }

  @Test
  public void test_url_nullParams() {
    assertThatNullPointerException()
        .isThrownBy(
            () -> {
              http.url(PATH, null);
            });
  }
}
