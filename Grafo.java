package Grafo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Grafo {
    int N;
    int[][] MatrizAdj;
    ArrayList<Integer>[] ListaAdj;
    
    public Grafo(int N) {
        this.N = N;
        MatrizAdj = new int[N][N];
        ListaAdj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            ListaAdj[i] = new ArrayList<Integer>();
        }
    }
    public void DeepSearch(int s, int t) {
        boolean[] visitado = new boolean[N];
        int[] pai = new int[N];
        Arrays.fill(pai, -1);
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(s);
        visitado[s] = true;

        while (!stack.isEmpty()) {
            int u = stack.pop();

            if (u == t) {
                int v = t;
                System.out.print(v);
                while (pai[v] != -1) {
                    System.out.print(" <- " + pai[v]);
                    v = pai[v];
                }
                return;
            }

            for (int v : ListaAdj[u]) {
                if (!visitado[v]) {
                    visitado[v] = true;
                    pai[v] = u;
                    stack.push(v);
                }
            }
        }
    }    
    
    public ArrayList<Integer> BreadthSearch(int s, int t) {
        boolean[] visitado = new boolean[N];
        int[] pai = new int[N];
        Queue<Integer> fila = new LinkedList<Integer>();
        ArrayList<Integer> caminho = new ArrayList<Integer>();

        fila.offer(s);
        visitado[s] = true;
        pai[s] = -1;

        while (!fila.isEmpty()) {
            int u = fila.poll();

            if (u == t) {
                int v = t;
                while (v != -1) {
                    caminho.add(v);
                    v = pai[v];
                }
                Collections.reverse(caminho);
                return caminho;
            }

            for (int i = 0; i < ListaAdj[u].size(); i++) {
                int v = ListaAdj[u].get(i);
                if (!visitado[v]) {
                    visitado[v] = true;
                    pai[v] = u;
                    fila.offer(v);
                }
            }
        }
        return null;
    }
}