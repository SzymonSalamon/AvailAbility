import React, { FC } from 'react';
import styles from './NaviBar.module.css';

interface NaviBarProps {}

const NaviBar: FC<NaviBarProps> = () => (
  <div className={styles.NaviBar} data-testid="NaviBar">
    NaviBar Component
  </div>
);

export default NaviBar;
