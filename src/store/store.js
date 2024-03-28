import { configureStore } from "@reduxjs/toolkit";
import  newDataReducer  from "./reducers/newDataReducer";
export const store = configureStore({
    reducer: {
        data: newDataReducer
    }
})