import * as React from 'react';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Unstable_Grid2';
import Paper from '@mui/material/Paper';
import NotFound from '../widgets/404';
import '../style/Info.sass';


export default function Info({data}) {
    console.log(data);
    
    return (
        <div className='info'>
            <Box sx={{ flexGrow: 1 }}>
                <Grid container spacing={4}>
                    <Grid xs={12} md={6}>
                        <img src={"data:image/png;base64," + data.original_image} alt="" />
                    </Grid>
                    <Grid xs={12} md={6}>
                        <div className="info__container">
                            <div className="info__title">Информация о дефекте</div>
                            <div className="info__item"><span>Вид дефекта: </span>{data.type}</div>
                            <div className="info__item"><span>Дата: </span>{"dadasd"}</div>
                            <div className="info__item"><span>Объём ямы: </span>{data.volume}</div>
                        </div>
                    </Grid>
                    <Grid xs={12}>
                        <Paper elevation={0} >
                           {data.description}
                        </Paper>
                    </Grid>
                </Grid>
            </Box>
        </div>

    );
}