import React from "react";

import Navbar from "../widgets/Navbar";
import Main from "../widgets/Main";
export default function Complete() {
    return (
        <>

            
            <section style={{display: 'flex', minHeight: '100%'}}>
                <Navbar></Navbar>
                <Main title={'Заказ выполнен'}></Main>
            </section>

        </>
    )
}