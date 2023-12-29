
import './App.css';
import React, {useState, useEffect} from 'react';
import FormData from "./FormData";
import LoginForm from "./LoginForm";
import TMDBList from "./TMDBList";
import MovieList from "./MovieList";


function App() {


    return (
       <div>
           <FormData/>
           <LoginForm/>
           <TMDBList/>
           <MovieList/>
       </div>
    );
};

export default App;