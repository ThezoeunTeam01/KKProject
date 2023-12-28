import React, { useState, useEffect } from 'react';
import axios from 'axios';

const TMDBList = () => {
    const [searchQuery, setSearchQuery] = useState('Avengers');
    const [movies, setMovies] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get(
                    `https://api.themoviedb.org/3/search/movie?api_key=9c8709e24862b7b00803591402286323&query=${searchQuery}`
                );
                setMovies(response.data.results);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchData();
    }, [searchQuery]);

    return (
        <div>
            <h1>TMDB Movie Search</h1>
            <input
                type="text"
                value={searchQuery}
                onChange={(e) => setSearchQuery(e.target.value)}
                placeholder="Enter movie title"
            />

            <h2>Search Results:</h2>
            <ul>
                {movies.map((movie) => (
                    <li key={movie.id}>
                        <img src={`https://image.tmdb.org/t/p/w500/${movie.poster_path}`} alt={movie.title}/>
                        <p>{movie.title}</p>
                        <button>버튼임</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default TMDBList;
