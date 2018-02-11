package cloud.timo.TimoCloud.cord.sockets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProxyUpstreamHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private Channel channel;
    private ProxyDownstreamHandler downstreamHandler;

    public ProxyUpstreamHandler(Channel channel, ProxyDownstreamHandler downstreamHandler) {
        this.channel = channel;
        this.downstreamHandler = downstreamHandler;
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf buf) throws Exception {
        ByteBuf sendBuf = Unpooled.copiedBuffer(buf);
        getChannel().writeAndFlush(sendBuf);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        getDownstreamHandler().getChannel().close();
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public ProxyDownstreamHandler getDownstreamHandler() {
        return downstreamHandler;
    }
}
