package com.diwa.serverTest;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;

/**
 * Created by di on 11/5/15.
 */
public class TestMongodb {
    public static void main(String[] args) throws ParseException {
        MongoClient mongoClient = new MongoClient("localhost");
        MongoDatabase db = mongoClient.getDatabase("test");
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss;Z;", Locale.ENGLISH);

        db.getCollection("test").insertOne(
                new Document("testUser", new Document().append("name", "diwa")
                        .append("age", 20)
                        .append("addTime", new Date().toString()
                        )
                )

        );
    }
}
