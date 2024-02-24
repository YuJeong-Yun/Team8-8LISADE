package com.lisade.togeduck.cache;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FcmTokenCacheRepository extends CrudRepository<FcmTokenCacheValue, String> {

    Optional<FcmTokenCacheValue> findByNickname(String nickname);

    List<FcmTokenCacheValue> findAllByNicknameIn(List<String> nickname);
}
