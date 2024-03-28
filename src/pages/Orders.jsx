import React from "react";
//import NotFound from "../widgets/404";
import Navbar from "../widgets/Navbar";
import Main from "../widgets/Main";
import Button from "../shared/Button";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { setData} from "../store/reducers/newDataReducer";
import { getAccept } from "../API/requests";

//import Error from "../widgets/Error";
export default function Orders() {
    const [isError, setIsError] = React.useState(false);
    const dispatch = useDispatch();
    useEffect(() => {
        try{
            async function fetchData() {
                const data = await getAccept();
                dispatch(setData(data));
            }
            fetchData();
        }
        catch(error) {
            setIsError(true);
        }

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