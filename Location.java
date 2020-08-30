public class Location
{    
    private String name;
    private String streetAddress;
    private String category;
    private double longitude;
    private double latitude;
    
    public Location(String n, String s, String c, double lo, double la)
    {
   	 name = n;
   	 streetAddress = s;
   	 category = c;
   	 longitude = lo;
   	 latitude = la;
    }
    
    public String getName() {
   	 return name;
    }
    public void setName(String name) {
   	 this.name = name;
    }
    public String getStreetAddress() {
   	 return streetAddress;
    }
    public void setStreetAddress(String streetAddress) {
   	 this.streetAddress = streetAddress;
    }
    public String getCategory() {
   	 return category;
    }
    public void setCategory(String category) {
   	 this.category = category;
    }
    public double getLongitude() {
   	 return longitude;
    }
    public void setLongitude(double longitude) {
   	 this.longitude = longitude;
    }
    public double getLatitude() {
   	 return latitude;
    }
    public void setLatitude(double latitude) {
   	 this.latitude = latitude;
    }

    public String toString()
    {
   	 return name + "\n" + streetAddress + "\n" + category + "\n" + longitude + "\n" + latitude + "\n";
    }    
}

