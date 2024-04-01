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
import TextInputWithFocusButton from './chapter7/TextInputWithFocusButton';
import MeasureExample from './chapter7/MeasureExample';

// Chapter8
import ConfirmButton from './chapter8/ConfirmButton';

// Chapter9
import Toolbar from './chapter9/Toolbar';
import LandingPage from './chapter9/LandingPage';

// Chapter10
import AttendanceBook from './chapter10/AttendanceBook';

// Chapter11
import NameForm from './chapter11/NameForm';
// import SignUp from './chapter11/SignUp';

// Component
import SignUp from './component/member/SignUp';
import Index from './component/Index';

// Chapter0
import AxiosComponent from './chapter0/AxiosComponent';
import RouteComponent from './chapter0/RouteComponent';

// Chapter12
import Calculator from './chapter12/Calculator';

// Chapter13
import ProfileCard from './chapter13/ProfileCard';

// Chapter14
import DarkOrLight from './chapter14/DarkOrLight';



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
  // root.render(
  //   // <Counter/>
  //   // <UseStateList/>
  //   // <Counter2/>
  //   // <TextInputWithFocusButton/>
  //   <MeasureExample/>
  // )

// Chapter8
  // root.render(
  //   <ConfirmButton/>
  // )

// Chapter9
  // root.render(
  //   <LandingPage/>
  // )

// Chapter10
  // root.render(
  //   <AttendanceBook/>
  // )

// Chapter11
  // root.render(
  //   <NameForm/>
  //   <SignUp/>
  // )

// Component
  root.render(
    // <SignUp/>
    <Index/>
  )

// Chapter0
  // root.render(
  //   <AxiosComponent/>
  //   <RouteComponent/>
  // )

// Chaptter12
  // root.render(
  //   <Calculator/>
  // )

// Chaptter13
  // root.render(
  //   <ProfileCard/>
  // )

// Chaptter14
  // root.render(
  //   <DarkOrLight/>
  // )


// ============================================================= //

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
