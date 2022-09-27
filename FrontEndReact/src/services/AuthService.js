import axios from "axios";

const apiEndpoint= "http://localhost:8080/login";

export function login(email,password)
{
     axios.post(apiEndpoint,{email,password}).then(function (response) {
       // console.log(response);
        localStorage.setItem("acces_token",response.data.acces_token);
        localStorage.setItem("refresh_token",response.data.refresh_token);
      })
      .catch(function (error) {
        console.log(error);
      });
     /* fetch(apiEndpoint,{
        method:'POST'
      })*/
}



axios.interceptors.response.use(null, (error) => {
    const expectedError =
      error.response &&
      error.response.status >= 400 &&
      error.response.status < 500
  
    if (!expectedError) {
      console.log(expectedError);
    }
  
    return Promise.reject(error)
  })
