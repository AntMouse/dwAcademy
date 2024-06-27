// src/componunts/js/AdminSalesMain.js
import React from 'react';

const AdminSalesMain = ({ salesData }) => {
    return (
        <div className="admin-sales-main-section">
            {salesData && (
                <div>
                    <h2>매출 데이터</h2>
                    <pre>{JSON.stringify(salesData, null, 2)}</pre>
                </div>
            )}
        </div>
    );
};

export default AdminSalesMain;
