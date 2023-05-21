import java.io.*;
import java.util.*;
public class AFNGen{
  String file,siminicial,salida2;
  String[] nterminales, alfa;
  LinkedList<Integer> controlLineas;
  LinkedList<String> alfabeto, ntermi,estados2,estados,nuevastran;
  int contntermi, contalfa;
  
  public AFNGen(String path,String salida){
    this.file = path;
    this.salida2=salida;
    controlLineas = new LinkedList<Integer>();
    alfabeto = new LinkedList<String>();
    ntermi = new LinkedList<String>();
    estados = new LinkedList<String>();
    estados2 = new LinkedList<String>();
    nuevastran = new LinkedList<String>();
    fileReader();
   /* System.out.println(this.controlLineas);
    System.out.println(this.alfabeto);
    System.out.println(this.ntermi);
    System.out.println(this.estados);
    System.out.println(this.estados2);
    System.out.println(this.contntermi);
    System.out.println(this.contalfa);*/
   // System.out.println(this.nuevastran);
  }
  public String getSalida(){
    return this.salida2 + ".afn";
  }
  public void fileReader(){
    try{
      File file = new File(this.file);
      File file2 = new File(this.file);
      Scanner obj = new Scanner(file);
      Scanner obj2 = new Scanner(file2);
      int lin = 0;
      int conti = 1;
      while(obj2.hasNextLine()){
        String lin2 = obj2.nextLine();
        lin++;      
      }
      lin = lin - 3;
      FileReader fr = new FileReader(this.file);
      BufferedReader bf = new BufferedReader(fr);
      int cantidadestados = 0;
      String noterminales = obj.nextLine();
      String alfabeto = obj.nextLine();
      this.siminicial = obj.nextLine();
     // FileWriter fw = new FileWriter(file);
      String nterminales[] = noterminales.split(",");
      String alfa[] = alfabeto.split(",");
      for(int i= 0;i<nterminales.length;i++){
        this.controlLineas.add(1);
        this.ntermi.add(nterminales[i]);
      }
      for(int j = 0;j<alfa.length;j++){
        this.alfabeto.add(alfa[j]);
      }
      
      while(obj.hasNextLine()){
        String linea = obj.nextLine();
        String datos[] = linea.split("->");
        this.estados.add(datos[0]);
        this.estados2.add(datos[1]);
      }
      String simbolos = "";
      int contaux = 0;
      for(int s = 0;s<estados2.size();s++){
        simbolos = estados2.get(s);
        contaux = 0;
        while(contaux<simbolos.length()){
          if(alfabeto.contains(simbolos.substring(contaux,contaux+1))){
            this.contalfa++;
          }
          if(ntermi.contains(simbolos.substring(contaux,contaux+1))){
            this.contntermi++;
          }
          contaux++;
          
        }
        if(((simbolos.length()==2)&&(ntermi.contains(simbolos.substring(simbolos.length()-1))))||((simbolos.length()==1))){
          nuevastran.add(estados.get(s)+"->"+simbolos);
        }else{
          getGrammar(this.contalfa,estados.get(s),estados.get(s),simbolos,1);
        }
      }
      try {
        int contin = 0;
        LinkedList<String> cantestados = new LinkedList<String>();
        LinkedList<String> estsinfinal = new LinkedList<String>();
        cantestados.add("0");
        while(contin<this.nuevastran.size()){
          String line = this.nuevastran.get(contin);
          String data[] = line.split("->");
           if(cantestados.contains(data[0])==false){
              cantestados.add(data[0]);
              estsinfinal.add(data[0]);
             }
          contin++;
        }
        for(int k = 0;k<this.ntermi.size();k++){
          if(cantestados.contains(this.ntermi.get(k))==false){
            cantestados.add(this.ntermi.get(k));
            estsinfinal.add(this.ntermi.get(k));
          }
        }
        cantestados.add("F");
        cantidadestados = cantestados.size();
        int finales = cantidadestados-1;
        String[][] transiciones = new String[this.alfabeto.size()+1][cantidadestados];
        int conta=0;
        //filas
        int pos2=0;
        int pos=0;
        //System.out.println(nuevastran);
        for(int filas=0;filas<this.alfabeto.size()+1;filas++){
          for(int columnas = 0;columnas<cantidadestados;columnas++){
            transiciones[filas][columnas]="0";
          }
        }
        //System.out.println(nuevastran.size());
        for(int columna2=0;columna2<cantidadestados;columna2++){
          transiciones[0][columna2] = Integer.toString(columna2);
        }
        for(int q = 0; q<this.alfabeto.size()+1;q++){
          
          //columnas
          
          for(int t = 1; t<cantidadestados;t++){
            //System.out.println(pos);
            if(pos<nuevastran.size()){
              //System.out.println(pos);
              String valor2 = nuevastran.get(pos);
              //System.out.println(valor2);
              String[] valor = valor2.split("->");
              //VA AL FINAL O RECURSIVO
              if(valor[1].length()==1){
               // System.out.println(Arrays.toString(valor));
                //System.out.println(valor2);
                if(estsinfinal.contains(valor[1])){
                  int pos5 = cantestados.indexOf(valor[0]);

                  if(transiciones[0][pos5].equals("0")){
                    transiciones[0][pos5]=Integer.toString(cantestados.indexOf(valor[1]));
                  }else{
                    transiciones[0][pos5]+=";"+Integer.toString(cantestados.indexOf(valor[1]));
                  }
                  pos2 = nuevastran.indexOf(valor2);
                  nuevastran.remove(pos2);
                }else if(estsinfinal.contains(valor[1])==false){
                  if(valor[1].equals("d")){
                    //System.out.println("JGALgjadighalghaighadkga");
                  }
                  int pos3 = cantestados.indexOf(valor[0]);
                  int pos4 = this.alfabeto.indexOf(valor[1]);
                  //System.out.print(pos2 + ' ' + pos2-1);
                  pos2 = nuevastran.indexOf(valor2);
                  //finales
                
                  if(transiciones[pos4+1][pos3].equals("0")){
                    transiciones[pos4+1][pos3]=Integer.toString(cantestados.size()-1);
                    pos=0;
                  }else{
                    transiciones[pos4+1][pos3]+=";"+Integer.toString(cantestados.size()-1);
                    pos=0;
                  }
                  //
                  //System.out.println(valor2);
                  nuevastran.remove(pos2);
                  //System.out.println(nuevastran);
                  pos=0;
                  //System.out.println(nuevastran);
                  break;
                  
                }
              //SI ES MAYOR QUE UNO
              }else{
                int pa=0;
                if(valor[1].length()==3){
                  String cuer = valor[1].substring(1,valor[1].length()-1);
                  int po= cantestados.indexOf(valor[0]);
                  int po1=cantestados.indexOf(cuer);
                  String cu = Character.toString(valor[1].charAt(0));
                  int ab=this.alfabeto.indexOf(cu);
                  if(transiciones[ab+1][po].equals("0")){
                  transiciones[ab+1][po] = Integer.toString(po1+1);
                  pos=0;
                  }else{
                    transiciones[ab+1][po] += ';'+Integer.toString(po1+1);
                    pos=0;
                  }
                  pa = nuevastran.indexOf(valor2);
                  nuevastran.remove(pa);
                }else if(valor[1].length()==2){
                  String cuer = Character.toString(valor[1].charAt(1));
                  int po= cantestados.indexOf(valor[0]);
                  int po1=cantestados.indexOf(cuer);
                  String cu = Character.toString(valor[1].charAt(0));
                  int ab=this.alfabeto.indexOf(cu);
                  if(transiciones[ab+1][po].equals("0")){
                    //System.out.println(Arrays.toString(valor));
                    
                    transiciones[ab+1][po] = Integer.toString(po1);
                    pos=0;
                  }else{
                    transiciones[ab+1][po] += (";"+Integer.toString(po1));
                    pos=0;
                  }
                  pa = nuevastran.indexOf(valor2);
                  nuevastran.remove(pa);
                }
              }
              
              //System.out.println("HOLAA: " + Arrays.toString(valor)); 
            }
            pos++;
          }
          
        }
        int con=0;
        while(nuevastran.size() != 0){
          String ola = nuevastran.get(con);
          String[] ol = ola.split("->");
          if(ol[1].length() == 1){
            if(estsinfinal.contains(ol[1])){
              if(transiciones[0][cantestados.indexOf(ol[0])].equals("0")){
                transiciones[0][cantestados.indexOf(ol[0])] = Integer.toString(cantestados.indexOf(ol[1])); 
                nuevastran.remove(ola);
              }else{
                transiciones[0][cantestados.indexOf(ol[0])] +=";"+Integer.toString(cantestados.indexOf(ol[1]));
                nuevastran.remove(ola); 
              }
            }else{
              transiciones[this.alfabeto.indexOf(ol[1])+1][cantestados.indexOf(ol[0])]=Integer.toString(cantestados.size()-1);
              nuevastran.remove(ola);
            }
          }else{
            int pa=0;
            if(ol[1].length()==3){
              String cuer = ol[1].substring(1,ol[1].length()-1);
              int po= cantestados.indexOf(ol[0]);
              int po1=cantestados.indexOf(cuer);
              String cu = Character.toString(ol[1].charAt(0));
              int ab=this.alfabeto.indexOf(cu);
              if(transiciones[ab+1][po].equals("0")){
              transiciones[ab+1][po] = Integer.toString(po1+1);
              pos=0;
              }else{
                transiciones[ab+1][po] += ';'+Integer.toString(po1+1);
                pos=0;
              }
              pa = nuevastran.indexOf(ol);
              nuevastran.remove(pa);
            }else if(ol[1].length()==2){
              String cuer = Character.toString(ol[1].charAt(1));
              int po= cantestados.indexOf(ol[0]);
              int po1=cantestados.indexOf(cuer);
              String cu = Character.toString(ol[1].charAt(0));
              int ab=this.alfabeto.indexOf(cu);
              if(transiciones[ab+1][po].equals("0")){
                //System.out.println(Arrays.toString(valor));
                
                transiciones[ab+1][po] = Integer.toString(po1);
                pos=0;
              }else{
                transiciones[ab+1][po] += (";"+Integer.toString(po1));
                pos=0;
              }
              pa = nuevastran.indexOf(ol);
              nuevastran.remove(pa);
            }
          }
        }
        for(int r = 0; r<this.alfabeto.size()+1;r++){   
          for(int z = 0; z<cantidadestados;z++){
            if(z<cantidadestados-1){
              System.out.print(transiciones[r][z]+",");
            }else{
              System.out.print(transiciones[r][z]);
            }
          }
          System.out.println();
        }
        //System.out.println(this.alfabeto);
        //System.out.println(cantestados);
        //System.out.println(cantidadestados);
        //System.out.println(finales);
        //System.out.println(estsinfinal);
        //System.out.println(this.nuevastran);

        //si se quiere hacer sin el grading tiene que ser de esta manera
        String ruta = this.salida2+".afn";
        //si se quiere hacer con grading lo que se tiene que hacer es quitar ese +".afn
        File file4 = new File(ruta);
        if (!file4.exists()) {
          file4.createNewFile();
        }
          FileWriter fw = new FileWriter(file4);
            BufferedWriter bw = new BufferedWriter(fw);
            String alfabetof="";
          	for (int i=0; i<alfa.length;i++){
              String dato=(alfa[i]);
              
              alfabetof=alfabetof+dato+",";
              
            }
            String alff=(alfabetof.substring(0, alfabetof.length() - 1));
            bw.write(String.valueOf(alff)+"\n");
            bw.write(String.valueOf(cantidadestados)+"\n");
            bw.write(String.valueOf(finales)+"\n");
        int cont69=0;
          for(int fi=0;fi<this.alfabeto.size()+1;fi++){
            for(int col=0;col<cantidadestados;col++){
              //escribis transiciones[fi][col]
              
              if(col==cantidadestados-1){
                bw.write(String.valueOf(transiciones[fi][col]));
                if(cont69!=this.alfabeto.size()){
                  bw.write("\n");
                }
                
              }
              else{
                String imprimir=transiciones[fi][col]+",";
                bw.write(imprimir);
              }
              
            }
            cont69++;
          }
            
            bw.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
      catch(FileNotFoundException ex){
       System.out.println("El archivo no existe");
    }/*catch(IOException ex2){
      System.out.println("Error");
    }*/
  }
  public void getGrammar(int contalfa, String constante, String cambiante, String simbolo, int cont){
    if(cont!=3){
    String factorizacion;
    factorizacion = simbolo.charAt(cont-1)+"";
    String nuevaregla;
    String nuevaregla2;
    String contador = cont +"";
    if(cont==1){ 
      nuevaregla = constante + contador;
    }
    else{
      nuevaregla = simbolo.substring(2);
    }
    nuevaregla2 = cambiante + "->" + factorizacion + nuevaregla;
    //System.out.println(nuevaregla2);
    cont++;
    cambiante = nuevaregla;
    if(cont<=contalfa){

      this.nuevastran.add(nuevaregla2);
      getGrammar(contalfa,constante,cambiante,simbolo,cont);
    }
    else{
      this.nuevastran.add(nuevaregla2);
    }
      }
  }
}