import React from "react";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { setData} from "../store/reducers/newDataReducer";
import Navbar from "../widgets/Navbar";
import Main from "../widgets/Main";
import { getComplete } from "../API/requests";
import Error from "../widgets/Error";
import { useNavigate } from "react-router-dom";

export default function Complete() {
    const [isError, setIsError] = React.useState(false);
    const navigate = useNavigate();
    const dispatch = useDispatch();
    useEffect(() => {
        try{
            async function fetchData() {
                const data = await getComplete();
                dispatch(setData(data));
            }
            fetchData();

        }
        catch(error) {
            setIsError(true);
        }

    }, [dispatch]);
    return (
        <>
            <section style={{display: 'flex', minHeight: '100%'}}>
                <Navbar></Navbar>
                <Main title={'Заказ выполнен'} index={0}></Main>
            </section>
            {isError && <Error></Error>}

        </>
    )
}