import * as React from 'react';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Unstable_Grid2';
import Paper from '@mui/material/Paper';
import '../style/Info.sass';


export default function Info() {
    return (
        <div className='info'>
            <Box sx={{ flexGrow: 1 }}>
                <Grid container spacing={4}>
                    <Grid xs={12} md={6}>
                        <img src="./img/Info.png" alt="" />
                    </Grid>
                    <Grid xs={12} md={6}>
                        <div className="info__container">
                            <div className="info__title">Информация о дефекте</div>
                            <div className="info__item"><span>Вид дефекта: </span>Выбоина</div>
                            <div className="info__item"><span>Дата: </span>23.12.2004</div>
                            <div className="info__item"><span>Объём ямы: </span>2м</div>
                        </div>
                    </Grid>
                    <Grid>
                        <Paper elevation={0} >
                            Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptate reprehenderit blanditiis necessitatibus facilis possimus, harum, aliquam animi repellat deserunt placeat explicabo magnam? Fugit itaque magni nulla earum, consectetur totam error.
                            Quasi nisi repellendus ipsum esse fuga quisquam atque autem facere corrupti molestiae, eveniet quod earum nobis, deserunt fugiat aperiam quae reprehenderit pariatur deleniti nulla quia beatae asperiores? Laboriosam, facere cumque.
                            Eligendi perspiciatis illo nihil vitae architecto quam, nam distinctio consectetur non voluptates. Expedita sit harum veniam ipsam reprehenderit fuga possimus quisquam ratione soluta, aperiam nostrum hic accusamus necessitatibus odio omnis.
                            Ex, qui amet nihil doloribus nostrum doloremque veritatis! Ad praesentium perferendis recusandae ipsum veritatis a architecto. Dicta maiores, laudantium ipsam quisquam commodi ducimus expedita, ut, quasi qui nemo mollitia nihil.
                            Sed, perferendis iusto. Unde consequuntur tempora illo, dolorum fugiat placeat dolore sapiente ullam iure autem quaerat dicta atque voluptatum saepe! Incidunt voluptatibus quasi maiores, unde ullam veritatis dicta illo recusandae!
                        </Paper>
                    </Grid>
                </Grid>
            </Box>
        </div>

    );
}