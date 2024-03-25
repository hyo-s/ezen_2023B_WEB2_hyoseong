import Toolbar from "./Toolbar";
import { useState } from "react";

export default function LandingPage(props){
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    console.log(isLoggedIn)
    console.log(useState(false));

    const onClickLogin = () =>{
        setIsLoggedIn(true);
    }
    const onClickLogout = () =>{
        setIsLoggedIn(false);
    }

    return(
        <div>
            <Toolbar isLoggedIn={isLoggedIn} onClickLogin={onClickLogin} onClickLogout={onClickLogout}/>
            <div>
                소플과 함께하는 리액트 공부!
            </div>
        </div>
    )
}