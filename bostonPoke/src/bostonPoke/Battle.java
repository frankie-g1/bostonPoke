package bostonPoke;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import monsters.Monster;

public class Battle {
	//bottom left
	String playerName;
	Monster playerMonster;
	Image show;
	
	//top right
	String tName;
	Monster[] tsMonster;
	Monster monsterInUse;
	Image showcased;
	
	
	Text pbar;
	Text ebar;
	

	public void start(Stage primaryStage, Player Player, Trainer oponnent) throws Exception{

		
		primaryStage.setTitle("Prudential Center");
		
	
		GridPane p = new GridPane(); //easiest i think
		p.setAlignment(Pos.CENTER);
		
		Text gym = new Text("First Floor"); //set position with fxml
		
		setPlayer();
		setTrainer();

		Button attack = new Button("Attack");
		Button defend = new Button("Defense Up");
		Button special = new Button("Special Attack");
		Button heal = new Button("Heal");
		
		
		
		
		
		attack.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent click) {
				
				//use get methods for math
				//then update the hbar string
				int newHealth = monsterInUse.getHealth() - playerMonster.getAttackDMG();
				if(newHealth <= 0) {
					Switch(tsMonster);
				}
				monsterInUse.setHealth(newHealth);
				setDisplay();
				
				
				
			}
		});
		
		heal.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent click) {
				
				//use get methods for math
				//then update the hbar string
				int newHealth = playerMonster.getHealth() + playerMonster.getHeal();
				if(newHealth <= playerMonster.getMaxHealth()) {
					newHealth = playerMonster.getMaxHealth();
				}
				setDisplay();
				
			}
		});
		
		primaryStage.setScene(new Scene(p, 400, 225));
		primaryStage.show();
	

}
	//updates battlescreen after each click
	protected void setDisplay() {
		pbar = healthBar(playerMonster);
		ebar = healthBar(monsterInUse);
		
	}

	public Monster Find(Monster[] tsMonster) {
		boolean found = false;
		for(int i = 0; i <= tsMonster.length -1; i++) {
			if(tsMonster[i].inUse) {
				return tsMonster[i];
			}
		}
	}
	
	
	public Monster Switch(Monster[] tsMonster) {
		int counter = 0;
		
		for(int i = 0; i <= tsMonster.length; i++) {
			
			if(tsMonster[i].isHealthy()) {
				return tsMonster[i];
			}
			
		}
		
		Text victory = new Text("YOU WON");
		return null;
	}
	
	
	public void setPlayer() {
		Player player1 = new Player("John", "Attack Helicopter");
		
		playerName	= player1.getName();	
		
		playerMonster = player1.getMonster();
		
		String player = playerMonster.getURL();
		show = new Image(player);
		
		
		Rectangle pHealth = new Rectangle();
		pHealth.setStroke(Color.BLACK);
		pHealth.setFill(Color.CORNFLOWERBLUE);
		
		pbar = healthBar(playerMonster);
	}
	
	
	
	public void setTrainer() {
		Trainer mark = new Trainer(1, true);
		tName = mark.getName();
		
		tsMonster =  mark.getMonster();
		monsterInUse=	Find(tsMonster);
		
		String url = monsterInUse.getURL();
		Image image = new Image(url);
		ImageView showcased = new ImageView(image);
		
		
		Rectangle eHealth = new Rectangle();
		eHealth.setStroke(Color.BLACK);
		eHealth.setFill(Color.BROWN);
		
		ebar = healthBar(monsterInUse);
	}
	
	
	public Text healthBar(Monster playerMonster2) {
		return new Text (String.format("HP: %d / %d", playerMonster2.getHealth(), playerMonster2.getMaxHealth()));
	}
	

}
