import React from "react";

import Navbar from "../widgets/Navbar";
import Main from "../widgets/Main";
export default function Orders() {
    return(
        <>

            
            <section style={{display: 'flex', minHeight: '100%'}}>
                <Navbar></Navbar>
                <Main title={'Заказ №123'}></Main>
            </section>

        </>

    )
}