import React from "react";
import '../style/Main.sass';
import Info from "../entities/Info";
import { Container } from "@mui/system";

function Main({children, title}) {
    return (
        <>
        <div className="main">
            <Container >
                <div className="main__title">{title}</div>
                <Info></Info>
                {children}
            </Container>
        </div>
        </>
    )
}

export default Main