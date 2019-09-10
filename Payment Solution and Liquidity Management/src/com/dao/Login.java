package com.dao;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.glassfish.jersey.server.ResourceConfig;
import org.json.simple.JSONObject;

@Path("/login")
public class Login {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/msg")
	public JSONObject authenticate() {
		JSONObject obj = new JSONObject();
		obj.put("name", "Krutika");
		obj.put("password", "krutika123");
		// int i=10;
//		ResourceConfig config = new ResourceConfig()
//		        .register(DebugMapper.class);
		return obj;

	}

	@GET
	@Path("/data")
	public String authenticate1() {
		return "hello";
	}

}

// class DebugMapper implements ExceptionMapper<Throwable> {
//    @Override
//    public Response toResponse(Throwable t) {
//        t.printStackTrace();
//        return Response.serverError()
//            .entity(t.getMessage())
//            .build();
//    }
//}
