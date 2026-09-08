package fastdns;

public final class ResolverBenchmark {
    private ResolverBenchmark() {
    }

    public static void main(String[] args) {
        FastDNS.clearCache();
        long start = System.nanoTime();
        for (int index = 0; index < 1000; index++) {
            FastDNS.resolve("localhost").join();
        }
        long elapsed = System.nanoTime() - start;
        System.out.printf("lookups=1,000, elapsedMs=%.2f%n", elapsed / 1_000_000.0);
    }
}
