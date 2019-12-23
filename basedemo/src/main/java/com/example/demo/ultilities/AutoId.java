package com.example.demo.ultilities;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component

public class AutoId {


		@Autowired
	     MongoTemplate mongoTemplate;
	
		 private  void createCountersCollection(MongoCollection<Document> countersCollection,String name) {
			  
		        Document document = new Document();
		        document.append("_id", name);
		        document.append("seq", 1);
		        countersCollection.insertOne(document);
		    }
		
	  public  String getNextSequence(String name) {
	          
//	        Object result = MongoTemplate.class
	      MongoDatabase db=mongoTemplate.getDb();
	     MongoCollection<Document> collection= db.getCollection("auto_id");
//	        		findOneAndUpdate(searchQuery, updateQuery);
	     
	     if (collection.count() == 0) {
	            createCountersCollection(collection,name);
	        }
	        Document searchQuery1 = new Document("_id", name);
	        Document increase1 = new Document("seq", 1);
	        Document updateQuery1 = new Document("$inc", increase1);
	        Document result = collection.findOneAndUpdate(searchQuery1, updateQuery1);
	 
	        return (String)result.get("seq").toString();
	 
	    }
}
