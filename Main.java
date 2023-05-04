package Grafo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Grafo loadFrom(String NomeArquivo) throws FileNotFoundException {
        File file = new File(NomeArquivo);
        Scanner scanner = new Scanner(file);
        
        int n = Integer.parseInt(scanner.nextLine());
        Grafo g = new Grafo(n);
        
        int l = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            String[] numeros = line.split("\t");

            int c = 0;
            for (int j = 0; j < g.N; j++) {
                int i = Integer.parseInt(numeros[j]);
                g.MatrizAdj[l][c] = i;
                if (g.MatrizAdj[l][c] > 0) {
                    g.ListaAdj[l].add(c);
                }
                c++;
            }
            l++;
        }
        for (int i = 0; i < n; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < g.ListaAdj[i].size(); j++) {
                int adjacente = g.ListaAdj[i].get(j);
                int peso = g.MatrizAdj[i][adjacente];
                System.out.print(adjacente + "(" + peso + ") ");
            }
            System.out.println();
        }
        scanner.close();
        return g;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Grafo grafo = loadFrom("/Users/kteot/OneDrive/Área de Trabalho/Java/src/Grafo/pcv10.txt");
        
      System.out.println("Lista de adjacência:");
      for (int i = 0; i < grafo.ListaAdj.length; i++) {
          System.out.println("linha : " + i + " - " + grafo.ListaAdj[i]);
      }
      System.out.println("Matriz do arquivo selecionado:");
      for (int i = 0; i < grafo.MatrizAdj.length; i++) {
       for (int j = 0; j < grafo.MatrizAdj.length; j++) {
        System.out.printf(grafo.MatrizAdj[i][j]+"\t");
       } 
       System.out.println();
      }
      
    int s = 1,  t = 5;  
    ArrayList<Integer> caminho = grafo.BreadthSearch(s, t);
    if (caminho == null) {
        System.out.println("Não há caminho entre os vértices");
    } else {
        System.out.print("Caminho entre " + s + " e " + t + ": ");
        for (int i = 0; i < caminho.size(); i++) {
            System.out.print(caminho.get(i));
            if (i < caminho.size() - 1) {
                System.out.print(" -> ");
            
            }
        }
    } 
    System.out.println("\nBusca em profundidade: ");
    int k = 3, y = 9;
    System.out.println("Caminho entre " + k + " e " + y + ": ");
    grafo.DeepSearch(k, y) ;
    }
}
