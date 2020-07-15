import React from 'react';
import './Navigator.css';

const Navigator = () => {

    return (
        <div className='Navigator'>
            <ul>
                <li><a href='/'>首頁</a></li>
                <li><a href='/'>課程管理</a></li>
                <li><a href='/'>課表管理</a></li>
                <li ><a href='/'>登入</a></li>
                <li ><a href='/'>註冊</a></li>
                <li ><a href='/'>登出</a></li>
            </ul>
        </div>
    );

};

export default Navigator;
