package com.saternos.embedded;

import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import java.text.SimpleDateFormat;
import io.netty.channel.*;
import java.util.*;
import io.netty.handler.codec.http.*;

public class JsonHandler extends ChannelInboundMessageHandlerAdapter<HttpRequest> {

    public void messageReceived(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) throws Exception {
        StringBuffer buf = new StringBuffer();
        buf.append("{\"testResponse\":\"Hello World\"}");

		SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
		dateFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		Calendar time = new GregorianCalendar();

        HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.setHeader(HttpHeaders.Names.CONTENT_TYPE, "application/json;charset=utf-8");
        response.setHeader(HttpHeaders.Names.CONTENT_LENGTH, buf.length());
        response.setHeader(HttpHeaders.Names.DATE, dateFormatter.format(time.getTime()));
		response.setHeader(HttpHeaders.Names.SERVER, TestNettyHttpServer.class.getName() + ":io.netty:netty:4.0.0.Alpha8");
        response.setContent(Unpooled.copiedBuffer(buf, CharsetUtil.UTF_8));
        channelHandlerContext.write(response).addListener(ChannelFutureListener.CLOSE);
    }
}