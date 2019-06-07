package io.pivotal.reactor.netty.experiment

import io.netty.handler.ssl.ClientAuth
import io.netty.handler.ssl.SslContext
import io.netty.handler.ssl.SslContextBuilder
import java.io.InputStream

class SslContext {
    companion object {
        @JvmStatic
        fun buildSSLContext(): SslContext {
            return SslContextBuilder
                    .forServer(readResource("certs/pasexporter.crt"), readResource("certs/pasexporter-pkcs8.key"))
                    .clientAuth(ClientAuth.REQUIRE)
                    .trustManager(readResource("certs/exporterCA.crt"))
                    .startTls(true)
                    .build()
        }

        @JvmStatic
        private fun readResource(path: String): InputStream {
            return this::class.java.classLoader.getResourceAsStream(path)
        }
    }
}


