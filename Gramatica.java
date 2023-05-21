public class Gramatica{
String file;

  public Gramatica(String pathsalida){
    this.file = pathsalida;
  }
  
	public static void main(String[] args) throws Exception{
		//Su codigo aqui
    if(args!=null){
      if(args[1].equals("-afd")){
        AFDGen afdgen = new AFDGen(args[0]);
        
        //Gramatica gram = new Gramatica(args[0]); Recibir de afdgen
        
      }
      else if(args[1].equals("-afn")){
        AFNGen afngen = new AFNGen(args[0],args[2]);
      }
      else if(args[1].equals("-check")){
        AFNGen afngen = new AFNGen(args[0],args[2]);
        AFN afn = new AFN(args[2]+".afn");
        afn.check(args[2]+".afn",args[2],args[3]);
      }
      else{
        System.out.println("No ingresó la cantidad de información necesaria, terminando programa...");
      }
    }
    else{
      System.out.println("No ingresó la cantidad de información necesaria, terminando programa...");
    }
		
	}
}