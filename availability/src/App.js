import React, { useState } from "react";
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import NaviBar from './components/NaviBar/NaviBar.tsx'
import Calendar from './components/Calendar/Calendar.js'
function App() {
  return (
    <div>
    <NaviBar />
    <div>
    <Calendar />
    </div>
   </div>
  );
}

export default App;
