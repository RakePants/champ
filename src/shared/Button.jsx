import React from "react";
import '../style/Button.sass';
function Button({ children }) {
    return <button className="button">{children}</button>;
}

export default Button