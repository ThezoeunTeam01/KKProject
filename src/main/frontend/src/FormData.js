import React, { useState } from "react";

const FormData = () => {
    const [formData, setFormData] = useState({
        userName: "",
        passWord: "",
    });

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        // POST 요청
        fetch("http://localhost:8088/create", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(formData),
        })
            .then((response) => response.json())
            .then((data) => {
                // 서버 응답 처리
                console.log(data);
            })
            .catch((error) => {
                console.error("에러:", error);
            });
    };

    return (
        <div>
        <h1>회원가입</h1>
        <form onSubmit={handleSubmit}>
            <label htmlFor="userName">사용자 이름:</label>
            <input
                type="text"
                id="userName"
                name="userName"
                value={formData.userName}
                onChange={handleChange}
            />

            <label htmlFor="passWord">비밀번호:</label>
            <input
                type="passWord"
                id="passWord"
                name="passWord"
                value={formData.passWord}
                onChange={handleChange}
            />

            <button type="submit">회원가입</button>
        </form>
        </div>
    );
};

export default FormData;
