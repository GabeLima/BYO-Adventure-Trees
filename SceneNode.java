public class SceneNode extends SceneTree{
	private String title;
	private String sceneDescription;
	private int sceneID = -1;
	private SceneNode left;
	private SceneNode middle;
	private SceneNode right;
	private SceneNode parent;
	private static int numScenes = 1;
	private String isCursor = "";
	int level = 0;
	/*
	 * Excess variable explanations:
	 * 
	 * @param parent
	 * Keeps track of the parent of this node in the tree
	 * 
	 * @param isCursor
	 * Keeps track of the * denoting if this node is a cursor
	 * 
	 * @param level
	 * Keeps track of the level this node is on for printing purposes
	 */
	
	public SceneNode() {
	
	}
	/*
	 * This constructor is used to initialize this node with a title,
	 * description, and an ID
	 * 
	 * 
	 */
	
	public SceneNode(String title, String sceneDescription, int sceneID) {
		this.title = title;
		this.sceneDescription = sceneDescription;
		this.sceneID = sceneID;
		numScenes++;
	}
	/*
	 * This method determines when the game ends by checking the next nodes
	 * in the ternary tree. It is only necessary to check the left most node
	 * due to the moveChildrenLeft() method in SceneTree
	 * 
	 * 
	 * 
	 */
	public boolean isEnding() {
		if(left == null) {
			return true;
		}
		else
			return false;
	}
	
	/*
	 * This method displays the scene as viewed in the game, printing the left,
	 * middle, and right nodes should they exist 
	 * 
	 * 
	 */
	public void displayScene() {
		System.out.println(title);
		System.out.println(sceneDescription);
		if (left != null) {
			System.out.println("A)" + left.getTitle());
		}
		if (middle != null) {
			System.out.println("B) " + middle.getTitle());
		}
		if (right != null) {
			System.out.println("C) " + right.getTitle());
		}
		
	}
	/*
	 * This method prints this nodes children
	 * 
	 * @param tempParent, level
	 * Used to determine the spacing in the printing of how many levels deep
	 * the child is from the root
	 */
	public void printChildren() {
		String spacing = "";
		for(int i = 0; i < level; i ++) {
			spacing += "     ";
		}
		if (left != null) {
			System.out.println(spacing + "A) " + left.getTitle() + "(#" + 
		      left.getID() + ")" + isCursor);
			left.setLevel(level++);
			left.printChildren();
		}
		if (middle != null) {
			System.out.println(spacing + "B) " + middle.getTitle()+ "(#" + 
			  middle.getID() + ")" + isCursor);
			middle.setLevel(level++);
			middle.printChildren();
		}
		if (right != null) {
			System.out.println(spacing + "C) " + right.getTitle()+ "(#" + 
		      right.getID() + ")" + isCursor);
			right.setLevel(level++);
			right.printChildren();
		}
	}
	public void displayFullScene() {
		System.out.println(toString());
	}
	/*
	 * This method works in tangent with the displayFullScene() in order
	 * to display the full scene, including immediate child nodes.
	 * 
	 * @param leftB
	 * Used to determine if a comma is necessary by stating whether or not
	 * the left node was printed
	 * @param midB
	 * Used to determine if a comma is necessary by stating whether or not
	 * the middle node was printed
	 * 
	 */
	
	public String toString() {
		String temp = "";
		boolean leftB = false;
		boolean midB = false;
		temp += "Scene ID #" + sceneID + "\n";
		temp +=  "Title: " + title + "\n";
		temp += "Scene: " + sceneDescription + "\n";
		temp+= "Leads to: ";
		if(left == null && right == null && middle == null) {
			temp += "NONE";
		}
		else {
			if (left != null) {
				temp+= "'" + left.getTitle() + "' (" + left.getID() + ")" ;
				leftB = true;
			}
			if (middle != null && leftB) {
				temp+= ", '" + middle.getTitle() + "' (" + middle.getID() + ")" ;
				midB = true;
			}
			else if (middle != null) {
				temp+= ", '" + middle.getTitle() + "' (" + middle.getID() + ")" ;
				midB = true;
			}
				
			if (right != null && midB) {
				temp+= ", '" + right.getTitle() + "' (" + right.getID() + ")" ;
			}
			else if (right != null && leftB) {
				temp+= ", '" + right.getTitle() + "' (" + right.getID() + ")" ;
			}
			else if (right != null) {
				temp+= "'" + right.getTitle() + "' (" + right.getID() + ")" ;
			}
		}
		return temp;
	}
	public int getID() {
		return sceneID;
	}
	public String getDesc() {
		return sceneDescription;
	}
	public String getTitle() {
		return title;
	}
	public void setLeft(SceneNode newLeft) {
		left = newLeft;
	}
	public void setMiddle(SceneNode newMid) {
		middle = newMid;
	}
	public void setRight(SceneNode newRight) {
		right = newRight;
	}
	public int getNumScenes() {
		return numScenes;
	}
	public SceneNode getLeft() {
		return left;
	}
	public SceneNode getRight() {
		return right;
	}
	public SceneNode getMiddle() {
		return middle;
	}
	public void setID(int newID) {
		sceneID = newID;
	}
	public SceneNode getParent() {
		return parent;
	}
	public void setParent(SceneNode newParent) {
		parent = newParent;
	}
	public void decrementNumScenes() {
		numScenes--;
	}
	public void setCursorString(String s) {
		isCursor = s;
	}
	public void setLevel(int x) {
		level = x;
	}
}
