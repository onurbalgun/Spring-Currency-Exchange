import React, { useEffect, useState } from 'react'
import { Card, Col, Container, Row } from 'react-bootstrap';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { getHesapDetails } from '../services/HesapService';

function HesapDetails() {
    const [data, setData] = useState([]);
    
    let {id} = useParams();

    const navigate = useNavigate();

    const getData = async () => {
        try {
            const result = await getHesapDetails(id);
            console.log(result.data);
            setData(result.data);
        } catch (ex) {
            if (ex.response && ex.response.status === 404)
                console.log(ex);
        }
    }

    useEffect(() =>{
        getData();
     }, []);

  return (
    <>
    <Container>
    <Row xs={1} md={2} className="g-4">
            {data.map((item, index) => {                    
              return (
                <React.Fragment key={item.id}>
                  <Col >
                    
                      <Card  style={{ width: '18rem' }}>
                       
                        <Card.Body >
                          <Card.Title>Açıklama: {item.details}</Card.Title>
                          <Card.Text>İşlem Tarihi: {item.date}</Card.Text>

                        </Card.Body>
                                Miktar: {item.totalSum}
                      </Card>
                   
                  </Col>
                </React.Fragment>
                     )
                    })}
    </Row>

</Container>
</>
  )
}

export default HesapDetails