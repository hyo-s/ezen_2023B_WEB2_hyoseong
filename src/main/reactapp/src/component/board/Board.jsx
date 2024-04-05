import axios from "axios"
import { useEffect, useState } from "react";
import MediaCard from "./MediaCard";
import { Pagination } from "@mui/material";

export default function Board(props){
    
    const [pageDto, setPageDto] = useState({
        page:1, count:0, data:[]
    });

    useEffect(()=>{
        const info = {page : pageDto.page, view:4}
        console.log(info)
        axios.get("/board/get.do",{params:info})
        .then(response=>{
            console.log(response.data);
            setPageDto(response.data);
        })
        .catch(error=>{console.log(error);})

    },[pageDto.page])

    const handleChange = (e, value)=>{
        pageDto.page = value;
        setPageDto({...pageDto})
    }

    return(<>
        <div style={{display:"flex"}}>
            {pageDto.data.map((data)=>{
                return(
                    <div key={data.bno}>
                        <MediaCard board={data}/>
                    </div>
                )
            })}
        </div>
        <Pagination count={pageDto.count} page={pageDto.page} onChange={handleChange} />
    </>)

}