package com.example

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import org.bson.types.ObjectId
import java.time.Instant

@MappedEntity(value="demo")
class Demo {

    @Id
    @GeneratedValue
    ObjectId id

    String name
    Instant createdDate
}