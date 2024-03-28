import React from "react";
import Navbar from "../widgets/Navbar";
import Main from "../widgets/Main";
import Button from "../shared/Button";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { setData } from "../store/reducers/newDataReducer";
import { getNew } from "../API/requests";
import Form from "../widgets/Form";
import { accept } from "../API/send";
import { useNavigate, useParams } from "react-router-dom";
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import InputLabel from '@mui/material/InputLabel';
import FormControl from '@mui/material/FormControl';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';

export default function Applications() {
    const navigate = useNavigate();
    const { id } = useParams();
    const data = useSelector(state => state.data.data);
    const dispatch = useDispatch();
    useEffect(() => {
        async function fetchData() {
            const data = await getNew();
            dispatch(setData(data));
            if (data.length === 0) { navigate('/apps/404') }
            else navigate(`/apps/${data[0].id}`);
        }
        fetchData();
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