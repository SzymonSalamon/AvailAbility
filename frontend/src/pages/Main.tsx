import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getToken } from "../utils/auth";
import Calendar from '../components/Calendar/Calendar.js'
import NaviBar from "../components/NaviBar/NaviBar";
const Main = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const token = getToken();
    if (!token) {
      navigate("/login", { replace: true });
    }
  }, []);

  
  return (
    <div className="z-5 flex w-screen flex-col bg-black">
      <NaviBar></NaviBar>
      <Calendar></Calendar>
    </div>
  );
};

export default Main;



