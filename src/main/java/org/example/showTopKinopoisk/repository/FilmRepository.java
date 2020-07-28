package org.example.showTopKinopoisk.repository;

import org.example.showTopKinopoisk.model.Film;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;
import java.time.Instant;
import java.util.List;

public interface FilmRepository extends CrudRepository<Film, Long> {

    @QueryHints(value = {
            @QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("FROM Film WHERE date(createInstant) = date(:instant)")
    List<Film> findByInstant(@Param("instant") Instant instant);
}
