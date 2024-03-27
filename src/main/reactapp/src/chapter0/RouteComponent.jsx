import { BrowserRouter, Link, Route, Routes } from "react-router-dom"
import JSX선언 from "../chapter3/1_JSX선언"
import Library from "../chapter3/Library"
import Clock from "../chapter4/Clock"


export default function RouteComponent(props){
    return (
        <BrowserRouter>
            <div id="wrap" style={{display:"flex"}}>
                <FixComponent/>
                <Routes>
                    <Route path="/chapter3/jsx선언" element={<JSX선언/>}/>
                    <Route path="/chapter3/library" element={<Library/>}/>
                    <Route path="/chapter4/clock" element={<Clock/>}/>
                </Routes>
            </div>
        </BrowserRouter>
    )
}

function FixComponent(porps){
    return(
        <div>
            <ul>
                <li><a href="/chapter3/jsx선언">A태그</a></li>
            </ul>
            <ul>
                <li><Link to="/chapter3/jsx선언">LINK태그 JSX선언</Link></li>
                <li><Link to="/chapter3/library">LINK태그 Library</Link></li>
                <li><Link to="/chapter4/clock">LINK태그 Clock</Link></li>
            </ul>
        </div>
    )
}