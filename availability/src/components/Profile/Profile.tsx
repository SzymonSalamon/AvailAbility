import React, { FC } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css'

interface ProfileProps {}
const avatar="obrazek";
const name="Szymon"

const Profile: FC<ProfileProps> = () => (
  <div className="Profile d-flex flex-row mx-1">
    <div className="Profile_avatr">
    <svg xmlns="http://www.w3.org/2000/svg" width="2.5rem" fill="currentColor" className="bi bi-person-circle" viewBox="0 0 16 16">
  <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
  <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
    </svg>
    </div>
    <div className='nav-link mx-1 d-flex align-items-center'>
    {name}
    </div>
  </div>
);

export default Profile;
