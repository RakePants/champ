import React from "react";
import Divider from "../shared/Divider";
import List from "../entities/List";
import '../style/Navbar.sass';
import { useSelector } from "react-redux";
import Skeleton from '@mui/material/Skeleton';
import { useParams } from "react-router-dom";
function Navbar() {
    const newData = useSelector(state => state.data.data);
    const { id } = useParams();
    if(+id === 0){
        return(
            <>
            <nav className="navbar">
            <Skeleton variant="rounded" width={'90%'} height={'10%'} sx={{ bgcolor: 'grey.700', margin: 'auto'}} />
                <Divider></Divider>
                <List></List>
            </nav>
        </>
        )
    }
    return (
        <>
            <nav className="navbar">
                <div className="navbar__detected">
                    Доступно:
                    <br/>
                    <span>{newData.length}</span>
                </div>
                <Divider></Divider>
                <List></List>
            </nav>
        </>
    )
}

export default Navbar