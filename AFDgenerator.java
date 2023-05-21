import java.io.*;
import java.util.*;

public class AFDgenerator{

    //campos 
    String file, simbbInicial, out;

    LinkedList<Integer> controller;
    LinkedList<String> abcAlfabetoo, simboTerminales, estadosNor, estadosTer, transNuevas;
    String[] cantidTerminales, alfabetoo;
    int contadorTerminal, contadorAlfabeto;

    BufferedReader Buff;
    FileReader Filer;

    //constructor
    public AFDgenerator(String path, String outss){
        //lectoreess
        this.file = path;
        this.out = outss;

        controller = new LinkedList<Integer>();
        
        abcAlfabetoo = new LinkedList<String>();
        simboTerminales = new LinkedList<String>();
        estadosNor = new LinkedList<String>();
        estadosTer = new LinkedList<String>();
        transNuevas = new LinkedList<String>();//


        fileReader();
    }
    public String getOuts(){
        return this.out + ".afd";
    }

    public void fileReader(){
        
        File archivoo = new File(this.file);
        File filess = new File(this.file);
        Scanner obs1 = new Scanner(archivoo);
        Scanner obs2 = new Scanner(filess);
//
        int almacenador1 = 0;
        int  contadorA = 1;
        
        //entiendo que es mientras halla que leer ira sumando las posiciones
        while(obs2.hasNextLine()){
            String lines = obs2.nextLine();
            almacenador1++;
        }

        //////
        almacenador1 = almacenador1 - 3;
        FileReader lectorArchi = new FileReader(this.file);

        int contEstados = 0;
        String simNoTerminales = obs1.nextLine();
        String abcAlfabetoo = obs1.nextLine();
        this.simbolosInicial = obs1.nextLine();
         String[] cantidTerminales = simNoTerminales.split(",");
         String[] alfabetoo = abcAlfabetoo.split(",");
        //////

        int conti1 = 0;
        while(conti1 < cantidTerminales.length){
            conti1++;
            this.controller.add(1);
            this.simboTerminales.add(cantidTerminales[conti1]);
        }

        for(int j = 0;j<alfabetoo.length;j++){
            this.abcAlfabetoo.add(alfabetoo[j]);
        }
        ///////
        while(obs1.hasNextLine()){
            String linea = obs1.nextLine();
            String datos[] = linea.split("->");
            this.estadosNor.add(datos[0]);
            this.estadosTer.add(datos[1]);
        }
        ///////////
        String simbb = "";
        int contaux = 0;

        /////////////
        for(int s = 0;s<estadosTer.size();s++){
        simbb = estadosTer.get(s);
        contaux = 0;
        while(contaux<simbb.length()){
          if(abcAlfabetoo.contains(simbb.substring(contaux,contaux+1))){
            this.contalfa++;
          }
          if(simboTerminales.contains(simbb.substring(contaux,contaux+1))){
            this.contntermi++;
          }
          contaux++;
          
        }
        if(((simbb.length()==2)&&(simboTerminales.contains(simbb.substring(simbb.length()-1))))||((simbb.length()==1))){
          transNuevas.add(estadosNor.get(s)+"->"+simbb);
        }else{
          grammar(this.contadorAlfabeto,estadosNor.get(s),estadosNor.get(s),simbb,1);
        }
      }

    }

///////////mi cerebro explotooooo
//aaaaaaaa jajajaja me petre ya 
//toy cagadisimo
    try {
        int z = 0;
        LinkedList<String> numestados = new LinkedList<String>();
        LinkedList<String> estfin = new LinkedList<String>();
        numestados.add("0");

        //Inicializa las listas para el estado final y las transiciones
        while (z < this.transNuevas.size()) {
            String linea = this.transNuevas.get(z);
            String exp[] = linea.split("->");
            if (numestados.contains(exp[0] == false)) {
                numestados.add(exp[0]);
                estfin.add(exp[0]);
                }
            z++;
        }
        //Verifica si hay simbolos terminales
        for(int i = 0; i < this.simboTerminales.size(); i++){
            if (numestados.contains(this.simboTerminales.get(i)) == false) {
                numestados.add(this.simboTerminales.get(i));
                estfin.add(this.simboTerminales.get(i));
            }
        }
        
        numestados.add("F");
        almacenador1 = almacenador1.size();
        int fins = almacenador1 - 1;
        String[][] trans = new String[this.abcAlfabetoo.size() + 1][almacenador1];
        int cont1 = 0;
        //Filas transiciones
        int t = 0;
        int u = 0;
        for(int filas = 0; filas < this.abcAlfabetoo.size() + 1; filas++){
            for(int colums = 0; colums < almacenador1; colums++ ){
                trans[filas][colums] = "0";
            }
        }

        for(int colums2 = 0; colums2 < almacenador1; colums2++){
            trans[0][colums2] = Integer.toString(colums2);
        }

        // Columnas transiciones
        for(int n = 0; n < this.abcAlfabetoo.size() + 1; n++){
            for(int c = 1; c < almacenador1; c++){
                if (t < transNuevas.size()) {
                    String value = transNuevas.get(t);
                    String[] value2 = value.split("->");
                    if (value2[1].length() == 1) {
                        if (estfin.contains(value2[1])) {
                            int lineanum = almacenador1.indexOf(value2[0]);
                            if (trans[0][lineanum].equals("0")) {
                                trans[0][lineanum] = Integer.toString(almacenador1.indexOf(value2[1]));
                            }
                            else{
                                trans[0][lineanum] += ";" + Integer.toString(almacenador1.indexOf(value2[1]));
                            }
                            t = transNuevas.indexOf(value2);
                            transNuevas.remove(t);
                        }
                        else if(estfin.contains(value2[1]) == false) {
                            if (value2[1].equals("d")) {
                                
                            }
                            int y = almacenador1.indexOf(value2[0]);;
                            int p = this.abcAlfabetoo.indexOf(value2);

                            t = transNuevas.indexOf(value2);

                            if (trans[p + 1][y].equals("0")) {
                                trans[p + 1][y] += ";" + Integer.toString(almacenador1.size() - 1);
                                u = 0;
                            }
                            else{
                                trans[p + 1][y] += ";" + Integer.toString(almacenador1.size() - 1);
                                u = 0;
                            }

                            transNuevas.remove(t);
                            u = 0;
                            break;

                        }
                    }
                    else{  //your turn (～￣▽￣)～

                    }
                }
            }
        }


    } catch (FileNotFoundException ex) {
        System.out.println("El archivo no existe");
    }


    public void grammar(int contabc, String same, String cam, String sim, int x) {
        if (x > 3) {
            String fac;
            fac = sim.charAt(x-1) + "";
            String nueva;
            String nueva2;
            String count = x + "";

            if (x == 1) {
                nueva = same + count;
            }
            else{
                nueva = sim.substring(2);
            }

            nueva2 = cam + "->" + fac + nueva;
            x++;
            cam = nueva;

            if (x <= contabc) {
                this.nueva.add(nueva2);
                grammar(contabc, same, cam, sim, x);
            }
            else{
                this.nueva.add(nueva2);
            }
        }
    }

    
}