import React from 'react';
import AdminSalesHeader from '../../Componunts/js/AdminSalesHeader';
import AdminSalesSubHeader from '../../Componunts/js/AdminSalesSubHeader';
import AdminSalesMain from '../../Componunts/js/AdminSalesMain';
import useAdminSalesData from '../../Componunts/js/useAdminSalesData';
import '../../Componunts/css/AdminSalesPage.css';

const AdminSalesPage = () => {
    const {
        selectedPeriod,
        selectedFunction,
        salesData,
        startDate,
        endDate,
        handlePeriodChange,
        handleFunctionChange,
        handleCustomDateChange,
        setStartDate,
        setEndDate,
    } = useAdminSalesData();

    return (
        <div className="admin-sales-page-container">
            <AdminSalesHeader />
            <AdminSalesSubHeader
                selectedPeriod={selectedPeriod}
                selectedFunction={selectedFunction} // 추가
                handlePeriodChange={handlePeriodChange}
                handleFunctionChange={handleFunctionChange} // 추가
                startDate={startDate}
                endDate={endDate}
                setStartDate={setStartDate}
                setEndDate={setEndDate}
                handleCustomDateChange={handleCustomDateChange}
            />
            <AdminSalesMain 
                selectedPeriod={selectedPeriod}
                selectedFunction={selectedFunction} // 추가
                salesData={salesData}
                startDate={startDate}
                endDate={endDate}
            />
        </div>
    );
};

export default AdminSalesPage;
