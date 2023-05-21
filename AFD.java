import java.io.*;

import java.util.*;
/*
	Utilice esta clase para guardar la informacion de su
	AFD. NO DEBE CAMBIAR LOS NOMBRES DE LA CLASE NI DE LOS 
	METODOS que ya existen, sin embargo, usted es libre de 
	agregar los campos y metodos que desee.
*/
public class AFD{ 
  String file;
  
	/*
		Implemente el constructor de la clase AFD
		que recibe como argumento un string que 
		representa el path del archivo que contiene
		la informacion del afd (i.e. "Documentos/archivo.afd").
		Puede utilizar la estructura de datos que desee
	*/
	public AFD(String path){
    this.file = path;
	}

	/*
		Implemente el metodo transition, que recibe de argumento
		un entero que representa el estado actual del AFD y un
		caracter que representa el simbolo a consumir, y devuelve 
		un entero que representa el siguiente estado
	*/
	public int getTransition(int currentState, char symbol){
    try{
      AFD prueba2 = new AFD(this.file);
      File file2 = new File(prueba2.file);
      Scanner obj2 = new Scanner(file2);
      String alfabeto = obj2.nextLine();
      String estados = obj2.nextLine();
      String finales = obj2.nextLine();
      int states = Integer.parseInt(estados);
      int cont = 0;
      String[] alfa = alfabeto.split(",");
      String[] est = new String[states];
      String[] fin = finales.split(",");
      for(String tm:alfa){
        if(tm.charAt(0) == symbol){
          cont++;
      }
        }
      if(cont == 0){
        return 0;
      }
      int pos = 0;
      for(int i = 0; i<states;i++){
        String x = i+"";
        est[pos] = x;
        pos++;
      }
      String[] estad = new String[states];
      char[][] matri = new char[alfa.length][est.length];
      for(int fila = 0; fila<alfa.length; fila++){
        String linea = obj2.nextLine();
        estad = linea.split(",");
        int val = 0;
        for(int columna = 0; columna<est.length; columna++){
          String v = estad[val];
          matri[fila][columna] = v.charAt(0); 
          val++;
        }
      }
      int c = 0;
      for(String t:alfa){
        if(t.charAt(0) == symbol){
          break;
        }else{    
          c++;
        }
      }
      
      
      /*
      System.out.print("2-D Array: \n[");
      // printing a 2-D array using two nested loops
      for (char[] array: matri) { 
        System.out.print("[");
        for (char n: array) {  
          System.out.print(n + " "); // printing each item
        }
        System.out.print("]"); // printing new line
      }
      System.out.println("]\n");

      //System.out.println(Arrays.toString(matri));*/
     // System.out.println(currentState);
      //System.out.println(c);
      int resultado = Character.getNumericValue(matri[c][currentState]);
      //System.out.println(resultado);
  		return resultado;
    }catch(FileNotFoundException ex2){
      System.out.println("Nunca tendría que activarse, pero por si acaso :v");
      return 0;
    }
	}

	/*
		Implemente el metodo accept, que recibe como argumento
		un String que representa la cuerda a evaluar, y devuelve
		un boolean dependiendo de si la cuerda es aceptada o no 
		por el afd
	*/
  //00010110
  //matriz, longitud sería el alfabeto (para columnas)
	public boolean accept(String string){
    try{
      AFD prueba = new AFD(this.file);
      //System.out.println(this.file);
      File file = new File(prueba.file);
      Scanner obj = new Scanner(file);
      //Leer alfabeto
      String alfabeto = obj.nextLine();
      //Leer estados
      String estados = obj.nextLine();
      //Leer finales 
      String finales = obj.nextLine();
      int states = Integer.parseInt(estados);
    
      String[] alfa = alfabeto.split(",");
      String[] est = new String[states];
      String[] fin = finales.split(",");

      int pos = 0;
      for(int i = 0; i<states;i++){
        String x = i+"";
        est[pos] = x;
        pos++;
      }
      
      //System.out.println(Arrays.toString(alfa));
      //System.out.println(Arrays.toString(est));
      //System.out.println(Arrays.toString(fin));

        
    
    int contador = 0;
    int posicion = 0;
    int posFinal = 1;
    while(contador < string.length()){
      posFinal = getTransition(posFinal, string.charAt(posicion));
      posicion += 1;
      contador++;
      if(posFinal == 0){
        return false;
      }
    }
    for(int j = 0; j<fin.length;j++){
      int valor = Integer.parseInt(fin[j]);
      if(posFinal == valor){
        return true;
      }
    }
    
    obj.close();
      }catch(FileNotFoundException ex){
        System.out.println("El archivo no existe");
      }
    return false;
  }
	/*
		El metodo main debe recibir como primer argumento el path
		donde se encuentra el archivo ".afd", como segundo argumento 
		una bandera ("-f" o "-i"). Si la bandera es "-f", debe recibir
		como tercer argumento el path del archivo con las cuerdas a 
		evaluar, y si es "-i", debe empezar a evaluar cuerdas ingresadas
		por el usuario una a una hasta leer una cuerda vacia (""), en cuyo
		caso debe terminar. Tiene la libertad de implementar este metodo
		de la forma que desee. 
	*/
	public static void main(String[] args) throws Exception{
    if(args!=null){
      if(args[1].equals("-i")){
        AFD afd = new AFD(args[0]);
        afd.iflag(args[0],afd);      
      }
      else if(args[1].equals("-f")){
        AFD afd = new AFD(args[0]);
        afd.fflag(afd.file,args[2]);
      }
      else{
        System.out.println("No ingresó la cantidad de información necesaria, terminando programa...");
      }
    }
    else{
      System.out.println("No ingresó la cantidad de información necesaria, terminando programa...");
    }
		
	}
  
  void iflag(String archivo, AFD afd){    
    while(true){
      Scanner leer = new Scanner(System.in);
      System.out.println("Ingrese la cuerda que desea evaluar: ");
      String cuerda="";
      cuerda = leer.nextLine();
      if(cuerda.length()!=0){
        if(afd.accept(cuerda)==true){
          System.out.println("La cuerda fue aceptada");
        }
        else{
          System.out.println("La cuerda fue rechazada");
        }
      }
      else{
        System.out.println("Cuerda nula, cerrando programa...");
        break;
      }
    }
  }
  
  void fflag(String archivo, String txtcuerdas){
    try{
      File file2=new File(txtcuerdas);
      Scanner leer2 = new Scanner(file2);
      while(leer2.hasNextLine()){
        String cuerdaf=leer2.nextLine();
        System.out.println(cuerdaf);
        if(accept(cuerdaf)==true){
          System.out.println("La cuerda fue aceptada");
        }
        else{
          System.out.println("La cuerda fue rechazada");
        }
      }
    }
    catch(FileNotFoundException ex2){
      System.out.println("Nunca tendría que activarse, pero por si acaso :v");
      
    }
  }
}