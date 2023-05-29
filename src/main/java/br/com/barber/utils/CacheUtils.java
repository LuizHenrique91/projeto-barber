package br.com.podologia.utils;

import br.com.podologia.dto.RegisterCredentialsDto;
import br.com.podologia.exception.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class CacheUtils {

    @Autowired
    private CacheManager cacheManager;

    private final String NAME_CACHE = "userDataCache";

    public UUID setChache(RegisterCredentialsDto registerCredentialsDto){
        Cache userDataCache = getCache();
        registerCredentialsDto.setPassword(encode(registerCredentialsDto.getPassword()));
        UUID hashId = UUID.randomUUID();
        userDataCache.put(hashId, registerCredentialsDto);
        return hashId;
    }

    public RegisterCredentialsDto getCacheUser(String id){
        Cache userDataCache = getCache();
        RegisterCredentialsDto userCache = userDataCache.get(UUID.fromString(id), RegisterCredentialsDto.class);
        if(Objects.isNull(userCache)){
            throw new CacheException("Código expirado");
        }
        return userCache;
    }

    public void cleanCache(String id){
        Cache userDataCache = cacheManager.getCache(NAME_CACHE);
        if(Objects.nonNull(userDataCache)){
            userDataCache.evict(UUID.fromString(id));
        }
    }

    public void updateCache(String id, RegisterCredentialsDto registerCredentialsDto){
        Cache userDataCache = getCache();
        userDataCache.put(id, registerCredentialsDto);
    }

    private Cache getCache() {
        Cache userDataCache = cacheManager.getCache(NAME_CACHE);
        if (Objects.isNull(userDataCache)){
            throw new CacheException("Erro ao processar cache");
        }
        return userDataCache;
    }

    public String encode(String password){
        return new BCryptPasswordEncoder().encode(password);
    }
}
