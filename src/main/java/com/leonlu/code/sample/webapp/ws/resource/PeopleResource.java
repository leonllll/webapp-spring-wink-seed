package com.leonlu.code.sample.webapp.ws.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leonlu.code.sample.webapp.ws.common.WebAppException;
import com.leonlu.code.sample.webapp.ws.common.WebAppResponse;
import com.leonlu.code.sample.webapp.ws.domain.People;
import com.leonlu.code.sample.webapp.ws.service.PeopleService;
import com.leonlu.code.sample.webapp.ws.util.JacksonUtil;

@Path("people")
@Component("PeopleResource")
public class PeopleResource {
	@Autowired
	private PeopleService peopleService;
	public static final Logger logger = LoggerFactory.getLogger(PeopleResource.class);
			
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAll() {
		return Response.ok(JacksonUtil.toJson(peopleService.getAll())).build();
	}
	
	@GET
	@Path("/{peopleId}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response get(@PathParam(value = "peopleId") Integer peopleId) {
		return Response.ok(JacksonUtil.toJson(peopleService.get(peopleId))).build();
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response add(String json, @Context UriInfo uriInfo) {
		logger.debug("add people: [" + json + ", url: " + uriInfo.getPath() + "]");
		People people;
		try {
			System.out.println(json);
			people = JacksonUtil.toBean(json, People.class);
			System.out.println(people.toString());
		} catch(RuntimeException e) {
			throw new WebAppException("invalid-json", 400);
		}
		if(people.getName() == null || people.getName().isEmpty()) {
			throw new WebAppException("invalid-parameter-not-null", 400);
		}
		if(people.getAge() == null) {
			throw new WebAppException("invalid-parameter-not-null", 400);
		}
		peopleService.insert(people);
		return Response.created(uriInfo.getRequestUri())
				.entity(new WebAppResponse().toString()).build();
	}
	
	@PUT
	@Path("/{peopleId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response update(String json, @PathParam(value = "peopleId") Integer peopleId, @Context UriInfo uriInfo) {
		logger.debug("update people: [" + json + ", url: " + uriInfo.getPath() + "]");
		People people;
		try {
			people = JacksonUtil.toBean(json, People.class);
		} catch(RuntimeException e) {
			throw new WebAppException("invalid-json", 400);
		}
		if(people.getName() == null && people.getAge() == null) {
			throw new WebAppException("invalid-parameter-not-null", 400);
		}
		people.setId(peopleId);
		People updated = peopleService.update(people);
		return Response.ok(JacksonUtil.toJson(updated)).build();
	}
	
	@DELETE
	@Path("/{peopleId}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response delete(@PathParam(value = "peopleId") Integer peopleId) {
		logger.debug("delete people: [ id: " + peopleId + "]");
		peopleService.delete(peopleId);
		return Response.ok(new WebAppResponse().toString()).build();
	}

}
