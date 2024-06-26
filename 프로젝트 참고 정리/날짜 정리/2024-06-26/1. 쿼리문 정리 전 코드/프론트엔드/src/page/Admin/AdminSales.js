import React, { useEffect, useState } from 'react';
import axios from 'axios';

const AdminSales = () => {
    const [weeklySales, setWeeklySales] = useState([]);
    const [totalSalesThisMonth, setTotalSalesThisMonth] = useState(0);
    const [productSales, setProductSales] = useState([]);
    const [productTypeSales, setProductTypeSales] = useState([]); // 상품 타입별 매출 데이터를 위한 상태 추가

    useEffect(() => {
        fetchSalesData();
    }, []);

    const fetchSalesData = () => {
        fetchWeeklySalesThisMonth();
        fetchTotalSalesThisMonth();
        fetchProductSales();
        fetchProductTypeSales(); // 상품 타입별 매출 데이터를 가져오는 함수 호출
    };

    const fetchWeeklySalesThisMonth = () => {
        axios.get('http://localhost:8080/api/sales/weekly-this-month')
            .then(response => {
                setWeeklySales(response.data);
            })
            .catch(error => console.error("Error fetching weekly sales:", error));
    };

    const fetchTotalSalesThisMonth = () => {
        axios.get('http://localhost:8080/api/sales/total-this-month')
            .then(response => {
                setTotalSalesThisMonth(response.data);
            })
            .catch(error => console.error("Error fetching total sales this month:", error));
    };

    const fetchProductSales = () => {
        axios.get('http://localhost:8080/api/sales/product')
            .then(response => {
                setProductSales(response.data);
            })
            .catch(error => console.error("Error fetching product sales:", error));
    };

    const fetchProductTypeSales = () => {
        axios.get('http://localhost:8080/api/sales/product-type-sales')
            .then(response => {
                setProductTypeSales(response.data);
            })
            .catch(error => console.error("Error fetching product type sales:", error));
    };

    const renderWeeklySales = () => {
        const maxWeek = Math.max(...weeklySales.map(ws => ws.week), 0);
        const weeks = Array.from({ length: maxWeek }, (_, i) => i + 1);
        return weeks.map(week => {
            const weekData = weeklySales.find(ws => ws.week === week);
            return (
                <tr key={week}>
                    <td>{week}주차</td>
                    <td>{weekData ? weekData.totalSalesAmount : 0}</td>
                </tr>
            );
        });
    };

    const renderProductSales = () => {
        return productSales.map((product, index) => (
            <tr key={index}>
                <td>{product.productName}</td>
                <td>{product.totalSalesAmount}</td>
            </tr>
        ));
    };

    const renderProductTypeSales = () => {
        return productTypeSales.map((type, index) => (
            <tr key={index}>
                <td>{type.productType}</td>
                <td>{type.totalSalesAmount}</td>
            </tr>
        ));
    };

    return (
        <div>
            <h2>This Month's Sales</h2>
            <h3>Total Sales: {totalSalesThisMonth}</h3>
            {weeklySales.length > 0 ? (
                <table>
                    <thead>
                        <tr>
                            <th>Week</th>
                            <th>Total Sales Amount</th>
                        </tr>
                    </thead>
                    <tbody>
                        {renderWeeklySales()}
                    </tbody>
                </table>
            ) : (
                <p>No sales data available for this month.</p>
            )}
            <h2>Product Sales</h2>
            {productSales.length > 0 ? (
                <table>
                    <thead>
                        <tr>
                            <th>Product Name</th>
                            <th>Total Sales Amount</th>
                        </tr>
                    </thead>
                    <tbody>
                        {renderProductSales()}
                    </tbody>
                </table>
            ) : (
                <p>No product sales data available.</p>
            )}
            <h2>Product Type Sales</h2>
            {productTypeSales.length > 0 ? (
                <table>
                    <thead>
                        <tr>
                            <th>Product Type</th>
                            <th>Total Sales Amount</th>
                        </tr>
                    </thead>
                    <tbody>
                        {renderProductTypeSales()}
                    </tbody>
                </table>
            ) : (
                <p>No product type sales data available.</p>
            )}
        </div>
    );
};

export default AdminSales;
