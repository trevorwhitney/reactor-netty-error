package io.pivotal.reactor.netty.experiment

import io.netty.handler.codec.http.HttpHeaderNames
import reactor.core.publisher.Mono
import reactor.netty.http.server.HttpServer

class ReactorNetty {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            HttpServer
                    .create()
                    .port(8080)
                    .secure { sslProviderBuilder ->
                        sslProviderBuilder.sslContext(SslContext.buildSSLContext())
                    }.route { routes ->
                        routes.get("/") { _, res ->
                            res.status(200)
                                    .header(HttpHeaderNames.CONTENT_TYPE, "text/plain")
                                    .sendString(Mono.just("Hello world"))
                        }
                    }.bindNow().onDispose().block()
        }
    }
}


