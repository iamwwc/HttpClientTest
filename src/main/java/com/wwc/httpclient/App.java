package com.wwc.httpclient;
import io.vertx.core.*;
/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {	
    	Vertx.vertx().deployVerticle(new HttpClientTest());
        System.out.println("i am client");
    }
}
