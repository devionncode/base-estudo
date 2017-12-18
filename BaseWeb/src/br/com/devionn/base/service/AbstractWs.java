/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devionn.base.service;
 
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar; 
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter; 
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import br.com.devionn.base.adapter.HibernateProxyAdapter;
import br.com.devionn.das.AbstractBean;
import br.com.devionn.model.Usuario;
import br.com.devionn.model.generic.BaseEntity;


public abstract class AbstractWs<T extends BaseEntity> {

	private Gson gson;
	private final String FORMATO_DATA = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	private String[] dependeciasIgnorar = new String[0];
	public abstract AbstractBean<T> getDAS();
	
	
    public AbstractWs(){    	
    }
    
    public GsonBuilder getGsonBuilder(){
    	TypeAdapter<Calendar> calendarAdapter = new TypeAdapter<Calendar>() {

    		@Override
    		public void write(JsonWriter arg0, Calendar data) throws IOException {
    			if (data != null) {
    				SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DATA);
    				arg0.value(sdf.format(data.getTime()));
    			} else {
    				arg0.nullValue();
    			}
    		}

    		@Override
    		public Calendar read(JsonReader arg0) throws IOException {
    			String valueDate = arg0.nextString();
    			if (valueDate != null) {
    				try {
    					SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DATA);
    					Date data = sdf.parse(valueDate);
    					GregorianCalendar calendario = new GregorianCalendar();
    					calendario.setTime(data);
    					return calendario;
    				} catch (ParseException e) {
    					try {
    						SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DATA);
    						Date data = sdf.parse(valueDate);
    						GregorianCalendar calendario = new GregorianCalendar();
    						calendario.setTime(data);
    						return calendario;
    					}catch (Exception ex){
    						ex.printStackTrace();
    					}
    					e.printStackTrace();
    					return null;
    				}
    			}
    			return null;
    		}
    	};
    	TypeAdapter<Date> calendarDate = new TypeAdapter<Date>() {

    		@Override
    		public void write(JsonWriter arg0, Date data) throws IOException {
    			if (data != null) {
    				SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DATA);
    				arg0.value(sdf.format(data));
    			} else {
    				arg0.nullValue();
    			}
    		}

    		@Override
    		public Date read(JsonReader arg0) throws IOException {
    			String valueDate = arg0.nextString();
    			if (valueDate != null) {
    				try {
    					SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DATA);
    					Date data = sdf.parse(valueDate);
    					return data;
    				} catch (ParseException e) {
    					try {
    						SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DATA);
    						Date data = sdf.parse(valueDate);
    						return data;
    					}catch (Exception ex){
    						ex.printStackTrace();
    					}
    					e.printStackTrace();
    					return null;
    				}
    			}
    			return null;
    		}
    	};
    	GsonBuilder builder = new GsonBuilder();
    	builder.serializeNulls();
    	builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    	builder.registerTypeAdapter(GregorianCalendar.class, calendarAdapter);
    	builder.registerTypeAdapter(Calendar.class, calendarAdapter);
    	builder.registerTypeAdapter(Date.class, calendarDate);
    	builder.enableComplexMapKeySerialization();
    	builder.registerTypeAdapterFactory(HibernateProxyAdapter.FACTORY);
    	builder.setPrettyPrinting();
    	builder.setExclusionStrategies(new ExclusionStrategy() {
			public boolean shouldSkipField(FieldAttributes f) {
				for (String dep: dependeciasIgnorar) {
					if (f.getName().equals(dep)){
    					return true;
    				}
				}
				return false;
			}
			public boolean shouldSkipClass(Class<?> clazz) {
				return false;
			}
		});
    	
    	return builder;
    }
    
    public Gson getGson(){
    	return getGsonBuilder().create();
    }
    
    public String toJson(Object obj){
    	return getGson().toJson(obj);
    }
    
    public String getToken(HttpHeaders headers){
    	String token = headers.getHeaderString("authorization");
    	return token;
    }
    
    public Usuario getUsuario(HttpHeaders headers){
    	String token = headers.getHeaderString("authorization");    	
    	return getDAS().getUsuarioPorToken(token);
    }
    
    public Response sendError(String mensagem) {
    	return Response.status(Status.INTERNAL_SERVER_ERROR).entity(mensagem).build();
    }
    
    public Response sendOk(Object obj) {
    	return Response.ok(toJson(obj)).build();
    }
    
    public void ignorarDependecias(String[] dependencias) {
    	this.dependeciasIgnorar = dependencias;
    }
    
    @Path("salvar")
    @POST 
    @Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
    public Response save(@Context HttpHeaders headers,T entity) throws Exception { 
    	Response res = null;
    	try{
    		res = Response.ok(toJson(getDAS().save(getUsuario(headers), entity))).build();
        }catch (Exception e) {
        	e.getMessage();
        	res  = Response.serverError().entity(e.getLocalizedMessage()).build();
		}
        return res;
    }
    
    @POST 
    @Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
    public Response create(@Context HttpHeaders headers,T entity) throws Exception{
    	String json = toJson(getDAS().insert(getUsuario(headers),entity)); 
        return Response.ok(json).build();
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
    public Response edit(@Context HttpHeaders headers,@PathParam("id") Long id, T entity) throws Exception {
    	String json = toJson(getDAS().update(getUsuario(headers),entity)); 
        return Response.ok(json).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
    public Response remove(@Context HttpHeaders headers,@PathParam("id") Long id) throws Exception{
        getDAS().remove(getUsuario(headers),id);
        return Response.ok(true).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
    public Response find(@Context HttpHeaders headers,@PathParam("id") Long id) throws Exception{
    	String obj = toJson(getDAS().find(getUsuario(headers),id)); 
        return Response.ok(obj).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
    public Response findAll(@Context HttpHeaders headers) throws Exception {
    	String json = toJson(getDAS().findAll(getUsuario(headers))); 
        return Response.ok(json).build();
    }
}
