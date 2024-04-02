import axios from "axios";
import { useRef } from "react"

export default function BoardWrite(props){

    // 재렌더링 고정 참조 변수
    const formElem = useRef(null);

    const onBoardWrite = ()=>{
        axios.post("/board/post.do", formElem.current) 
        .then(response => {
            console.log(response)
            if(response.data){
                alert('글쓰기 성공')
            }else{
                alert('글쓰기 실패')
            }
        })
        .catch(error=>{console.log(error)})
    }

    return(<>
        <form ref={formElem}>
            <textarea type="text" name='bcontent'></textarea>
            <input name="multipartFileList" type="file" multiple accept="image/*"/>
            <button type="button" onClick={onBoardWrite}>글쓰기</button>
        </form>
    </>)

}