import axi from "axios";
import {cy} from "@/export/exfortVar";

const axios = axi.create({
    baseURL:"http://localhost:8080/",
    headers:{
        ["Access-Control-Allow-Origin"]:"http://localhost:8080"
    }
});
const axiosEx = axi.create({
});

export function getDataByAxios(URL:string,params:any,success:any,n:number){
    let cnt = 1;
    byAxios(URL,params,success);

    function byAxios(URL:string,params:any,success:any){
        if(URL.startsWith("https://")){
            axiosEx.get(URL,
                {
                    // header:("", "http://localhost:8082"),
                    params:params})
                .then(function (response){
                    return success(response);
                })
                .catch(function (error) {
                    console.log(error);
                    cnt++;
                    if(cnt<n){
                        byAxios(URL,params,success);
                    }
                });
        }else{
            axios.get(URL,
                {
                    params:params})
                .then(function (response){
                    return success(response);
                })
                .catch(function (error) {
                    console.log(error);
                    cnt++;
                    if(cnt<n){
                        byAxios(URL,params,success);
                    }
                });
        }

    }
}


export function sendDataPostAxios(URL:string,params:any,success:any){
    let cnt = 1;
    byAxios(URL,params,success);

    function byAxios(URL:string,params:any,success:any){
        if(URL.startsWith("https://")){
            axiosEx.post(URL,
                {
                    params:params})
                .then(function (response){
                    return success(response);
                })
                .catch(function (error) {
                    console.log(error);
                    cnt++;
                    if(cnt<4){
                        byAxios(URL,params,success);
                    }
                });

        }else{
            axios.post(URL,
                {
                    params:params})
                .then(function (response){
                    return success(response);
                })
                .catch(function (error) {
                    console.log(error);
                    cnt++;
                    if(cnt<4){
                        byAxios(URL,params,success);
                    }
                });
        }

    }
}

export function turnForm(){
    const statusSwitch:any = document.getElementById("statusSwitch");
    statusSwitch.click();
}

export function turnLeftBar(){
    const leftBarSwitch:any = document.getElementById("leftBarSwitch");
    leftBarSwitch.click();
    if(leftBarSwitch.checked){
        // @ts-ignore
        document.getElementById("cy-wrapper").style.left='5%';
        // @ts-ignore
        document.getElementById("cy-wrapper").style.width='95%';
    }else{
        // @ts-ignore
        document.getElementById("cy-wrapper").style.left='';
        // @ts-ignore
        document.getElementById("cy-wrapper").style.width='100%';
    }
    cy.resize();
    return leftBarSwitch.checked;
}