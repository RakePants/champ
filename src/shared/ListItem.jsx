import React from "react";
import '../style/ListItem.sass';
import ConstructionIcon from '@mui/icons-material/Construction';
import { useNavigate, useLocation } from "react-router-dom";
import { useSelector } from "react-redux";
export default function ListItem({className, data}) {
    const navigate = useNavigate();
    const { pathname } = useLocation();
    const newData = useSelector(state => state.data.data);

    function handleClick(){
        navigate('/' + pathname.split('/')[1] + '/' + newData[data].id);
    }
    return(
        <li onClick={handleClick} className={"list__item " + className} >
            <ConstructionIcon className="list__item__icon"></ConstructionIcon>
            <div className={"list__item__text"}>{'Дефект #' + data}</div>
        </li>
    )
}