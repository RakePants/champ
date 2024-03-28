import React, { useEffect } from "react";
import ListItem from "../shared/ListItem";
import { useSelector } from "react-redux";
import { useLocation } from "react-router-dom";
import Skeleton from '@mui/material/Skeleton';
import { useParams } from "react-router-dom";

function List(){
    const { id } = useParams();
    const data = useSelector(state => state.data.data);
    const activeId = data.findIndex(item => item.id === id);
    console.log(data)
    if(+id === 0){
        return(
            <>
                <Skeleton variant="text" sx={{ fontSize: '1rem', bgcolor: 'grey.700', width: '90%', margin: 'auto' }} />
                <Skeleton variant="text" sx={{ fontSize: '1rem', bgcolor: 'grey.700', width: '90%', margin: 'auto' }} />
                <Skeleton variant="text" sx={{ fontSize: '1rem', bgcolor: 'grey.700', width: '90%', margin: 'auto' }} />
            </>
        )
    }
    return (
    <ul>
        {data.map((item, index) => <ListItem key={index} className={activeId === index ? 'list__item-active' : ''}  data={index}></ListItem>)}
    </ul>
    );
}

export default List
