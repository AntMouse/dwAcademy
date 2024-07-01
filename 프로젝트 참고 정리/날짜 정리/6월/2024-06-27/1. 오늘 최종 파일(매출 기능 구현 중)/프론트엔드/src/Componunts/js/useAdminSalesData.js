// src/componunts/js/useAdminSalesData.js
import { useState } from 'react';
import { format } from 'date-fns';

const baseUrl = 'http://localhost:8080';

const useAdminSalesData = () => {
    const [selectedPeriod, setSelectedPeriod] = useState('today');
    const [salesData, setSalesData] = useState(null);
    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(new Date());

    const handlePeriodChange = async (e) => {
        const period = e.target.value;
        setSelectedPeriod(period);

        if (period !== 'custom') {
            setStartDate(new Date());
            setEndDate(new Date());
        }

        let apiUrl = '';

        switch (period) {
            case 'today':
                apiUrl = `${baseUrl}/api/sales/today`;
                break;
            case 'weekly':
                apiUrl = `${baseUrl}/api/sales/weekly?year=${format(startDate, 'yyyy')}&month=${format(endDate, 'MM')}`;
                break;
            case 'monthly':
                apiUrl = `${baseUrl}/api/sales/month?year=${format(startDate, 'yyyy')}&month=${format(endDate, 'MM')}`;
                break;
            case 'quarterly':
                apiUrl = `${baseUrl}/api/sales/quarter?year=${format(startDate, 'yyyy')}&quarter=${endDate}`;
                break;
            case 'yearly':
                apiUrl = `${baseUrl}/api/sales/year?year=${format(startDate, 'yyyy')}`;
                break;
            case 'custom':
                // 사용자 지정 기간에 대한 처리가 필요합니다.
                break;
            default:
                return;
        }

        if (apiUrl) {
            const response = await fetch(apiUrl);
            const data = await response.json();
            setSalesData(data);
        }
    };

    const handleCustomDateChange = async () => {
        if (startDate && endDate) {
            const apiUrl = `${baseUrl}/api/sales/period?startDate=${startDate.toISOString()}&endDate=${endDate.toISOString()}`;
            const response = await fetch(apiUrl);
            const data = await response.json();
            setSalesData(data);
        }
    };

    return {
        selectedPeriod,
        salesData,
        startDate,
        endDate,
        handlePeriodChange,
        handleCustomDateChange,
        setStartDate,
        setEndDate,
    };
};

export default useAdminSalesData;
