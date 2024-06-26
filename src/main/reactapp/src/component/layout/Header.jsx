import axios from "axios";
import { useContext, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { LoginInfoContext } from "../Index";

export default function Header(props){

    // Provider 컴포넌트의 value 호출
    const {loginInfo, setLoginInfo } = useContext(LoginInfoContext);

    // 컴포넌트 생성 시 axios 실행해서 로그인 회원정보 호출
    useEffect(()=>{
        axios.get("/member/login/info/get.do")
        .then(response=>{
            console.log(response);
            setLoginInfo(response.data)
        })
        .catch(error=>{console.log(error)})
    },[])

    // 2. 로그아웃
    const onLogout = ()=>{
        axios.get('/member/logout/get.do')
        .then(r=>{
            if(r.data){
                alert('로그아웃 성공');
                window.location.href = "/member/login"
            }else{alert('로그아웃실패')}
        })
        setLoginInfo('');
    }

    return(<>
        <div>
            {loginInfo && <span>{loginInfo.memail}님</span> }
            <ul>
                <li><Link to="/">홈</Link></li>
                <li><Link to="/member/signup">회원가입</Link></li>
                <li>{loginInfo?<Link to="/member/logout">로그아웃</Link>:<Link to="/member/login">로그인</Link>}</li>
                <li><button type="button" onClick={onLogout}>로그아웃</button></li>
                <li><a href="/board/write">글쓰기</a></li>
                <li><a href="/board">전체글보기</a></li>
                <li><a href="/chat">채팅</a></li>
            </ul>
        </div>
    </>)
}