package fastdns;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.InetAddress;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class FastDNSTest {
    @AfterEach
    void clearCache() {
        FastDNS.clearCache();
    }

    @Test
    void resolvesLoopbackAsynchronously() {
        InetAddress address = FastDNS.resolve("localhost").join();
        assertEquals("localhost", address.getHostName());
    }

    @Test
    void rejectsNegativeTtl() {
        assertThrows(IllegalArgumentException.class, () -> FastDNS.resolve("localhost", -1));
    }
}
