package ru.stepup.javapro.javapro5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stepup.javapro.javapro5.entity.LimitEntity;

import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<LimitEntity, Long> {
    Optional<LimitEntity> findFirstByUserId(Long userId);
}
