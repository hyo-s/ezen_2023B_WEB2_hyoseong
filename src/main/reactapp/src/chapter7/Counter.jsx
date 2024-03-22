import { useState } from "react";

export default function Counter(props){

    // 1. 변수
    let countValue = 0;

    // 2. 함수
    function click(){
        countValue++;
        console.log(countValue);
    }

    // 컴포넌트 내부
    console.log(countValue);

    // 3. State 값의 변화가 있으면 해당 컴포넌트 재호출
        // [0] : 초기값 또는 값
        // [1] : state의 set 함수 (state 값을 변경할 때 사용되는 함수)
            // 참조 주소 값이 변경될 때
            // 
    let state = useState('훅이란무엇인가?');
    console.log(state);

    // 4. useState
    const [count, setCount] = useState(0);
    console.log(count);
    console.log(setCount);

    // 5. Hook
    const [inputValue1, setInputValue1] = useState('')
    function inputValue1Update(e){
        console.log(e);                     // 이벤트
        console.log(e.target);              // 마크업
        setInputValue1(e.target.value);     // set 함수 호출해서 값 대입
    }

    return(
        <div>
            <div>
                <p>CountValue : 총 {countValue}번 클릭했습니다.</p>
                <button onClick={(e)=>count++} type="button">클릭</button>
            </div>
            <div>
                <p>State : 총 {count}번 클릭했습니다.</p>
                <button onClick={()=>setCount(count+1)} type="button">클릭</button>
            </div>
            <div>
                <input type="text"/><br/>
                <input type="text" value={inputValue1} readOnly/><br/>
                <input type="text" value={inputValue1} onChange={inputValue1Update}/>
            </div>
        </div>
    );
}