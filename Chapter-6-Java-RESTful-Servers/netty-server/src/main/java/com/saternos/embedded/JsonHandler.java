package com.saternos.embedded;

import io.netty.channel.*;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import java.util.*;
import java.text.SimpleDateFormat;

public class JsonHandler extends ChannelInboundMessageHandlerAdapter<HttpRequest> {

    public void messageReceived(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) throws Exception {
		String json = "{\"testResponse\":\"Hello World\"}";
        HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.setHeader(HttpHeaders.Names.CONTENT_TYPE, "application/json;charset=utf-8");
        response.setHeader(HttpHeaders.Names.CONTENT_LENGTH, json.length());

		SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
		dateFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		Calendar time = new GregorianCalendar();

        response.setHeader(HttpHeaders.Names.DATE, dateFormatter.format(time.getTime()));
		response.setHeader(HttpHeaders.Names.SERVER, TestNettyHttpServer.class.getName()+":io.netty:netty:4.0.0.Alpha8");


        StringBuffer buf = new StringBuffer();
        buf.append(json);
        response.setContent(Unpooled.copiedBuffer(buf, CharsetUtil.UTF_8));
        channelHandlerContext.write(response).addListener(ChannelFutureListener.CLOSE);
    }
}