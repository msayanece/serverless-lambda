package com.sayan.aws;

import java.util.Collections;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sayan.aws.models.DummyPojo;
import com.sayan.aws.models.GatewayResponse;

public class AwsHandler implements RequestHandler<Object, GatewayResponse> {

	@Override
	public GatewayResponse handleRequest(Object input, Context context) {
		DummyPojo dummyPojo = new DummyPojo("dummy prod name", "dummy description");
		String body = "";
		try {
			body = new ObjectMapper().writeValueAsString(dummyPojo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		Map<String, String> headers = Collections.singletonMap("X-Powered-By", "Sayan");
		return new GatewayResponse(body, 200, headers, false);
	}

}
