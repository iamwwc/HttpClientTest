package com.wwc.httpclient;
import org.apache.qpid.proton.message.Message;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.proton.ProtonClient;
import io.vertx.proton.ProtonConnection;
import io.vertx.proton.ProtonHelper;
import io.vertx.proton.ProtonSender;
public class HttpClientTest extends AbstractVerticle{
	private ProtonClient client;
	@Override
	public void start(Future<Void> startFuture)	{
		client = ProtonClient.create(vertx);
		client.connect( "127.0.0.1",9999,ar->{
			if(ar.succeeded()) {
				ProtonConnection connection = ar.result();
				connection.open();
				connection.createSender("Quene").openHandler(openResult->{
					if(openResult.succeeded()) {
						ProtonSender sender = openResult.result();
						Message message = ProtonHelper.message();
						message.setAddress("MyTopic");
						sender.send(message,delivery->{
							System.out.println("Message send! local state: " 
									+ delivery.getLocalState()
									+ " remote state"
									+delivery.getRemoteState()
									+ " remote settled " + delivery.remotelySettled());							
						});
					}
				}).open();
			}
		});
	}
}
