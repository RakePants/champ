import * as React from 'react';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Unstable_Grid2';
import { Skeleton } from '@mui/material';
import '../style/Info.sass';
import '../style/Main.sass';
import Container from '@mui/material/Container';

export default function InfoSkeleton() {
    return (
        <div className="main">
            <Container >
                <div className="main__title">
                    <Skeleton variant="text" width={`100%`} sx={{ fontSize: '1.5rem', bgcolor: 'grey.700' }} />
                </div>
                <div className='info'>
                    <Box sx={{ flexGrow: 1 }}>
                        <Grid container spacing={4}>
                            <Grid xs={12} md={6}>
                                <Skeleton variant="rounded" width={'100%'} height={468} sx={{ bgcolor: 'grey.700' }} />
                            </Grid>
                            <Grid xs={12} md={6}>
                                <div className="info__container">
                                    <Skeleton variant="text" width={`100%`} sx={{ fontSize: '1rem', bgcolor: 'grey.700' }} />
                                    <Skeleton variant="text" width={`100%`} sx={{ fontSize: '1rem', bgcolor: 'grey.700' }} />
                                    <Skeleton variant="text" width={`100%`} sx={{ fontSize: '1rem', bgcolor: 'grey.700' }} />
                                    <Skeleton variant="text" width={`100%`} sx={{ fontSize: '1rem', bgcolor: 'grey.700' }} />
                                </div>
                            </Grid>
                            <Grid xs={12}>
                                <Skeleton variant="rounded" width={`100%`} height={200} sx={{ bgcolor: 'grey.700' }} />
                            </Grid>
                        </Grid>
                    </Box>
                </div>
            </Container>
        </div>

    )
}