import React, { useState } from 'react'
import { Button,Form } from 'react-bootstrap';
import { login } from '../services/AuthService';
 

function LoginForm() {
    const [user, setUser] = useState({
        email:'',
        password:''
    }) 
    const [errorState, setErrorState] = useState(false)

    const handleFormChange = (e) => {
        
        const { name, value } = e.target;
        setUser((prevState) => ({
          ...prevState,
          [name]: value,
        }));
      };
      async function  handleLogin(e){
        e.preventDefault();
        login(user.email,user.password);
        
       // const error= await userctx.userLogin(user.email,user.password)
    
    }
  return (
    <Form className="container justify-content-center   mt-4 ">
      <Form.Group className="mb-3" controlId="formBasicEmail">
        <Form.Label>Email address</Form.Label>
        <Form.Control type="email"  name='email' placeholder="Enter email" value={user.email} onChange={handleFormChange} />
        {errorState&&<Form.Text className="text-danger">Invalid password or username</Form.Text>}
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicPassword">
        <Form.Label>Password</Form.Label>
        <Form.Control type="password" name='password' placeholder="Password"  value={user.password} onChange={handleFormChange} />
      </Form.Group>
 
      <Button variant="primary" type="submit" onClick={handleLogin}>
        Login
      </Button>
    </Form>
  )

  }
export default LoginForm