import { createSlice } from "@reduxjs/toolkit";

const initState = {
    title: '',
}

const newInfoReducer = createSlice({
    name: 'title',
    initialState: initState,
    reducers: {
        setTitle: (state, action) => {
            state.title = action.payload
        }
    }
})

export const {setTitle} = newInfoReducer.actions;
export default newInfoReducer.reducer