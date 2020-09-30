package HW5;
//Gabriello Lima, 112803276, R01
import java.util.Scanner;
public class AdventureDesigner{
	public void playGame() {
		
	}
	public static void main(String[] args) throws FullSceneException {
		Scanner stdin = new Scanner(System.in);
		SceneTree STObj = new SceneTree();
		System.out.println("Creating a story...");
		System.out.println("Please enter a title:");
		String title = stdin.nextLine();
		System.out.println("Please enter a scene: ");
		String scene = stdin.nextLine();
		STObj.addNewNode(title, scene);
		System.out.println("Scene #" + (STObj.getID() - 1) + " added");
		boolean quitFlag = false;
		while(!quitFlag) {
			System.out.println("A) Add Scene \r\n" + 
					"R) Remove Scene\r\n" + 
					"S) Show Current Scene\r\n" + 
					"P) Print Adventure Tree\r\n" + 
					"B) Go Back A Scene\r\n" + 
					"F) Go Forward A Scene\r\n" + 
					"G) Play Game\r\n" + 
					"N) Print Path To Cursor\r\n" + 
					"M) Move Scene\r\n" + 
					"Q) Quit - Exits the program \n");
			System.out.print("Please enter a selection: ");
			try {
				String selection = stdin.next().toLowerCase();
				switch(selection) {
					case("a"):
						System.out.println("Please enter a title: ");
						title = stdin.next();
						title += stdin.nextLine();
						System.out.print("Please enter a scene: ");
						scene = stdin.next();
						scene += stdin.nextLine();
						STObj.addNewNode(title, scene);
						System.out.println("Scene #" + (STObj.getID()-1) + " added");
						break;
					case("r"):
						System.out.print("Which child would you like to remove? ");
						String remove = stdin.nextLine();
						STObj.removeScene(remove);
						break;
					case("s"):
						STObj.getCursor().displayFullScene();
						break;
					case("p"):
						STObj.toString1();
						break;
					case("b"):
						STObj.moveCursorBackwards();
						break;
					case("f"):
						System.out.print("Which option do you wish to go to: ");
						String option = stdin.next();
						STObj.moveCursorForwards(option);
						break;
					case("g"):
						System.out.println("Now beginning game...");
						STObj.beginGame();
						while(STObj.getCursor().isEnding()!= true) {
							System.out.println("Please enter an option: ");
							option = stdin.next();
							STObj.moveCursorForwards(option);
							STObj.getCursor().displayScene();
						}
						STObj.endGame();
						System.out.println("The End");
						System.out.println("Now returning back to creation mode...");
						break;
					case("n"):
						System.out.println(STObj.getPathFromRoot());
						break;
					case("m"):
						System.out.print("Move currrent scene to: ");
						int nextScene = stdin.nextInt();
						STObj.moveScene(nextScene);
						break;
					case("q"):
						quitFlag = true;
						System.out.println("Program terminating normally...");
						break;
				}
			}catch(NoSuchNodeException ex) {
				System.out.println(ex.getMessage());
			}catch(FullSceneException ex) {
				System.out.println(ex.getMessage());
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			
			
			
		}
	}
}