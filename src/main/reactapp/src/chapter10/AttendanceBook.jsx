const student =[
    {id:1, name : 'Inje'},
    {id:2, name : 'Steve'},
    {id:3, name : 'Bill'},
    {id:4, name : 'Jeff'}
]

export default function AttendanceBook(props){
    return(
        <div>
            <ul>
                {student.map((student)=>{
                    return (<li key={student.id} id={student.id} className={student.id}>{student.name}</li>)
                })}
            </ul>
        </div>
    )
}