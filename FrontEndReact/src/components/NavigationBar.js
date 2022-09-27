import React from 'react'
import { Container, Nav } from 'react-bootstrap'
import { Link } from 'react-router-dom'

function NavigationBar() {
  return (
    <>
    
    <Container>
  
      <Nav  className="me-auto "> 
       <Nav.Link as={Link} to="/profile">Hesaplarım</Nav.Link>
        <Nav.Link as={Link} to="/login" >Login</Nav.Link>
        <Nav.Link as={Link} to="/register">Register</Nav.Link>
        <Nav.Link as={Link} to="/takas">Takas</Nav.Link>
      
       
       </Nav>
      
    </Container>

</>

  )
}

export default NavigationBar