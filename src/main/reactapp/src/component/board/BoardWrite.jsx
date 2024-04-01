import axios from "axios";
import { useRef, useState } from "react"

export default function BoardWrite(props){

    const formElem = useRef(null);

    const handleInput = (e)=>{
        formElem.current = {'bcontent' : e.target.value};
    }
    
    const onBoardWrite = (e)=>{
        const axiosFormData= new FormData();
        axiosFormData.append('bcontent', formElem.current.bcontent)
        axios.post("/board/post.do", axiosFormData)
        .then(response => {
            console.log(response);
        })
        .catch(error=>{console.log(error)})
    }

    return(<>
        <form ref={formElem}>
            <textarea type="text" ref={formElem} onChange={handleInput}></textarea>
            <button type="button" onClick={onBoardWrite}>글쓰기</button>
        </form>
    </>)

}