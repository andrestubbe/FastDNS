package fastdns;

import java.net.InetAddress;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/** Asynchronous DNS facade with a small time-based result cache. */
public final class FastDNS {
    private static final long DEFAULT_TTL_MILLIS = 30_000L;
    private static final ConcurrentHashMap<String, CachedAddress> CACHE = new ConcurrentHashMap<>();

    private FastDNS() {
    }

    public static CompletableFuture<InetAddress> resolve(String hostname) {
        return resolve(hostname, DEFAULT_TTL_MILLIS);
    }

    public static CompletableFuture<InetAddress> resolve(String hostname, long ttlMillis) {
        Objects.requireNonNull(hostname, "hostname");
        if (ttlMillis < 0) {
            throw new IllegalArgumentException("ttlMillis must be non-negative");
        }
        long now = System.currentTimeMillis();
        CachedAddress cached = CACHE.get(hostname);
        if (cached != null && cached.expiresAtMillis > now) {
            return CompletableFuture.completedFuture(cached.address);
        }
        return CompletableFuture.supplyAsync(() -> {
            try {
                InetAddress address = InetAddress.getByName(hostname);
                CACHE.put(hostname, new CachedAddress(address, System.currentTimeMillis() + ttlMillis));
                return address;
            } catch (java.net.UnknownHostException exception) {
                throw new ResolveException(hostname, exception);
            }
        });
    }

    public static void clearCache() {
        CACHE.clear();
    }

    public static final class ResolveException extends RuntimeException {
        public ResolveException(String hostname, Throwable cause) {
            super("DNS resolution failed for " + hostname, cause);
        }
    }

    private record CachedAddress(InetAddress address, long expiresAtMillis) {
    }
}
