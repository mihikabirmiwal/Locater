import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Scanner;
public class Database
{
    public Database(double perLong, double perLat)
    {
   	 super();
   	 stock(perLong, perLat);
    }
    double perLat;
    double perLong;
    String name;
    String category;
    static HashMap<String, ArrayList<Location>> byName = new HashMap();
    static HashMap<Location, Double> byDistance = new HashMap();
    static TreeMap<Double, Location> byDistanceTree = new TreeMap();
    static HashMap<Location, Integer> byPreference = new HashMap();
    static HashMap<String, ArrayList<Location>> byCategory = new HashMap();
    public void stock(double perLong, double perLat)
    {
   	 //FILLING UP DATABASES
   	 Scanner scan = new Scanner(this.getClass().getResourceAsStream("locations.txt"));
   	 while(scan.hasNextLine())
   	 {
   		 String[] myArray = scan.nextLine().split("\t");
   		 String myName = myArray[0];
   		 String myStreetAddress = myArray[1];
   		 String myCategory = myArray[2];
   		 double myLong = Double.parseDouble(myArray[3]);
   		 double myLat = Double.parseDouble(myArray[4]);
   		 Location myLocation = new Location(myName, myStreetAddress, myCategory, myLong, myLat);
   		 //ADD TO byName
   		 if(!byName.containsKey(myLocation.getName()))
   		 {
   			 ArrayList<Location> addThis = new ArrayList<Location>();
   			 addThis.add(myLocation);
   			 byName.put(myName, addThis);
   		 }
   		 else
   		 {
   			 ArrayList<Location> update = byName.get(myName);
   			 update.add(myLocation);
   			 byName.put(myName, update);
   		 }
   		 //ADD TO byDistance
   		 double dist = calcDistance(perLat, perLong, myLat, myLong);
   		 byDistance.put(myLocation, dist);
   		 //ADD TO byDistanceTree
   		 byDistanceTree.put(dist, myLocation);
   		 //ADD TO byPreference
   		 byPreference.put(myLocation, 0);
   		 //ADD TO byCategory
   		 if(byCategory.containsKey(myCategory))
   		 {
   			 ArrayList<Location> update = byCategory.get(myCategory);
   			 update.add(myLocation);
   			 byCategory.put(myCategory, update);
   		 }
   		 else
   		 {   	 
   			 ArrayList<Location> addThis = new ArrayList<Location>();
   			 addThis.add(myLocation);
   			 byCategory.put(myCategory, addThis);
   		 }
   	 }
   	 scan.close();
    }
    public double calcDistance(double lat1, double long1, double lat2, double long2)
    {
   	 double ret = Math.pow((Math.pow((lat1-lat2), 2.0) + Math.pow((long1-long2), 2.0)), .5);
   	 return ret;
    }
    public void likeLocation(Location l)
    {
   	 byPreference.put(l, 1);
    }
    public void dislikeLocation(Location l)
    {
   	 byPreference.put(l, -1);
    }
    public ArrayList<Location> findBestByCategory(String cat, int entries)
    {
   	 ArrayList<Location> ret = new ArrayList<Location>();
   	 
   	 if(entries==0)
   	 {
   		 return ret;
   	 }   	 
   	 ArrayList<Location> total = byCategory.get(cat);
   	 ArrayList<Location> liked = new ArrayList<Location>();
   	 ArrayList<Location> neutral = new ArrayList<Location>();
   	 ArrayList<Location> disliked = new ArrayList<Location>();   	 
   	 for(int i=0;i<total.size();i++)
   	 {
   		 if(byPreference.get(total.get(i)) == 1)
   		 {
   			 liked.add(total.get(i));
   		 }
   		 if(byPreference.get(total.get(i)) == 0)
   		 {
   			 neutral.add(total.get(i));
   		 }    
   		 else
   		 {
   			 disliked.add(total.get(i));
   		 }
   	 }
   	 //populating the treemaps
   	 TreeMap<Double, Location> likedDistances = new TreeMap<>();
   	 TreeMap<Double, Location> neutralDistances = new TreeMap<>();
   	 TreeMap<Double, Location> dislikedDistances = new TreeMap<>();
   	 for(int j=0;j<liked.size();j++)
   	 {
   		 Location lloc = liked.get(j);
   		 double dist = byDistance.get(lloc);
   		 likedDistances.put(dist, lloc);
   	 }
   	 for(int j=0;j<neutral.size();j++)
   	 {
   		 Location nloc = neutral.get(j);
   		 double dist = byDistance.get(nloc);
   		 neutralDistances.put(dist, nloc);
   	 }
   	 for(int j=0;j<disliked.size();j++)
   	 {
   		 Location dloc = disliked.get(j);
   		 double dist = byDistance.get(dloc);
   		 dislikedDistances.put(dist, dloc);
   	 }
   	 if (likedDistances.size()>=entries && entries > 0)    //all liked
   	 {
   		 ret.add(likedDistances.get(likedDistances.firstKey()));
   		 for(int i=1;i<entries;i++)
   		 {
   			 Location toAdd = likedDistances.entrySet().stream()
   						.skip(i)
   						.map(map -> map.getValue()).findFirst().get();
   			 ret.add(toAdd);
   		 }
   		 return ret;
   	 }
   	 else if ((likedDistances.size()+neutralDistances.size())>=entries)    //all liked, some neutral
   	 {
   		 System.out.println(likedDistances.size()+neutralDistances.size());
   		 int numLiked = likedDistances.size();
   		 int numToGetFromNeutral = entries-numLiked;
   		 if(numLiked!=0)
   		 {
   			 ret.add(likedDistances.get(likedDistances.firstKey()));
   			 for(int i=1;i<likedDistances.size();i++)
   			 {
   				 Location toAdd = likedDistances.entrySet().stream()
   							.skip(i)
   							.map(map -> map.getValue()).findFirst().get();
   				 ret.add(toAdd);
   			 }
   		 }
   		 ret.add(neutralDistances.get(neutralDistances.firstKey()));
   		 for(int i=1;i<numToGetFromNeutral;i++)
   		 {
   			 Location toAdd = neutralDistances.entrySet().stream()
   						.skip(i)
   						.map(map -> map.getValue()).findFirst().get();
   			 ret.add(toAdd);
   		 }
   		 return ret;
   	 }
   	 else if ((likedDistances.size()+neutralDistances.size()+dislikedDistances.size())>=entries)    //all liked, all neutral, some disliked
   	 {
   		 int numLiked = likedDistances.size();
   		 int numNeutral = neutralDistances.size();
   		 int numToGetFromDisliked = entries-numLiked-numNeutral;
   		 if(numLiked!=0)
   		 {
   			 ret.add(likedDistances.get(likedDistances.firstKey()));
   			 for(int i=1;i<likedDistances.size();i++)
   			 {
   				 Location toAdd = likedDistances.entrySet().stream()
   							.skip(i)
   							.map(map -> map.getValue()).findFirst().get();
   				 ret.add(toAdd);
   			 }
   		 }
   		 if(numNeutral!=0)
   		 {
   			 ret.add(neutralDistances.get(neutralDistances.firstKey()));
   			 for(int i=1;i<neutralDistances.size();i++)
   			 {
   				 Location toAdd = neutralDistances.entrySet().stream()
   							.skip(i)
   							.map(map -> map.getValue()).findFirst().get();
   				 ret.add(toAdd);
   			 }
   		 }
   		 ret.add(dislikedDistances.get(dislikedDistances.firstKey()));
   		 for(int i=1;i<numToGetFromDisliked;i++)
   		 {
   			 Location toAdd = dislikedDistances.entrySet().stream()
   						.skip(i)
   						.map(map -> map.getValue()).findFirst().get();
   			 ret.add(toAdd);
   		 }
   		 return ret;
   	 }
   	 else    //all liked, all neutral, all disliked, rest random
   	 {
   		 int numLiked = likedDistances.size();
   		 int numNeutral = neutralDistances.size();
   		 int numDisliked = dislikedDistances.size();
   		 int numToGetRandom = entries-numLiked-numNeutral-numDisliked;
   		 if(numLiked!=0)
   		 {
   			 ret.add(likedDistances.get(likedDistances.firstKey()));
   			 for(int i=1;i<likedDistances.size();i++)
   			 {
   				 Location toAdd = likedDistances.entrySet().stream()
   							.skip(i)
   							.map(map -> map.getValue()).findFirst().get();
   				 ret.add(toAdd);
   			 }
   		 }
   		 if(numNeutral!=0)
   		 {
   			 ret.add(neutralDistances.get(neutralDistances.firstKey()));
   			 for(int i=1;i<neutralDistances.size();i++)
   			 {
   				 Location toAdd = neutralDistances.entrySet().stream()
   							.skip(i)
   							.map(map -> map.getValue()).findFirst().get();
   				 ret.add(toAdd);
   			 }
   		 }
   		 if(numDisliked!=0)
   		 {
   			 ret.add(dislikedDistances.get(dislikedDistances.firstKey()));
   			 for(int i=0;i<dislikedDistances.size();i++)
   			 {
   				 Location toAdd = dislikedDistances.entrySet().stream()
   							.skip(i)
   							.map(map -> map.getValue()).findFirst().get();
   				 ret.add(toAdd);
   			 }
   		 }
   		 Location should = byDistanceTree.get(byDistanceTree.firstKey());
   		 if(!ret.contains(should))
   		 {
   			 ret.add(should);
   		 }
   		 for(int i=1;i<numToGetRandom;i++)
   		 {
   			 Location toAdd = byDistanceTree.entrySet().stream()
   						.skip(i)
   						.map(map -> map.getValue()).findFirst().get();
   			 if(!ret.contains(toAdd))
   			 {
   				 ret.add(toAdd);
   			 }
   		 }
   		 int thisShouldBeGood = ret.size();
   		 int need = entries-thisShouldBeGood;
   		 for(int i=1;i<(numToGetRandom+need);i++)
   		 {
   			 Location toAdd = byDistanceTree.entrySet().stream()
   						.skip(i)
   						.map(map -> map.getValue()).findFirst().get();
   			 if(!ret.contains(toAdd))
   			 {
   				 ret.add(toAdd);
   			 }
   		 }
   		 return ret;
   	 }   	 
    }
    public ArrayList<Location> findBestByName(String n)
    {
   	 ArrayList<Location> ret = new ArrayList<Location>();
   	 if(byName.keySet().contains(n))
   	 {
   		 ArrayList<Location> locations = byName.get(n);
   		 TreeMap<Double, Location> distances = new TreeMap();
   		 for(int i=0;i<locations.size();i++)
   		 {
   			 double dist = byDistance.get(locations.get(i));
   			 distances.put(dist, locations.get(i));
   		 }
   		 ret.add(distances.get(distances.firstKey()));    //adding all the named locations
   		 for(int i=1;i<distances.size() && i < 8; i++)
   		 {
   			 Location toAdd = distances.entrySet().stream()
   						.skip(i)
   						.map(map -> map.getValue()).findFirst().get();
   			 ret.add(toAdd);
   		 }
   		 int numToGetFromCategory = 8-ret.size();
   		 String cat = locations.get(0).getCategory();
   		 ArrayList<Location> canAdd = findBestByCategory(cat, numToGetFromCategory);
   		 for(int i=0;i<canAdd.size();i++)
   		 {
   			 if(!ret.contains(canAdd.get(i)))
   			 {
   				 ret.add(canAdd.get(i));
   			 }
   		 }
   		 int thisShouldBeEightUnlessTheLocationsMatchUp = ret.size();
   		 if(thisShouldBeEightUnlessTheLocationsMatchUp !=8)
   		 {
   			 int howManyMore = 8-thisShouldBeEightUnlessTheLocationsMatchUp;
   			 ArrayList<Location> lastTimeDoingThis = findBestByCategory(cat, numToGetFromCategory+howManyMore);
   			 for(int i=0;i<lastTimeDoingThis.size();i++)
   			 {
   				 if(!ret.contains(lastTimeDoingThis.get(i)))
   				 {
   					 ret.add(lastTimeDoingThis.get(i));
   				 }
   			 }
   		 }
   		 return ret;
   	 }
   	 else
   	 {
   		 return eightClosest();
   	 }
    }
    public ArrayList<Location> eightClosest()
    {
   	 ArrayList<Location> ret = new ArrayList<Location>();
   	 ret.add(byDistanceTree.get(byDistanceTree.firstKey()));
   	 for(int i=1;i<8;i++)
   	 {
   		 Location toAdd = byDistanceTree.entrySet().stream()
   					.skip(i)
   					.map(map -> map.getValue()).findFirst().get();
   		 ret.add(toAdd);   	 
   	 }
   	 return ret;
    }
}