# FastDNS 0.1.0 [ALPHA-2026-09] — Ultra-Fast DNS Resolver for Java

[![Status](https://img.shields.io/badge/status-0.1.0-brightgreen.svg)](https://github.com/andrestubbe/FastDNS/releases/tag/0.1.0)
[![Java](https://img.shields.io/badge/Java-17+-blue.svg)](https://www.java.com)
[![Platform](https://img.shields.io/badge/Platform-Windows%2010+-lightgrey.svg)]()
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![JitPack](https://img.shields.io/badge/JitPack-ready-green.svg)](https://jitpack.io/#andrestubbe/FastDNS)

---

**⚡ Ultra-fast asynchronous name resolution for the FastJava ecosystem.**

**FastDNS** combines a stable asynchronous API with a local TTL cache for crawlers, service clients, telemetry gateways and socket pools. It uses the portable JDK resolver today while reserving a native Windows `DnsQueryEx`, DoH and DoT backend for high-concurrency workloads.

[**Run the DNS Cache Demo**](examples/Demo/src/main/java/fastdns/DnsCacheDemo.java) | [**Run the Resolver Benchmark**](examples/Benchmark/src/main/java/fastdns/ResolverBenchmark.java)

---

## Quick Start

```java
import fastdns.FastDNS;

public class Example {
	public static void main(String[] args) {
		FastDNS.resolve("example.com")
				.thenAccept(address -> System.out.println("Resolved: " + address));
	}
}
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

Synchronous hostname resolution becomes a bottleneck when crawlers, telemetry clients or socket pools open many endpoints:

- **Blocking lookups**: A resolver call can hold a worker while the operating system waits for a response.
- **Repeated work**: Stable service names are resolved again even when a recent answer is still valid.
- **Connection coupling**: Network code becomes harder to scale when resolution and socket setup share a blocking path.

**FastDNS** addresses this with a small resolver facade:

- **Asynchronous completion**: Resolution returns a `CompletableFuture` for natural fan-out.
- **TTL-aware cache**: Successful answers are reused until the caller-selected expiry time.
- **Native-ready providers**: `DnsQueryEx`, DoH and DoT can replace the fallback without changing callers.

---

## Features

- **⚡ Asynchronous resolution**: Resolve many service names without blocking crawler or client workers.
- **🗃️ TTL-aware cache**: Reuse successful answers with a caller-selected lifetime.
- **🧵 Thread-safe facade**: Concurrent lookups share a predictable cache contract.
- **🪟 Native-ready backend**: Designed for Windows `DnsQueryEx`, DoH and DoT integration.

---

## Real-World Scenarios

- **Web crawling:** Resolve thousands of crawler endpoints without blocking worker threads.
- **FastNet startup:** Resolve service endpoints before opening socket connections.
- **Microservice clients:** Reuse short-lived DNS answers during burst traffic.
- **Telemetry:** Avoid repeated resolver work for stable device gateways.

---

## Performance Benchmarks

FastDNS includes a cache demo and a repeated-lookup benchmark to expose resolver overhead before native `DnsQueryEx` integration.

| Metric / Resolution Type | Current Java Fallback | Native Target |
|-------------------------|----------------------|---------------|
| **Cached lookup** | Measured by benchmark | Atomic native cache |
| **Cold lookup** | JDK resolver | `DnsQueryEx` |
| **Secure lookup** | Provider-dependent | DoH / DoT |

*The benchmark measures repeated local lookups. Native latency and cache figures are reported only after the platform resolver backend is integrated.*

---

## API Quick Reference

| Method | Description |
|---|---|
| Method | Description |
|--------|-------------|
| `resolve(hostname)` | Resolves a hostname asynchronously using the default TTL. |
| `resolve(hostname, ttlMillis)` | Resolves and caches with a caller-selected TTL. |
| `clearCache()` | Removes all cached answers. |

---

## Technical Examples & Hero Demos

| Case | Java Example | Launcher | Description |
|---|---|---|---|
| **Crawler Endpoint Cache** | [DnsCacheDemo.java](examples/Demo/src/main/java/fastdns/DnsCacheDemo.java) | `run-demo.bat` | Compares the first lookup with a cached service endpoint lookup. |
| **Resolver Cache Throughput** | [ResolverBenchmark.java](examples/Benchmark/src/main/java/fastdns/ResolverBenchmark.java) | `run-benchmark.bat` | Measures repeated cached lookups for a high-volume client. |

---

## Installation

### Option 1: Maven (Recommended)

```xml
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
<dependencies>
	<dependency>
		<groupId>com.github.andrestubbe</groupId>
		<artifactId>FastDNS</artifactId>
		<version>0.1.0</version>
	</dependency>
</dependencies>
```

### Option 2: Gradle (via JitPack)

```groovy
repositories {
	maven { url 'https://jitpack.io' }
}

dependencies {
	implementation 'com.github.andrestubbe:FastDNS:0.1.0'
}
```

### Option 3: Direct Download (No Build Tool)

Download the latest FastDNS JAR from the [GitHub releases](https://github.com/andrestubbe/FastDNS/releases) page.

---

## Documentation

* **[COMPILE.md](docs/COMPILE.md)**: Full compilation guide and launcher instructions.
* **[REFERENCE.md](docs/REFERENCE.md)**: Resolver and cache contract.
* **[PHILOSOPHY.md](docs/PHILOSOPHY.md)**: Async and cache principles.
* **[ROADMAP.md](docs/ROADMAP.md)**: Native resolver milestones.
* **[CHANGELOG.md](docs/CHANGELOG.md)**: Version history.

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

**Part of the FastJava Ecosystem** — *Making the JVM faster. Small package. Maximum speed. Zero bloat. 🚀📋*
