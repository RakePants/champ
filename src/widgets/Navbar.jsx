import React from "react";
import Divider from "../shared/Divider";
import List from "../entities/List";
import '../style/Navbar.sass';

function Navbar() {
    return (
        <>
            <nav className="navbar">
                <div className="navbar__detected">
                    Доступно:
                    <br/>
                    <span>0</span>
                </div>
                <Divider></Divider>
                <List></List>
            </nav>
        </>
    )
}

export default Navbar