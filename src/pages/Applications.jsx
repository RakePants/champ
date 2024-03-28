import React from "react";
import Navbar from "../widgets/Navbar";
import Main from "../widgets/Main";
import Button from "../shared/Button";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { setData } from "../store/reducers/newDataReducer";
import { getNew } from "../API/requests";


export default function Applications() {
    const dispatch = useDispatch();
    useEffect(() => {
        async function fetchData() {
            const data = await getNew();
            console.log(data)
            dispatch(setData(data));
        }
        fetchData();
    }, [dispatch]);

    
    return (
        <>
            
            <section style={{display: 'flex', minHeight: '100%'}}>
                <Navbar></Navbar>
                <Main title={'Заявка №123'} index={0}>
                    <div
                        style={{
                            display: 'flex',
                            width: '100%',
                            justifyContent: 'space-evenly',
                            marginTop: '20px',
                        }}
                    >
                        <Button className={'button-decline'}>Отклонить</Button>
                        <Button  className={'button-accept'}>Принять</Button>
                    </div>
                    
                </Main>
            </section>
        </>
    )
}