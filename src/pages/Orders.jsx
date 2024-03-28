import React from "react";
//import NotFound from "../widgets/404";
import Navbar from "../widgets/Navbar";
import Main from "../widgets/Main";
import Button from "../shared/Button";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { setData} from "../store/reducers/newDataReducer";
import { getAccept } from "../API/requests";
import { useNavigate, useParams } from "react-router-dom";
//import Error from "../widgets/Error";
export default function Orders() {
    const navigate = useNavigate();
    const { id } = useParams();
    const dispatch = useDispatch();
    useEffect(() => {
        async function fetchData() {
            const data = await getAccept();
            dispatch(setData(data));
            if(data.length === 0) {navigate('/orders/0')}
            else navigate(`/orders/${data[0].id}`);
        }
        fetchData();
    }, [dispatch]);

    return(
        <>
            <section style={{display: 'flex', minHeight: '100%'}}>
                <Navbar></Navbar>
                <Main title={'Заказ №123'} index={0}>
                    <Button className={'button-complete'}>Выполнено</Button>
                </Main>
                {/* <NotFound text={'Заказы не найдены'}></NotFound> */}
            </section>

        </>

    )
}