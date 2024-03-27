import React from "react";
import Header from "../widgets/Header";
import Navbar from "../widgets/Navbar";
import Main from "../widgets/Main";
export default function Orders() {
    return(
        <>

            <Header></Header>
            <section style={{display: 'flex', minHeight: '100%'}}>
                <Navbar></Navbar>
                <Main></Main>
            </section>

        </>

    )
}