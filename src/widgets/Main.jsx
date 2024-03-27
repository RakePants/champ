import React from "react";
import '../style/Main.sass';
import Info from "../entities/Info";
import { Container } from "@mui/system";

function Main() {
    return (
        <>
        <div className="main">
            <Container >
                <div className="main__title">Заказ #2132</div>
                <Info></Info>
            </Container>
        </div>
        </>
    )
}

export default Main