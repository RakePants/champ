import React, { useEffect } from "react";
import ListItem from "../shared/ListItem";
import { useSelector } from "react-redux";
import { useLocation } from "react-router-dom";
import Skeleton from '@mui/material/Skeleton';
function List(){
    const data = useSelector(state => state.data.data);
    console.log(data)
    if(true){
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
        {data.map((item, index) => <ListItem key={index} data={item}></ListItem>)}
    </ul>
    );
}

export default List
