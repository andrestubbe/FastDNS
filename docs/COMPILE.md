# Building FastDNS

Requires JDK 17+ and Maven 3.9+.

- `mvn clean test` compiles the library and unit tests.
- `run-demo.bat` resolves a public web endpoint.
- `run-benchmark.bat` measures repeated cached lookups.

The current backend uses the JDK resolver; Windows `DnsQueryEx` can replace it without changing the asynchronous API.
