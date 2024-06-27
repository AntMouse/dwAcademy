// src/page/AdminSalesPage.js
import React from 'react';
import AdminSalesHeader from '../../Componunts/js/AdminSalesHeader';
import AdminSalesSubHeader from '../../Componunts/js/AdminSalesSubHeader';
import AdminSalesMain from '../../Componunts/js/AdminSalesMain';
import useAdminSalesData from '../../Componunts/js/useAdminSalesData';
import '../../Componunts/css/AdminSalesPage.css';

const AdminSalesPage = () => {
    const {
        selectedPeriod,
        salesData,
        startDate,
        endDate,
        handlePeriodChange,
        handleCustomDateChange,
        setStartDate,
        setEndDate,
    } = useAdminSalesData();

    return (
        <div className="admin-sales-container">
            <AdminSalesHeader />
            <AdminSalesSubHeader
                selectedPeriod={selectedPeriod}
                handlePeriodChange={handlePeriodChange}
                startDate={startDate}
                endDate={endDate}
                setStartDate={setStartDate}
                setEndDate={setEndDate}
                handleCustomDateChange={handleCustomDateChange}
            />
            <AdminSalesMain salesData={salesData} />
        </div>
    );
};

export default AdminSalesPage;