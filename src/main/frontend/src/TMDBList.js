import React, { useState, useEffect } from 'react';
import axios from 'axios';

const TMDBList = () => {
    const [searchQuery, setSearchQuery] = useState('');
    const [movies, setMovies] = useState([]);
    const [userId, setUserId] = useState(localStorage.getItem('userId'));
    const [likedMovies, setLikedMovies] = useState([]); // 찜한 목록 저장

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get(
                    `https://api.themoviedb.org/3/search/movie?api_key=9c8709e24862b7b00803591402286323&query=${searchQuery}`
                );
                const moviesWithLikeStatus = response.data.results.map((movie) => ({
                    ...movie,
                    likeStatus: likedMovies.some((likedMovie) => likedMovie.movieId === movie.id),
                }));
                setMovies(moviesWithLikeStatus);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchData();
    }, [searchQuery, likedMovies]);

    const handleLikeRead = async () => {
        try {
            const response = await axios.post('/likeRead', { userId: userId });
            setLikedMovies(response.data);
        } catch (error) {
            console.error('Error reading likes:', error);
        }
    };

    useEffect(() => {
        // 컴포넌트가 마운트될 때 찜한 목록을 가져오기
        handleLikeRead();
    }, []); // 빈 배열을 전달하여 한 번만 호출되도록 설정

    const handleLikeButtonClick = async (movieId) => {
        try {
            let newLikedMovies;

            // 찜 상태 확인
            const isLiked = likedMovies.some((likedMovie) => likedMovie.movieId === movieId);

            if (isLiked) {
                // 찜 해제
                const response = await axios.post('/likeDelete', {
                    userId: userId,
                    movieId: movieId,
                });
                console.log('Like successfully deleted:', response.data);

                // 찜 목록 갱신
                newLikedMovies = likedMovies.filter((likedMovie) => likedMovie.movieId !== movieId);
            } else {
                // 찜하기
                const response = await axios.post('/likeCreate', {
                    userId: userId,
                    movieId: movieId,
                });
                console.log('Like successfully created:', response.data);

                // 찜 목록 갱신
                newLikedMovies = [...likedMovies, { userId: userId, movieId: movieId }];
            }

            // 찜 상태 업데이트
            setLikedMovies(newLikedMovies);
        } catch (error) {
            console.error('Error handling like:', error);
        }
    };

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
                        <img src={`https://image.tmdb.org/t/p/w500/${movie.poster_path}`} alt={movie.title} />
                        <p>{movie.title}</p>
                        <button onClick={() => handleLikeButtonClick(movie.id)}>
                            {movie.likeStatus ? '찜 해제' : '찜하기'}
                        </button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default TMDBList;
