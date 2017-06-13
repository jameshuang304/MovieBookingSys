import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.mongodb.*;
import com.mongodb.client.*;
import com.sun.media.jfxmedia.control.VideoDataBuffer;

public class HallDB implements Database
{
	//private Map<String, String> HallMap;
	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document> bigRoom;
	private MongoCollection<Document> smallRoom;

	public enum HallType
	{
		BIG_HALL, SMALL_HALL
	}

	public HallDB()
	{
		// HallMap = new HashMap<String, String>();
		initialDB();
		// constructMap();
	}

	public void createHall(String movieId, String time, HallType hallType)
	{
		MongoCollection<Document> myCollection;
		switch (hallType)
		{
			case BIG_HALL:

				//database.createCollection(generateID(movieId, time));

				//System.out.println(generateID(movieId, time));
				myCollection = database.getCollection(generateID(movieId, time));
				System.out.println(myCollection);
				hallInit(myCollection, HallType.BIG_HALL);
				break;
	
			case SMALL_HALL:
				myCollection = database.getCollection(generateID(movieId, time));
				hallInit(myCollection, HallType.SMALL_HALL);
				break;
	
			default:
				break;
		}

	}

	@Override
	public Seat queryByID(String hallID)
	{

		return null;
	}

	public String generateID(String movieId, String time)
	{
		//may change in the future
		return movieId + time;
	}

	/**
	 * This method will construct Database properly!
	 * 
	 */
	private void initialDB()
	{
		this.mongoClient = new MongoClient();
		this.database = mongoClient.getDatabase("TicketSys");
		this.bigRoom = database.getCollection("big_room");
		this.smallRoom = database.getCollection("small_room");
	}
	
	private void hallInit(MongoCollection<Document> myCollection, HallType hallType)
	{
		FindIterable<Document> findIterable;
		MongoCursor<Document> mongoCursor;
		switch(hallType)
		{
			case BIG_HALL:
				findIterable = this.bigRoom.find();
				mongoCursor = findIterable.iterator();
				while (mongoCursor.hasNext()) {
					Document doc = mongoCursor.next();
					//System.out.println(doc);
					//myCollection.insertOne(doc);

					//System.out.println(doc.getString("row") + doc.getInteger("seatNum"));
				myCollection.insertOne(new Document("GG","VERY BIG"));
				}
				//myCollection.insertOne(new Document("GG","VERY BIG"));
				break;

			case SMALL_HALL:
				findIterable = this.smallRoom.find();
				mongoCursor = findIterable.iterator();
				while (mongoCursor.hasNext()) {
					//Document doc = mongoCursor.next();
					//myCollection.insertOne(doc);
					//myCollection.insertOne(new Document("GG","VERY BIG"));
				}
				break;

			default:
				break;

		}
	}


	public static void main(String[] args)
	{
		HallDB db = new HallDB();
		//db.createHall("asd", "9:30", HallType.SMALL_HALL);


		MongoCollection<Document> myCollection
				= db.database.getCollection(db.generateID("asd", "9:30"));
		myCollection.insertOne(new Document("GG","VERY BIG"));



	}


}
