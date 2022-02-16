/**********************************************
Workshop #7
Course: JAC444 - Semester Winter
Last Name: Solovev
First Name: Artem
ID: 136267184
Section: NFF
This assignment represents my own work in accordance with Seneca Academic Policy. Signature: Solovev
Date: 03/21/2021
**********************************************/
package application;
	
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
	
	 /**
     * @param args the command line arguments
     */
     

	private SimpleObjectProperty<Color> playerColorProperty = new SimpleObjectProperty<Color>(Color.RED);
    private int row;
    private int col;
    private char[][] grid = new char[6][7];
    int counter = 1;
    boolean winner = false;
    Label result = new Label("");
    
	@Override
	public void start(Stage primaryStage) {
		try {
			
			//initialize array
			for (int row = 0; row < grid.length; row++){
				for (int col = 0; col < grid[0].length; col++){
					grid[row][col] = ' ';
				}
			}
			
			final BorderPane root = new BorderPane();
			final GridPane gridpane = new GridPane();
			gridpane.setGridLinesVisible(false);
			Scene scene = new Scene(root, 750, 800, true);
			primaryStage.setTitle("JavaFX Connect Four");
			
			gridpane.setTranslateY(80);
	        gridpane.setAlignment(Pos.CENTER);
			
			gridpane.getColumnConstraints().addAll(
						new ColumnConstraints(100,100,Double.MAX_VALUE), 
		                new ColumnConstraints(100,100,Double.MAX_VALUE),
		                new ColumnConstraints(100,100,Double.MAX_VALUE),
		                new ColumnConstraints(100,100,Double.MAX_VALUE),
		                new ColumnConstraints(100,100,Double.MAX_VALUE),
		                
		                new ColumnConstraints(100,100,Double.MAX_VALUE)
		                );
		    gridpane.getRowConstraints().addAll(
		    			new RowConstraints(100,100,Double.MAX_VALUE), 
		                new RowConstraints(100,100,Double.MAX_VALUE),
		                new RowConstraints(100,100,Double.MAX_VALUE),
		                new RowConstraints(100,100,Double.MAX_VALUE),
		                new RowConstraints(100,100,Double.MAX_VALUE),
		                new RowConstraints(100,100,Double.MAX_VALUE),
		                new RowConstraints(100,100,Double.MAX_VALUE)
		                );
		    
		    createGrids(gridpane);
		    root.setCenter(gridpane);
		    
		    
		    
		    result.setFont(new Font("Arial", 50));
		    
		    
		    root.setBottom(result);
		    root.setAlignment(result, Pos.BOTTOM_CENTER);
		    
		       
		    
		    primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void createGrids(final GridPane gridpane) {
		gridpane.getChildren().clear();
		for(row=0; row<gridpane.getColumnConstraints().size(); row++) {
			for(col=0; col<gridpane.getRowConstraints().size(); col++) {
				
				Rectangle rect = new Rectangle(100,100);
				Circle circ = new Circle(47);
				circ.centerXProperty().set(50);
				circ.centerYProperty().set(50);
				Shape cell = Path.subtract(rect,  circ);
				cell.setFill(Color.BLUE);
				cell.setStroke(Color.BLUE);
				cell.setOpacity(.8);
				
				final Circle diskPreview = new Circle(40);
				diskPreview.setOpacity(.5);;
				diskPreview.setFill(Color.TRANSPARENT);
				
				diskPreview.setOnMouseEntered(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
//						diskPreview.setFill(Color.WHITE);
						if(playerColorProperty.get()==Color.RED) {
							diskPreview.setFill(Color.RED);
						}else {
							diskPreview.setFill(Color.YELLOW);
						}
					
					}
				});
				
				diskPreview.setOnMouseExited(new EventHandler<MouseEvent>(){
		                @Override
		                public void handle(MouseEvent arg0) {
		                    diskPreview.setFill(Color.TRANSPARENT);
		                }
		            });
				
				final Circle disk = new Circle(40);
	            disk.fillProperty().bind(playerColorProperty);
	            disk.setOpacity(.5);
	            disk.setTranslateY(-(100*(row+1)));
	            disk.setUserData(counter);
	            counter++;
	            
	            final TranslateTransition translateTranstion = new TranslateTransition(Duration.millis(300), disk);
	            
	            disk.setOnMouseEntered(new EventHandler<MouseEvent>(){
	                @Override
	                public void handle(MouseEvent arg0) {
	                    diskPreview.setFill(Color.WHITE);
	                    if(playerColorProperty.get()==Color.RED){
	                        diskPreview.setFill(Color.RED);
	                    }else{
	                        diskPreview.setFill(Color.YELLOW);
	                    }
	                }
	            });
	            
	            disk.setOnMouseExited(new EventHandler<MouseEvent>(){
	                @Override
	                public void handle(MouseEvent arg0) {
	                    diskPreview.setFill(Color.TRANSPARENT);
	                }
	            });
	             
	            disk.setOnMouseClicked(new EventHandler<MouseEvent>(){
	                @Override
	                public void handle(MouseEvent arg0) {
	                	  
	                	
	                    if(disk.getTranslateY()!=0){
	                    	
	                    	
	                    	int play = (int) disk.getUserData();
	                    	
	                    	while(play>7) {
	                    		play = play - 7;
	                    	}
	                    	play = play - 1;
	                    	System.out.println(play);
	                    	
	                		for (int row = grid.length-1; row >= 0; row--){
	            				if(grid[row][play] == ' '){
	            					
	            					 if(playerColorProperty.get()==Color.RED){
	            						 grid[row][play] = 'Y';
	         	                    }else{
	         	                    	grid[row][play] = 'R';
	         	                    }
	            					break;
	            				}
	            			}
	                		 display(grid);
	 	                    char player;
	 	                    if(playerColorProperty.get()==Color.RED){
	 	                    	player = 'Y';
	 	                    }else{
	 	                    	player = 'R';
	 	                    }
	 	                    winner = isWinner(player,grid);
	 	                    
	 	                    
	 	                    if (winner){
	 	            			if (player=='R'){
	 	            				System.out.println("Red won");
	 	            			}else{
	 	            				System.out.println("Yellow won");
	 	            			}
	 	            		}
	                    	
	                        translateTranstion.setToY(0);
	                        translateTranstion.play();
	                        if(playerColorProperty.get()==Color.RED){
	                            playerColorProperty.set(Color.YELLOW);
	                            disk.fillProperty().bind(new SimpleObjectProperty<Color>(Color.RED));
	                        }else{
	                            playerColorProperty.set(Color.RED);
	                            disk.fillProperty().bind(new SimpleObjectProperty<Color>(Color.YELLOW));
	                        }
	                    }
	                   
	                   
	                }
	            });
	            
	            diskPreview.setOnMouseClicked(new EventHandler<MouseEvent>(){
	                @Override
	                public void handle(MouseEvent arg0) {
	                	 	                	
	                    if(disk.getTranslateY()!=0) {
	                    	System.out.println((int) disk.getUserData());
	                    	
	                    	int play = (int) disk.getUserData();
	                    	
	                    	while(play>7) {
	                    		play = play - 7;
	                    	}
	                    	play = play - 1;
	                    	
	                    	System.out.println(play);
	                		for (int row = grid.length-1; row >= 0; row--){
	            				if(grid[row][play] == ' '){
	            					
	            					 if(playerColorProperty.get()==Color.RED){
	            						 grid[row][play] = 'R';
	         	                    }else{
	         	                    	grid[row][play] = 'Y';
	         	                    }
	            					break;
	            				}
	            			}
	                		 display(grid);
	 	                    char player;
	 	                    if(playerColorProperty.get()==Color.RED){
	 	                    	player = 'R';
	 	                    }else{
	 	                    	player = 'Y';
	 	                    }
	 	                    winner = isWinner(player,grid);
	 	                    
	 	                    
	 	                    if (winner){
	 	            			if (player=='R'){
	 	            				System.out.println("Red won");
	 	            				result.setText("Red Won");
	 	            				result.setTextFill(Color.web("#FF0800"));
	 	            				
	 	            			}else{
	 	            				System.out.println("Yellow won");
	 	            				result.setText("Yellow Won");
	 	            				result.setTextFill(Color.web("#FFE400"));
	 	            			}
	 	            		}
	                        translateTranstion.setToY(0);
	                        translateTranstion.play();
	                        if(playerColorProperty.get()==Color.RED){
	                            playerColorProperty.set(Color.YELLOW);
	                            disk.fillProperty().bind(new SimpleObjectProperty<Color>(Color.RED));
	                        }else{
	                            playerColorProperty.set(Color.RED);
	                            disk.fillProperty().bind(new SimpleObjectProperty<Color>(Color.YELLOW));
	                        }
	                    }
	                   
	                    
	                    
	                }
	            });
				
				
		
				
				StackPane stack = new StackPane();
				
				stack.getChildren().addAll(cell, diskPreview, disk);
				
				gridpane.add(stack, col, row);
								
			}
		}
		
	}
	public static void display(char[][] grid){
		System.out.println(" 0 1 2 3 4 5 6");
		System.out.println("---------------");
		for (int row = 0; row < grid.length; row++){
			System.out.print("|");
			for (int col = 0; col < grid[0].length; col++){
				System.out.print(grid[row][col]);
				System.out.print("|");
			}
			System.out.println();
			System.out.println("---------------");
		}
		System.out.println(" 0 1 2 3 4 5 6");
		System.out.println();
	}
	
	public static boolean isWinner(char player, char[][] grid){
		//check for 4 across
		for(int row = 0; row<grid.length; row++){
			for (int col = 0;col < grid[0].length - 3;col++){
				if (grid[row][col] == player   && 
					grid[row][col+1] == player &&
					grid[row][col+2] == player &&
					grid[row][col+3] == player){
					return true;
				}
			}			
		}
		//check for 4 up and down
		for(int row = 0; row < grid.length - 3; row++){
			for(int col = 0; col < grid[0].length; col++){
				if (grid[row][col] == player   && 
					grid[row+1][col] == player &&
					grid[row+2][col] == player &&
					grid[row+3][col] == player){
					return true;
				}
			}
		}
		//check upward diagonal
		for(int row = 3; row < grid.length; row++){
			for(int col = 0; col < grid[0].length - 3; col++){
				if (grid[row][col] == player   && 
					grid[row-1][col+1] == player &&
					grid[row-2][col+2] == player &&
					grid[row-3][col+3] == player){
					return true;
				}
			}
		}
		//check downward diagonal
		for(int row = 0; row < grid.length - 3; row++){
			for(int col = 0; col < grid[0].length - 3; col++){
				if (grid[row][col] == player   && 
					grid[row+1][col+1] == player &&
					grid[row+2][col+2] == player &&
					grid[row+3][col+3] == player){
					return true;
				}
			}
		}
		return false;
	}
	
	
}
