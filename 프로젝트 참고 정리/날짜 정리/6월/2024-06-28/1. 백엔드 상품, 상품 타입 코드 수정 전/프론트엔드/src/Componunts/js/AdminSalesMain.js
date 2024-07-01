import React from 'react';

const AdminSalesMain = ({ selectedPeriod, selectedFunction, salesData, startDate, endDate }) => {
    const renderSalesData = () => {
        console.log('Selected Period in renderSalesData:', selectedPeriod);
        console.log('Selected Function in renderSalesData:', selectedFunction);
        console.log('Sales Data in renderSalesData:', salesData);

        if (selectedFunction === 'period') {
            switch (selectedPeriod) {
                case 'today':
                    return <p>오늘의 총 매출: {salesData.totalSalesAmount || 0}원</p>;
                case 'weekly':
                    return (
                        <div>
                            {salesData?.length > 0 ? (
                                salesData.map((week, index) => (
                                    <p key={index}>{week.week}주차 총 매출: {week.totalSalesAmount || 0}원</p>
                                ))
                            ) : (
                                <p>해당 주차의 매출 데이터가 없습니다.</p>
                            )}
                        </div>
                    );
                case 'monthly':
                    return <p>{startDate}년 {endDate}월의 총 매출: {salesData.totalSalesAmount || 0}원</p>;
                case 'quarterly':
                    return <p>{startDate}년 {endDate}분기의 총 매출: {salesData.totalSalesAmount || 0}원</p>;
                case 'yearly':
                    return <p>{startDate}년의 총 매출: {salesData.totalSalesAmount || 0}원</p>;
                case 'custom':
                    return <p>{startDate}부터 {endDate}까지의 총 매출: {salesData.totalSalesAmount || 0}원</p>;
                default:
                    return null;
            }
        } else if (selectedFunction === 'product') {
            return (
                <div>
                    {salesData?.content?.length > 0 ? (
                        salesData.content.map((product, index) => (
                            <p key={index}>{product.productName}: {product.totalSalesAmount || 0}원</p>
                        ))
                    ) : (
                        <p>해당 기간의 상품별 매출 데이터가 없습니다.</p>
                    )}
                </div>
            );
        } else if (selectedFunction === 'productType') {
            return (
                <div>
                    {salesData?.sales?.length > 0 ? (
                        salesData.sales.map((productType, index) => (
                            <p key={index}>{productType.productType}: {productType.totalSalesAmount || 0}원</p>
                        ))
                    ) : (
                        <p>해당 기간의 상품 타입별 매출 데이터가 없습니다.</p>
                    )}
                </div>
            );
        }
    };

    return (
        <div className="admin-sales-main-section">
            {salesData ? (
                <div>
                    <h2>매출 데이터</h2>
                    {renderSalesData()}
                </div>
            ) : (
                <p>데이터를 조회해 주세요.</p>
            )}
        </div>
    );
};

export default AdminSalesMain;
