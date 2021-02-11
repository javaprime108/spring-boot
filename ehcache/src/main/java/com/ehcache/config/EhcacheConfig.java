package com.ehcache.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EhcacheConfig {

	
	@Bean
	public net.sf.ehcache.CacheManager ehCacheManager() {

		net.sf.ehcache.config.CacheConfiguration customCache = new net.sf.ehcache.config.CacheConfiguration();
		customCache.setName("customCache");
		customCache.setMemoryStoreEvictionPolicy("LRU");
		customCache.setMaxEntriesLocalHeap(10000);
		customCache.setEternal(false);
		customCache.internalSetTimeToLive(86400);

		net.sf.ehcache.config.Configuration configs = new net.sf.ehcache.config.Configuration();
		configs.addCache(customCache);
		return net.sf.ehcache.CacheManager.newInstance(configs);
	}

	@Bean
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheManager());

	}
	
}
