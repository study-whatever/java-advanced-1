## 자바 메모리 구조 복습
![Pasted image 20250128161734.png](../image/Pasted%20image%2020250128161734.png)![[Pasted image 20250128161734.png]]
- 메서드 영역
    - 프로그램을 실행하는데 필요한 공통 데이터 관리
    - 클래스 정보: 클래스의 실행 코드, 필드, 메서드와 생성자 코드 등
    - static 영역: static 변수 들
    - 런타임 상수 풀: 프로그램 실행에 필요한 공통 리터럴 상수들
- 스택 영역
    - 자바 실행 시, 하나의 실행 스택이 생성됨
    - 각 스택 프레임은 지역 변수, 중간 연산 결과, 메서드 호출 정보를 포함
    - 스택 프레임: 스택 영역에 쌓이는 네모박스 하나, 메서드 호출때마다 쌓인다
    - **각 스레드별로 하나의 실행 스택이 생성된다**
- 힙 영역
    - 객체와 배열이 생성되는 영역
    - 가비지 컬렉션이 이루어지는 주요 영역

## 스레드 생성
```java
package thread.start;  
  
public class HelloThreadMain {  
    public static void main(String[] args) {  
        System.out.println(Thread.currentThread().getName() + ": main() start"); // main  
        HelloThread helloThread = new HelloThread();  
        System.out.println(Thread.currentThread().getName() + ": start() 호출 전");  
        helloThread.start(); //  
        System.out.println(Thread.currentThread().getName() + ": start() 호출 ");  
        System.out.println(Thread.currentThread().getName() + ": main() end"); // main  
    }  
}
```
![Pasted image 20250128163355.png](../image/Pasted%20image%2020250128163355.png)

> run 메서드가 아니라 반드시 start() 메서드를 호출해야 스레드에서 run 코드가 실행된다

- main() 메서드는 main 스레드가 실행한다
  스레드 생성 후 start 메서드 호출 -> 이 스레드를 위한 별도 스택 공간 할당
- start()를 호출해야 스택 공간이 할당된다
- 이름은 Thread-0 같은 임의의 이름을 부여
- 이 스레드가 run() 메서드의 스택 프레임을 스택에 올리면서 메서드를 시작한다
  ![Pasted image 20250128163624.png](../image/Pasted%20image%2020250128163624.png)

**main 스레드가 run() 메서드를 실행하는게 아니라, Thread-0 스레드가 run() 메서드를 호출한다**
main 스레드는 다른 스레드에게 실행을 지시할 뿐

#### 스레드 간 실행 순서는 보장하지 않는다
스레드는 동시에 실행되니까
그리고 스레드가 얼마나 오랜 기간 실행되는지도 보장x
-> 순서와 실행 기간을 모두 보장하지 않는다

## start() vs run()
start()를 하지 않고 run()을 바로 실행하면?
```
main: main() start
main: start() 호출 전
main: run()
main: start() 호출 
main: main() end
```
main이 스레드를 실행한다

스레드의 start(): 스레드에 스택 공간 할당 + 스레드 시작

## 데몬 스레드
스레드의 종류: 사용자, 데몬
**사용자 스레드**
- 프로그램의 주요 작업 수행
- 작업이 완료될 때까지 실행
- 모든 user 스레드가 종료되면 JVM도 종료
  **데몬 스레드**
- 백그라운드에서 보조적인 작업 수행
- 모든 user 스레드가 종료되면 데몬 스레드는 자동 종료

```java
package thread.start;  
  
public class DaemonThreadMain {  
    public static void main(String[] args) {  
        System.out.println(Thread.currentThread().getName() + ": main() start"); // main  
        DaemonThread daemonThread = new DaemonThread();  
        daemonThread.setDaemon(true); // 데몬 스레드로 설정  
        daemonThread.start();  
        System.out.println(Thread.currentThread().getName() + ": main() end"); // main  
    }  
  
    static class DaemonThread extends Thread {  
        @Override  
        public void run() {  
            System.out.println(Thread.currentThread().getName() + ": run()");  
  
            try {  
                Thread.sleep(10000); // 10초 대기  
            } catch (InterruptedException e) {  
                throw new RuntimeException(e);  
            }  
            System.out.println(Thread.currentThread().getName() + ": run() end");  
        }  
    }  
}
```
데몬 스레드를 true로 하면, 데몬 스레드의 실행을 기다리지 않고 jvm은 종료된다

> 참고: run() 메서드 안에서 Thread.sleep()을 호출할 때 InterruptedException은 반드시 catch해야 한다
> 왜인지는 뒤에서 설명


## 스레드 생성 - Runnable
Runnable 인터페이스 구현

```java
public class HelloRunnableMain {  
    public static void main(String[] args) {  
        System.out.println(Thread.currentThread().getName() + ": main() start"); // main  
  
        HelloRunnable helloRunnable = new HelloRunnable();  
        Thread thread = new Thread(helloRunnable); // Runnable을 Thread 생성자에 전달  
        thread.start();  
  
        System.out.println(Thread.currentThread().getName() + ": main() end"); // main  
    }  
}
```
스레드와 해당 스레드가 실행할 작업을 분리

### Thread 상속 vs Runnable 구현
Runnable 인터페이스 구현을 사용해야 한다

Thread 상속
- 장점
    - 간단한 구현: run()만 재정의
- 단점
    - 자바는 단일 상속만 허용, 상속의 제한
    - 유연성 부족
      Runnable 인터페이스 구현
- 장점
    - 상속의 자유로움
    - 코드 분리
    - 여러 스레드가 동일한 Runnable 객체를 공유할 수 있음 -> 자원 관리가 효율적
- 단점
    - 코드가 복잡해질 수 있다

## 로거 만들기
```java
package util;  
  
import java.time.LocalTime;  
import java.time.format.DateTimeFormatter;  
  
public abstract class MyLogger {  
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");  
  
    public static void log(Object obj) {  
        String time = LocalTime.now().format(formatter);  
        System.out.printf("%s [%9s] %s\n", time, Thread.currentThread().getName(), obj);  
    }  
}
```

## 여러 스레드 만들기
```java
package thread.start;  
  
import static util.MyLogger.log;  
  
public class ManyThreadMainV1 {  
    public static void main(String[] args) {  
        log("main() start");  
  
        HelloRunnable helloRunnable = new HelloRunnable();  
        Thread thread1 = new Thread(helloRunnable);  
        thread1.start();  
        Thread thread2 = new Thread(helloRunnable);  
        thread2.start();  
        Thread thread3 = new Thread(helloRunnable);  
        thread3.start();  
  
        log("main() end");  
    }  
}
```

3개 스레드 모두 helloRunnable 인스턴스의 run() 메서드를 실행한다

## Runnable을 만드는 다양한 방법
중첩 클래스를 사용하면 더 편리하다
- static class
- 익명 클래스
- 람다

