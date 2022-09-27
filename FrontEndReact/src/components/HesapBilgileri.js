import React, { useEffect, useState } from 'react'
import { Card, Col, Container, Row } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { getHesaps } from '../services/HesapService'
import { getUserDetails } from '../services/UserService'

function HesapBilgileri() {
    const [hesaps, setHesaps] = useState([]);
  const getData = async () => {
    const result = await  getHesaps()  
    console.log(result)    
 setHesaps(result.data)
     
    
   }

   useEffect(() => {
     getData()
   }, [])

return(
    <>
    <Container>
        <Row xs={1} md={2} className="g-4">
                {hesaps.map((item, index) => {                    
                  return (
                    <React.Fragment key={index}>
                      <Col >
                        <Link to={`/hesapdetay/${item.id}`}>
                          <Card  style={{ width: '18rem' }}>
                           
                            <Card.Body >
                              <Card.Title>Hesap No: {item.hesapNo}</Card.Title>
                              <Card.Text>Hesap Turu: {item.hesapTur}</Card.Text>
 
                            </Card.Body>
                                    Bakiye: {item.bakiye}
                          </Card>
                        </Link>
                      </Col>
                    </React.Fragment>
                         )
                        })}
        </Row>
        <Row className='mt-4'> 
         <Link to={`/hesapdetay/ `}>Para Takasi yap.</Link>
    </Row>
    </Container>
    { /*hesaps.map(element=>{
     return  <div>{element.hesapNo}</div>
    })*/ }
    </>
)


}

export default HesapBilgileri