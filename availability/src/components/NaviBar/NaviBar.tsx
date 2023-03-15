import React, { FC } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Profile from '../Profile/Profile.tsx';

interface NaviBarProps {}

const NaviBar: FC<NaviBarProps> = () => (
  <nav className="navbar navbar-expand-lg navbar-dark bg-dark mb-1">
  <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
    <span className="navbar-toggler-icon"></span>
  </button>
  <a className="navbar-brand" href="#">
  <Profile></Profile>
</a>

  <div className="collapse navbar-collapse position-relative" id="navbarTogglerDemo03">
    <ul className="navbar-nav mr-auto mt-2 mt-lg-0">
      <li className="nav-item active">
        <a className="nav-link" href="#">Home <span className="sr-only">(current)</span></a>
      </li>
      <li className="nav-item">
        <a className="nav-link" href="#">Link</a>
      </li>
      <li className="nav-item">
        <a className="nav-link disabled" href="#">Disabled</a>
      </li>
    </ul>
    <form className="form-inline my-2 my-lg-0 d-flex position-absolute end-0">
      <input className="form-control mr-sm-2 mr-1" type="search" placeholder="Search" aria-label="Search"/>
      <button className="btn btn-outline-success my-2 my-sm-0 mr-1" type="submit">Search</button>
    </form>
  </div>
</nav>
);

export default NaviBar;
