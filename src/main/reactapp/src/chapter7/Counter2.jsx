import { useEffect, useState } from "react";

export default function Counter(){
    const[count, setCount] = useState(0);
    const[count2, setCount2] = useState(0);
/*
    // useEffect(이펙트함수,의존성배열)
    // 1. 의존성배열 생략 시 : 컴포넌트가 업데이트/재호출 될 때마다 (이펙트함수) 호출
        // mount(출생), unmount(사망)일때 (이펙트함수) 호출
        // 모든 렌더링에 있어서 (이펙트함수)호출
    useEffect(()=>{
        console.log('useEffect()');
        document.title=`총${count}번 클릭했습니다.`
    })
*/
/*
    // 2. 의존성배열[ ] : 컴포넌트가 mount(출생), unmount(사망)일때만 (이펙트함수) 호출
        // mount(출생), unmount(사망)일때 (이펙트함수) 호출
        // 모든 렌더링에 있어서 (이펙트함수) 호출 안됨
    useEffect(()=>{
        console.log('useEffect()');
        document.title=`총${count}번 클릭했습니다.`
    },[])
*/
    // 3. 의존성배열[state변수들] : 컴포넌트가 mount(출생), unmount(사망), 의존성배열에 포함된 state 변수가 수정될 때 (이펙트함수)
        // mount(출생), unmount(사망)일때 (이펙트함수) 호출
        // 의존성 배열에 하나라도 포홤된 변수가 변경 되었을 때 (이펙트함수) 호출
    useEffect(()=>{
        console.log('useEffect()');
        document.title=`총${count}번 클릭했습니다.`
    },[count])

    return(
        <div>
            <p>총{count}번 클릭했습니다.</p>
            <button onClick={()=>setCount(count+1)}>
                클릭1
            </button>
            <p>총{count2}번 클릭했습니다.</p>
            <button onClick={()=>setCount2(count2+1)}>
                클릭2
            </button>
        </div>
    )
}