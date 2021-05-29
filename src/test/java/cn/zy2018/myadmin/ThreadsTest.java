package cn.zy2018.myadmin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadsTest{

    @Test
    public void test(){
        CyclicBarrier cb = new CyclicBarrier(2);

        Thread t1 =  new Thread(){
            @Override
            public void run() {
                for(int i = 0;i<50;i++){
                    System.out.print("a");
                }
                try {
                    cb.await();//等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("");
                System.out.println("a执行完毕!");

            }
        };
        Thread t2 =  new Thread(){
            @Override
            public void run() {
                for(int i = 0;i<50;i++){
                    System.out.print("b");
                }
                try {
                    cb.await();//等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("");
                System.out.println("b执行完毕!");
            }
        };
        t1.start();
        t2.start();


    }

}
