import React from "react";
import '../style/404.sass';
function NotFound({text}) {
    return (
        <div className="not-found">
            <img className="not-found__img" src="./img/404.png" alt="404" />
            <div className="not-found__text">{text}</div>
        </div>
    );
}

export default NotFound