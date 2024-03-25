import { useState } from "react";

export default function ConfirmButton(props){

    // 1. 상태(state) 관리 변수
    const[isConfirmed, setIsConfirmed] = useState(false);
        // useState(초기값) : 리액트 훅
            // [0] : 값이 저장된 변수
            // [1] : 값을 수정할 수 있는 set 함수 [*(주소값) 변경 시 해당 컴포넌트 렌더링]
    console.log(useState(false));
    console.log(isConfirmed);
    console.log(setIsConfirmed);

    // 2. JS함수 정의 방법
    const handleConfirm = () =>{
        setIsConfirmed((prevIsConfirmed)=>!prevIsConfirmed)
    }

    return(
        <div>
            <button onClick={handleConfirm} disabled={isConfirmed}>
                {isConfirmed?'확인됨':'확인하기'}
            </button>
        </div>
    );
}