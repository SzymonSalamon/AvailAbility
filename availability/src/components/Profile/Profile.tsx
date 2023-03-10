import React, { FC } from 'react';
import styles from './Profile.module.css';

interface ProfileProps {}

const Profile: FC<ProfileProps> = () => (
  <div className={styles.Profile} data-testid="Profile">
    Profile Component
  </div>
);

export default Profile;
