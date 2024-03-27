import axios from "axios";

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
    </form>
    </>)
}