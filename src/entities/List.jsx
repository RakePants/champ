import React from "react";
import ListItem from "../shared/ListItem";
import { useSelector } from "react-redux";
import Skeleton from '@mui/material/Skeleton';
import { useParams } from "react-router-dom";
import hashToNumber from "../utils/hashToNumber";
import { useLocation } from "react-router-dom";
import { useDispatch } from "react-redux";
import { setTitle } from "../store/reducers/newInfoReducer";
function List(){
    const { id } = useParams();
    const { pathname } = useLocation();
    const dispatch = useDispatch();
    const data = useSelector(state => state.data.data);
    const activeId = data.findIndex(item => item.id === id);
    const path = pathname.split('/')[1];
    switch (path) {
        case 'apps': {
            dispatch(setTitle('Заявка #' + hashToNumber(id)));
            break;
        }
        case 'orders': {
            dispatch(setTitle('В процессе #' + hashToNumber(id)));
            break;
        }
        case 'complete': {
            dispatch(setTitle('Выполнено #' + hashToNumber(id)));
            break;
        }
        default:
            break;
    }
    if(+id === 0){
        return(
            <>
                <Skeleton variant="text" sx={{ fontSize: '1rem', bgcolor: 'grey.700', width: '90%', margin: 'auto', height: '60px' }} />
                <Skeleton variant="text" sx={{ fontSize: '1rem', bgcolor: 'grey.700', width: '90%', margin: 'auto', height: '60px' }} />
                <Skeleton variant="text" sx={{ fontSize: '1rem', bgcolor: 'grey.700', width: '90%', margin: 'auto', height: '60px' }} />
            </>
        )
    }
    return (
    <ul 
        style={{listStyle: 'none', padding: '0', overflowY: 'scroll', maxHeight: '80vh'}}
    >
        {data.map((item, index) => <ListItem key={index} className={activeId === index ? 'list__item-active' : ''} item={item} data={hashToNumber(item.id)}></ListItem>)}
    </ul>
    );
}

export default List
