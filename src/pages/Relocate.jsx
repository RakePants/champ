import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
function Relocate() {
    const navigate = useNavigate();

    useEffect(() => {
        navigate('/apps/0')
    }, [])
}
export default Relocate