/**
 *  @author Jason Brasse
 *
 *  Represents a linked element (file or directory) in a file system
 */

import java.io.File;
import java.util.Scanner;

public class Data {
	/**
	 * Creates a Data element at the root of the file system
	 */
	public Data() {
		data = new File("/Users/firas");
	}

	/**
	 * Tests whether the current item is a container (directory).
	 * 
	 * @return boolean true if this is a directory, false if a file
	 */
	public boolean isContainer() {
		return (data != null && data.isDirectory());
	}

	/**
	 * Returns descriptive name.
	 * 
	 * @return String name of this item
	 */
	public String getName() {
		if (data == null)
			return "Root";
		String ret = data.getName();
		if (ret.length() == 0)
			ret = data.toString();
		return ret;
	}

	/**
	 * Returns parent of this item.
	 * 
	 * @return Data parent of this item, null if this is the root.
	 */
	public Data getParent() {
		if (data == null)
			return null;
		return new Data(data.getParentFile(), depth - 1);
	}

	/**
	 * Returns all sub directories.
	 * 
	 * @return Data[] array of all subdirectory nodes
	 */
	public Data[] getChildContainers() {
		if (depth == MAX_DEPTH)
			return new Data[0];
		File arr[];
		if (data == null) {
			arr = File.listRoots();
		} else {
			arr = data.listFiles();
			if (arr == null)
				return new Data[0];
		}
		int count = 0;
		for (int x = 0; x < arr.length && count < MAX_CHILD_CONTAINERS; x++)
			if (arr[x].isDirectory())
				count++;
		Data ret[] = new Data[count];
		int d = 0;
		for (int x = 0; x < arr.length && d < count; x++) {
			if (arr[x].isDirectory())
				ret[d++] = new Data(arr[x], depth + 1);
		}
		return ret;
	}

	/**
	 * Returns all sub files.
	 * 
	 * @return Data[] array of all subfile nodes
	 */
	public Data[] getChildData() {
		File arr[];
		if (data == null) {
			return new Data[0];
		} else {
			arr = data.listFiles();
			if (arr == null)
				return new Data[0];
		}
		int count = 0;
		for (int x = 0; x < arr.length && count < MAX_CHILD_DATA; x++)
			if (arr[x].isFile())
				count++;
		Data ret[] = new Data[count];
		int d = 0;
		for (int x = 0; x < arr.length && d < count; x++) {
			if (arr[x].isFile())
				ret[d++] = new Data(arr[x], depth + 1);
		}
		return ret;
	}

	/**
	 * Returns a full element name including path to node.
	 * 
	 * @return String path + name of node
	 */
	public String toString() {
		if (data == null)
			return "Root";
		return data.getAbsolutePath();
	}

	/**
	 * constant to represent the maximum depth explored in a file system
	 */
	private final static int MAX_DEPTH = 3;
	/**
	 * constant to represent the maximum number of subdirectories explored in a file
	 * system
	 */
	private final static int MAX_CHILD_CONTAINERS = 3;
	/**
	 * constant to represent the maximum number of subfiles explored in a file
	 * system
	 */
	private final static int MAX_CHILD_DATA = 3;

	/**
	 * File element containing file/directory information
	 */
	private File data;
	/**
	 * Current depth of this item
	 */
	private int depth;

	/**
	 * Creates an new node from a File object.
	 * 
	 * @param f
	 *            represents the associated file info of the node
	 * @param d
	 *            represents the depth of the current node
	 */
	private Data(File f, int d) {
		data = f;
		depth = d;
	}

	/**
	 * Test harness to navigate file system using Data nodes.
	 * 
	 * @param args
	 *            Not used
	 */
	public static void main(String[] args) {
		Data d = new Data();
		Scanner scanner = new Scanner(System.in);
		String input;
		int sel;
		do {
			System.out.println("[Q]=Quit,P=Parent,C=Container,D=Data");
			System.out.println("Location:" + d);
			Data p = d.getParent();
			System.out.print("[0]P:");
			if (p == null)
				System.out.println("");
			else
				System.out.println(p.getName());
			sel = 1;
			Data cc[] = d.getChildContainers();
			for (int x = 0; x < cc.length; x++)
				System.out.println("[" + (sel++) + "]C:" + cc[x].getName());
			Data cd[] = d.getChildData();
			for (int x = 0; x < cd.length; x++)
				System.out.println("[" + (sel++) + "]D:" + cd[x].getName());
			System.out.print("Command:");
			input = scanner.nextLine();
			int choice = -1;
			try {
				choice = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				choice = -1;
			}
			if (choice == 0) {
				if (p != null)
					d = p;
			} else {
				if (choice > 0 && choice <= cc.length) {
					d = cc[choice - 1];
				} else {
					choice = choice - cc.length;
					if (choice > 0 && choice <= cd.length)
						d = cd[choice - 1];
				}
			}
		} while (!input.equals("Q"));
		scanner.close();
		System.out.println("Bye");
	}
}