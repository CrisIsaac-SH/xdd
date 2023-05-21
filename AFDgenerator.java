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
        int alamacenador1 = 0;
        int  contadorA = 1;
        
        //entiendo que es mientras halla que leer ira sumando las posiciones
        while(obs2.hasNextLine()){
            String lines = obs2.nextLine();
            alamacenador1++;
        }

        //////
        alamacenador1 = alamacenador1 - 3;
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