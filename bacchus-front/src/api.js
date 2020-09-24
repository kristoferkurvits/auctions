import { host, port } from "./config";

export const getAuctions = async () => {

    let targetUrl = `${host}${port}/api`;
    
    const response = await fetch(
        targetUrl
    );
    const json = await response.json();
    return json;

};