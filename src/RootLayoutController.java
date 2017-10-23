import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.stage.FileChooser;


/**
 * RootLayout Controller Object
 * 
 * @author AJ Priola
 * @version 1.0
 */

public class RootLayoutController {

	@FXML private MenuBar menu;
	
	private Main main;
	
	@FXML private void initialize() {

	}
	
	public void toggleMenu(boolean on) {
		menu.setVisible(on);
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	@FXML private void openFileClicked() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		main.setDataFile(fileChooser.showOpenDialog(main.getPrimaryStage()));
	}

}
