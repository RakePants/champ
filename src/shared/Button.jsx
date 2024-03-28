import React from "react";
import '../style/Button.sass';
function Button({ children, title, onClick, className }) {
    return <button title={title} onClick={onClick} className={"button " + className}>{  children  }</button>;
}

export default Button