package cn.zy2018.myadmin;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;
import java.net.URI;

@ClientEndpoint
public class webSocketConnectionTest {
    private static volatile int devicecount;

    public webSocketConnectionTest () {}

    protected boolean start() {
        WebSocketContainer Container = ContainerProvider.getWebSocketContainer();
        String uri = "ws://47.104.98.66:10005/ws";
        try {
            Container.connectToServer(webSocketConnectionTest.class, URI.create(uri));
            devicecount++;
            System.out.println("Connecting to " + uri+" "+ devicecount);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * 测试websocket netty
     */
    @Test
    public void websocketNetty (){
        for (int i = 1; i < 10000; i++) {
            try{
                webSocketConnectionTest wSocketTest = new webSocketConnectionTest();
                if (!wSocketTest.start()) {
                    System.out.println("测试结束！");
//                    break;
                }else{
                    System.out.println("连接成功！");
                }
            }catch (Exception e){

            }

        }
    }

}
