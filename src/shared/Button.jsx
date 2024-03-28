import React from "react";
import '../style/Button.sass';
function Button({ children, onClick, className }) {
    return <button onClick={onClick} className={"button " + className}>{  children  }</button>;
}

export default Button