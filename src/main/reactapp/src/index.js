import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

// 내가 만든 컴포넌트 import 컴포넌트명 from 컴포넌트파일경로;

// Chapter3
import JSX선언 from './chapter3/1_JSX선언';
import Book from './chapter3/Book';
import Library from './chapter3/Library'

// Chapter4
import Clock from './chapter4/Clock';

// Chapter5
import CommentList from './chapter5/CommentList';

// Chapter7
import Counter from './chapter7/Counter';
import UseStateList from './chapter7/UseStateList';
import Counter2 from './chapter7/Counter2';

// Chapter8
import ConfirmButton from './chapter8/ConfirmButton';

// Chapter9
import Toolbar from './chapter9/Toolbar';
import LandingPage from './chapter9/LandingPage';

const root = ReactDOM.createRoot(document.getElementById('root'));

// ============================================================= //

// Chapter3
  // root.render(
  //   <React.StrictMode> 유효성 검사
  //     {/* <App /> */}
  //     {/* <JSX /> */}
  //     {/* <Book /> */}
  //     {/* <Library /> */}
  //     <Clock/>
  //   </React.StrictMode>
  // );

// Chapter4
  // setInterval(()=>{
  //   root.render(
  //   <Clock/>
  //   )
  // },1000)

// Chapter5
  // root.render(
  //   <CommentList/>
  // )
  // setInterval(함수, 밀리초)

// Chapter7
  root.render(
    // <Counter/>
    // <UseStateList/>
    <Counter2/>
  )

// Chapter8
  // root.render(
  //   <ConfirmButton/>
  // )

// Chapter8
  // root.render(
  //   <LandingPage/>
  // )

// ============================================================= //

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
