
import './App.css';
import React, {useState, useEffect} from 'react';
import FormData from "./FormData";
import LoginForm from "./LoginForm";
import TMDBList from "./TMDBList";


function App() {


    return (
       <div>
           <FormData/>
           <LoginForm/>
           <TMDBList/>
       </div>
    );
};

export default App;