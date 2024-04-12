import { useContext, useEffect, useRef, useState } from "react";
import { LoginInfoContext } from "../Index";
import './chatting.css'
import * as React from 'react';
import Button from '@mui/material/Button';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import { type } from "@testing-library/user-event/dist/type";

export default function Chatting(props){

    const [anchorEl, setAnchorEl] = React.useState(null);
    const open = Boolean(anchorEl);
    const handleClick = (event) => {
      setAnchorEl(event.currentTarget);
    };
    const handleClose = () => {
      setAnchorEl(null);
    };

    const {loginInfo} = useContext(LoginInfoContext);

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
                console.log(e.data);
                msgList.push(JSON.parse(e.data));
                setMsgList([...msgList]);
            }
                    // 클라이언트 소켓이 open 되었을 때 콜백함수 정의
            clientSocket.current.onopen = (e)=>{console.log(e);}
        // ================================================================ //
    }

    const [msgInput, setMsgInput] = useState('');
    const [msgList, setMsgList] = useState([]);
    const activeEnter = (e)=>{
        console.log(e)
        if(e.keyCode == 13 && e.ctrlKey){
            console.log(msgInput);
            setMsgInput(msgInput + '\n'); return;
        }
        if(e.keyCode == 13){
            onSend(e); return;
        }
    }
    

    const onSend = (e)=>{
        // 연결된 서버소켓에게 메시지 보내기
        let info = {
            msg : msgInput,
            fromMname : loginInfo.mname,
            type : 'msg',
            date : new Date().toLocaleString()
        }
        clientSocket.current.send(JSON.stringify(info));
        console.log(msgList);
        e.preventDefault();
        setMsgInput('');
    }
    useEffect(()=>{
        let chatcont = document.querySelector('.chatcont')
        console.log(chatcont.scroll);
        console.log(chatcont.scrollTop);
        console.log(chatcont.scrollHeight);
        chatcont.scrollTop = chatcont.scrollHeight;
    })

    console.log(Array(43))
    console.log(Array(43).fill(6))

    const onEmoSend = (emo)=>{
        console.log(emo);
        let info = {
            msg : emo,
            fromMname : loginInfo.mname,
            type : 'emo',
            date : new Date().toLocaleString()
        }
        clientSocket.current.send(JSON.stringify(info));
        handleClose();
    }

    // msg타입에 따른 HTML 변수
    const typeHTML = (m)=>{
        if(m.type == 'msg'){
            return (<div className="content">{m.msg}</div>)
        }else if (m.type =='emo'){
            return (<img src={'/emo/'+m.msg} />)
        }
    }

    return (<>
        <h3> 채팅방 </h3>
        <div className="chatbox">
            <div className="chatcont">
                {
                    msgList.map( (m)=>{
                        return (<>
                            {
                                loginInfo.mname == m.fromMname ? 
                                    (
                                        <div className="rcont">
                                            <div className="subcont">
                                                <div className="date"> {m.date} </div>
                                                {typeHTML(m)}
                                            </div>
                                        </div>
                                    ) :
                                    <div className="lcont">
                                        <img className="pimg" src="/uploadimg/default.png" />
                                        <div className="tocont">
                                            <div className="name">{m.fromMname} </div>
                                            <div className="subcont" >
                                                    {typeHTML(m)}
                                            <div className="date"> {m.date} </div>
                                            </div>
                                        </div>
                                    </div>
                            }       
                        </>);
                    })
                }
            </div>
            <div className="chatbottom">
                <textarea value={msgInput} onChange= { (e)=>{ setMsgInput( e.target.value) }} onKeyDown={activeEnter} > </textarea>
                <button type="button" onClick={ onSend }> 전송 </button>
            </div>
            <div>
                <div>
                    <Button
                        id="basic-button"
                        aria-controls={open ? 'basic-menu' : undefined}
                        aria-haspopup="true"
                        aria-expanded={open ? 'true' : undefined}
                        onClick={handleClick}
                    >
                        이모티콘
                    </Button>
                    <Menu
                        id="basic-menu"
                        anchorEl={anchorEl}
                        open={open}
                        onClose={handleClose}
                        MenuListProps={{
                        'aria-labelledby': 'basic-button',
                        }}
                    >
                    <div style={{height:200, overflowY:'scroll'}}>
                        {
                        Array(43).fill().map((v, i)=>{
                                return (<>
                                    <img src={`/emo/emo${i+1}.gif`} onClick={()=>onEmoSend(`emo${i+1}.gif`)}/>
                                    {(i+1) % 5 == 0 && <br/>}
                                </>)
                        }) 
                        }
                    </div>
                    </Menu>
                </div>
            </div>
        </div>
    </>)
}