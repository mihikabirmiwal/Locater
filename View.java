import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.Arrays;

public class View
{
    private ComboBox categories;
	private Button locateButton;
	private Stage myStage;
	private TextArea myLatitude;
	private TextArea myLongitude;
	private TextArea myName;
	private TextArea yourLocationDescription;
	private TextArea enterYourC;
	private Button exit;
	final int FONT = 13;
	private TextArea loc1;
	private TextArea loc2;
	private TextArea loc3;
	private TextArea loc4;
	private TextArea loc5;
	private TextArea loc6;
	private TextArea loc7;
	private TextArea loc8;
	private Button like1;
	private Button dislike1;
	private Button like2;
	private Button dislike2;
	private Button like3;
	private Button dislike3;
	private Button like4;
	private Button dislike4;
	private Button like5;
	private Button dislike5;
	private Button like6;
	private Button dislike6;
	private Button like7;
	private Button dislike7;
	private Button like8;
	private Button dislike8;
   	 
	public Stage getStage()   
	{
   	 return myStage;  
	}
	public Button getButton()
	{
   	 return exit;
	}
	public Button getLocate()
	{
   	 return locateButton;
	}
	public Button getLike1()
	{
   	 return like1;
	}
    public Button getDislike1() {
   	 return dislike1;
    }
    public Button getLike2() {
   	 return like2;
    }
    public Button getDislike2() {
   	 return dislike2;
    }
    public Button getLike3() {
   	 return like3;
    }
    public Button getDislike3() {
   	 return dislike3;
    }
    public Button getLike4() {
   	 return like4;
    }
    public Button getDislike4() {
   	 return dislike4;
    }
    public Button getLike5() {
   	 return like5;
    }
    public Button getDislike5() {
   	 return dislike5;
    }
    public Button getLike6() {
   	 return like6;
    }
    public Button getDislike6() {
   	 return dislike6;
    }
    public Button getLike7() {
   	 return like7;
    }
    public Button getDislike7() {
   	 return dislike7;
    }
    public Button getLike8() {
   	 return like8;
    }
    public Button getDislike8() {
   	 return dislike8;
    }
    
	private void createLabel(String labelName, int xIndex, int yIndex, Font font, GridPane gPane)
	{
    	Label label = new Label(labelName);

    	if(font != null)
       	label.setFont(font);
    	GridPane.setConstraints(label, xIndex, yIndex);
    	GridPane.setColumnSpan(label, 1);
    	gPane.getChildren().add(label);
	}

	private void createSeparator(int yIndex, GridPane gPane)
	{
    	final Separator sep = new Separator();
    	sep.setValignment(VPos.CENTER);
    	GridPane.setConstraints(sep, 0, yIndex);
    	GridPane.setColumnSpan(sep,7);
    	gPane.getChildren().add(sep);
	}

	private ComboBox createComboBox(String categories[], int xIndex, int yIndex, GridPane gPane)
	{
    	ComboBox comboBox = new ComboBox(FXCollections.observableArrayList(categories));
    	comboBox.setValue("select");
    	GridPane.setConstraints(comboBox, xIndex, yIndex);
    	GridPane.setColumnSpan(comboBox,1);
    	gPane.setValignment(comboBox, VPos.TOP);
    	gPane.getChildren().add(comboBox);
    	return comboBox;
	}

