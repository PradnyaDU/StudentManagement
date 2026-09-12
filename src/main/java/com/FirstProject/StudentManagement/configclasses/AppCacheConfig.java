package com.FirstProject.StudentManagement.configclasses;

import com.FirstProject.StudentManagement.DTO.AppCacheDTO;
import com.FirstProject.StudentManagement.Repository.AppCacheRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class AppCacheConfig {
    @Autowired
    private AppCacheRepository appCacheRepository;
    private final Map<String, String> cache = new HashMap<>();

    @PostConstruct
    public void loadCache() {

        List<AppCacheDTO> configurations = appCacheRepository.findAll();

        for (AppCacheDTO config : configurations) {
            cache.put(config.getConfigKey(), config.getConfigValue());
        }

        System.out.println("Configuration cache loaded.");
        System.out.println("Cache: " + cache);
    }

    public String get(String key) {
        return cache.get(key);
    }
}
