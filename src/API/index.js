import axios from "axios";

const BASE_URL = "http://2.58.70.16:7778";
const axiosInstance = axios.create({
    baseURL: BASE_URL
})

export default axiosInstance
