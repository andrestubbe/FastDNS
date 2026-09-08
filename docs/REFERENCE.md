# FastDNS Reference

- `FastDNS.resolve(String)` returns a `CompletableFuture<InetAddress>`.
- `FastDNS.resolve(String, long)` applies a caller-provided cache TTL in milliseconds.
- `FastDNS.clearCache()` removes all cached results.

The current implementation uses the JDK resolver as a portable fallback. Native Windows `DnsQueryEx` support must preserve asynchronous completion and failure semantics.
