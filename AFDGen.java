import java.io.*;
import java.util.*;
public class AFDGen{
  String file;
  public AFDGen(String path){
    this.file = path;
  }

  public void fileGenerator(){
    try{
      File file = new File(this.file);
      Scanner obj = new Scanner(file);
      FileWriter fw = new FileWriter(file);
    }catch(FileNotFoundException ex){
        System.out.println("El archivo no existe");
      }catch(IOException ex2){
      System.out.println("Error");
    }
  }
}