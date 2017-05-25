import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Class to handle save and loading of various assets
 * 
 * @author Group 1 Tutorial H14A
 *
 */
public class SaveLoad {
    private static final String filename = "savefile";
    private static final String settingsfilename = "savedsettings";

    /**
     * Method for loading a saved game returns a Map object that is saved in
     * 'savefile'
     * 
     * @return the saved Map
     * @throws IOException
     */
    public static MapModel load() throws IOException {
        ObjectInputStream in = null;
        MapModel map = null;
        try {
            FileInputStream inFile = new FileInputStream(filename);
            in = new ObjectInputStream(inFile);
            map = (MapModel) in.readObject();
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
     * Saves the game writing it into the file 'savefile'
     * 
     * @param map
     *            as the map to save
     * @throws IOException
     */
    public static void save(MapModel map) throws IOException {
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
    }

    /**
     * Loads the GameSettings stores in 'savesettings' if it exists
     * 
     * @return the loaded GameSettings
     * @throws IOException
     */
    public static SettingsModel loadSettings() throws IOException {
        ObjectInputStream in = null;
        SettingsModel settings = null;
        try {
            FileInputStream inFile = new FileInputStream(settingsfilename);
            in = new ObjectInputStream(inFile);
            settings = (SettingsModel) in.readObject();
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
        return settings;
    }

    /**
     * Saves the settings into 'savesettings'
     * 
     * @param settings
     *            as the settings to save
     * @throws IOException
     */
    public static void saveSettings(SettingsModel settings) throws IOException {
        ObjectOutputStream out = null;
        // will create the file only if it does not exist
        File f = new File(settingsfilename);
        f.createNewFile();
        try {
            FileOutputStream outFile = new FileOutputStream(settingsfilename);
            out = new ObjectOutputStream(outFile);
            out.writeObject(settings);
            System.out.println("Stored to file");
        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            if (out != null)
                out.close();
        }
    }
}