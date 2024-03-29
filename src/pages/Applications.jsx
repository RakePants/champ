import React from "react";
import Navbar from "../widgets/Navbar";
import Main from "../widgets/Main";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { setData } from "../store/reducers/newDataReducer";
import { setTitle } from "../store/reducers/newInfoReducer";
import { getNew } from "../API/requests";
import Form from "../widgets/Form";
import { useNavigate, useParams } from "react-router-dom";
import hashToNumber from "../utils/hashToNumber";


export default function Applications() {
    const navigate = useNavigate();
    const { id } = useParams();
    const dispatch = useDispatch();
    useEffect(() => {
        async function fetchData() {
            const data = await getNew();
            dispatch(setData(data));
            if (data.length === 0) { navigate('/apps/404') }
            else if(id === '0') {navigate(`/apps/${data[0].id}`); dispatch(setTitle('Заявка #' + hashToNumber(data[0].id))) }
        }
        fetchData();
        // eslint-disable-next-line
    }, [dispatch]);

    

    return (
        <>

            <section style={{ display: 'flex', minHeight: '100%' }}>
                <Navbar></Navbar>
                <Main title={'Заявка №123'} index={0}>
                    <Form id={id}></Form>
                </Main>
            </section>
        </>
    )
}