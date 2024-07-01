import React from 'react';
import './MainPage.css';
import Test from '../js/Test';
import Slide from '../js/Slide';

function MainPage() {
  return (
    <div className="App">
      <div className='main-header'>
        <Test />
      </div>
      <div className='main-section1'>
        <Slide />
      </div>
    </div>
  );
}

export default MainPage;
