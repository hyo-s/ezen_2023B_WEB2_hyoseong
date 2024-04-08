package ezenweb.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Component
public class ChatSocket extends TextWebSocketHandler {
    
    // 접속 성공한 session들을 모아두기 (접속명단)
    private List<WebSocketSession> socketSessionList = new Vector<>();

    // 클라이언트 소켓이 서버 소켓에 접속성공일 때 (session : 클라이언트의 소켓 정보)
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("session = " + session);
        // 접속한 세션정보를 리스트에 담기
        socketSessionList.add(session);
        System.out.println("socketSessionList = " + socketSessionList);
    }
    // 클라이언트로부터 서버가 메시지를 받았을 때 (session : 메시지를 보낸 클라이언트의 소켓, message : 클라이언트에게 받은 메시지)
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("session = " + session + ", message = " + message);
        System.out.println("message.getPayload() = " + message.getPayload());
        // 접속 명단의 클라이언트 소켓에 메시지 보내기
        for(WebSocketSession socketSession : socketSessionList){
            socketSession.sendMessage(message);
        }
    }
    // 클라이언트 소켓과 접속 종료 되었을 때 (session : 접속이 종료된 클라이언트 소켓 정보)
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        socketSessionList.remove(session);
    }
}
