import { useState } from "react";

export default function SignUp(){
    const [name, setName] = useState('');
    const [gender, setGender] = useState('남자');

    const handleChangeName = (e)=>{
        setName(e.target.value);
        e.preventDefault();
    }

    const handleChangeGender = (e)=>{
        setGender(e.target.value);
        e.preventDefault();
    }

    const handleSubmit = (e)=>{
        console.log(name);
        console.log(gender);
    }

    return(
        <div>
            <form>
                이름 : <input type="text" value={name} onChange={handleChangeName}/>
                성별 : 
                <select value={gender} onChange={handleChangeGender}>
                    <option value="남자">남자</option>
                    <option value="여자">여자</option>
                </select>
                <button type="button" onClick={handleSubmit}>제출</button>
            </form>
        </div>
    )

}