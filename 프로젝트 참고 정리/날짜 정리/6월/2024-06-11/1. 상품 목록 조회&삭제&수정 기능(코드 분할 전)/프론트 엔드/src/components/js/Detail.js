import React from 'react'
import '../css/Detail.css';
import infoIcon from '../assets/Info.png';
export const Detail = () => {

    
    return (

        <div className='product-detail-containor'>
            <section className='product-detail-img-info'>
                <div className='product-img'><img src={infoIcon}></img></div>
                <div className='product-detail'>
                    <h3>트로벳 강아지 ASD 방광 결석용 수입 처방식 사료 1.13kg</h3>
                    <hr/>
                    <h1>20620원</h1>
                    <hr/>
                    <span style={{fontWeight:'bold', fontSize:'15px'}}>무료배송</span>
                    <p>5/31 도착예정</p>
                    <hr/>
                    <span>판매자: DW</span>
                    <hr/>
                    <span>배송사: 대한통운</span>
                    <br></br>
                    <button class="custom-btn btn-3"><span>장바구니 담기</span></button>
                    <button class="custom-btn btn-5"><span>구매하기</span></button>
                </div>
            </section>
            <section className='product-detail-detailinfo'>
                
            </section>
        </div>


    )
}
