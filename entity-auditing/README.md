# Spring Boot Hibernate Envers Audit History Logging


### How to run?

- Docker
```shell
docker build -t hibernate-envers . && docker run -d -p 8080:8080 hibernate-envers
```

- Maven
```shell
mvn spring-boot:run
```


### How Can I use?

- Use ```@Audited``` for generate audit history of entity

```java

@Audited
@Entity
public class Address extends BaseEntity {
```

- If you want to mark field for not auditing use ```@NotAudited```

```java
@NotAudited
@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
private List<Address> addresses;
```

#### USER_AUD

| ID | REV | REVTYPE | NAME           | USERNAME |
|----|-----|---------|----------------|----------|
| 1  | 1   | 0       | gurkan         | @grkn    |
| 2  | 2   | 0       | gurkan2        | @grkn2   |
| 1  | 3   | 1       | gurkan_UPDATE  | @grkn    |
| 1  | 4   | 1       | gurkan_UPDATE2 | @grkn    |
| 1  | 5   | 1       | gurkan_UPDATE  | @grkn    |
| 1  | 6   | 2       | null           | null     |

#### ADDRESS_AUD

| ID | REV | REVTYPE | CITY             | COUNTRY      | STREET           | USER_ID  |
|----|-----|---------|------------------|--------------|------------------|----------|
| 1  | 7   | 0       | Istanbul         | TR           | Taksim           | 2        |
| 1  | 8   | 1       | Istanbul_UPDATED | TR_UPDATED   | Taksim_UPDATED   | 2        |

#### REVINFO

| REV | REVTSTMP        |
|-----|----------------|
| 1   | 1686001700932  |
| 2   | 1686001705279  |
| 3   | 1686001719637  |
| 4   | 1686001737580  |
| 5   | 1686001743540  |
| 6   | 1686001784099  |

### example responses ([postman collection here](https://github.com/gurkanucar/hibernate-envers-audit-history/blob/master/hibernate-envers-audit-history.postman_collection.json))

### ```/user/revision/<ID>```

```json
[
  {
    "id": 1,
    "name": "gurkan",
    "username": "@grkn",
    "rev": 1,
    "revType": "ADD",
    "revDate": "2023-06-05T21:48:20.932+00:00"
  },
  {
    "id": 1,
    "name": "gurkan_UPDATE",
    "username": "@grkn",
    "rev": 3,
    "revType": "MODIFY",
    "revDate": "2023-06-05T21:48:39.637+00:00"
  },
  {
    "id": 1,
    "name": "gurkan_UPDATE2",
    "username": "@grkn",
    "rev": 4,
    "revType": "MODIFY",
    "revDate": "2023-06-05T21:48:57.580+00:00"
  },
  {
    "id": 1,
    "name": "gurkan_UPDATE",
    "username": "@grkn",
    "rev": 5,
    "revType": "MODIFY",
    "revDate": "2023-06-05T21:49:03.540+00:00"
  },
  {
    "id": 1,
    "name": null,
    "username": null,
    "rev": 6,
    "revType": "DELETE",
    "revDate": "2023-06-05T21:49:44.099+00:00"
  }
]
```

### ```/address/revision/<ID>```

```json
[
  {
    "id": 1,
    "street": "Taksim",
    "city": "Istanbul",
    "country": "TR",
    "rev": 7,
    "revType": "ADD",
    "revDate": "2023-06-06T18:30:36.545+00:00"
  },
  {
    "id": 1,
    "street": "Taksim_UPDATED",
    "city": "Istanbul_UPDATED",
    "country": "TR_UPDATED",
    "rev": 8,
    "revType": "MODIFY",
    "revDate": "2023-06-06T18:32:48.150+00:00"
  }
]
```

### res:

[https://progressivecoder.com/setting-hibernate-envers-spring-boot/](https://progressivecoder.com/setting-hibernate-envers-spring-boot/)

[http://www.java2s.com/example/java-api/org/hibernate/envers/auditreader/createquery-0-3.html](http://www.java2s.com/example/java-api/org/hibernate/envers/auditreader/createquery-0-3.html)

[https://thorben-janssen.com/hibernate-envers-query-data-audit-log/](https://thorben-janssen.com/hibernate-envers-query-data-audit-log/)
