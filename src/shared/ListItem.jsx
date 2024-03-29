import React from "react";
import '../style/ListItem.sass';
import ConstructionIcon from '@mui/icons-material/Construction';
import { useNavigate, useLocation } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { setTitle } from "../store/reducers/newInfoReducer";

export default function ListItem({className, data, item}) {
    const navigate = useNavigate();
    const { pathname } = useLocation();
    const dispatch = useDispatch();
    const newData = useSelector(state => state.data.data);

    function handleClick(){ 
        navigate('/' + pathname.split('/')[1] + '/' + item.id);
    }
    return(
        <li onClick={handleClick} className={"list__item " + className} >
            <ConstructionIcon className="list__item__icon"></ConstructionIcon>
            <div className={"list__item__text"}>{'Дефект #' + data}</div>
        </li>
    )
}