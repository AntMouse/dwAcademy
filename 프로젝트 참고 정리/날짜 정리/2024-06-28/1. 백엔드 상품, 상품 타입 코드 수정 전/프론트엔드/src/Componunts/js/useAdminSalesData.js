import { useState, useEffect } from 'react';

const baseUrl = 'http://localhost:8080';

const useAdminSalesData = () => {
    const [selectedPeriod, setSelectedPeriod] = useState('today');
    const [selectedFunction, setSelectedFunction] = useState('period');
    const [salesData, setSalesData] = useState(null);
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [shouldFetch, setShouldFetch] = useState(false);

    const handlePeriodChange = (e) => {
        const period = e.target.value;
        setSelectedPeriod(period);

        if (period !== 'custom') {
            setStartDate('');
            setEndDate('');
        }
        setShouldFetch(false);
    };

    const handleFunctionChange = (e) => {
        const func = e.target.value;
        setSelectedFunction(func);
        setShouldFetch(false);
    };

    const handleFetch = async (apiUrl) => {
        try {
            const response = await fetch(apiUrl);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            setSalesData(data);
        } catch (error) {
            console.error('Fetch error:', error);
        }
    };

    const handleCustomDateChange = () => {
        setShouldFetch(true);
    };

    useEffect(() => {
        let apiUrl = '';

        if (selectedFunction === 'period') {
            switch (selectedPeriod) {
                case 'today':
                    apiUrl = `${baseUrl}/api/sales/today`;
                    break;
                case 'weekly':
                    if (startDate && endDate) {
                        apiUrl = `${baseUrl}/api/sales/weekly?year=${startDate}&month=${endDate}`;
                    }
                    break;
                case 'monthly':
                    if (startDate && endDate) {
                        apiUrl = `${baseUrl}/api/sales/month?year=${startDate}&month=${endDate}`;
                    }
                    break;
                case 'quarterly':
                    if (startDate && endDate) {
                        apiUrl = `${baseUrl}/api/sales/quarter?year=${startDate}&quarter=${endDate}`;
                    }
                    break;
                case 'yearly':
                    if (startDate) {
                        apiUrl = `${baseUrl}/api/sales/year?year=${startDate}`;
                    }
                    break;
                case 'custom':
                    if (startDate && endDate) {
                        apiUrl = `${baseUrl}/api/sales/period?startDate=${startDate}&endDate=${endDate}`;
                    }
                    break;
                default:
                    return;
            }
        } else if (selectedFunction === 'product') {
            apiUrl = `${baseUrl}/api/sales/product`;
        } else if (selectedFunction === 'productType') {
            apiUrl = `${baseUrl}/api/sales/product-type-sales`;
        }

        if (apiUrl && shouldFetch) {
            handleFetch(apiUrl);
        }
    }, [selectedFunction, selectedPeriod, startDate, endDate, shouldFetch]);

    return {
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
    };
};

export default useAdminSalesData;
