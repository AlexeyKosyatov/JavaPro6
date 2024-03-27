package ru.stepup.javapro.javapro5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stepup.javapro.javapro5.entity.ParamEntity;

import java.util.Optional;

@Repository
public interface ParamRepository extends JpaRepository<ParamEntity, Long> {
    Optional<ParamEntity> findFirstByCode(String code);
}
