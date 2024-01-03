import React, { useState, useEffect } from 'react';
import axios from 'axios';

const TMDBList = () => {
    // 검색 쿼리를 저장하는 상태
    const [searchQuery, setSearchQuery] = useState('');
    // 영화 목록을 저장하는 상태
    const [movies, setMovies] = useState([]);
    // 현재 사용자 ID를 저장하는 상태
    const [userId, setUserId] = useState(localStorage.getItem('userId'));
    // 찜한 영화 목록을 저장하는 상태
    const [likedMovies, setLikedMovies] = useState([]);

    // 검색 쿼리나 찜 목록이 변경될 때마다 실행되는 effect
    useEffect(() => {
        const fetchData = async () => {
            try {
                // TMDB API를 통해 영화 검색 결과를 가져옴
                const response = await axios.get(
                    `https://api.themoviedb.org/3/search/movie?api_key=9c8709e24862b7b00803591402286323&query=${searchQuery}`
                );
                // 각 영화에 대해 찜 상태를 추가
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

    // 사용자의 찜 목록을 읽어오는 함수
    const handleLikeRead = async () => {
        try {
            const response = await axios.post('/likeRead', { userId: userId });
            setLikedMovies(response.data);
        } catch (error) {
            console.error('Error reading likes:', error);
        }
    };

    // 컴포넌트가 마운트될 때 찜 목록을 가져오는 effect
    useEffect(() => {
        handleLikeRead();
    }, []);

    // 찜 버튼 클릭 시 실행되는 함수
    const handleLikeButtonClick = async (movieId) => {
        try {
            let newLikedMovies;

            // 현재 영화가 찜되었는지 확인
            const isLiked = likedMovies.some((likedMovie) => likedMovie.movieId === movieId);

            if (isLiked) {
                // 찜 해제 요청을 서버에 전송
                const response = await axios.post('/likeDelete', {
                    userId: userId,
                    movieId: movieId,
                });
                console.log('Like successfully deleted:', response.data);

                // 찜 목록에서 해당 영화를 제거
                newLikedMovies = likedMovies.filter((likedMovie) => likedMovie.movieId !== movieId);
            } else {
                // 찜 요청을 서버에 전송
                const response = await axios.post('/likeCreate', {
                    userId: userId,
                    movieId: movieId,
                });
                console.log('Like successfully created:', response.data);

                // 찜 목록에 해당 영화를 추가
                newLikedMovies = [...likedMovies, { userId: userId, movieId: movieId }];
            }

            // 찜 상태 업데이트
            setLikedMovies(newLikedMovies);
        } catch (error) {
            console.error('Error handling like:', error);
        }
    };

    // 사용자 인터페이스를 정의하는 부분
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
