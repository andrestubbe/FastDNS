# FastDNS Philosophy

FastDNS treats hostname resolution as asynchronous infrastructure: cache successful answers, keep expiration visible and allow native resolver providers without coupling callers to Windows APIs.
