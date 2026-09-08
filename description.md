# FastDNS

Asynchroner, nativer DNS-Resolver mit lokalem Cache und minimalen Lookup-Zeiten.

---

## ⚡ Overview

**FastDNS** umgeht den synchronen, blockierenden Java-Standard-Resolver (`InetAddress.getByName`) und nutzt asynchrone Win32 DNS-APIs (`DnsQueryEx`) mit integriertem atomarem Cache.

---

## 🔑 Kernmerkmale

- **Asynchrones DnsQueryEx**: Parallele Auflösung tausender Hostnames ohne Thread-Exhaustion.
- **Zero-GC Cache**: In-Memory-Cache mit TTL-Handling und atomaren Hashmaps.
- **DoH / DoT Vorbereitung**: Native Erweiterbarkeit für DNS-over-HTTPS.

---

## 🏗️ Ecosystem Integration

- **FastNet**: Sofortige Auflösung von Endpunkten vor Socket-Verbindungen.
- **FastWebSpider / FastWebScrape**: Massiv paralleles Crawling ohne DNS-Flaschenhals.