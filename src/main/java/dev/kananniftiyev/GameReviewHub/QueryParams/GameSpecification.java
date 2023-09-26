package dev.kananniftiyev.GameReviewHub.QueryParams;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import dev.kananniftiyev.GameReviewHub.entity.Game;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class GameSpecification {
    public static Specification<Game> searchByCriteria(
            String name, String developer, String publisher, String releaseDate, String generalRating) {
        return (Root<Game> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!StringUtils.isEmpty(name)) {
                predicates.add(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if (!StringUtils.isEmpty(developer)) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("developer")),
                        "%" + developer.toLowerCase() + "%"));
            }

            if (!StringUtils.isEmpty(publisher)) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("publisher")),
                        "%" + publisher.toLowerCase() + "%"));
            }

            if (!StringUtils.isEmpty(releaseDate)) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("releaseDate")),
                        "%" + releaseDate.toLowerCase() + "%"));
            }

            if (!StringUtils.isEmpty(generalRating)) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("generalRating")),
                        "%" + generalRating.toLowerCase() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
