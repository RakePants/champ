import React from "react";

import Navbar from "../widgets/Navbar";
import Main from "../widgets/Main";
import Button from "../shared/Button";


export default function Applications() {
    return (
        <>
        
            <section style={{display: 'flex', minHeight: '100%'}}>
                <Navbar></Navbar>
                <Main title={'Заявка №123'}>
                    <div
                        style={{
                            display: 'flex',
                            width: '100%',
                            justifyContent: 'space-evenly',
                            marginTop: '20px',
                        }}
                    >
                        <Button>Отклонить</Button>
                        <Button  className={'button-accept'}>Принять</Button>
                    </div>
                    
                </Main>
            </section>
        </>
    )
}