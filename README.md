# FastDNS

FastDNS provides asynchronous hostname resolution for the FastJava ecosystem.

The initial release defines a stable asynchronous API and bounded local cache; native Windows `DnsQueryEx` integration can be added behind the same resolver contract.

## Quick start

```java
FastDNS.resolve("example.com").thenAccept(address -> System.out.println(address));
```

## Build

Requires JDK 17+ and Maven 3.9+.

```text
mvn test
mvn package
```

See [docs/REFERENCE.md](docs/REFERENCE.md) for the API contract.
