import React from "react";
//import NotFound from "../widgets/404";
import Navbar from "../widgets/Navbar";
import Main from "../widgets/Main";
import Button from "../shared/Button";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { setData } from "../store/reducers/newDataReducer";
import { getAccept } from "../API/requests";
import { useNavigate, useParams } from "react-router-dom";
import hashToNumber from "../utils/hashToNumber";
import { setTitle } from "../store/reducers/newInfoReducer";
import Input from "../shared/Input";
import { accept, complete } from "../API/send";
import { removeItem } from "../store/reducers/newDataReducer";
import { setMutex } from "../store/reducers/newMutexReducer";


//import Error from "../widgets/Error";
export default function Orders() {
    const navigate = useNavigate();
    const { id } = useParams();
    const dispatch = useDispatch();
    const [image, setImage] = React.useState(undefined);
    const newData = useSelector(state => state.data.data);
    useEffect(() => {
        async function fetchData() {
            dispatch(setMutex(false));

            const data = await getAccept();
            dispatch(setData(data));
            if (data.length === 0) { navigate('/orders/0') }
            else if (id === '0') { navigate(`/orders/${data[0].id}`); dispatch(setTitle('В процессе #' + hashToNumber(data[0].id))) }
            dispatch(setMutex(true));
        }
        fetchData();
        // eslint-disable-next-line
    }, []);

    function go() {
        const index = (newData.findIndex(item => item.id === id) + 1) % newData.length;
        dispatch(removeItem(id));
        navigate(`/orders/${newData[index].id}`);
        const root = document.getElementById('root');
        root.scrollIntoView({ behavior: 'smooth' });
    }
    function handleComplete() {

        const formData = new FormData();
        formData.append('completion_image', image);
        complete(id, formData);
        go();
    }
    useEffect(() => {
        console.log(image)
    }, [image])
    return (
        <>
            <section style={{ display: 'flex', minHeight: '100%' }}>
                <Navbar></Navbar>
                <Main title={'Заказ №123'} index={0}>
                    <div style={{ display: 'flex', justifyContent: 'center', marginTop: '20px', alignItems: 'center' }}>
                        <Button disabled={image === undefined} onClick={handleComplete} className={'button-complete'}>Выполнено</Button>
                        <Input image={image} setImage={setImage}></Input>
                    </div>
                </Main>
            </section>

        </>

    )
}