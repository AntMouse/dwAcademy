import React from 'react';
import AdminSalesDateSelector from './AdminSalesDateSelector';

const AdminSalesSubHeader = ({
    selectedPeriod,
    selectedFunction,
    handlePeriodChange,
    handleFunctionChange,
    startDate,
    endDate,
    setStartDate,
    setEndDate,
    handleCustomDateChange,
}) => {
    return (
        <div className="admin-sales-sub-header-section">
            <select value={selectedPeriod} onChange={handlePeriodChange}>
                <option value="today">오늘 매출</option>
                <option value="weekly">주간 매출</option>
                <option value="monthly">월간 매출</option>
                <option value="quarterly">분기 매출</option>
                <option value="yearly">연간 매출</option>
                <option value="custom">사용자 지정</option>
            </select>
            <AdminSalesDateSelector
                selectedPeriod={selectedPeriod}
                startDate={startDate}
                endDate={endDate}
                setStartDate={setStartDate}
                setEndDate={setEndDate}
                handleCustomDateChange={handleCustomDateChange}
            />
            {/* 기능 선택 드롭다운 메뉴 추가 */}
            <select value={selectedFunction} onChange={handleFunctionChange}>
                <option value="period">기간별 매출보기</option>
                <option value="product">상품별 매출보기</option>
                <option value="productType">상품 타입별 매출보기</option>
            </select>
        </div>
    );
};

export default AdminSalesSubHeader;
