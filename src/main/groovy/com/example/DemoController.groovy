package com.example


import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.QueryValue
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.repository.jpa.criteria.PredicateSpecification
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import jakarta.inject.Inject
import java.time.Instant

@Controller("/demo")
class DemoController {

    @Inject
    DemoRepository demoRepository

    @Get("/")
    List<Demo> getAllDemo(@QueryValue Boolean spec) {
        if (spec) {
            PredicateSpecification<Demo> predicateSpecification = new PredicateSpecification<Demo>() {
                @Override
                Predicate toPredicate(@NonNull Root<Demo> root, @NonNull CriteriaBuilder criteriaBuilder) {
                    criteriaBuilder & criteriaBuilder.lessThan(root.get("createdDate"), Instant.now())
                }
            }
            demoRepository.findAll(predicateSpecification)
        } else {
            demoRepository.findByCreatedDateLessThan(Instant.now())
        }
    }

    @Post("/")
    Demo createNewRecord(Demo demo) {
        demo.createdDate = Instant.now()
        demoRepository.save(demo)
    }

}
