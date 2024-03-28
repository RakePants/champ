import * as React from 'react';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import InputLabel from '@mui/material/InputLabel';
import FormControl from '@mui/material/FormControl';
import Button from "../shared/Button";
import dayjs from 'dayjs';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { useSelector } from "react-redux";
import { accept } from "../API/send";

function Form({ id }) {
    const contractors = useSelector(state => state.contractors.contractors);
    const [date, setDate] = React.useState('2024-03-28');
    const [contractor, setContractor] = React.useState(contractors[0].id);
    function handleAcceptClick() {
        console.log(contractor)
        accept(id, contractor, date);
    }
    return (
        <div className='form-container'>
            <FormControl fullWidth>
                <InputLabel id="demo-simple-select-label" sx={{ color: 'white' }}>Принять</InputLabel>
                <Select
                    labelId="demo-simple-select-label"
                    id="demo-simple-select"
                    value={contractor}
                    label="Принять"
                    onChange={(e) => setContractor(e.target.value)}
                >
                    {contractors.map((contractor) => (
                        <MenuItem value={contractor.id} key={contractor.id}>{contractor.email}</MenuItem>
                    ))}

                </Select>
                <LocalizationProvider dateAdapter={AdapterDayjs}>
                    <DatePicker defaultValue={dayjs('2024-03-28')} onChange={(newValue) => { setDate(newValue); }} />
                </LocalizationProvider>
                <Button title={"Отклонить это заявление"} onClick={() => { }} className={'button-decline'}>Отклонить</Button>
                <Button title={"Принять это заявление"} onClick={handleAcceptClick} className={'button-accept'}>Принять</Button>
            </FormControl>
        </div>

    )
}

export default Form