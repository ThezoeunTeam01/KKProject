import React, { useState, useEffect } from 'react';
import axios from 'axios';

const LikeList = () => {
    const [movies, setMovies] = useState([]);
    const [userId, setUserId] = useState(localStorage.getItem('userId'));

    const handleButtonClick = async (movieId) => {
        try {
            const response = await axios.post('/likeDelete', {
                userId: userId,
                movieId: movieId,
            });

            console.log('Movie successfully deleted:', response.data);
            const newMovies = movies.filter((movie) => movie.id !== movieId);
            setMovies(newMovies);
        } catch (error) {
            console.error('Error deleting movie:', error);
        }
    };

    useEffect(() => {
        const fetchMovies = async () => {
            try {
                const response = await axios.post('/likeRead', { userId: userId });
                const movieIds = response.data.map((likedMovie) => likedMovie.movieId);
                const apiKey = '9c8709e24862b7b00803591402286323';

                const promises = movieIds.map(async (id) => {
                    const response = await fetch(`https://api.themoviedb.org/3/movie/${id}?api_key=${apiKey}`);
                    if (!response.ok) {
                        console.error(`Failed to fetch movie with id ${id}`);
                        return null;
                    }
                    return response.json();
                });

                const movieDataArray = await Promise.all(promises);
                const validMovieData = movieDataArray.filter((data) => data !== null);
                setMovies(validMovieData);
            } catch (error) {
                console.error('Error fetching movies:', error);
            }
        };

        fetchMovies();
    }, [userId]);

    return (
        <div>
            <h1>Movie List</h1>
            <ul>
                {movies.map((movie) => (
                    <li key={movie.id}>
                        <img src={`https://image.tmdb.org/t/p/w500/${movie.poster_path}`} alt={movie.title}/>
                        <p>{movie.title}</p>
                        <button onClick={() => handleButtonClick(movie.id)}>찜에서 제거</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default LikeList;
