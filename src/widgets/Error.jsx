import * as React from 'react';
import Alert from '@mui/material/Alert';
import Stack from '@mui/material/Stack';
import '../style/Error.sass'
export default function Error() {
    return (
      <Stack className='error' spacing={2}>       
        <Alert severity="error">Ошибка.</Alert>
      </Stack>
    );
  }