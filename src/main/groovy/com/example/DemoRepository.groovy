package com.example

import io.micronaut.context.annotation.Executable
import io.micronaut.data.mongodb.annotation.MongoFindQuery
import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository
import io.micronaut.data.repository.jpa.JpaSpecificationExecutor
import org.bson.types.ObjectId

import java.time.Instant

@MongoRepository
interface DemoRepository extends CrudRepository<Demo, ObjectId>, JpaSpecificationExecutor {

    @Executable
    List<Demo> findByCreatedDateLessThan(Instant date)

}