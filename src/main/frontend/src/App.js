
import './App.css';
import React, {useState, useEffect} from 'react';
import FormData from "./FormData";
import LoginForm from "./LoginForm";
import TMDBList from "./TMDBList";
import LikeList from "./LikeList";


function App() {


    return (
       <div>
           <FormData/>
           <LoginForm/>
           <TMDBList/>
           <LikeList/>
       </div>
    );
};

export default App;