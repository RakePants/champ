import axios from "axios";

const BASE_URL = "http://2.58.70.16:7778";
const OLD_URL = 'http://212.60.20.177:7778';
const axiosInstance = axios.create({
    baseURL: OLD_URL
})

export default axiosInstance
