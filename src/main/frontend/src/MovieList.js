import React, { useState, useEffect } from 'react';

const MovieList = () => {
    const [movies, setMovies] = useState([]);

    useEffect(() => {
        const fetchMovies = async () => {
            const movieIds = [695721, 572802, 891699];
            const apiKey = '9c8709e24862b7b00803591402286323';

            try {
                // Promise.all을 사용하여 여러 개의 API 호출을 병렬로 수행
                const promises = movieIds.map(async (id) => {
                    const response = await fetch(`https://api.themoviedb.org/3/movie/${id}?api_key=${apiKey}`);
                    console.log(response);
                    if (!response.ok) {
                        console.error(`Failed to fetch movie with id ${id}`);
                        return null;
                    }
                    return response.json();
                });

                // 모든 Promise가 해결될 때까지 기다린 후 결과를 받아옴
                const movieDataArray = await Promise.all(promises);

                // 유효한 데이터만 필터링하여 설정
                const validMovieData = movieDataArray.filter((data) => data !== null);

                // 결과를 state에 설정
                setMovies(validMovieData);
            } catch (error) {
                console.error('Error fetching movies:', error);
            }
        };

        fetchMovies();
    }, []);

    return (
        <div>
            <h1>Movie List</h1>
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

export default MovieList;
