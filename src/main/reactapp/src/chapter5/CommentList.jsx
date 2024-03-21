import Comment from "./Comment"

let response = [ {name : '유재석' , content : '안녕하세요1', id:1} ,
    {name : '강호동' , content : '안녕하세요2', id:2} ,
    {name : '신동엽' , content : '안녕하세요3', id:3} ];

export default function CommentList(props){
    
    return (
        <div>
            {
                response.map((data)=>{
                    return (<Comment name={data.name} comment={data.content} key={data.id}/>)
                })
            }
        </div>
    )
}