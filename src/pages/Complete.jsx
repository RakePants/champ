import React from "react";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { setData} from "../store/reducers/newDataReducer";
import Navbar from "../widgets/Navbar";
import Main from "../widgets/Main";
import { setMutex } from "../store/reducers/newMutexReducer";
import { getComplete } from "../API/requests";
import { useNavigate, useParams } from "react-router-dom";
import hashToNumber from "../utils/hashToNumber";
import { setTitle } from "../store/reducers/newInfoReducer";

export default function Complete() {
    const navigate = useNavigate();
    const { id } = useParams();
    const dispatch = useDispatch();
    const mutex = useSelector(state => state.data.mutex);
    useEffect(() => {
        async function fetchData() {
            dispatch(setMutex(false));
            const data = await getComplete();
            dispatch(setData(data));
            if(data.length === 0) {navigate('/complete/0')}
            else if(id === '0') {
                navigate(`/complete/${data[0].id}`); 
                dispatch(setTitle('Выполнено #' + hashToNumber(data[0].id))) 
            }
            dispatch(setMutex(true));

        }
        fetchData();
        // eslint-disable-next-line
    }, []);
    return (
        <>
            <section style={{display: 'flex', minHeight: '100%'}}>
                <Navbar></Navbar>
                <Main isComplete={true}></Main>
            </section>
        </>
    )
}