package fastdns.benchmark;

import fastdns.FastDNS;

public final class Benchmark {
    public static void main(String[] args) {
        FastDNS.clearCache();
        long start = System.nanoTime();
        for (int index = 0; index < 1000; index++) {
            FastDNS.resolve("localhost").join();
        }
        System.out.printf("1000 cached lookups in %.3f ms%n", (System.nanoTime() - start) / 1_000_000.0);
    }
}
