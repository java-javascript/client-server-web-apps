package com.saternos.embedded;

import io.netty.channel.*;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class JsonHandler extends ChannelInboundMessageHandlerAdapter<HttpRequest> {

    public void messageReceived(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) throws Exception {
        HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.setHeader(HttpHeaders.Names.CONTENT_TYPE, "application/json;charset=utf-8");
        StringBuffer buf = new StringBuffer();
        buf.append("{\"testResponse\":\"Hello World\"}");
        response.setContent(Unpooled.copiedBuffer(buf, CharsetUtil.UTF_8));
        channelHandlerContext.write(response).addListener(ChannelFutureListener.CLOSE);
    }
}