# Job-scheduling



## Approximation Algorithms(근사 알고리즘)

- **NP-완전 문제**를 해결
- 최적해에 아주 근사한(가까운) 해를 찾아주는 것
- 근사해를 찾는 대신에 **다항식 시간의 복잡도**를 가진다.
- 근사해가 얼마나 최적해에 근사한지를 나타내는 **근사비율**을 알고리즘과 함께 제시해야한다.
  - 1.0에 가까울수록 정확도가 높은 알고리즘이다.
  - 근사 비율을 계산하려면 최적해를 알아야하는 모순이 생긴다.
- 최적해를 대신할 수 있는 **간접적인 최적해**를 찾고, 이를 최적해로 삼아서 근사 비율을 계산한다.



---

### Job Scheduling problem

- ```n```개의 작업, 각 작업의 수행시간 ```ti``` (i=0 ~ n-1), ```m```개의 동일한 기계가 주어질 때, 모든 작업이 가장 빨리 종료되도록 작업을 기계에 배정하는 문제이다.

- 단, 한 작업은 배정된 기계에서 연속적으로 수행되어야 한다.

  또한 기계는 1번에 하나의 작업만을 수행한다.

#### 1. Greedy Algorithm

- N = [4, 8, 16] (작업의 갯수) 
- M = 2 (기계의 갯수)

```java
 public static int[] random() {
        Random random = new Random();
        int[] elements = new int[N];

        for(int i=0; i<N;i++){
            int x =random.nextInt(10);//10초이내
            if(x!=0){
                elements[i] = x;
            }
            else
                i--;//0일경우
        }
        return elements;
    }
```

- 작업시간 ti를 10초 이내의 랜덤값으로 정해준다.
- 이때 작업시간이 0일경우, 다시 랜덤값으로 정해준다.

```java
public static int scheduling(int a[]){
        int L[] = new int[M];
        for(int j=0;j<M;j++){
            L[j]=0;//L[j] = 기계 Mj에 배정된 마지막 작업의 종료시간 
            for(int i =0;i<N;i++){
                int min=0;
                for(j=1;j<M;j++){//가장 일찍 끝나는 기계를 찾는다.
                    if(L[j]<L[min])
                        min =j; // 더 작은 값의 index를 갱신시킨다.
                }
            L[min] = L[min] + a[i];//작업i를 기계 Mmin에 배정한다.
            machine[min].add(a[i]);
            }
        }
        if(L[0]>L[1])
            return  L[0];
        else
            return L[1];//기계1과 기계2 중 가장 늦은 작업 종료시간을 리턴해준다.
    }
```

- N = 4
- N = 8
- N = 16

#### 2. Brute force 

```java
public static ArrayList<Integer> sum = new ArrayList();

public static void bruteforce(int a[],boolean[] visited,int n, int num){//a[]의 부분집합을 구한다.
        if (num == n) {
            print(a, visited, n);
            return;
        }
        visited[num] = false;
        bruteforce(a, visited, n, num + 1);

        visited[num] = true;
        bruteforce(a, visited, n, num + 1);//방문여부의 visited 배열, 재귀함수 활용
    }
    static void print(int[] a, boolean[] visited, int n) {
        int sum1 = 0;//기계1
        int sum2 = 0;//기계2

        for (int i = 0; i < n; i++) {
            if (visited[i] == true) 
                sum1 = sum1 + a[i]; //기계1 부분집합의 합구하기
            
            if (visited[i] == false)
                sum2 = sum2 + a[i];//a[]-(기계의 부분집합)= 기계2의 부분집합의 합 구하기
        }
        if(sum1>=sum2)
            sum.add(sum1);
        else
            sum.add(sum2); //더 늦은 종료시간을 Arraylist sum에 넣어준다.
    }
```

- 더 늦은 종료시간을 넣어줌으로 공집합일 때 합 0은 존재하지 않는다.

