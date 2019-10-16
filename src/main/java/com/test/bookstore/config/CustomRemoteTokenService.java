package com.test.bookstore.config;

import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;

@Component
public class CustomRemoteTokenService implements ResourceServerTokenServices {

  private final RestOperations restTemplate;
  private final AccessTokenConverter tokenConverter;

  @Value("${oauth.check-token-url}")
  private String checkTokenUrl;

  public CustomRemoteTokenService(RestOperations restTemplate,
      AccessTokenConverter tokenConverter) {
    this.restTemplate = restTemplate;
    this.tokenConverter = tokenConverter;
  }

  @Override
  public OAuth2Authentication loadAuthentication(String accessToken)
      throws AuthenticationException, InvalidTokenException {
    HttpHeaders headers = new HttpHeaders();
    Map<String, Object> response =
        executeGet(this.checkTokenUrl + accessToken, headers);
    if (Objects.isNull(response) || response.isEmpty() || response.get("error") != null) {
      throw new InvalidTokenException("Token not allowed");
    }
    return tokenConverter.extractAuthentication(response);
  }

  @Override
  public OAuth2AccessToken readAccessToken(String accessToken) {
    throw new UnsupportedOperationException("Not supported");
  }

  private Map<String, Object> executeGet(String path, HttpHeaders headers) {
    try {
      if (headers.getContentType() == null) {
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      }
      Map<String, Object> map = (Map<String, Object>)restTemplate.exchange(path, HttpMethod.GET,
          new HttpEntity<MultiValueMap<String, String>>(null, headers), Map.class).getBody();
      return map;
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
    return null;
  }

}
