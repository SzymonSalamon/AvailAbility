import React, { lazy, Suspense } from 'react';

const LazyNaviBar = lazy(() => import('./NaviBar'));

const NaviBar = (props: JSX.IntrinsicAttributes & { children?: React.ReactNode; }) => (
  <Suspense fallback={null}>
    <LazyNaviBar {...props} />
  </Suspense>
);

export default NaviBar;
