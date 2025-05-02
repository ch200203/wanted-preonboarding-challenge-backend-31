package com.study.wanted.common.cache;

/**
 * 캐시에 저장되는 객체의 경우 이 인터페이스 구현
 */
public interface CacheKey {

    /**
     * 캐싱 키
     */
    String cacheKey();

}
