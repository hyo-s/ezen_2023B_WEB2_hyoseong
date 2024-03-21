// CSS 파일 호출
import styles from './Comment.css';
// 이미지 파일 호출
import img from './default.png';

export default function Comment(props){
    console.log('props : ');
    console.log(props);
    return (
        <div className="wrapper">
            <div>
                <img className="image" src={img}/>
            </div>
            <div className="contentContainer">
                <span className="nameText">{props.name}</span>
                <span className="commentText">{props.comment}</span>
            </div>
        </div>
    )
}