package fastdns;

import fastdns.FastDNS;

public final class Demo {
    public static void main(String[] args) {
        FastDNS.resolve("example.com").thenAccept(address ->
                System.out.println("Crawler endpoint: " + address.getHostAddress())).join();
    }
}
