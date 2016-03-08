package com.gv.midway.service.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gv.midway.pojo.Track;

public interface JSONService {
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Track getTrackInJSON() ;
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Track createTrackInJSON(Track track);
}
