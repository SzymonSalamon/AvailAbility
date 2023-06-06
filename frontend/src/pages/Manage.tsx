import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getToken } from "../utils/auth";
import ShiftCalendar from "../components/Calendar/ManageCalendar";

import NaviBar from "../components/NaviBar/NaviBar";
const queryParams = new URLSearchParams(location.search);
const username = queryParams.get('username');
const Manage = () => {
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
     
      <ShiftCalendar username={username}/>
     
    </div>
  );
};

export default Manage;