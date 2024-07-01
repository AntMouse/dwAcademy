import React, { useState } from 'react';
import './Goods.css'; // 스타일 파일 경로 수정

// 이미지 파일을 가져옵니다.
import a from '../assets/Goods/1.jpg';
import b from '../assets/Goods/2.jpg';
import c from '../assets/Goods/3.jpg';
import d from '../assets/Goods/4.jpg';
import e from '../assets/Goods/5.jpg';
import f from '../assets/Goods/6.jpg';
import g from '../assets/Goods/7.jpg';
import h from '../assets/Goods/8.jpg';
import i from '../assets/Goods/9.jpg';
import j from '../assets/Goods/10.jpg';
import k from '../assets/Goods/11.jpg';
import l from '../assets/Goods/12.jpg';
import m from '../assets/Goods/13.jpg';
import n from '../assets/Goods/14.jpg';
import o from '../assets/Goods/15.jpg';
import p from '../assets/Goods/16.jpg';
import q from '../assets/Goods/17.jpg';
import r from '../assets/Goods/18.jpg';
import s from '../assets/Goods/19.jpg';
import t from '../assets/Goods/20.jpg';

const products = [
  { id: 1, name: '[Ellon]<br>미니 레터링 후드', price: 10000, imageUrl: a, delivery: "메타택배" },
  { id: 2, name: '[Paris]<br>자수 오버핏 후드', price: 20000, imageUrl: b, delivery: "버스택배" },
  { id: 3, name: '[Candy]<br>이중지 오버핏탄탄 후드', price: 20000, imageUrl: c, delivery: "메타택배" },
  { id: 4, name: '[University]<br>오버핏 후드', price: 20000, imageUrl: d, delivery: "버스택배" },
  { id: 5, name: '[NewYork]<br>유얼뉴욕 자수레터링 후드', price: 20000, imageUrl: e, delivery: "메타택배" },
  { id: 6, name: '[Hawkins]<br>프린팅 후드', price: 20000, imageUrl: f, delivery: "버스택배" },
  { id: 7, name: '[X]<br>X 오버핏 후드 (1+1)', price: 20000, imageUrl: g, delivery: "메타택배" },
  { id: 8, name: '[MUSINSA]<br>개구리 기모 후드', price: 20000, imageUrl: h, delivery: "버스택배" },
  { id: 9, name: '[Smiley]<br>오버핏 기모 후드', price: 20000, imageUrl: i, delivery: "메타택배" },
  { id: 10, name: '[2%]<br>루즈핏 레이어드 후드', price: 20000, imageUrl: j, delivery: "버스택배" },
  { id: 11, name: '[Chicago]<br>양기모 후드', price: 20000, imageUrl: k, delivery: "메타택배" },
  { id: 12, name: '[Seattle]<br>자수 오버핏 후드 (남녀공용)', price: 20000, imageUrl: l, delivery: "버스택배" },
  { id: 13, name: '[PinkSunset]<br>테디베어 반집업 후드', price: 20000, imageUrl: m, delivery: "메타택배" },
  { id: 14, name: '[Brookyln]<br>오버핏 후드', price: 20000, imageUrl: n, delivery: "버스택배" },
  { id: 15, name: '[What it isnt]<br>스몰로고 와펜 후드', price: 20000, imageUrl: o, delivery: "메타택배" },
  { id: 16, name: '[Genzo]<br>블랙 프론트프린팅 후드', price: 20000, imageUrl: p, delivery: "버스택배" },
  { id: 17, name: '[Celine]<br>16루스 코튼 후드', price: 20000, imageUrl: q, delivery: "메타택배" },
  { id: 18, name: '[Ebbetsfield]<br>어센틱 그레이 후드', price: 20000, imageUrl: r, delivery: "버스택배" },
  { id: 19, name: '[YesEyeSee]<br>C-로고 네이비 후드', price: 20000, imageUrl: s, delivery: "메타택배" },
  { id: 20, name: '[Shaka Wear]<br>헤비웨이트 기모 무지 후드', price: 20000, imageUrl: t, delivery: "버스택배" },
  // 추가적인 상품 데이터
];

const Goods = () => {
  const [searchTerm] = useState('');

  const filteredProducts = products.filter(product =>
    product.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const handleScrollToTop = () => {
    window.scrollTo({
      top: 0,
      behavior: 'smooth'
    });
  };

  return (
    <div className="goods">
      <div className="product-list">
        {chunkArray(filteredProducts, 4).map((column, columnIndex) => (
          <div key={columnIndex} className="product-column">
            {column.map(product => (
              <div key={product.id} className="product-card">
                <img src={product.imageUrl} alt={product.name} />
                <h2 dangerouslySetInnerHTML={{ __html: product.name }}></h2>
                <p className='price'><strong>{product.price.toLocaleString()}원</strong></p>
                <p className="delivery"><strong>배송업체: {product.delivery}</strong></p>
              </div>
            ))}
          </div>
        ))}
      </div>
      <button className="top-btn" onClick={handleScrollToTop}><strong>▲<br></br>Top</strong></button>
    </div>
  );
};

// 배열을 특정한 개수씩 나누는 함수
const chunkArray = (arr, size) => {
  return Array.from({ length: size }, (_, index) => {
    return arr.filter((_, idx) => idx % size === index);
  });
};

export default Goods;
