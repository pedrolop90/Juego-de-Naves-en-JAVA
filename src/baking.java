import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baking {
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner();
        int vector[]=null;
        while(sc.hasNext()){
            int n=Integer.parseInt(sc.next());
            int perimetro=0;
            vector=new int[n];
            for (int i = 0; i < n; i++) {
                 vector[i]=Integer.parseInt(sc.next());
                 perimetro+=vector[i];
            }
            if(perimetro!=n){
                if(perimetro==3&&n==3){
                    System.out.println(3);
                }else if(n==3&&perimetro>3){
                    System.out.println(-1);
                }
                if(perimetro%2==0){
                    System.out.println(perimetro/2);
                }else{
                    System.out.println(-1);
                }
            }else{
                System.out.println(n);
            }
            
            
        }
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
