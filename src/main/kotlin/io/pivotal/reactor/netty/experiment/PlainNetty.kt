package io.pivotal.reactor.netty.experiment

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler

class PlainNetty {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val eventLoopGroup = NioEventLoopGroup()
            val sslContext = SslContext.buildSSLContext()

            val bootstrap: ServerBootstrap = ServerBootstrap()
                    .group(eventLoopGroup)
                    .handler(LoggingHandler(LogLevel.INFO))
                    .childHandler(HttpServerInitializer(sslContext))
                    .channel(NioServerSocketChannel::class.java)

            bootstrap.bind(8080)

            while(true) {}
        }
    }
}
