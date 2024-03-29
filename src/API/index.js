import axios from "axios";

const BASE_URL = "localhost:7778";
const axiosInstance = axios.create({
    baseURL: BASE_URL
})

export default axiosInstance
