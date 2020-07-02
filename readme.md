
# febs-identify-spring-cloud-starter

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/cn.brainpoint/febs-identify-spring-cloud-starter/badge.svg)](https://maven-badges.herokuapp.com/maven-central/cn.brainpoint/febs-identify-spring-cloud-starter/)
[![License](https://img.shields.io/github/license/brainpoint/febs-identify-spring-cloud-starter)](https://opensource.org/licenses/MIT)

Distributed Unique Identify.

- like objectId, a process can generator id `2^24-1` per second.
- Container `20` chars in id
- Use database to assign unique machineId.

## How to use

maven config.

```html
<dependency>
    <groupId>cn.brainpoint</groupId>
    <artifactId>febs-identify-spring-cloud-starter</artifactId>
    <version>0.0.1</version>
</dependency>
```

## config

bootstrap.properties

```properties
febs.identify.db.type = mysql
febs.identify.db.url = localhost:3306/xx
febs.identify.db.username = username
febs.identify.db.password = password
febs.identify.db.tablename = machine_id # db table use to store meachine id.
febs.identify.db.retryCount = 1
febs.identify.db.connectTimeout = 5000  # connectTimeout connect timeout in milliseconds.

febs.identify.createMachineIdSelf = false # create a machine id in this instance.
```

## usage:

If configured have `createMachineIdSelf=true`, then can do the following:

```java
import cn.brainpoint.febs.identify.Identify;

// create next id.
Identify.nextId();

// validate id.
assert Identify.isValid(id);
```

Following to make a new machine id:

```java
import cn.brainpoint.febs.identify.Identify;

// Generate a new machine id.
Identify.generateNewMachineId()
```
