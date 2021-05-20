import java.util.*;

public class JobScheduling2 {
    public static int M = 2;// 기계수
    public static int N = 16;// 작업수
    public static ArrayList[] machine = new ArrayList[2];
    public static ArrayList<Integer> sum = new ArrayList();
    public static int[] random() {
        Random random = new Random();
        int[] elements = new int[N];

        for(int i=0; i<N;i++){
            int x =random.nextInt(10);
            if(x!=0){
                elements[i] = x;
            }
            else
                i--;
        }
        return elements;
    }
    public static void bruteforce(int a[],boolean[] visited,int n, int num){
        if (num == n) {
            print(a, visited, n);
            return;
        }

        visited[num] = false;
        bruteforce(a, visited, n, num + 1);

        visited[num] = true;
        bruteforce(a, visited, n, num + 1);
    }
    static void print(int[] a, boolean[] visited, int n) {
        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i] == true)
                sum1 = sum1 + a[i];

            if (visited[i] == false)
                sum2 = sum2 + a[i];
        }
        if(sum1>=sum2)
            sum.add(sum1);
        else
            sum.add(sum2);
    }
    public static int scheduling(int a[]){
        int L[] = new int[M];
        for(int j=0;j<M;j++){
            L[j]=0;
            for(int i =0;i<N;i++){
                int min=0;
                for(j=1;j<M;j++){
                    if(L[j]<L[min])
                        min =j;
                }
            L[min] = L[min] + a[i];
            machine[min].add(a[i]);

            }
        }
        if(L[0]>L[1])
            return  L[0];
        else
            return L[1];
    }
    public static void main(String arg[]){
        machine[0] = new ArrayList();
        machine[1] = new ArrayList();

        boolean[] visited = new boolean[N];

        int job[] = random();

        int result1=scheduling(job);
        for(int i =0;i<N;i++){
            System.out.print(job[i]+" ");
        }
        System.out.println();
        for(int i =0;i<M;i++){
            System.out.print("machine"+(i+1)+" : ");
            for(int j =0; j <machine[i].size();j++)
                System.out.print(machine[i].get(j) +" ");
            System.out.println();
        }
        System.out.println("근사 종료시간 : "+result1);

        bruteforce(job, visited, N, 0);
        int result2 = sum.get(0);
        for(int i =1;i<sum.size();i++){
            if(result2> sum.get(i) && sum.get(i)!=0)
                result2 = sum.get(i);
        }
        System.out.println("최적해 종료시간 : "+result2);
    }
}
