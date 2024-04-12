import axios from "axios";
import { useContext } from "react";
import { LoginInfoContext } from "../Index";

export default function Login(props){

    const onLogin = (e)=>{
        const loginForm = document.querySelector('#loginForm');
        const loginFormData = new FormData(loginForm);
        axios.post("/member/login/post.do", loginFormData)
        .then(response=>{
            console.log(response)
            if(response.data){
                alert('로그인 성공')
                window.location.href="/"
            }else{
                alert('로그인 실패')
            }
        })
        .catch(error=>{console.log(error)})
    }

    return(<>
    <form id="loginForm">
        아이디 : <input type="text" name="memail"/>
        비밀번호 : <input type="password" name="mpassword"/>
        <button type="button" onClick={onLogin}>로그인</button>
        <a href="/oauth2/authorization/kakao">카카오 로그인</a>
        <a href="/oauth2/authorization/naver">네이버 로그인</a>
        <a href="/oauth2/authorization/google">구글 로그인</a>
    </form>
    </>)
}