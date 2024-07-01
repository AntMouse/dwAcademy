import React, { useState } from 'react';
import axios from 'axios';

const AdminSales = () => {
    const [sales, setSales] = useState([]);
    const [totalSales, setTotalSales] = useState(0);
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [productSales, setProductSales] = useState({});
    const [quarter, setQuarter] = useState(1);
    const [year, setYear] = useState(new Date().getFullYear()); // 연도 선택 추가

    const fetchSales = (url, params = {}, isTotalSales = false) => {
        axios.get(url, { params })
            .then(response => {
                if (isTotalSales) {
                    setTotalSales(response.data);
                } else {
                    setSales(response.data.sales || []);
                    setTotalSales(response.data.totalSalesAmount || 0);
                }
            })
            .catch(error => console.error("Error fetching sales:", error));
    };

    const fetchTodaySales = () => fetchSales('http://localhost:8080/api/sales/today');
    const fetchYearlySales = (year) => fetchSales('http://localhost:8080/api/sales/year', { year }); // 연도 매개변수 추가
    const fetchSalesByPeriod = () => fetchSales('http://localhost:8080/api/sales/period', { startDate, endDate });
    const fetchQuarterlySales = () => fetchSales('http://localhost:8080/api/sales/quarter', { year: new Date().getFullYear(), quarter });
    const fetchMonthlySales = (year, month) => fetchSales('http://localhost:8080/api/sales/month', { year, month });
    const fetchWeeklySales = (year, month) => fetchSales('http://localhost:8080/api/sales/weekly', { year, month });
    const fetchSalesByProduct = () => {
        axios.get('http://localhost:8080/api/sales/product')
            .then(response => setProductSales(response.data))
            .catch(error => console.error("Error fetching product sales:", error));
    };

    const currentYear = new Date().getFullYear();

    return (
        <div>
            <h2>Sales</h2>
            <div>
                <button onClick={fetchTodaySales}>Today's Sales</button>
                <button onClick={() => fetchYearlySales(year)}>Fetch Sales by Year</button> {/* 연도별 매출 조회 버튼 */}
                <button onClick={fetchSalesByProduct}>Sales by Product</button>
            </div>
            <div>
                <label>Year:</label>
                <input type="number" value={year} onChange={e => setYear(e.target.value)} /> {/* 연도 선택 인풋 */}
            </div>
            <div>
                <label>Start Date:</label>
                <input type="datetime-local" value={startDate} onChange={e => setStartDate(e.target.value)} />
                <label>End Date:</label>
                <input type="datetime-local" value={endDate} onChange={e => setEndDate(e.target.value)} />
                <button onClick={fetchSalesByPeriod}>Fetch Sales By Period</button>
            </div>
            <div>
                <label>Quarter:</label>
                <select value={quarter} onChange={e => setQuarter(e.target.value)}>
                    <option value={1}>1st Quarter</option>
                    <option value={2}>2nd Quarter</option>
                    <option value={3}>3rd Quarter</option>
                    <option value={4}>4th Quarter</option>
                </select>
                <button onClick={fetchQuarterlySales}>Fetch Quarterly Sales</button>
            </div>
            <div>
                <h3>Total Sales: {totalSales}</h3>
            </div>
            <div>
                {Array.from({ length: 12 }, (_, i) => (
                    <button key={i + 1} onClick={() => fetchMonthlySales(currentYear, i + 1)}>
                        {i + 1}월
                    </button>
                ))}
            </div>
            {sales.length > 0 && (
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Create Date</th>
                            <th>Product ID</th>
                            <th>Price</th>
                            <th>Quantity</th>
                        </tr>
                    </thead>
                    <tbody>
                        {sales.map(sale => (
                            <tr key={sale.id}>
                                <td>{sale.id}</td>
                                <td>{new Date(sale.createDate).toLocaleString()}</td>
                                <td>{sale.productId}</td>
                                <td>{sale.price}</td>
                                <td>{sale.quantity}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
            {Object.keys(productSales).length > 0 && (
                <div>
                    <h3>Sales by Product</h3>
                    <table>
                        <thead>
                            <tr>
                                <th>Product ID</th>
                                <th>Total Sales Amount</th>
                            </tr>
                        </thead>
                        <tbody>
                            {Object.entries(productSales).map(([productId, salesAmount]) => (
                                <tr key={productId}>
                                    <td>{productId}</td>
                                    <td>{salesAmount}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
};

export default AdminSales;
