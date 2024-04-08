import { useRef, useState } from "react";

export default function Chatting(props){

    // 1. 해당 컴포넌트가 렌더링 될때 소켓은 재렌더링 방지
        // useRef(초기값) : {current : 값} // 해당 컴포넌트가 렌더링시 참조값을 고정
    let clientSocket = useRef(null);

    // 2. Ref 참조가 없으면
    if(!clientSocket.current){
        // ==================== (클라이언트) Web Socket ==================== //
                // new WebSocket(서버 소켓 URL); // 비동기 // 서버소켓에게 접속 요청
            clientSocket.current = new WebSocket('ws://192.168.17.131:80/chat');
            console.log(clientSocket);
                // 각 메소드 정의 // onclose // onerror // onmessage // onopen : Web Socket 객체 내 포함된 메소드들
                    // 클라이언트 소켓이 close 되었을 때 콜백함수 정의
            clientSocket.current.onclose = (e)=>{console.log(e);}
                    // 클라이언트 소켓이 error 발생했을 때 콜백함수 정의
            clientSocket.current.onerror = (e)=>{console.log(e);}
                    // 클라이언트 소켓이 message 받았을 때 콜백함수 정의
            clientSocket.current.onmessage = (e)=>{
                console.log(e);
                msgList.push(e.data);
                setMsgList([...msgList]);
            }
                    // 클라이언트 소켓이 open 되었을 때 콜백함수 정의
            clientSocket.current.onopen = (e)=>{console.log(e);}
        // ================================================================ //
    }

    const [msgInput, setMsgInput] = useState('');
    const [msgList, setMsgList] = useState([]);

    const onSend = ()=>{
        // 연결된 서버소켓에게 메시지 보내기
        clientSocket.current.send(msgInput);
    }

    return(<>
        <div>
            <h3>채팅방</h3>
            <div>
                {msgList.map((msg)=>{
                    return <div>{msg}</div>
                })
                }
            </div>
            <textarea value={msgInput} onChange={(e)=>{setMsgInput(e.target.value)}}></textarea>
            <button type="button" onClick={onSend}>전송</button>
        </div>
    </>)
}