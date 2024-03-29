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
import { useSelector, useDispatch } from "react-redux";
import { accept } from "../API/send";
import { useNavigate } from "react-router-dom";
import { decline } from '../API/send';
import { removeItem } from '../store/reducers/newDataReducer';
import '../style/Form.sass';
import Divider from '../shared/Divider';

function Form({ id }) {
    const contractors = useSelector(state => state.contractors.contractors);
    const [date, setDate] = React.useState('2024-03-28');
    const data = useSelector(state => state.data.data);
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const [contractor, setContractor] = React.useState(contractors[0].id);
    function go() {
        const index = (data.findIndex(item => item.id === id) + 1) % data.length;
        dispatch(removeItem(id));
        navigate(`/apps/${data[index].id}`);
        const root = document.getElementById('root');
        root.scrollIntoView({ behavior: 'smooth' });
    }
    function handleAcceptClick() {
        function formatDate(date) {
            const year = date.getFullYear();
            let month = date.getMonth() + 1; // Месяцы в JavaScript начинаются с 0
            month = month < 10 ? '0' + month : month; // Добавляем ноль, если месяц меньше 10
            let day = date.getDate();
            day = day < 10 ? '0' + day : day; // Добавляем ноль, если день меньше 10
            return `${year}-${month}-${day}`;
        }
        console.log( date)
        if(date === '2024-03-28'){
            accept(id, contractor, date);
        }
        else{
            const strDate = formatDate(date.$d);
            console.log(strDate)
            accept(id, contractor, strDate);
        }
        go();
        //     
    }

    function handleDeclineClick() {
        decline(id);
        go();
    }
    return (
        <>
            <Divider></Divider>
            <div className='form-container'>
                <FormControl fullWidth>
                    <InputLabel id="demo-simple-select-label" sx={{  marginTop: '10px' }}>Принять</InputLabel>
                    <Select
                        sx={{ color: 'white', marginTop: '10px', marginBottom: '20px' }}
                        labelId="demo-simple-select-label"
                        id="demo-simple-select"
                        value={contractor}
                        label="Принять"
                        onChange={(e) => setContractor(e.target.value)}
        
                    >
                        {contractors.map((contractor) => (
                            <MenuItem value={contractor.id} key={contractor.id}>{contractor.name}</MenuItem>
                        ))}

                    </Select>
                    <LocalizationProvider dateAdapter={AdapterDayjs}>
                        <DatePicker defaultValue={dayjs(Date.now)} onChange={(newValue) => { setDate(newValue); }} />
                    </LocalizationProvider>
                    <div className='form__buttons'>
                        <Button title={"Отклонить это заявление"} onClick={handleDeclineClick} className={'button-decline'}>Отклонить</Button>
                        <Button title={"Принять это заявление"} onClick={handleAcceptClick} className={'button-accept'}>Принять</Button>
                    </div>
                </FormControl>
            </div>

        </>

    )
}

export default Form