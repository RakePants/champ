import React from "react";
import '../style/Main.sass';
import Info from "../entities/Info";
import { Container } from "@mui/system";
import { useSelector } from "react-redux"
import NotFound from "../widgets/404";
function Main({children, title, index}) {
    const data = useSelector(state => state.data.data);
    if(data.length === 0){
        return <NotFound text={'Информация о дефекте не найдена'}></NotFound>
    }
    return (
        <>
        <div className="main">
            <Container >
                <div className="main__title">{title}</div>
                <Info data={data[index]}></Info>
                {children}
            </Container>
        </div>
        </>
    )
}

export default Main