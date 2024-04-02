import axios from "axios"
import { useEffect, useState } from "react";
import MediaCard from "./MediaCard";

export default function Board(props){
    
    const [boardList, setBoardList] = useState([]);

    useEffect(()=>{
        axios.get("/board/get.do")
        .then(response=>{
            console.log(response.data);
            setBoardList(response.data);
        })
        .catch(error=>{console.log(error);})

    },[])

    return(<>
        {boardList.map((data)=>{
            return(
                <div key={data.bno}>
                    <MediaCard board={data}/>
                </div>

            )
        })}
    </>)

}