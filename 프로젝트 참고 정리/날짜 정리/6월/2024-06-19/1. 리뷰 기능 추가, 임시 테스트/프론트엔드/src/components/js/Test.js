import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // 1. useNavigate import 추가
import '../css/Test.css';
import logo from '../../assets/Logo.png';
import infoIcon from '../../assets/Info.png';
import cartIcon from '../../assets/Cart.png';
import searchIcon from '../../assets/Search.png'; // 검색 아이콘 추가
import categoryBannerIcon from '../../assets/CatThree.png'; // 카테고리 배너 아이콘 추가
import topIcon from '../../assets/Top.png'; // 상의 아이콘 추가
import bottomIcon from '../../assets/Bottom.png'; // 하의 아이콘 추가
import outerIcon from '../../assets/Over.png'; // 아우터 아이콘 추가
import shoesIcon from '../../assets/Shoes.png'; // 신발 아이콘 추가
import accessoryIcon from '../../assets/AC.png'; // 액세서리 아이콘 추가
import HoodiIcon from '../../assets/sub/Top/Hoodi.png';  // 후드티 아이콘 추가
import LongTshirtIcon from '../../assets/sub/Top/LongTshirt.png'; // 긴소매 아이콘 추가
import SweaterIcon from '../../assets/sub/Top/Sweater.png'; // 스웨터 아이콘 추가
import CTshirtIcon from '../../assets/sub/Top/CTshirt.png'; // 카라티 아이콘 추가
import MentoMenIcon from '../../assets/sub/Top/MentoMen.png'; // 맨투맨 아이콘 추가
import ShirtIcon from '../../assets/sub/Top/Shirt.png'; // 셔츠 아이콘 추가
import ShotTshirtIcon from '../../assets/sub/Top/ShotTshirt.png'; // 반소매 아이콘 추가
import SportTshirtIcon from '../../assets/sub/Top/SportTshirt.png'; // 스포츠웨어 아이콘 추가
import CapIcon from '../../assets/sub/AC/Cap.png'; // 모자 아이콘 추가
import BagIcon from '../../assets/sub/AC/Bag.png'; // 가방 아이콘 추가
import NecklaceIcon from '../../assets/sub/AC/Necklace.png'; // 목걸이 아이콘 추가
import EarringsIcon from '../../assets/sub/AC/Earrings.png'; // 귀걸이 아이콘 추가
import RingIcon from '../../assets/sub/AC/Ring.png'; // 반지 아이콘 추가
import BraceletIcon from '../../assets/sub/AC/Bracelet.png'; // 팔찌 아이콘 추가
import GlassesIcon from '../../assets/sub/AC/Glasses.png'; // 안경 아이콘 추가
import WristwatchIcon from '../../assets/sub/AC/Wristwatch.png'; // 시계 아이콘 추가
import LoaferIcon from '../../assets/sub/Shoes/Loafer.png'; // 로퍼 아이콘 추가
import ShoesIcon from '../../assets/sub/Shoes/Shoes.png'; // 구두 아이콘 추가
import HellIcon from '../../assets/sub/Shoes/Hell.png'; // 힐 아이콘 추가
import CrocsIcon from '../../assets/sub/Shoes/Crocs.png'; // 크록스 아이콘 추가
import SliperIcon from '../../assets/sub/Shoes/Sliper.png'; // 슬리퍼 아이콘 추가
import RunningIcon from '../../assets/sub/Shoes/Running.png'; // 런닝화 아이콘 추가
import DefaultIcon from '../../assets/sub/Shoes/Default.png'; // 단화 아이콘 추가
import BootsIcon from '../../assets/sub/Shoes/Boots.png'; // 부츠 아이콘 추가
import SportsShoesIcon from '../../assets/sub/Shoes/SportsShoes.png'; // 스포츠신발 아이콘 추가
import MustangIcon from '../../assets/sub/Over/Mustang.png'; // 무스탕 아이콘 추가
import SuitIcon from '../../assets/sub/Over/Suit.png'; // 슈트 아이콘 추가
import AnorakIcon from '../../assets/sub/Over/Anorak.png'; // 아노락 아이콘 추가
import SPaddingIcon from '../../assets/sub/Over/SPadding.png'; // 숏패딩 아이콘 추가
import LPaddingIcon from '../../assets/sub/Over/LPadding.png'; // 롱패딩 아이콘 추가
import CardiganIcon from '../../assets/sub/Over/Cardigan.png'; // 가디건 아이콘 추가
import CoatIcon from '../../assets/sub/Over/Coat.png'; // 코트 아이콘 추가
import CottonIcon from '../../assets/sub/Bottom/Cotton.png'; // 코튼 아이콘 추가
import DenimIcon from '../../assets/sub/Bottom/Denim.png'; // 데님 아이콘 추가
import JoggerIcon from '../../assets/sub/Bottom/Jogger.png'; // 조거 아이콘 추가
import SuitBottomIcon from '../../assets/sub/Bottom/SuitBottom.png'; // 슈트 하의 아이콘 추가
import JumpsuitIcon from '../../assets/sub/Bottom/Jumpsuit.png'; // 점프슈트 아이콘 추가
import LeggingsIcon from '../../assets/sub/Bottom/Leggings.png'; // 레깅스 아이콘 추가
import SkirtIcon from '../../assets/sub/Bottom/Skirt.png'; // 치마 아이콘 추가
import DressIcon from '../../assets/sub/Bottom/Dress.png'; // 원피스 아이콘 추가
import SportsBottomIcon from '../../assets/sub/Bottom/SportsBottom.png'; // 스포츠 하의 아이콘 추가
import useAuth from '../../test-list/Hooks/useAuth'; // useAuth import 추가

