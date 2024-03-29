import axios from "axios";
import { useEffect } from "react";

export default function Logout(){

    useEffect(()=>{
        axios.get("/member/logout/get.do")
        .then(response=>{
            console.log(response);
            if(response.data){
                alert('로그아웃 성공')
                window.location.href="/"
            }else{
                alert('로그아웃 실패')
            }
        })
        .catch(error=>{console.log(error)})
    })
}