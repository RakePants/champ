import { createSlice } from "@reduxjs/toolkit";

const initState = {
    data: [],
}

export const newDataReducer = createSlice({
    name: 'data',
    initialState: initState,
    reducers: {
        setNewData: (state, action) => {
            state.newData = action.payload
        },
        setAcceptData: (state, action) => {
            state.acceptData = action.payload
        },
        setDeclinedData: (state, action) => {
            state.declinedData = action.payload    
        },
        setCompletedData: (state, action) => {
            state.completedData = action.payload
        },
        setData: (state, action) => {
            state.data = action.payload
        }

    }
})

export const {setNewData, setData} = newDataReducer.actions;
export default newDataReducer.reducer;