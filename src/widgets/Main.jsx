import React from "react";
import '../style/Main.sass';
import Info from "../entities/Info";
import { Container } from "@mui/system";
import { useSelector } from "react-redux"
import NotFound from "../widgets/404";
import { useParams } from "react-router-dom";
import InfoSkeleton from "../entities/InfoSkeleton";

function Main({children, title, index}) {
    const { id } = useParams();
    console.log(id)
    const data = useSelector(state => state.data.data);
    const activeItem = data.find(item => item.id === id);
    if(+id === 0){
        return (
            <InfoSkeleton></InfoSkeleton>
        )
    }
    if(data.length === 0 || activeItem === undefined) {
        return <NotFound text={'Информация о дефекте не найдена'}></NotFound>
    }

    return (
        <>
        <div className="main">
            <Container >
                <div className="main__title">{title}</div>
                <Info data={activeItem}></Info>
                {children}
            </Container>
        </div>
        </>
    )
}

export default Main