package HW5;
//Gabriello Lima, 112803276, R01
public class SceneTree{
	private SceneNode root;
	private SceneNode cursor;
	private int id = 1;
	private boolean inGame = false;
	SceneNode[] tree = new SceneNode[100];
	/*
	 * Excess variable explanations:
	 * 
	 * @param inGame
	 * Used to keep track of if the user is in game or not to prevent some
	 * print statements from executing
	 * 
	 */
	public SceneTree() {

	}
	/*
	 * This method moves the cursor to it's respective parent node
	 * 
	 * @throws NoSuchNodeException
	 * When the cursor doesn't have a parent
	 */
	public void moveCursorBackwards() throws NoSuchNodeException{
		if (cursor.getParent() != null) {
			cursor.setCursorString("");
			cursor = cursor.getParent();
			cursor.setCursorString("*");
			System.out.println("You have successfully moved back to " +
			  cursor.getTitle());
		}
		else
			throw new NoSuchNodeException("This node does not have a parent");
	}
	/*
	 * This method moves the cursor forward to a child node, denoted by the 
	 * user in A (left) B (middle) and C (right) format
	 * 
	 * @throws NoSuchNodeException
	 * If the String input from the user does not lead to a child node, or 
	 * is not in the correct formatting.
	 */
	public void moveCursorForwards(String option) throws NoSuchNodeException{
		cursor.setCursorString("");
		if(option.equals("A")) {
			if(cursor.getLeft() != null) {
				cursor = cursor.getLeft();
				cursor.setCursorString("*");
				if(!inGame)
					System.out.println("Cursor succesfully moved to " + 
				      cursor.getTitle());
			}
			else
				throw new NoSuchNodeException("There is no such path");
		}
		else if(option.equals("B")) {
			if(cursor.getMiddle() != null) {
				cursor = cursor.getMiddle();
				cursor.setCursorString("*");
				if(!inGame)
					System.out.println("Cursor succesfully moved to " +
				      cursor.getTitle());
			}
			else {
				cursor.setCursorString("*");
				throw new NoSuchNodeException("There is no such path");
			}
		}
		else if(option.equals("C")) {
			if(cursor.getRight() != null) {
				cursor = cursor.getRight();
				cursor.setCursorString("*");
				if(!inGame)
					System.out.println("Cursor succesfully moved to " +
				      cursor.getTitle());
			}
			else {
				cursor.setCursorString("*");
				throw new NoSuchNodeException("There is no such path");
			}
		}
		else {
			cursor.setCursorString("*");
			throw new NoSuchNodeException("Please enter either \"A\" \"B\" or \"C\"");
		}
	}

