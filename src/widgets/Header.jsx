import React from "react";
import Button from "../shared/Button";
import '../style/Header.sass';
import { useNavigate } from "react-router-dom";
import { getContractor } from "../API/requests";
import { useDispatch, useSelector } from "react-redux";
import { setContractors } from "../store/reducers/newContractorReducer";

export default function Header() {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const [isActive, setActive] = React.useState(0);
    const mutex = useSelector(state => state.mutex.mutex);

    React.useEffect(() => {
        async function fetchData() {
            const data = await getContractor();
            dispatch(setContractors(data));
        }
        fetchData();
        // eslint-disable-next-line
    }, [])

    function handleClick(index, to) {
        console.log(mutex);
        if(mutex) {
            setActive(index);
            navigate(to);
        }
    }
    const navigation = [{route: '/apps/0', name: 'Заявки'}, {route: '/orders/0', name: 'В процессе'}, {route: '/complete/0', name: 'Выполнено'},];
    return (
        <header>
            <img onClick={() => {handleClick(0, '/apps/0')}} className="logo" src="/img/MainLogo.png" alt="Logo" />
            <div className="header__menu">
                {navigation.map((item, index) => (
                    <Button title={"Открыть " + item.name} key={index} disabled={isActive === index} className={isActive === index ? 'button-active' : ''} onClick={() => {handleClick(index, item.route)}}>{item.name}</Button>
                ))}
            </div>

        </header>
    )
}