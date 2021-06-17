package equo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {
  public static void main(String[] args) {
    JFileChooser jchooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("txt Files", "txt", "text");
    jchooser.setFileFilter(filter);
    jchooser.showOpenDialog(null);

    File selectedFile = jchooser.getSelectedFile();

    if (selectedFile != null) {
      ArrayList<String> list = readFile(selectedFile);
      try {
        App a = new App(list.remove(0), list);
        a.resolve();
      } catch (Error e) {
        e.printStackTrace();
      }
    } else {
      JOptionPane.showMessageDialog(null, "No selecciono ningun archivo");
      System.exit(0);
    }
  }

  public static ArrayList<String> readFile(File file) {
    ArrayList<String> out = new ArrayList<>(); // constructs a string buffer with no characters
    try {
      FileReader fr = new FileReader(file); // reads the file
      BufferedReader br = new BufferedReader(fr); // creates a buffering character input stream
      String line;
      while ((line = br.readLine()) != null) {
        out.add(line.trim());
      }
      fr.close(); // closes the stream and release the resources
      return out;
    } catch (IOException e) {
      e.printStackTrace();
      return out;
    }
  }
}
