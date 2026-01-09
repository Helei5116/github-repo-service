package com.github.repo.repository;

import com.github.repo.entity.GithubInfoCache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GithubInfoCacheRepository extends JpaRepository<GithubInfoCache, Long> {
    // 根据仓库完整名称查询缓存
    Optional<GithubInfoCache> findByFullName(String fullName);
}