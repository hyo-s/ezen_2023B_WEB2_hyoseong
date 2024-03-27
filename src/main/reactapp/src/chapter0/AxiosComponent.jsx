import axios from "axios";

export default function AxiosComponent(){

    //  1. 기본함수
    function emptyFunction1(event){
        console.log('emptyFunction1()');
        console.log(event);
    }

    // 2. 화살표 함수
    const emptyFunction2 = (e)=>{
        console.log('emptyFunction2()');
        console.log(e);
    }

    // - 매개변수 포함
    const emptyFunction3 = (e, param)=>{
        console.log('emptyFunction3()');
        console.log(e);
        console.log(param);
    }

    // 1. GET
    const doGet = async ()=>{

        console.log(1);
        await axios.get('https://jsonplaceholder.typicode.com/posts')
        .then(response=>{console.log(response);})
        .catch(error=>{console.log(error)})

        console.log(2);
        await axios.get('https://jsonplaceholder.typicode.com/posts/1') // PATH
        .then(response=>{console.log(response);})
        .catch(error=>{console.log(error)})

        console.log(3);
        axios.get('https://jsonplaceholder.typicode.com/comments?postId=1') // QueryString
        .then(response=>{console.log(response);})
        .catch(error=>{console.log(error)})

        console.log(4);
        axios.get('https://jsonplaceholder.typicode.com/comments', {params:{'postId' : 1}}) // QueryString
        .then(response=>{console.log(response);})
        .catch(error=>{console.log(error)})

    }
    // 2. POST
    const doPost = ()=>{
        const saveInfo = {
            title: 'foo',
            body: 'bar',
            userId: 1,
          }

        axios.post('https://jsonplaceholder.typicode.com/posts', saveInfo)
        .then(response=>{console.log(response)})
        .catch(error=>{console.log(error)})

        // Form
        const axiosForm = document.querySelector('#axiosForm');
        const axiosFormData = new FormData(axiosForm);
        axios.post('http://localhost:8080/', axiosFormData) // Content-Type : multipart/form-data
        .then(response=>{console.log(response)})
        .catch(error=>{console.log(error)})
    }

    // 3. PUT
    const doPut = ()=>{
        const updateInfo = {
            id: 1,
            title: 'foo',
            body: 'bar',
            userId: 1,
        }
        axios.put('https://jsonplaceholder.typicode.com/posts/1', updateInfo)
        .then(response=>{console.log(response)})
        .catch(error=>{console.log(error)})
    }

    // 4. DELETE
    const doDelete = ()=>{
        axios.delete('https://jsonplaceholder.typicode.com/posts/1')
        .then(response=>{console.log(response)})
        .catch(error=>{console.log(error)})
    }

    return(
        <div>
            <h3>AXIOS TEST</h3>
            <button type="button" onClick={emptyFunction1}>EmptyFunction1</button>
            <button type="button" onClick={emptyFunction2}>EmptyFunction2</button>
            <button type="button" onClick={(e)=>{emptyFunction3(e,3)}}>EmptyFunction3</button>
            <button type="button" onClick={doGet}>DoGetAXIOS</button>
            <form id="axiosForm">
                <input type="text"/>
            </form>
            <button type="button" onClick={doPost}>DoPostAXIOS</button>
            <button type="button" onClick={doPut}>DoPutAXIOS</button>
            <button type="button" onClick={doDelete}>DoDeleteAXIOS</button>
        </div>
    )
}