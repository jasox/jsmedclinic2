package pl.jasox.medward.util;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;

import java.sql.Clob;
import java.sql.SQLException;

/**
 * Klasa pomocnicza do obsługi plików. 
 */
public final class FileUtil {
  // ...
  public static final String LINE_SEPARATOR = 
    System.getProperty("line.separator");

  /** */
  private FileUtil() {
  }

  /**
   * Zapisuje strumień bajtów do pliku z nadpisywaniem istniejacego.
   * 
   * @param file  Plik docelowy.
   * @param input Strumień wejściowy.
   */
  public static void write(File file, InputStream input) 
  throws IOException {
    mkdirs(file);
    BufferedOutputStream output = null;
    try {
      output = 
        new BufferedOutputStream(new FileOutputStream(file, false));
      int data = -1;
      while ((data = input.read()) != -1) {
        output.write(data);
      }
    } 
    finally {
      close(input, file);
      close(output, file);
    }
  }

  /**
   * Generuje unikatowy plik o podanej scieżce i nazwie. 
   * Jeśli plik istnieje, dodaje przyrostek "[i]" do nazwy pliku 
   *  (wartosc i z przedzialu 0 - 2147483647).
   * 
   * @param filePath ścieżka do pliku.
   * @param fileName Nazwa pliku.
   * @return         Unikatowy plik.
   */
  public static File uniqueFile(File filePath, String fileName)
  throws IOException {
    File file = new File(filePath, fileName);
    if (file.exists()) {
      String prefix;
      String suffix;
      int dotIndex = fileName.lastIndexOf(".");
      if (dotIndex > -1) {
        prefix = fileName.substring(0, dotIndex) + "[";
        suffix = "]" + fileName.substring(dotIndex);
      } 
      else {
        prefix = fileName + "[";
        suffix = "]";
      }
      int count = 0;
      while ( file.exists() ) {
        if (count < 0) {
          throw new IOException("No unique filename available for "
              + fileName + " in path " + filePath.getPath() + ".");
        }
        file = new File(filePath, prefix + (count++) + suffix);
      }
    }
    return file;
  }

  /**
   * Sprawdza i tworzy brakujące katalogi nadrzędne dla danego pliku.
   * 
   * @param file Plik do sprawdzenia.
   */
  private static void mkdirs(File file) throws IOException {
    if ( file.exists() && !file.isFile() ) {
      throw new IOException("File " + file.getPath()
          + " is actually not a file.");
    }
    File parentFile = file.getParentFile();
    if ( !parentFile.exists() && !parentFile.mkdirs() ) {
      throw new IOException("Creating directories "
          + parentFile.getPath() + " failed.");
    }
  }

  /**
   * Zamyka dane źródło plikowe.
   * 
   * @param resource zamykane źródło.
   * @param file     Plik.
   */
  private static void close(Closeable resource, File file) {
    if (resource != null) {
      try {
        resource.close();
      } 
      catch (IOException e) {
        String message = "Closing file " + file.getPath() + " failed.";
        System.err.println(message);
        e.printStackTrace();
      }
    }
  }
  
  
  /** konwersja typu java.sql.Clob na string */
  public static String clobStringConversion(Clob clb) 
  throws IOException, SQLException  {
    if ( clb == null ) {
      return  null;
    }           
    StringBuffer str = new StringBuffer();
    String strng;             
    // ...   
    BufferedReader bufferRead = new BufferedReader(clb.getCharacterStream());  
    while ( (strng = bufferRead.readLine()) != null ) {
      str.append(strng);
    }
    return str.toString();               
  }

}
