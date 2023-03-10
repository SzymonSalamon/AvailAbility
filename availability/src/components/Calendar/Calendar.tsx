import React, { FC } from 'react';
import styles from './Calendar.module.css';

interface CalendarProps {}

const Calendar: FC<CalendarProps> = () => (
  <div className={styles.Calendar} data-testid="Calendar">
    Calendar Component
  </div>
);

export default Calendar;
