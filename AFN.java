import java.io.*;
import java.util.*;

public class AFN {
  String file, cuerdas;
  boolean verificacion = false;

  public AFN(String path) {
    this.file = path;

  }

  public void getTransition(int currentState, String cuerda, String[] alfa, String[] est, String[] fin,
      String[][] trans, int estadoAnterior) {
    try {
      verificacion = false;
      AFN prueba = new AFN(this.file);
      File file = new File(prueba.file);
      Scanner obj = new Scanner(file);
      //System.out.println(cuerda);
      //System.out.println(currentState);
      if ((currentState == 0)) {
        //System.out.println("La cuerda es rechazada");
        verificacion = false;
        // return false;
      } else if (cuerda.equals("")) {
        for (String r : fin) {
          if (r.equals(Integer.toString(currentState))) {
            verificacion = true;
            break;
          } else {
            verificacion = false;
          }
        }
        if (verificacion == true) {
          // System.out.println("La cuerda es aceptada");
          //System.out.println("getTrans: " + verificacion);
          // return true;

        } else if (verificacion == false) {
          // System.out.println("La cuerda es rechazada");
          //System.out.println("getTrans: " + verificacion);
          // return false;
        }

        // return currentState;
      } else {
        int contador = 1;
        int cont = 1;
        int hola = 1;
        while (cont < alfa.length) {
          if (alfa[cont].charAt(0) == (cuerda.charAt(0))) {
            if (trans[contador][currentState].length() == 1) {
              hola = (Integer.parseInt(trans[contador][currentState]));
            }
            if (hola == 0) {
              if (trans[0][currentState].length() > 1) {
                String[] split = trans[0][currentState].split(",");
                getTransition(Integer.parseInt(split[0].substring(split[0].length() - 1)), cuerda, alfa, est, fin,
                    trans, currentState);
              } else {
                // System.out.println("La cuerda es rechazada");
                // return false;
              }
            } else {
              if (trans[cont][currentState].length() > 1) {
                String[] split2 = trans[cont][currentState].split(",");
                int con = 0;
                for (String n : fin) {

                }
                int pep = 1;
                if (cuerda.length() >= 2) {
                  if (cuerda.charAt(1) == cuerda.charAt(0)) {
                    pep = 0;
                  } else {
                    pep = 0;
                  }

                }
                if (pep == 0) {
                  char caracter = split2[0].charAt(0);
                  String nuevoValor = Character.toString(caracter);
                  getTransition(Integer.parseInt(nuevoValor), cuerda.substring(1, cuerda.length()), alfa, est, fin,
                      trans, currentState);
                } else {
                  // System.out.println("final");

                  int contaa = 0;
                  int c = 0;

                  for (int q = 0; q < trans[cont][currentState].length(); q++) {
                    for (int n = 0; n < est.length; n++) {
                      if (trans[cont][currentState].charAt(q) == est[n].charAt(0)) {
                        contaa++;
                      }
                    }
                  }
                  if (contaa > 1) {
                    char caracter = split2[0].charAt(2);
                    String nuevoValor = Character.toString(caracter);
                    getTransition(Integer.parseInt(nuevoValor), cuerda.substring(1, cuerda.length()), alfa, est, fin,
                        trans, currentState);
                  } else if (contaa == 1) {
                    getTransition(Character.getNumericValue(trans[cont][currentState].charAt(0)),
                        cuerda.substring(1, cuerda.length()), alfa, est, fin, trans, currentState);
                  }
                }
              } else {
                getTransition(Integer.parseInt(trans[contador][currentState]), cuerda.substring(1, cuerda.length()),
                    alfa, est, fin, trans, currentState);
              }
            }

            break;
          } else {
            cont++;
            contador++;
          }
        }
        // return currentState;
        // return false;
      }
    } catch (FileNotFoundException ex2) {
      System.out.println("Nunca tendría que activarse, pero por si acaso :v");
      // return 0;
      // return false;
    }
    // return false;
  }

