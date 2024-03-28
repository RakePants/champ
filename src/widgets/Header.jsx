import React from "react";
import Button from "../shared/Button";
import '../style/Header.sass';
import { useNavigate } from "react-router-dom";


export default function Header() {
    const navigate = useNavigate();
    const [isActive, setActive] = React.useState(1);
    function handleClick(index, to) {
        setActive(index);
        navigate(to);
    }
    const navigation = [{route: '/apps', name: 'Заявки'}, {route: '/orders', name: 'В процессе'}, {route: '/complete', name: 'Выполнено'},];
    return (
        <header>
            <img onClick={() => {handleClick(0, '/apps')}} className="logo" src="./img/MainLogo.png" alt="Logo" />
            <div className="header__menu">
                {navigation.map((item, index) => (
                    <Button key={index} className={isActive === index ? 'button-active' : ''} onClick={() => {handleClick(index, item.route)}}>{item.name}</Button>
                ))}
            </div>

        </header>
    )
}