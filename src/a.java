import java.util.Scanner;
import java.util.TreeMap;


public class a {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeMap<Integer,Integer> d=new TreeMap<Integer,Integer>();
        TreeMap<Integer,Integer> e=new TreeMap<Integer,Integer>();
        while(sc.hasNext()){
            int n=sc.nextInt();
            int c=0;
            int numero=0;
            while(n!=0){
                numero=sc.nextInt();
                c=sc.next().charAt(0);
                if(c=='D'){
                    if(d.get(numero)!=null){
                        d.put(numero, d.get(numero)+1);
                    }else{
                        d.put(numero, 1);
                    }
                }else {
                    if(e.get(numero)!=null){
                        e.put(numero, e.get(numero)+1);
                    }else{
                        e.put(numero, 1);
                    }
                }
                n--;
            }
            int cont=0;
            while(d.size()!=0&&e.size()!=0){
              if(d.get(e.lastKey())!=null){
                   if((d.get(e.lastKey())-1)>0){
                       d.put(e.lastKey(), d.get(e.lastKey())-1);
                       if((e.get(e.lastKey())-1)>0){
                           e.put(e.lastKey(), e.get(e.lastKey())-1);
                       }else{
                           e.remove(e.lastKey());
                       }
                   }else{
                       d.remove(e.lastKey());
                   }
                   cont++;
              }else{
                  e.pollLastEntry();
              }
            }
            d.clear();
            e.clear();
            System.out.println(cont);
        }
        
       
    }
}
