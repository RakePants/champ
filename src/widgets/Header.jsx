import React from "react";
import Button from "../shared/Button";
import '../style/Header.sass';
import { useNavigate } from "react-router-dom";

export default function Header() {
    const navigate = useNavigate();
    return (
        <header>
            <img onClick={() => {navigate('/')}} className="logo" src="./img/MainLogo.png" alt="Logo" />
            <div className="header__menu">
                <Button onClick={() => {navigate('/apps')}}>Заявки</Button>
                <Button onClick={() => {navigate('/orders')}}>Заказы</Button>
                <Button onClick={() => {navigate('/complete')}}>Выполнено</Button>
            </div>

        </header>
    )
}