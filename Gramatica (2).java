import java.util.*;
import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//imports

public class Gramatica{
	String file;
	
	//constructor
	public Gramatica(String files){
		this.file = files;
	}

	public static void main(String[] args) throws Exception{

		if (args != null) {
			if (args[1].equals("-afd")) {
				//AFD afd = new AFD(args[0]);
			}
			else if(args[1].equals("-afn")){
				//AFN afn = new AFN(args[0]);
			}
			else if(args[1].equals("-check")){
				
			}
		}
	}
}
