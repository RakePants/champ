import React from "react";
import Button from "../shared/Button";
import '../style/Header.sass';
export default function Header() {
    return (
        <header>
            <img className="logo" src="./img/MainLogo.png" alt="Logo" />
            <div className="header__menu">
                <Button>Заявки</Button>
                <Button>Выполнено</Button>
            </div>

        </header>
    )
}