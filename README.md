# FastDNS 0.1.0 [ALPHA-2026-09] — Ultra-Fast DNS Resolver for Java

[![Status](https://img.shields.io/badge/status-0.1.0-brightgreen.svg)](https://github.com/andrestubbe/FastDNS)
[![Java](https://img.shields.io/badge/Java-17+-blue.svg)](https://www.java.com)
[![Platform](https://img.shields.io/badge/Platform-Windows%2010+-lightgrey.svg)]()
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

---

**Asynchronous hostname resolution for the FastJava ecosystem.** FastDNS combines a stable async API with a local TTL cache and leaves a clear boundary for native Windows `DnsQueryEx`, DoH and DoT providers.

## Quick Start

```java
FastDNS.resolve("example.com").thenAccept(System.out::println);
```

## Table of Contents

- [Why FastDNS?](#why-fastdns)
- [Quick Start](#quick-start)
- [Features](#features)
- [Real-World Scenarios](#real-world-scenarios)
- [Performance Benchmarks](#performance-benchmarks)
- [API Quick Reference](#api-quick-reference)
- [Technical Examples & Hero Demos](#technical-examples--hero-demos)
- [Installation](#installation)
- [Documentation](#documentation)
- [Platform Support](#platform-support)
- [License](#license)
- [Related Projects](#related-projects)

---

## Why FastDNS?

Synchronous hostname resolution blocks application threads and becomes a bottleneck when crawlers, telemetry clients or socket pools open many endpoints. FastDNS keeps resolution asynchronous and reuses successful answers until their TTL expires.

---

## Features

- `CompletableFuture`-based asynchronous resolution.
- Thread-safe local cache with caller-selected TTL.
- Portable JDK resolver fallback.
- Native `DnsQueryEx`, DoH and DoT integration boundary.

---

## Real-World Scenarios

- **Web crawling:** Resolve thousands of crawler endpoints without blocking worker threads.
- **FastNet startup:** Resolve service endpoints before opening socket connections.
- **Microservice clients:** Reuse short-lived DNS answers during burst traffic.
- **Telemetry:** Avoid repeated resolver work for stable device gateways.

---

## Performance Benchmarks

The included benchmark measures repeated cached `localhost` lookups; native resolver figures must be measured after `DnsQueryEx` integration.

| Operation | Current Java fallback | Native target |
|---|---:|---:|
| Cached lookup | Measured by `run-benchmark.bat` | Atomic native cache |
| Cold lookup | JDK resolver | `DnsQueryEx` |

---

## API Quick Reference

| Method | Description |
|---|---|
| `resolve(hostname)` | Resolves a hostname asynchronously using the default TTL. |
| `resolve(hostname, ttlMillis)` | Resolves and caches with a caller-selected TTL. |
| `clearCache()` | Removes all cached answers. |

---

## Technical Examples & Hero Demos

| Case | Java Example | Launcher | Description |
|---|---|---|---|
| **Crawler Endpoint** | [Demo.java](examples/Demo/src/main/java/fastdns/Demo.java) | `run-demo.bat` | Resolves a real public web endpoint asynchronously. |
| **Resolver Cache** | [Benchmark.java](examples/Benchmark/src/main/java/fastdns/benchmark/Benchmark.java) | `run-benchmark.bat` | Measures repeated cached lookups for a service client. |

---

## Installation

### Option 1: Maven (Recommended)

```xml
<dependency>
	<groupId>com.github.andrestubbe</groupId>
	<artifactId>FastDNS</artifactId>
	<version>0.1.0</version>
</dependency>
```

### Option 2: Gradle (via JitPack)

```groovy
implementation 'com.github.andrestubbe:FastDNS:0.1.0'
```

### Option 3: Direct Download (No Build Tool)

Download the latest FastDNS JAR from the [GitHub releases](https://github.com/andrestubbe/FastDNS/releases) page.

---

## Documentation

- [COMPILE.md](docs/COMPILE.md): Build and launcher instructions.
- [REFERENCE.md](docs/REFERENCE.md): Resolver and cache contract.
- [PHILOSOPHY.md](docs/PHILOSOPHY.md): Async and cache principles.
- [ROADMAP.md](docs/ROADMAP.md): Native resolver milestones.
- [CHANGELOG.md](docs/CHANGELOG.md): Version history.

---

## Platform Support

| Platform | Status |
|---|---|
| Windows 10/11 x64 | Native `DnsQueryEx` planned |
| Linux | Java fallback |
| macOS | Java fallback |

---

## License

MIT License — See [LICENSE](LICENSE) for details.

---

## Related Projects

- [FastNet](https://github.com/andrestubbe/FastNet) — Asynchronous network transport
- [FastTLS](https://github.com/andrestubbe/FastTLS) — Secure endpoint transport
- [FastWebSpider](https://github.com/andrestubbe/FastWebSpider) — Parallel crawling

---

**Part of the FastJava Ecosystem** — Making the JVM faster. Small package. Maximum speed.
