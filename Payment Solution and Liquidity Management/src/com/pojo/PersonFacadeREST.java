//package com.pojo;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
//import javax.ws.rs.GET;
//import javax.ws.rs.PUT;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.Status;
// 
////import com.genuitec.webclipse.example.EntityManagerHelper;
////import com.genuitec.webclipse.example.Person;
// 
///**
// * @author MyEclipse Web Service Tools
// */
//@javax.inject.Singleton
//@Path("Person")
//public class PersonFacadeREST extends AbstractFacade<Person> {
// 
//	private EntityManager em;
// 
//	public PersonFacadeREST() {
//		super(Person.class);
//	}	
// 
//	@PUT
//	@Override
//	@Consumes({ "application/xml", "application/json" })
//	public Response edit(Person entity) {
//		if (entity.getName().length() <= 3) {
//			return Response.status(Status.CONFLICT).entity("Customer name is too short").type(MediaType.TEXT_PLAIN).build();
//		}
//		return super.edit(entity);
//	}
// 
//	@DELETE
//	@Path("remove/{id}")
//	public Response remove(@PathParam("id") Integer id) {
//		return super.remove(super.find(id));
//	}
// 
//	@GET
//	@Path("{id}")
//	@Produces({ "application/json" })
//	public Person find(@PathParam("id") Integer id) {
//		return super.find(id);
//	}
// 
//	@GET
//	@Override
//	@Produces({ "application/json" })
//	public List<Person> findAll() {
//		return super.findAll();
//	}
// 
//	@GET
//	@Path("{from}/{to}")
//	@Produces({ "application/xml", "application/json" })
//	public List<Person> findRange(@PathParam("from") Integer from,
//			@PathParam("to") Integer to) {
//		return super.findRange(new int[] { from, to });
//	}
// 
//	@GET
//	@Path("count")
//	@Produces("text/plain")
//	public String countREST() {
//		return String.valueOf(super.count());
//	}
// 
//	@Override
//	protected EntityManager getEntityManager() {
//		em = EntityManagerHelper.getEntityManager();
//		return em;
//	}
//}
