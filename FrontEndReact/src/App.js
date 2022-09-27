import React from 'react';
import { Route, Routes } from 'react-router-dom'; 
import RegisterForm from './components/RegisterForm';
import Login from './pages/Login';
import 'bootstrap/dist/css/bootstrap.min.css'
import NavigationBar from './components/NavigationBar';
import Profile from './pages/Profile';
import HesapDetails from './components/HesapDetails';
import Takas from './components/Takas';
 

function App() {
  return (
    <>
      <NavigationBar></NavigationBar>
      <main>
      <Routes>
         
        <Route path='/login' element={<Login/>}/> 
        <Route path='/register'  element={   <RegisterForm/>}/>       
        <Route path='/profile'  element={   <Profile/>}/>
        <Route path='/takas'  element={   <Takas/>}/>
        <Route path="/hesapdetay/:id" element={<HesapDetails />} />       
      
      </Routes> 
      </main>
     
    </>
  );
}

export default App;
