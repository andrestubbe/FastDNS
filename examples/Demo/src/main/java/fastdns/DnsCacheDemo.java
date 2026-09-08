package fastdns;

public final class DnsCacheDemo {
    private DnsCacheDemo() {
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        String first = FastDNS.resolve("localhost").join().getHostAddress();
        long firstNanos = System.nanoTime() - start;
        start = System.nanoTime();
        String second = FastDNS.resolve("localhost").join().getHostAddress();
        long secondNanos = System.nanoTime() - start;
        System.out.printf("first=%s (%dns), cached=%s (%dns)%n", first, firstNanos, second, secondNanos);
    }
}
