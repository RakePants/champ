import { configureStore } from "@reduxjs/toolkit";
import newContractorReducer  from "./reducers/newContractorReducer";
import  newDataReducer  from "./reducers/newDataReducer";
import newInfoReducer  from "./reducers/newInfoReducer";
import newMutexReducer  from "./reducers/newMutexReducer";

export const store = configureStore({
    reducer: {
        data: newDataReducer,
        contractors: newContractorReducer,
        title: newInfoReducer,
        mutex: newMutexReducer
    }
})