	private TextArea createTextArea(int xIndex, int yIndex, GridPane gPane)
	{
    	TextArea textArea = new TextArea("");
    	GridPane.setConstraints(textArea,xIndex,yIndex);
    	GridPane.setColumnSpan(textArea,2);
    	gPane.getChildren().add(textArea);
    	return textArea;
	}
	private Button createButton(String buttonName, int xIndex, int yIndex, GridPane gPane)
	{
    	Button button = new Button(buttonName);
    	GridPane.setConstraints(button,xIndex,yIndex);
    	GridPane.setColumnSpan(button,1);
    	gPane.getChildren().add(button);
    	return button;
	}
	public void setStage(Stage stage)
	{
    	myStage = stage;
    	int yIndex = 0;   	 
    	Group root = new Group();
    	GridPane gPane = new GridPane();
    	gPane.setPadding(new Insets(10,10,10,10));
    	String categoriesToPickFrom[] = {"accounting", "air travel", "auto", "auto repair", "bakery", "banking", "biomedical", "camping", "coffee shop", "dentist", "education", "festival", "financial service", "government", "grocery store", "hospital", "hotel", "interior design", "jewelry", "landscaping", "livery service", "museum", "office supplies", "pest control", "physician", "plumbing", "public relations", "recreation","restaurant","retail","select", "technology","theater","transportation","travel","venue"};
    	Arrays.sort(categoriesToPickFrom);
    	Scene scene = new Scene(root, 500, 400, Color.GRAY);
    	stage.setScene(scene);
    	scene.setRoot(gPane);
   	 
    	createLabel("Locater",3,yIndex,new Font(20),gPane);
    	yIndex++;
    	createSeparator(yIndex, gPane);
    	yIndex++;  
   	 
    	yourLocationDescription = new TextArea("Enter your coordinates, and then either a name or category to get the 8 best search results");
    	yourLocationDescription.setPrefHeight(100);
    	GridPane.setConstraints(yourLocationDescription, 0, yIndex);
    	GridPane.setColumnSpan(yourLocationDescription, 7);
    	yourLocationDescription.setEditable(false);
    	gPane.getChildren().add(yourLocationDescription);
    	yIndex++;
    	createSeparator(yIndex, gPane);
    	yIndex++;
   	 
    	createLabel("Enter your Location:", 1, yIndex, new Font(FONT), gPane);
    	yIndex++;
    	myLongitude = createTextArea(1, yIndex, gPane);
    	createLabel("Category:", 3, yIndex, new Font(FONT), gPane);
    	categories = createComboBox(categoriesToPickFrom, 4, yIndex, gPane);
    	yIndex++;
    	myLatitude = createTextArea(1,yIndex,gPane);
    	createLabel("Name:", 3, yIndex, new Font(FONT), gPane);
    	myName = createTextArea(4, yIndex, gPane);
    	yIndex++;   	 
   	 
    	createLabel("How to filter results:", 3, yIndex-3, new Font(FONT), gPane);
    	yIndex++;

    	createSeparator(yIndex, gPane);
    	yIndex++;
   	 
    	locateButton = createButton("LOCATE!", 3, yIndex, gPane);
    	yIndex++;
    	createSeparator(yIndex, gPane);
    	yIndex++;
   	 
    	loc1 = createTextArea(1, yIndex, gPane);
    	like1 = createButton(":)", 2, yIndex, gPane);
    	dislike1 = createButton(":(", 3, yIndex, gPane);
    	loc5 = createTextArea(4, yIndex, gPane);
    	like5 = createButton(":)", 5, yIndex, gPane);
    	dislike5 = createButton(":(", 6, yIndex, gPane);
    	yIndex++;
    	loc2 = createTextArea(1, yIndex, gPane);
    	like2 = createButton(":)", 2, yIndex, gPane);
    	dislike2 = createButton(":(", 3, yIndex, gPane);
    	loc6 = createTextArea(4, yIndex, gPane);
    	like6 = createButton(":)", 5, yIndex, gPane);
    	dislike6 = createButton(":(", 6, yIndex, gPane);
    	yIndex++;
    	loc3 = createTextArea(1, yIndex, gPane);
    	like3 = createButton(":)", 2, yIndex, gPane);
    	dislike3 = createButton(":(", 3, yIndex, gPane);
    	loc7 = createTextArea(4, yIndex, gPane);
    	like7 = createButton(":)", 5, yIndex, gPane);
    	dislike7 = createButton(":(", 6, yIndex, gPane);
    	yIndex++;
    	loc4 = createTextArea(1, yIndex, gPane);
    	like4 = createButton(":)", 2, yIndex, gPane);
    	dislike4 = createButton(":(", 3, yIndex, gPane);
    	loc8 = createTextArea(4, yIndex, gPane);
    	like8 = createButton(":)", 5, yIndex, gPane);
    	dislike8 = createButton(":(", 6, yIndex, gPane);
    	yIndex++; 	 
   	 
   	 
    	exit = createButton("Close",3,yIndex,gPane);
    	exit.setPrefWidth(80);
   	 
   	 
    	MainScene myMainScene = App.getMainScene();
    	Object list[] = new Object[12];
    	list[0] = myLatitude;
    	list[1] = myLongitude;
    	list[2] = categories;
    	list[3] = myName;
    	list[4] = loc1;
    	list[5] = loc2;
    	list[6] = loc3;
    	list[7] = loc4;
    	list[8] = loc5;
    	list[9] = loc6;
    	list[10] = loc7;
    	list[11] = loc8;
    	myMainScene.setList(list);
    	locateButton.setOnAction(myMainScene);
    	exit.setOnAction(myMainScene);  
    	like1.setOnAction(myMainScene);
    	dislike1.setOnAction(myMainScene);
    	like2.setOnAction(myMainScene);
    	dislike2.setOnAction(myMainScene);
    	like3.setOnAction(myMainScene);
    	dislike3.setOnAction(myMainScene);
    	like4.setOnAction(myMainScene);
    	dislike4.setOnAction(myMainScene);
    	like5.setOnAction(myMainScene);
    	dislike5.setOnAction(myMainScene);
    	like6.setOnAction(myMainScene);
    	dislike6.setOnAction(myMainScene);
    	like7.setOnAction(myMainScene);
    	dislike7.setOnAction(myMainScene);
    	like8.setOnAction(myMainScene);
    	dislike8.setOnAction(myMainScene);
    	myStage.show();   	 
	}
}