	/*
	 * This method adds a new node to the tree array, and utilizes the functions
	 * that otherwise would have been used in the addScene() method in SceneNode
	 * 
	 * @throws FullSceneException
	 * When there are already three child nodes under the cursor and the user
	 * attempts to add another
	 * 
	 * 
	 */
	public void addNewNode(String title, String sceneDescription)throws FullSceneException { 
		SceneNode newNode = new SceneNode(title, sceneDescription, id);
		tree[id - 1] = newNode;
		id = tree[0].getNumScenes();
		if(id == 2) {
			root = tree[0];
			cursor = root;
		}
		if(cursor.getLeft() == null && newNode != cursor) {
			cursor.setLeft(newNode);
			cursor.getLeft().setParent(cursor);
		}
		else if(cursor.getMiddle() == null && newNode != cursor) {
			cursor.setMiddle(newNode);
			cursor.getMiddle().setParent(cursor);
		}
		else if(cursor.getRight() == null && newNode != cursor) {
			cursor.setRight(newNode);
			cursor.getRight().setParent(cursor);
		}
		else if (cursor.getLeft() != null && cursor.getRight()!= null){
			tree[id - 1] = null;
			tree[0].decrementNumScenes();
			throw new FullSceneException("You cannot add another scene!");
		}

	}
	/*
	 * This method removes a child node from its respective parent. It is used
	 * in tangent with the moveChildrenLeft() method in order to keep nodes 
	 * as far left as possible
	 * 
	 * @throws NoSuchNodeException
	 * When the user attempts to remove a child node that doesn't exist, or the
	 * user enters input in an incorrect format
	 * 
	 * 
	 * 
	 */
	public void removeScene(String option) throws NoSuchNodeException{
		if(option.equals("A")) {
			if(cursor.getLeft() != null) {
				cursor.setLeft(null);
				System.out.println("Scene A succesfully removed");
			}
			else
				throw new NoSuchNodeException("There is no such path");
		}
		else if(option.equals("B")) {
			if(cursor.getMiddle() != null) {
				cursor.setMiddle(null);
				System.out.println("Scene B succesfully removed");
			}
			else
				throw new NoSuchNodeException("There is no such path");
		}
		else if(option.equals("C")) {
			if(cursor.getRight() != null) {
				cursor.setRight(null);
				System.out.println("Scene C succesfully removed");
			}
			else
				throw new NoSuchNodeException("There is no such path");
		}
		else
			throw new NoSuchNodeException("Please enter either \"A\" \"B\" or \"C\"");
		moveChildrenLeft();
	}
	/*
	 * This method works in tangent with the removeScene() and moveScene() methods
	 * in order to keep child nodes as far left as possible
	 * 
	 * 
	 */
	public void moveChildrenLeft() {
		if(cursor.getLeft() == null && cursor.getMiddle() !=null) {
			cursor.setLeft(cursor.getMiddle());
			cursor.setMiddle(null);
		}
		if(cursor.getMiddle() == null && cursor.getRight() != null) {
			cursor.setMiddle(cursor.getRight());
			cursor.setRight(null);
		}
		if(cursor.getLeft() == null && cursor.getMiddle() !=null) {
			cursor.setLeft(cursor.getMiddle());
			cursor.setMiddle(null);
		}
	}
	/*
	 * This method moves the cursor node to a child under the sceneID the user
	 * enters as input
	 * 
	 * @param tempCursor
	 * Used to keep track of the old position of the tempCursor
	 * 
	 * @param placeHolder
	 * Used to temporarily hold the new place of the cursor in order for child
	 * nodes to be moved left if necessary
	 * 
	 * @throws NoSuchNodeException
	 * If the sceneID the user enters doesn't exist
	 * @throws FullSceneException
	 * If the scene the user is attempting to move the cursor to is already full
	 * with three children
	 * 
	 */
	public void moveScene(int sceneIDToMoveTo) throws NoSuchNodeException, FullSceneException{
		//moves the cursor to a child of the sceneIDToMoveTo
		if(tree[sceneIDToMoveTo] == null) {
			throw new NoSuchNodeException("This scene does not exist! ");
		}
		SceneNode tempCursor = cursor;
		cursor = tree[sceneIDToMoveTo];
		if(cursor.getLeft() == null) {
			cursor.setLeft(tempCursor);
			cursor = cursor.getLeft();
		}
		else if(cursor.getMiddle() == null) {
			cursor.setMiddle(tempCursor);
			cursor = cursor.getMiddle();
		}
		else if(cursor.getRight() == null) {
			cursor.setRight(tempCursor);
			cursor = cursor.getRight();
		}
		else {
			cursor = tempCursor;
			throw new FullSceneException("Scene is full, cannot move there. ");
		}
		SceneNode placeHolder = cursor;
		cursor = tempCursor.getParent();
		tree[tempCursor.getID() + 1] = null;
		moveChildrenLeft();
		cursor = placeHolder;
		System.out.println("Scene successfully moved! ");

	}
	
	/*
	 * This method gets the path from the root.
	 * 
	 * @param tempString, bin
	 * Used to properly format the order in which the titles of each node are
	 * received in String format
	 * 
	 */
	public String getPathFromRoot() {
		SceneNode tempCursor = cursor;
		String bin = "";
		String tempString = "";
		bin = cursor.getTitle();
		while(cursor.getParent()!=null) {
			cursor = cursor.getParent();
			tempString = cursor.getTitle() + ", ";
			bin = tempString + bin;
		}
		cursor = tempCursor;
		return bin;
	}

	
	/*
	 * This method prints the entire ternary tree
	 * 
	 * 
	 * 
	 * 
	 */
	public void toString1(){
		System.out.println(root.getTitle() + "(#" + root.getID() + ")");
		root.printChildren();
	}
	public String spaceCounter(int k) {
		String tempString ="";
		for (int i = 0; k < i; k ++) {
			tempString+= "\t";
		}
		return tempString;
	}
	public int getID() {
		return id;
	}
	public SceneNode getCursor() {
		return cursor;
	}
	/*
	 * This method is used in order to simulate the beginning of the game, or
	 * option (G)
	 */
	public void beginGame() {
		inGame = true;
		cursor = root;
		cursor.displayScene();
	}
	public void endGame() {
		inGame = false;
	}
}