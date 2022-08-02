import axi from "axios";
const axios = axi.create({
    baseURL:"http://localhost:8080/",
    headers:{
        ["Access-Control-Allow-Origin"]:"http://localhost:8080"
    }
});
const axiosEx = axi.create({
});

export function getDataByAxios(URL,params,success,n){
    var cnt = 1;
    byAxios(URL,params,success);

    function byAxios(URL,params,success){
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


export function sendDataPostAxios(URL,params,success){
    var cnt = 1;
    byAxios(URL,params,success);

    function byAxios(URL,params,success){
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