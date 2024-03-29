import axios from "axios";
import { useState } from "react"

export default function SignUp(){

    // 0. 유효성검사
    

    // 1. 상태변수
    const [memail, setMemail] = useState('');
    const [mpassword, setMpassword] = useState('');
    const [mname, setMname] = useState('');

    // 2. memail 수정함수
    const onChangeMemail = (e)=>{
        setMemail(e.target.value);
    }

    // 3. 아이디 중복검사
    const onFindId = (e)=>{
        axios.get("/member/findid/get.do",{params:{'memail':memail}})
        .then(response=>{
            console.log(response);
            if(response.data){
                alert('중복된 아이디 입니다.')
            }else(
                alert('사용 가능한 아이디입니다.')
            )
        })
        
    }

    // 3. 전송함수
    const onSignUp = (e)=>{
        console.log(memail);
        console.log(mpassword);
        console.log(mname);
        
        // Axios
        // content-type : application/json
        // axios.HTTP메소드명(url, data).then(응답매개변수=>{응답로직})
        let info = {memail : memail, mpassword : mpassword, mname : mname}
        axios.post("/member/signup/post.do", info)
        .then(response=>{
            console.log(response);
            if(response.data){
                alert('회원가입 성공');
                window.location.href="/member/login";
            }else{
                alert('회원가입 실패');
            }
        })
        .catch(error=>{console.log(error)})
    }

    return (
        <div>
            <form>
                아이디 : <input type="text" value={memail} onChange={onChangeMemail}/>
                <button type="button" onClick={onFindId}>아이디중복검사</button>
                패스워드 : <input type="password" value={mpassword} onChange={(e)=>setMpassword(e.target.value)}/>
                이름 : <input type="text" value={mname} onChange={(e)=>setMname(e.target.value)}/>
                <button type="button" onClick={onSignUp}>회원가입</button>
            </form>
        </div>
    )
}