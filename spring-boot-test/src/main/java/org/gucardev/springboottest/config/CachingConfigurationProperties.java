package org.gucardev.springboottest.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "caching.config")
@Getter
public class CachingConfigurationProperties {


  private final CacheProperties user;
  private final CacheProperties address;

  public CachingConfigurationProperties(CacheProperties user, CacheProperties address) {
    this.user = user;
    this.address = address;
  }

  @Getter
  public static class CacheProperties {

    private final String cacheName;
    private final Long cacheTtl;

    public CacheProperties(String cacheName, Long cacheTtl) {
      this.cacheName = cacheName;
      this.cacheTtl = cacheTtl;
    }
  }
}
