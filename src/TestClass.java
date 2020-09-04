import java.io.*;
import java.util.*;


public class TestClass {
       // static ArrayList<String> tempor=new ArrayList<>();
        public static void main(String[] args) throws IOException {
            //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            //PrintWriter wr = new PrintWriter(System.out);
            //int T = Integer.parseInt(br.readLine().trim());
            //for(int t_i=0; t_i<T; t_i++)
            //{
                //int N = Integer.parseInt(br.readLine().trim());

                //long out_ = CountWays(2);
                CountWays(2);
               // System.out.println(out_);
           // }

           // wr.close();
           // br.close();
        }
        static void CountWays(int N){
            // Write your code here
            String str="";
            ArrayList<Character> list =new ArrayList<>();
            ArrayList<String> result=new ArrayList<>();
            long countways=0;
            //ArrayList<Integer> res = new ArrayList<>();
            for(int i=1;i<=N;i++){
                str+=Integer.toString(i);
            }
            //System.out.println(str);
            ArrayList<String> tempor=combinations(str.toCharArray());
            //boolean answer=list.isEmpty();
            //System.out.println(answer);
            for(String c:tempor){
               // char ch=c;
                String s=c;
                while(s.length()<=6){
                    s=s+s;
                }
                // System.out.println(s);
                result.add(s);
            }

            System.out.println(tempor);
            /*for(String l:result){
                //int num=Integer.ParseInt(l);
                char[] ch=l.toCharArray();
                int[] arr=new int[ch.length];
                for(int i=0;i<ch.length;i++){
                    arr[i]=Integer.parseInt(String.valueOf(ch[i]));
                    // boolean m=isDivisible(arr[i],)
                }
                boolean m=isDiv(arr);
                if(m==true){
                    countways++;
                }
            }

            return countways;*/
        }

        static boolean isDiv(int a[]){
            int i,j,k,x,y,z;
            i=a[0];
            j=a[1];
            k=a[2];
            x=a[3];
            y=a[4];
            z=a[5];

            int temp1=(i+(2*j)+k);
            int temp2=(x+y+(2*z));
            if(temp1%temp2==0){
                return true;
            }
            return false;
        }

        static ArrayList<String> combinations(char[] in){
            //r.forEach
            ArrayList<String> list = new ArrayList<>();
            Map<Character,Integer> countmap=new HashMap<>();
            for (char ch:in){
                if(!countmap.containsKey(ch)){
                    countmap.put(ch,1);
                }
                else{
                    countmap.put(ch,countmap.get(ch)+1);
                }
            }

            char str[]=new char[countmap.size()];
            int count[]=new int[countmap.size()];
            int index=0;
            for (Map.Entry<Character,Integer> entry:countmap.entrySet()){
                str[index]=entry.getKey();
                count[index]=entry.getValue();
                index++;
            }
            char[] output=new char[in.length];
            combination(str,count,0,output,0,list);

            return list;
        }

        static void combination(char input[],int count[],int pos,char output[],int len,ArrayList<String> list){

            add(output,len,list);
            for (int i=pos;i<input.length;i++){
                if (count[i]==0)
                    continue;
                output[len]=input[i];
                count[i]--;
                combination(input,count,i,output,len+1,list);
                count[i]++;
            }
        }

        static void add(char result[],int pos,ArrayList<String> list){
            String res="";
            for (int i=0;i<pos;i++){
                res=res+String.valueOf(result[i]);
            }
            list.add(res);
        }
}

