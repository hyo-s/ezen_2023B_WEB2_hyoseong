import { useState } from "react";

export default function NameForm(){

    // 1.
    function submit1(){
        let nameInput = document.querySelector('#nameInput').value;
        console.log(nameInput);
    }

    // 2.
    const [value, setValue] = useState('');

    const handleChange = (event)=>{
        // console.log(event); // 해당 event를 발생한 결과 정보 객체
        // console.log(event.target);
        // console.log(event.target.value);
        setValue(event.target.value);
        event.preventDefault(); // 브라우저들의 이벤트들을 제거
    } 

    // 3.
    const [value2, setValue2] = useState('');

    const handleChange2 = (event)=>{
        setValue2(event.target.value);
        event.preventDefault(); // 브라우저들의 이벤트들을 제거
    }

    // 4.
    const [value3, setValue3] = useState('grape');

    const handleChange3 = (event)=>{
        setValue3(event.target.value);
    }

    const handleSubmit = (event)=>{
        console.log(value);
        console.log(value2);
        console.log(value3);
    }


    return(
        <div>
            <form>
                이름 : <input id="nameInput"/>
                <button type="button" onClick={submit1}>제출1</button>
            </form>
            <form>
                이름 : <input type="text" value={value} onChange={handleChange}/>
                긴글 : <textarea value={value2} onChange={handleChange2}/>
                과일을 선택해주세요
                <select value={value3} onChange={handleChange3}>
                    <option value="apple">사과</option>
                    <option value="banana">바나나</option>
                    <option value="grape">포도</option>
                    <option value="watermelon">수박</option>
                </select>
                <button type="button" onClick={handleSubmit}>제출2</button>
            </form>
        </div>
    )
}