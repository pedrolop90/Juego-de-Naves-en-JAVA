import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeMap;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        TreeMap<Integer,Integer> mayores= new TreeMap<Integer,Integer>();
        LinkedList<Integer> pila=new LinkedList<Integer>();
        LinkedList<Integer> cola=new LinkedList<Integer>();
        while (sc.hasNext()) {
            boolean p = true;
            boolean c = true;
            boolean cP = true;
            int tamaño = sc.nextInt();
            int aux=0;
            while(tamaño>0) {
                if (sc.nextInt()== 1) {
                     aux= sc.nextInt();
                    if(mayores.get(aux)!=null){
                        mayores.put(aux, mayores.get(aux)+1); 
                    }else{
                        mayores.put(aux, 1); 
                    }
                    pila.addFirst(aux);
                    cola.addLast(aux);
                } else{
                     aux = sc.nextInt();
                    if (mayores.size()>0) {
                        if (c&&cola.getFirst() != aux) {
                            c=false;
                        }
                        if (p&&pila.getFirst() != aux) {
                            p=false;
                        }
                        if (cP&&mayores.lastKey()!= aux) {
                            cP=false;
                        }
                        if((mayores.get(mayores.lastKey())-1)>0){
                            mayores.put(mayores.lastKey(), mayores.get(mayores.lastKey())-1); 
                        }else{
                            mayores.remove(mayores.lastKey());
                        }
                        cola.removeFirst();
                        pila.removeFirst();
                    }
                }
                tamaño--;
            }
            if((p&&c)||(p&&cP)||(cP&&c)){
               System.out.println("not sure");
            }else if(p){
                 System.out.println("stack");
            }else if(c){
                System.out.println("queue");
            }else if(cP){
                System.out.println("priority queue");
            }else{
                System.out.println("impossible"); 
            }
            mayores.clear();
            cola.clear();
            pila.clear();
        }
    }
}