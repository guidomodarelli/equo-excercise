package equo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MyFile {
  public static File selectFile() {
    JFileChooser jchooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("txt Files", "txt", "text");
    jchooser.setFileFilter(filter);
    jchooser.showOpenDialog(null);

    File selectedFile = jchooser.getSelectedFile();

    if (selectedFile == null) {
      JOptionPane.showMessageDialog(null, "No selecciono ningun archivo");
      System.exit(0);
    }
    return selectedFile;
  }

  public static ArrayList<String> readFile(File file) {
    ArrayList<String> out = new ArrayList<>();
    try {
      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr); // creates a buffering character input stream
      String line;

      while ((line = br.readLine()) != null) {
        out.add(line.trim());
      }

      fr.close(); // closes the stream and release the resources
    } catch (IOException e) {
      e.printStackTrace();
    }
    return out;
  }
}
