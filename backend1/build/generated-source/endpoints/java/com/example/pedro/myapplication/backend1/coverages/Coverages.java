/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2015-08-03 17:34:38 UTC)
 * on 2015-10-16 at 01:24:51 UTC 
 * Modify at your own risk.
 */

package com.example.pedro.myapplication.backend1.coverages;

/**
 * Service definition for Coverages (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link CoveragesRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Coverages extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.20.0 of the coverages library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://myApplicationId.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "coverages/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public Coverages(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Coverages(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "addCoverage".
   *
   * This request holds the parameters needed by the coverages server.  After setting any optional
   * parameters, call the {@link AddCoverage#execute()} method to invoke the remote operation.
   *
   * @param content the {@link com.example.pedro.myapplication.backend1.coverages.model.Coverage}
   * @return the request
   */
  public AddCoverage addCoverage(com.example.pedro.myapplication.backend1.coverages.model.Coverage content) throws java.io.IOException {
    AddCoverage result = new AddCoverage(content);
    initialize(result);
    return result;
  }

  public class AddCoverage extends CoveragesRequest<Void> {

    private static final String REST_PATH = "addCoverage";

    /**
     * Create a request for the method "addCoverage".
     *
     * This request holds the parameters needed by the the coverages server.  After setting any
     * optional parameters, call the {@link AddCoverage#execute()} method to invoke the remote
     * operation. <p> {@link
     * AddCoverage#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link com.example.pedro.myapplication.backend1.coverages.model.Coverage}
     * @since 1.13
     */
    protected AddCoverage(com.example.pedro.myapplication.backend1.coverages.model.Coverage content) {
      super(Coverages.this, "POST", REST_PATH, content, Void.class);
    }

    @Override
    public AddCoverage setAlt(java.lang.String alt) {
      return (AddCoverage) super.setAlt(alt);
    }

    @Override
    public AddCoverage setFields(java.lang.String fields) {
      return (AddCoverage) super.setFields(fields);
    }

    @Override
    public AddCoverage setKey(java.lang.String key) {
      return (AddCoverage) super.setKey(key);
    }

    @Override
    public AddCoverage setOauthToken(java.lang.String oauthToken) {
      return (AddCoverage) super.setOauthToken(oauthToken);
    }

    @Override
    public AddCoverage setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (AddCoverage) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public AddCoverage setQuotaUser(java.lang.String quotaUser) {
      return (AddCoverage) super.setQuotaUser(quotaUser);
    }

    @Override
    public AddCoverage setUserIp(java.lang.String userIp) {
      return (AddCoverage) super.setUserIp(userIp);
    }

    @Override
    public AddCoverage set(String parameterName, Object value) {
      return (AddCoverage) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "getCoverages".
   *
   * This request holds the parameters needed by the coverages server.  After setting any optional
   * parameters, call the {@link GetCoverages#execute()} method to invoke the remote operation.
   *
   * @return the request
   */
  public GetCoverages getCoverages() throws java.io.IOException {
    GetCoverages result = new GetCoverages();
    initialize(result);
    return result;
  }

  public class GetCoverages extends CoveragesRequest<com.example.pedro.myapplication.backend1.coverages.model.CoverageCollection> {

    private static final String REST_PATH = "coveragecollection";

    /**
     * Create a request for the method "getCoverages".
     *
     * This request holds the parameters needed by the the coverages server.  After setting any
     * optional parameters, call the {@link GetCoverages#execute()} method to invoke the remote
     * operation. <p> {@link
     * GetCoverages#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected GetCoverages() {
      super(Coverages.this, "GET", REST_PATH, null, com.example.pedro.myapplication.backend1.coverages.model.CoverageCollection.class);
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public GetCoverages setAlt(java.lang.String alt) {
      return (GetCoverages) super.setAlt(alt);
    }

    @Override
    public GetCoverages setFields(java.lang.String fields) {
      return (GetCoverages) super.setFields(fields);
    }

    @Override
    public GetCoverages setKey(java.lang.String key) {
      return (GetCoverages) super.setKey(key);
    }

    @Override
    public GetCoverages setOauthToken(java.lang.String oauthToken) {
      return (GetCoverages) super.setOauthToken(oauthToken);
    }

    @Override
    public GetCoverages setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetCoverages) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetCoverages setQuotaUser(java.lang.String quotaUser) {
      return (GetCoverages) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetCoverages setUserIp(java.lang.String userIp) {
      return (GetCoverages) super.setUserIp(userIp);
    }

    @Override
    public GetCoverages set(String parameterName, Object value) {
      return (GetCoverages) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link Coverages}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link Coverages}. */
    @Override
    public Coverages build() {
      return new Coverages(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link CoveragesRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setCoveragesRequestInitializer(
        CoveragesRequestInitializer coveragesRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(coveragesRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}