import { createSlice } from "@reduxjs/toolkit";

const initState = {
    data: [],
}

export const newDataReducer = createSlice({
    name: 'data',
    initialState: initState,
    reducers: {
        removeItem: (state, action) => {
            state.data = state.data.filter(item => item.id !== action.payload)
        },
        setData: (state, action) => {
            state.data = action.payload
        }

    }
})

export const {removeItem, setData} = newDataReducer.actions;
export default newDataReducer.reducer;