import React from "react";
import Navbar from "../widgets/Navbar";
import Main from "../widgets/Main";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { setData } from "../store/reducers/newDataReducer";
import { setTitle } from "../store/reducers/newInfoReducer";
import { getNew } from "../API/requests";
import Form from "../widgets/Form";
import { useNavigate, useParams } from "react-router-dom";
import hashToNumber from "../utils/hashToNumber";
import { setMutex } from "../store/reducers/newMutexReducer";

export default function Applications() {
    const navigate = useNavigate();
    const { id } = useParams();
    const dispatch = useDispatch();
    const newData = useSelector(state => state.data.data);

    useEffect(() => {
        async function fetchData() {
            dispatch(setMutex(false));

            console.log(newData);
            const data = await getNew(); // Start the asynchronous request
            // At this point, the mutex is still false because we're waiting for the request to complete
            console.log(newData)
            dispatch(setData(data)); // Dispatch the data to the store
            
            if (data.length === 0) {
                navigate('/apps/404');
            } else if (id === '0') {
                navigate(`/apps/${data[0].id}`);
                dispatch(setTitle('Заявка #' + hashToNumber(data[0].id)));
            }
            dispatch(setMutex(true));

        }
        fetchData();

        // eslint-disable-next-line
    }, []);


    

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