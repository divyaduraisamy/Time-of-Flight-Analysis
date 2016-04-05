package mongo.mongo_java;


import java.io.FileWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bson.BasicBSONObject;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

/**
 * Java + MongoDB Hello world Example
 * 
 */
public class Calendar {

  public static void main(String[] args) throws IOException {

    try {

	/**** Connect to MongoDB ****/
	// Since 2.10.0, uses MongoClient
	MongoClient mongo = new MongoClient("localhost", 27017);

	/**** Get database ****/
	// if database doesn't exists, MongoDB will create it for you
	DB db = mongo.getDB("FlightsDB");

	/**** Get collection / table from 'testdb' ****/
	// if collection doesn't exists, MongoDB will create it for you
	
    DBCollection maintable=db.getCollection("flights_original");

    DBCollection table=db.getCollection("arrivalcalendar");
	/**** Insert ****/

	/**** Find and display ****/
    FileWriter x= new FileWriter("arrivalCalendar.csv");
	DBCursor cursor = table.find();
	Map <String,String> month=new HashMap<String,String>();
	month.put("Jan","01");
	month.put("Feb","02");
	month.put("Mar","03");
	month.put("Apr","04");
	month.put("May","05");
	month.put("Jun","06");
	month.put("Jul","07");
	month.put("Aug","08");
	month.put("Sep","09");
	month.put("Oct","10");
	month.put("Nov","11");
	month.put("Dec","12");
	while (cursor.hasNext()) {
		DBObject obj = cursor.next();
		System.out.println(obj);
		Date orig = ((Date) obj.get("_id"));
		Object delay = ((Double) obj.get("delay"));
		
		String date=orig.toString();
		
		String[] parts=date.split(" ");
		String p=parts[5]+"-"+month.get(parts[1])+"-"+parts[2];
		x.write(p);
		x.write(",");
		x.write(delay.toString());
		x.write("\n");
		

		
		/*Object dest = ((DBObject)obj.get("_id")).get("dest");
		Map <String,Object> outs=new HashMap<String,Object>();
		Map<String,Object >each = new HashMap<String, Object>();
	    JSONObject outer=new JSONObject();
	    JSONObject inner=new JSONObject();
	    outs.put("totalouts", 0.0);
	    
	    each.put(dest.toString(),0.0);
	    outs.put("toeach", each);
	    
	    outer.put("details", outs);
	   // System.out.println(outer.toString());
	    origsout.put((Integer) orig, outer);
	    for(Object objname:origsout.keySet()) {
	    	 // System.out.println(objname);
	    	 
	    	  JSONObject val=(JSONObject)origsout.get(objname);
	    	  // System.out.println(origsout.get(objname));
	    	  //System.out.println(val.get("details"));
	    	   JSONObject det=((JSONObject) val.get("details"));
	    	   JSONObject x=(JSONObject)det .get("toeach");
	    	   Double y=(Double)det.get("totalouts");
	    	   
	    	   
	    	   Double depno= (Double) x.get((String) (x.keySet().iterator().next()));
	    	   
	    	   depno+=(Double)obj.get("flew");
	    	   y+=(Double)obj.get("flew");
	    	   x.put(dest.toString(), depno);
	    	   det.put("totalouts", y);
	    	   
	    	   System.out.println(origsout.get(objname));
	    }
	}
	    
	      //get the origAirportID
	      ArrayList origs = (ArrayList) maintable.distinct("origAirportId");
	      ArrayList dests= (ArrayList) maintable.distinct("destAirportId");
	      
	    Set<Integer> unionairport=new HashSet<Integer>();
	    unionairport.addAll(origs);
	    unionairport.addAll(dests);
	   // System.out.println(unionairport.toString());
	    float totalNodes=(unionairport.size()); 
	    float st=1/totalNodes;
	    
	    //for each distinct airport
	   Set<Integer> airid=origsout.keySet();
	   ArrayList<Integer> x=new ArrayList<Integer>(airid);
	    for (int airportId : unionairport)
	    {
	    	if (x.contains(airportId))
	    	{
	    		JSONObject val=(origsout.get(airportId));
	    		Double totalcounts=(Double) ((JSONObject) val.get("details")).get("totalouts");
	    		System.out.println(totalcounts);
	    		JSONObject toteach=(JSONObject) ((JSONObject) val.get("details")).get("toeach");
	    		String eachdest=toteach.toString();
	    		Map<String, Object> retMap = new Gson().fromJson(eachdest, new TypeToken<HashMap<String, Object>>() {}.getType());
	    		String doc="{'_id':"+ Integer.toString(airportId) +", 'value' : {'totalNodes' :"+ Double.toString(totalNodes)+", 'pg' :"+ Double.toString(st)+", 'prs' : {}}}";
	    		JSONObject k=new JSONObject(doc);
	    		
	    		JSONObject value=(JSONObject) ((JSONObject) k.get("value")).get("prs");
	    		for (String key:retMap.keySet())
	    		{
	    			value.put(key, ((Double)retMap.get(key))/(totalcounts));
	    		}
	    		DBObject dbo=(DBObject) JSON.parse(k.toString());
	    		
	    	}*/
	    }
	    x.close();
		
		
		
	

	/**** Update ****/
	// search document where name="mkyong" and update it with new values
/*	BasicDBObject query = new BasicDBObject();
	query.put("name", "mkyong");

	BasicDBObject newDocument = new BasicDBObject();
	newDocument.put("name", "mkyong-updated");

	BasicDBObject updateObj = new BasicDBObject();
	updateObj.put("$set", newDocument);

	table.update(query, updateObj);*/

	/**** Find and display ****/
	/*BasicDBObject searchQuery2 
	    = new BasicDBObject().append("name", "mkyong-updated");

	DBCursor cursor2 = table.find(searchQuery2);

	while (cursor2.hasNext()) {
		System.out.println(cursor2.next());
	}
*/
	/**** Done ****/
	

    } catch (UnknownHostException e) {
	e.printStackTrace();
    } catch (MongoException e) {
	e.printStackTrace();
    }

  }
}