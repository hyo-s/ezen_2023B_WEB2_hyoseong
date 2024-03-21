
import Book from './Book';
export default function Library(props){

    let successData = [
        {name : '강호동', age : 30},
        {name : '유재석', age : 40},
        {name : '신동엽', age : 50}
    ]
    
    return(
        <div>
            <Book name="강호동" age={30}/>
            <Book name="유재석" age={40}/>
            <Book name="신동엽" age={50}/>
        </div>
    )
}

/*
    <div>
        {
            successData.map( (data)=>{
                return (
                    <Book name = { data.name} age = { data.age } />
                );
            })
        }
    </div>
*/