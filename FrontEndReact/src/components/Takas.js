import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button, Card, Col, Container, Row } from "react-bootstrap";
import { Link } from "react-router-dom";
import { getHesaps } from "../services/HesapService";
import HesapBilgileri from "./HesapBilgileri";

function Takas() {
  const [hesaps, setHesaps] = useState([]);
  const [seciliHesap, setSeciliHesap] = useState({});
  const [hedefHesap, setHedefHesap] = useState({});
  const [satisOrani, setSatisOrani] = useState(0)
  const [alisOrani, setAlisOrani] = useState(0)
  const [miktar, setMiktar] = useState(0)
  const getData = async () => {
    const result = await getHesaps();
    console.log(result);
    setHesaps(result.data);
  };

  useEffect(() => {
    getData();
  }, [seciliHesap.ba]);
  function handleSelect(item) {
    setSeciliHesap(item);
    console.log("Hello", item);
  }
  function handlehedefSelect(item) {
    setHedefHesap(item);
    //oranlari getir

    console.log(item);
  }
  function handleAlis()
  {
    axios.post(
        `http://localhost:8080/api/v1/hesap/exchange`,{
            alanHesapId:seciliHesap.id,
            satanHesapId:hedefHesap.id,
            alanHesapPB:seciliHesap.hesapTur,
            satanHesapPB:hedefHesap.hesapTur,
            miktar:miktar
        },
       {headers: {
           "Authorization" : "Bearer "+ localStorage.getItem('acces_token')
         }
       }
     ).then(function (response) {        
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
  }
  function handleSatis()
  {
    axios.post(
        `http://localhost:8080/api/v1/hesap/exchange`,{
            alanHesapId:hedefHesap.id,
            satanHesapId:seciliHesap.id,
            alanHesapPB:hedefHesap.hesapTur,
            satanHesapPB:seciliHesap.hesapTur,
            miktar:miktar
        },
       {headers: {
           "Authorization" : "Bearer "+ localStorage.getItem('acces_token')
         }
       }
     ).then(function (response) {        
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
  }
  useEffect(() => {
    if (seciliHesap.hesapTur===hedefHesap.hesapTur) {
        setAlisOrani(1);
        setSatisOrani(1);
    }
    axios
      .post("http://localhost:8081/api/v1/oran", {
        paraBirimi: seciliHesap.hesapTur,
        paraBirimiTakas: hedefHesap.hesapTur,
      })
      .then(function (response) {
        setSatisOrani(response.data.oran)
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
      axios
      .post("http://localhost:8081/api/v1/oran", {
        paraBirimi: hedefHesap.hesapTur,
        paraBirimiTakas: seciliHesap.hesapTur ,
      })
      .then(function (response) {
        setAlisOrani(response.data.oran)
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, [hedefHesap.hesapTur, seciliHesap.hesapTur]);

  return (
    <>
      <Container>
        {seciliHesap.hesapNo !== undefined ? (
          <p className="m-4">Secili Hesap {seciliHesap.hesapNo}</p>
        ) : (
          <p className="m-4">Takas yapmak istediğiniz hesabı seçiniz.</p>
        )}
        <Row xs={1} md={2} className="g-4">
          {hesaps.map((item, index) => {
            return (
              <React.Fragment key={index}>
                <Col onClick={() => handleSelect(item)}>
                  <Card style={{ width: "18rem" }}>
                    <Card.Body>
                      <Card.Title>Hesap No: {item.hesapNo}</Card.Title>
                      <Card.Text>Hesap Turu: {item.hesapTur}</Card.Text>
                    </Card.Body>
                    Bakiye: {item.bakiye}
                  </Card>
                </Col>
              </React.Fragment>
            );
          })}
        </Row>

        {seciliHesap.hesapNo !== undefined && (
          <>
            {hedefHesap.hesapNo !== undefined ? (
              <p className="m-4">Secili Hesap {hedefHesap.hesapNo}</p>
            ) : (
              <p className="m-4">Takas yapmak istediğiniz hesabı seçiniz.</p>
            )}

            <Row xs={1} md={2} className="g-4 mt-4">
              {hesaps.map((item, index) => {
                if (item === seciliHesap) {
                  return;
                }
                return (
                  <React.Fragment key={index}>
                    <Col onClick={() => handlehedefSelect(item)}>
                      <Card style={{ width: "18rem" }}>
                        <Card.Body>
                          <Card.Title>Hesap No: {item.hesapNo}</Card.Title>
                          <Card.Text>Hesap Turu: {item.hesapTur}</Card.Text>
                        </Card.Body>
                        Bakiye: {item.bakiye}
                      </Card>
                    </Col>
                  </React.Fragment>
                );
              })}
            </Row>
          </>
        )}
        <div className="mt-4">
            <p>Seçili hesap {seciliHesap.hesapTur} hesabı </p>
            <p>Hedef hesap {hedefHesap.hesapTur} hesabı </p>
            <p>Satış fiyatı {satisOrani}  </p>
            <p>Alış fiyatı {alisOrani}  </p>
            <label for="miktar">Miktar:</label>
            <input type="number" id="miktar" placeholder="Miktar giriniz."value={miktar} onChange={(e)=>{
                setMiktar(e.target.value)
            }}></input>
            <Button className="m-2" onClick={handleSatis}>Satış yap</Button>
            <Button className="m-2" onClick={handleAlis}>Alış yap</Button>
        </div>
      </Container>
    </>
  );
}

export default Takas;
