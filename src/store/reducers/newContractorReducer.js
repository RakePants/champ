import { createSlice } from "@reduxjs/toolkit";

const initState = {
    contractors: [],
}

export const newContractorReducer = createSlice({
    name: 'contractors',
    initialState: initState,
    reducers: {
        setContractors: (state, action) => {
            state.contractors = action.payload
        }
    }
})

export const {setContractors} = newContractorReducer.actions;
export default newContractorReducer.reducer