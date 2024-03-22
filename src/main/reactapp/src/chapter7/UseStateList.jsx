import { useState } from "react"

export default function UseStateList(props){

    // point 상태관리
        // input 입력된 값
    let [pointInput, setPotintInput] = useState('');
        // input 입력된 값의 리스트
    let [pointList, setPotintList] = useState([]);

    function submit(){
        console.log('submit()');
        pointList.push(pointInput);
        setPotintList([...pointList]);
    }

    function inputChange(e){
        setPotintInput(e.target.value);
        console.log(e.target.value);
    }

    return(<>
        <div>
            <div>
                <input value={pointInput} type="text" onChange={inputChange}/>
                <button type="button" onClick={submit}>등록</button>
            </div>
            <div>
                {pointList.map((point)=>{return (<div>{point}</div>)})}
            </div>
        </div>
    </>)
}