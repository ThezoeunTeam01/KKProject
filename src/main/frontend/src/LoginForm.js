import React, { useState, useEffect } from "react";

const LoginForm = () => {
    const [loginData, setLoginData] = useState({
        userName: "",
        passWord: "",
    });

    const [welcomeMessage, setWelcomeMessage] = useState("");

    const handleChange = (e) => {
        setLoginData({
            ...loginData,
            [e.target.name]: e.target.value,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch("http://localhost:8088/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(loginData),
            });

            if (response.ok) {
                try {
                    // 로그인이 성공하면 서버에서 반환한 세션 정보를 활용할 수 있습니다.
                    const sessionData = await response.json();
                    console.log("로그인 성공. 세션 정보:", sessionData);

                    // 세션 정보를 로컬 스토리지에 저장
                    localStorage.setItem("userId", sessionData['userId']);
                    localStorage.setItem("userName", sessionData['userName']);

                    setWelcomeMessage(`환영합니다, ${sessionData['userName']}님!`);
                } catch (error) {
                    console.error("에러:", error);
                }
            } else {
                console.error("로그인 실패");
            }
        } catch (error) {
            console.error("에러:", error);
        }
    };

    const handleLogout = async () => {
        try {
            // 로그아웃 요청을 서버에 전송
            const response = await fetch("http://localhost:8088/logout", {
                method: "POST",
            });

            if (response.ok) {
                // 로컬 스토리지에서 세션 정보 제거
                localStorage.removeItem("userId");
                localStorage.removeItem("userName");

                // 환영 메시지 초기화
                setWelcomeMessage("");
            } else {
                console.error("로그아웃 실패");
            }
        } catch (error) {
            console.error("에러:", error);
        }
    };

    // 페이지 로드 시 세션 정보 확인
    useEffect(() => {
        // 로컬 스토리지에서 세션 정보 가져오기
        const storedUserId = localStorage.getItem("userId");
        const storedUserName = localStorage.getItem("userName");

        if (storedUserId && storedUserName) {
            setWelcomeMessage(`환영합니다, ${storedUserName}님!`);
        }
    }, []);

    return (
        <div>
            <h1>로그인</h1>
            <form onSubmit={handleSubmit}>
                <label htmlFor="userName">사용자 이름:</label>
                <input
                    type="text"
                    id="userName"
                    name="userName"
                    value={loginData.userName}
                    onChange={handleChange}
                />

                <label htmlFor="passWord">비밀번호:</label>
                <input
                    type="password"
                    id="passWord"
                    name="passWord"
                    value={loginData.passWord}
                    onChange={handleChange}
                />

                <button type="submit">로그인</button>
            </form>

            {/* 환영 메시지 표시 */}
            {welcomeMessage && (
                <div>
                    <p>{welcomeMessage}</p>
                    <button onClick={handleLogout}>로그아웃</button>
                </div>
            )}
        </div>
    );
};

export default LoginForm;
