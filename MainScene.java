import java.util.ArrayList;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
public class MainScene implements EventHandler<ActionEvent>
{
	Object list[];    
	public void setList(Object oList[])
	{
    	list = oList;
	}
	// Event Handler Method
	public void handle(ActionEvent event)
	{
    	String source = ((Button)event.getSource()).getText();
    	if (source.contentEquals("Close"))
        	Platform.exit();
    	else
    	{
   		 ArrayList<Location> ret = new ArrayList<Location>();
        	TextArea myLatitude = (TextArea) list[0];
        	TextArea myLongitude = (TextArea) list[1];  	 
   		    ComboBox categories = (ComboBox) list[2];
        	TextArea myName = (TextArea) list[3];     
        	String category = (String)categories.getSelectionModel().getSelectedItem();
        	String myNameString = myName.getText();
        	Double doubleLat = Double.parseDouble(myLatitude.getText());
        	Double doubleLong = Double.parseDouble(myLongitude.getText());
   		 	Database myDatabase = new Database(doubleLong, doubleLat);
       	 
        	TextArea loc1 = (TextArea) list[4];
        	loc1.setEditable(false);
        	TextArea loc2 = (TextArea) list[5];
        	loc2.setEditable(false);
        	TextArea loc3 = (TextArea) list[6];
        	loc3.setEditable(false);
        	TextArea loc4 = (TextArea) list[7];
        	loc4.setEditable(false);
        	TextArea loc5 = (TextArea) list[8];
        	loc5.setEditable(false);
        	TextArea loc6 = (TextArea) list[9];
        	loc6.setEditable(false);
        	TextArea loc7 = (TextArea) list[10];
        	loc7.setEditable(false);
        	TextArea loc8 = (TextArea) list[11];
        	loc8.setEditable(false);
       	 
        	if (!category.equals("select"))
        	{
       		 ret = myDatabase.findBestByCategory(category, 8);
        	}
        	else
        	{
       		 ret = myDatabase.findBestByName(myNameString);
        	}    	 

        	loc1.setText(ret.get(0).toString());
        	loc2.setText(ret.get(1).toString());
        	loc3.setText(ret.get(2).toString());
        	loc4.setText(ret.get(3).toString());
        	loc5.setText(ret.get(4).toString());
        	loc6.setText(ret.get(5).toString());
        	loc7.setText(ret.get(6).toString());
        	loc8.setText(ret.get(7).toString());
       	 
        	if(event.getSource() == App.getView().getLike1())
   		 {
       		 myDatabase.likeLocation(ret.get(0));
   		 }
        	if(event.getSource() == App.getView().getDislike1())
   		 {
       		 myDatabase.dislikeLocation(ret.get(0));
   		 }
        	if(event.getSource() == App.getView().getLike2())
   		 {
       		 myDatabase.likeLocation(ret.get(1));
   		 }
        	if(event.getSource() == App.getView().getDislike2())
   		 {
       		 myDatabase.dislikeLocation(ret.get(1));
   		 }
        	if(event.getSource() == App.getView().getLike3())
   		 {
       		 myDatabase.likeLocation(ret.get(2));
   		 }
        	if(event.getSource() == App.getView().getDislike3())
   		 {
       		 myDatabase.dislikeLocation(ret.get(2));
   		 }
        	if(event.getSource() == App.getView().getLike4())
   		 {
       		 myDatabase.likeLocation(ret.get(3));
   		 }
        	if(event.getSource() == App.getView().getDislike4())
   		 {
       		 myDatabase.dislikeLocation(ret.get(3));
   		 }
        	if(event.getSource() == App.getView().getLike5())
   		 {
       		 myDatabase.likeLocation(ret.get(4));
   		 }
        	if(event.getSource() == App.getView().getDislike5())
   		 {
       		 myDatabase.dislikeLocation(ret.get(4));
   		 }
        	if(event.getSource() == App.getView().getLike6())
   		 {
       		 myDatabase.likeLocation(ret.get(5));
   		 }
        	if(event.getSource() == App.getView().getDislike6())
   		 {
       		 myDatabase.dislikeLocation(ret.get(5));
   		 }
        	if(event.getSource() == App.getView().getLike7())
   		 {
       		 myDatabase.likeLocation(ret.get(6));
   		 }
        	if(event.getSource() == App.getView().getDislike7())
   		 {
       		 myDatabase.dislikeLocation(ret.get(6));
   		 }
        	if(event.getSource() == App.getView().getLike8())
   		 {
       		 myDatabase.likeLocation(ret.get(7));
   		 }
        	if(event.getSource() == App.getView().getDislike8())
   		 {
       		 myDatabase.dislikeLocation(ret.get(7));
   		 }
    	}
	}
}
