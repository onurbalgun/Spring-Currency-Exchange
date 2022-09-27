import axios from "axios";

const apiEndpoint = 'http://localhost:8080/api/v1/hesap'



export  function  getHesaps() {
    let acces_token = localStorage.getItem("acces_token");
   return axios.get(
      apiEndpoint,
      {headers: {
          "Authorization" : "Bearer "+acces_token
        }
      }
    )
    
}

export function getHesapDetails(hesapid)
{
    let acces_token = localStorage.getItem("acces_token");
    return axios.get(
        `http://localhost:8080/api/v1/hesapdetay/${hesapid}`,
       {headers: {
           "Authorization" : "Bearer "+acces_token
         }
       }
     )
}
