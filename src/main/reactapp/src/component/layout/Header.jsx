import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export default function Header(props){

    // 로그인정보 state 변수
    const [loginInfo, setLoginInfo] = useState('');




    // 컴포넌트 생성 시 axios 실행해서 로그인 회원정보 호출
    useEffect(()=>{
        axios.get("/member/login/info/get.do")
        .then(response=>{
            console.log(response);
            setLoginInfo(response.data)
        })
        .catch(error=>{console.log(error)})
    },[])

    return(<>
        <div>
            {loginInfo && <span>{loginInfo.memail}님</span> }
            <ul>
                <li><Link to="/">홈</Link></li>
                <li><Link to="/member/signup">회원가입</Link></li>
                <li><Link to="/member/login">로그인</Link></li>
            </ul>
        </div>
    </>)
}