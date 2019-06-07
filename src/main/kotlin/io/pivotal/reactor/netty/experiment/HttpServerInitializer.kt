package io.pivotal.reactor.netty.experiment

import io.netty.channel.Channel
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInitializer
import io.netty.handler.codec.http.HttpServerCodec
import io.netty.handler.ssl.SslContext

class HttpServerInitializer(
        private val sslContext: SslContext
) : ChannelInitializer<Channel>() {
    override fun initChannel(ch: Channel) {
        val pipeline = ch.pipeline()

        pipeline.addLast(
                sslContext.newHandler(ch.alloc()),
                HttpServerCodec(),
                HttpServerHandler()
        )
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        cause?.printStackTrace()
        ctx?.close()
    }
}
