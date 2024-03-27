import React from "react";
import '../style/ListItem.sass';
import ConstructionIcon from '@mui/icons-material/Construction';

export default function ListItem() {
    return(
        <li className="list__item">
            <ConstructionIcon className="list__item__icon"></ConstructionIcon>
            <div className="list__item__text">12312323</div>
        </li>
    )
}