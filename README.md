## Micronaut 3.4.4 Documentation

Micronaut mongo data issue with specification filter by date.

Simple test demo to show that Micronaut data with specification filter by date does not work.

### expected results
query Mongo data via specification, find all data before given date


### to run the example:
connect to a local mongo database with name as 'qm-dev'
```$xslt
./gradlew run
```

### to create some test data:
make POST API call to generate some test
```$xslt
curl --location --request POST 'http://localhost:8080/demo' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "demo name"
}'
```

### using repository interface works

```$xslt
List<Demo> findByCreatedDateLessThan(Instant date)
```

to test/verify filter by date via repository interface:
make an API call to
```$xslt
curl --location --request GET 'http://localhost:8080/demo?spec=false'
```

expected to see response

mongo query generated and printed in log:
filter and cast to "$date"
```$xslt
Executing exists Mongo 'find' with filter: {"createdDate": {"$lt": {"$date": "2022-05-24T18:38:31.881Z"}}}
``` 

### using specification does NOT work

```$xslt
Predicate toPredicate(@NonNull Root<Demo> root, @NonNull CriteriaBuilder criteriaBuilder) {
        criteriaBuilder & criteriaBuilder.lessThan(root.get("createdDate"), Instant.now())
}
```


to test/verify filter by date via specification filter:
```$xslt
curl --location --request GET 'http://localhost:8080/demo?spec=true'
```
NO results found

mongo query generated and printed in log:
```$xslt
 Executing exists Mongo 'find' with filter: {"createdDate": {"$lt": "2022-05-24T18:40:53.246383Z"}}
```

missing case to "$date" causing it to fail
