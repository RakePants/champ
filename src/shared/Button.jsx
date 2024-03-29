import React from "react";
import '../style/Button.sass';
function Button({ children, title, onClick, className, disabled }) {
    return <button disabled={disabled} title={title} onClick={onClick} className={"button " + className}>{  children  }</button>;
}

export default Button