const Test = () => {
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);
  const [activeCategory, setActiveCategory] = useState(null);
  const { currentMember } = useAuth(); // currentMember 사용
  const navigate = useNavigate(); // useNavigate 훅 사용

  const handleCartClick = () => {
    if (!currentMember) {
      alert('로그인 후 이용해 주세요.');
      return;
    }
    navigate('/cart');
  };

  const categories = [
    {
      name: "상의",
      icon: topIcon,
      subcategories: [
        { name: "후드티셔츠", icon: HoodiIcon },
        { name: "반소매티셔츠", icon: ShotTshirtIcon },
        { name: "긴소매티셔츠", icon: LongTshirtIcon },
        { name: "니트/스웨터", icon: SweaterIcon },
        { name: "카라티셔츠", icon: CTshirtIcon },
        { name: "맨투맨", icon: MentoMenIcon },
        { name: "셔츠/블라우스", icon: ShirtIcon },
        { name: "스포츠웨어", icon: SportTshirtIcon }
      ]
    },
    {
      name: "하의",
      icon: bottomIcon,
      subcategories: [
        { name: "코튼팬츠", icon: CottonIcon },
        { name: "데님팬츠", icon: DenimIcon },
        { name: "조거팬츠", icon: JoggerIcon },
        { name: "슈트/슬랙스", icon: SuitBottomIcon },
        { name: "점프슈트", icon: JumpsuitIcon },
        { name: "레깅스", icon: LeggingsIcon },
        { name: "치마", icon: SkirtIcon },
        { name: "원피스", icon: DressIcon },
        { name: "스포츠웨어", icon: SportsBottomIcon }
      ]
    },
    {
      name: "아우터",
      icon: outerIcon,
      subcategories: [
        { name: "무스탕", icon: MustangIcon },
        { name: "슈트", icon: SuitIcon },
        { name: "아노락재킷", icon: AnorakIcon },
        { name: "숏패딩", icon: SPaddingIcon },
        { name: "롱패딩", icon: LPaddingIcon },
        { name: "가디건", icon: CardiganIcon },
        { name: "코트", icon: CoatIcon },
      ]
    },
    {
      name: "신발",
      icon: shoesIcon,
      subcategories: [
        { name: "로퍼", icon: LoaferIcon },
        { name: "구두", icon: ShoesIcon },
        { name: "하이힐", icon: HellIcon },
        { name: "크록스", icon: CrocsIcon },
        { name: "슬리퍼", icon: SliperIcon },
        { name: "런닝화", icon: RunningIcon },
        { name: "단화", icon: DefaultIcon },
        { name: "부츠", icon: BootsIcon },
        { name: "스포츠신발", icon: SportsShoesIcon }
      ]
    },
    {
      name: "액세서리",
      icon: accessoryIcon,
      subcategories: [
        { name: "모자", icon: CapIcon },
        { name: "가방", icon: BagIcon },
        { name: "목걸이/팬던트", icon: NecklaceIcon },
        { name: "귀걸이", icon: EarringsIcon },
        { name: "반지", icon: RingIcon },
        { name: "팔찌", icon: BraceletIcon },
        { name: "선글라스/안경", icon: GlassesIcon },
        { name: "시계", icon: WristwatchIcon }
      ]
    }
  ];

  return (
    <header className="header">
      <div
        className="category-dropdown"
        onMouseEnter={() => setIsDropdownOpen(true)}
        onMouseLeave={() => setIsDropdownOpen(false)}
      >
        <div className="category-banner">
          <img src={categoryBannerIcon} alt="Category Banner" className="category-banner-icon" />
          <span>카테고리</span>
        </div>
        {isDropdownOpen && (
          <div className="dropdown-menu">
            {categories.map((category, index) => (
              <div
                key={index}
                className="dropdown-item"
                onMouseEnter={() => setActiveCategory(index)}
                onMouseLeave={() => setActiveCategory(null)}
              >
                <img src={category.icon} alt={category.name} className="category-icon" />
                <span>{category.name}</span>
                {activeCategory === index && (
                  <div className="subcategory-menu">
                    {category.subcategories.map((subcategory, subIndex) => (
                      <div key={subIndex} className="subcategory-item">
                        <img src={subcategory.icon} alt={subcategory.name} className="subcategory-icon" />
                        <span>{subcategory.name}</span>
                      </div>
                    ))}
                  </div>
                )}
              </div>
            ))}
          </div>
        )}
      </div>
      <div className="logo">
        <img src={logo} alt="Logo" className="logo-image" />
      </div>

      <div className="search-bar">
        <input type="text" placeholder="찾으실 상품을 검색하세요..." />
        <img src={searchIcon} alt="Search" className="search-icon" /> {/* 돋보기 아이콘 추가 */}
      </div>

      <div className="icon-container">
        <div className="icon-wrapper">
          <img src={infoIcon} alt="Info" className="icon" />
          <span>내정보</span>
        </div>
        <div className="icon-wrapper" onClick={handleCartClick}> {/* onClick 이벤트 추가 */}
          <img src={cartIcon} alt="Cart" className="icon" />
          <span>장바구니</span>
        </div>
      </div>
    </header>
  );
};

export default Test;