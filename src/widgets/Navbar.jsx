import React from "react";
import Divider from "../shared/Divider";
import List from "../entities/List";
import '../style/Navbar.sass';
import { useSelector } from "react-redux";

function Navbar() {
    const newData = useSelector(state => state.data.data);

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