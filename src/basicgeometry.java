
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class basicgeometry {
    
     public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner();
        
        int n=Integer.parseInt(sc.next());
        String alergia="";
        while(n!=0){
            alergia+=sc.next();
            n--;
        }
        int x=Integer.parseInt(sc.next());
        String alimentos="";
        while(x!=0){
            alimentos+=sc.next();
           x--; 
        }
        String no="";
        int t1=alimentos.length()-1;
         while (t1!= 0) {
             int t2 = alergia.length()-1;
             while (t2 != 0) {
                 if(alergia.charAt(t2)==alimentos.charAt(t1)){
                     break;
                 }else{
                    no+=" "+alergia.charAt(t2);
                 }
                 t2--;
             }
             t1--;
         }
         System.out.println(no.substring(1, no.length()));
        
    }
    
    static class Scanner {

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer("");
        int espacios = 0;

        public String nextLine() throws IOException {
            if (espacios > 0) {
                espacios--;
                return "";
            } else if (st.hasMoreTokens()) {
                StringBuilder salida = new StringBuilder();
                while (st.hasMoreTokens()) {
                    salida.append(st.nextToken());
                    if (st.countTokens() > 0) {
                        salida.append(" ");
                    }
                }
                return salida.toString();
            }
            return br.readLine();
        }

        public String next() throws IOException {
            espacios = 0;
            while (!st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        public boolean hasNext() throws IOException {
            while (!st.hasMoreTokens()) {
                String linea = br.readLine();
                if (linea == null) {
                    return false;
                }
                if (linea.equals("")) {
                    espacios++;
                }
                st = new StringTokenizer(linea);
            }
            return true;
        }
    }
}
    
   

    
