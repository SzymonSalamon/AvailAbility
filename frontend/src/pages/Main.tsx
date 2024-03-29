import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getToken } from "../utils/auth";
import ShiftCalendar from "../components/Calendar/Calendar";
//import ShiftCalendar from "../components/Calendar/Calendar3";
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
    <div className="z-5 flex w-screen flex-col bg-gray-700 h-screen">
      <NaviBar></NaviBar>
     
      <ShiftCalendar/>
     
    </div>
  );
};

export default Main;



