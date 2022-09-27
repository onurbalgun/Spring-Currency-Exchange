
import axios from "axios";

const apiEndpoint= "http://localhost:8080/api/v1/user";


export function getUserDetails()
{
    let acces_token = localStorage.getItem("acces_token");
  axios.get(
    apiEndpoint,
    {headers: {
        "Authorization" : "Bearer "+acces_token
      }
    }
  )
  .then((response) => {
     console.log(response)
    },
    (error) => {
      console.log(error.response.status)
    }
  );
}