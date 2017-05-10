import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveLoad {
	private static final String filename = "savefile";

	/**
	 * method for loading a saved game returns a Map object that is saved in
	 * savefile
	 * 
	 * @throws IOException
	 */
	public static Map load() throws IOException {
		ObjectInputStream in = null;
		Map map = null;
		try {
			FileInputStream inFile = new FileInputStream(filename);
			in = new ObjectInputStream(inFile);
			map = (Map) in.readObject();
			System.out.println("Read from file");
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		} finally { // if the file exists then close it
			if (in != null)
				in.close();
		}
		System.out.println("in load");
		return map;
	}

	/**
	 * method for saving a game
	 * 
	 * @throws IOException
	 */
	public static void save(Map map) throws IOException {
		ObjectOutputStream out = null;
		// will create the file only if it does not exist
		File f = new File(filename);
		f.createNewFile();
		try {
			FileOutputStream outFile = new FileOutputStream(filename);
			out = new ObjectOutputStream(outFile);
			out.writeObject(map);
			System.out.println("Stored to file");
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			if (out != null)
				out.close();
		}
		System.out.println("in save");
	}
}