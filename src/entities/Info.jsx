import * as React from 'react';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Unstable_Grid2';
import Paper from '@mui/material/Paper';
import '../style/Info.sass';
import { YMaps, Map, Placemark } from '@pbe/react-yandex-maps';
import { useLocation } from "react-router-dom";
import { complete } from '../API/send';
import { dateToString } from '../utils/dateToString';
import { useSelector } from 'react-redux';

export default function Info({ data, isComplete }) {
    const contractors = useSelector(state => state.contractors.contractors);
    console.log(data.longitude, data.latitude);
    return (
        <div className='info'>
            <Box sx={{ flexGrow: 1 }}>
                <Grid container spacing={4}>
                    <Grid xs={12} md={6}>
                        <div className='info__image' >{isComplete === true ? "До:" : "Оригинал"}</div>
                        <img src={"data:image/png;base64," + data.original_image} alt="" />
                    </Grid>
                    <Grid xs={12} md={6}>
                        <div className='info__image' >{isComplete === true ? "После:" : "Обработка"}</div>
                        <img src={"data:image/png;base64," + (isComplete === true ? data.completion_image : data.processed_image) || data.processed_image} alt="" />
                    </Grid>
                    <Grid xs={12} md={6}>
                        <div className="info__container">
                            <div className="info__title">Информация о дефекте</div>
                            <div className="info__item"><span>Вид дефекта: </span>{data.type}</div>
                            <div className="info__item"><span>Дата: </span>{dateToString(new Date(data.timestamp))}</div>
                            <div className="info__item"><span>Объём ямы: </span>{data.volume}</div>
                        </div>
                    </Grid>
                    <Grid xs={12} md={6}>
                        <div className="info__container">
                            <div className="info__item"><span>Адрес: </span>{data.address}</div>
                            {isComplete === true &&
                                (
                                    <>
                                        <div className="info__item"><span>Дата выполнения работы: </span>{data.completion_target_date}</div>
                                        <div className="info__item"><span>Подрядчик: </span>{contractors.find(item => item.id === data.contractor_id).name}</div>
                                    </>
                                )}
                        </div>
                    </Grid>

                    <Grid xs={12}>
                        <Paper elevation={0} >
                            {data.description}
                        </Paper>
                    </Grid>
                    <Grid xs={12}>
                        <YMaps >
                            <Map height='500px' width='100%' defaultState={{ center: [data.latitude, data.longitude], zoom: 5 }}>
                            <Placemark geometry={[data.latitude, data.longitude]} />
                            </Map>
                        </YMaps>
                    </Grid>
                </Grid>
            </Box>
        </div>

    );
}