import { createSlice } from "@reduxjs/toolkit";

const initState = {
    mutex: false
}

const newMutexReducer = createSlice({
    name: 'mutex',
    initialState: initState,
    reducers: {
        setMutex: (state, action) => {
            state.mutex = action.payload
        }
    }
})

export const { setMutex } = newMutexReducer.actions
export default newMutexReducer.reducer