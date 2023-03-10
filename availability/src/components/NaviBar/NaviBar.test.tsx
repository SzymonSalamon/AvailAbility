import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import NaviBar from './NaviBar';

describe('<NaviBar />', () => {
  test('it should mount', () => {
    render(<NaviBar />);
    
    const naviBar = screen.getByTestId('NaviBar');

    expect(naviBar).toBeInTheDocument();
  });
});