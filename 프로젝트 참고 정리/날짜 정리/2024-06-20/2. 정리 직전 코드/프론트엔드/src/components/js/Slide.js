import React, { useState } from 'react';
import '../css/Slide.css';

// 이미지 파일을 import 합니다
import img1 from '../../assets/Slide/Slide1.png';
import img2 from '../../assets/Slide/Slide2.png';
import img3 from '../../assets/Slide/Slide3.png';
import img4 from '../../assets/Slide/Slide4.png';
import img5 from '../../assets/Slide/Slide5.png';
import img6 from '../../assets/Slide/Slide6.png';
import img7 from '../../assets/Slide/Slide7.png';
import img8 from '../../assets/Slide/Slide8.png';
import img9 from '../../assets/Slide/Slide9.png';

const Slide = () => {
    const images = [img1, img2, img3, img4, img5, img6, img7, img8, img9];
    const [currentImage, setCurrentImage] = useState(0);

    // 다음 슬라이드로 넘어가는 함수
    const nextSlide = () => {
        setCurrentImage((prevIndex) => (prevIndex + 1) % images.length);
    };

    // 이전 슬라이드로 넘어가는 함수
    const prevSlide = () => {
        setCurrentImage((prevIndex) => (prevIndex - 1 + images.length) % images.length);
    };

    return (
        <div className="slideshow-container">
            <img src={images[currentImage]} alt={`Slide ${currentImage + 1}`} className="slide" />
            <button onClick={prevSlide} className="prev">&#10094;</button>
            <button onClick={nextSlide} className="next">&#10095;</button>
        </div>
    );
};

export default Slide;
