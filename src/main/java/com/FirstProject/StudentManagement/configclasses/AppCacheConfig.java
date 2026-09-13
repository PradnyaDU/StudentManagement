package com.FirstProject.StudentManagement.configclasses;

import com.FirstProject.StudentManagement.dto.AppCacheDTO;
import com.FirstProject.StudentManagement.repository.AppCacheRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class AppCacheConfig {
    private final Map<String, String> cache = new HashMap<>();
    @Autowired
    private AppCacheRepository appCacheRepository;

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