  public boolean acceptFinal(String string) {
    try {
      AFN prueba = new AFN(this.file);
      File file = new File(prueba.file);
      Scanner obj = new Scanner(file);
      String alfabeto = obj.nextLine();
      String estados = obj.nextLine();
      String finales = obj.nextLine();
      int states = Integer.parseInt(estados);
      String[] alfa = alfabeto.split(",");
      String[] est = new String[states];
      String[] fin = finales.split(",");
      String[] alfaafn = new String[alfa.length + 1];
      alfaafn[0] = "";
      int conta = 0;
      for (int m = 1; m < alfaafn.length; m++) {
        alfaafn[m] = alfa[conta];
        conta++;
      }
      int pos = 0;
      for (int i = 0; i < states; i++) {
        String x = i + "";
        est[pos] = x;
        pos++;
      }
      String[] estad = new String[est.length];
      for (int z = 0; z < est.length; z++) {
        estad[z] = est[z];
      }
      String[][] transiciones = new String[alfaafn.length][est.length];
      for (int fila = 0; fila < alfaafn.length; fila++) {
        String linea = obj.nextLine();
        est = linea.split(",");
        int val = 0;
        for (int columna = 0; columna < est.length; columna++) {
          String v = est[val];
          transiciones[fila][columna] = v;
          val++;
        }
      }
      getTransition(1, string, alfaafn, estad, fin, transiciones, 1);
     //System.out.println("accept: " + verificacion);
      if (verificacion == false) {
        obj.close();
        return false;
      }
      // for(int j = 0; j<fin.length;j++){
      // int valor = Integer.parseInt(fin[j]);
      if (verificacion == true) {
        obj.close();
        return true;
      }
      // }
      obj.close();
    } catch (FileNotFoundException ex) {
      System.out.println("El archivo no existe");
    }
    return false;
  }

  public void check(String archivo, String result, String txtcuerdas) {
    LinkedList<String> cuerdas = new LinkedList<String>();
    LinkedList<String> cuerdas2 = new LinkedList<String>();
    int cantlineas = 0;

    try {
      File file = new File(txtcuerdas);
      Scanner leer = new Scanner(file);
      while (leer.hasNextLine()) {
        String cuerda = leer.nextLine();
        cantlineas++;
      }
      File file2 = new File(txtcuerdas);
      Scanner leer2 = new Scanner(file2);
      String c = "";
      int contador = 0;

      while (leer2.hasNextLine()) {
        String linea = leer2.nextLine();
        String datos[] = linea.split(",");
        cuerdas.add(datos[0]);
        cuerdas2.add(datos[1]);
        /*
         * if(acceptFinal(cuerdas.getFirst(),this.cuerdas)==true){
         * System.out.println("La cuerda fue aceptada");
         * cuerdas.removeFirst();
         * }
         * else{
         * System.out.println("La cuerda fue rechazada");
         * }
         */
        contador++;
      }

      String ruta5 = result + "resultado.txt";
      File file5 = new File(ruta5);
      if (!file5.exists()) {
        file5.createNewFile();
      }
      FileWriter fw5 = new FileWriter(file5);
      BufferedWriter bw5 = new BufferedWriter(fw5);
      for (int i = 0; i < cuerdas.size(); i++) {
        String cuerdafinal = cuerdas.get(i);
        if (i != cuerdas.size() - 1) {

          if (acceptFinal(cuerdafinal) == true) {
            bw5.write("aceptada" + "\n");
          } else {
            bw5.write("rechazada" + "\n");
          }
        } else {
          if (acceptFinal(cuerdafinal) == true) {
            bw5.write("aceptada");
          } else {
            bw5.write("rechazada");
          }
        }
      }
      bw5.close();
      //System.out.println(cuerdas);
    } catch (FileNotFoundException ex2) {
      System.out.println("Nunca tendría que activarse, pero por si acaso :v");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
