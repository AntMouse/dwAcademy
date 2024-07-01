import React, { useState } from 'react';
import axios from 'axios';

const AdminSales = () => {
    const [sales, setSales] = useState([]);
    const [totalSales, setTotalSales] = useState(0);
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');

    const fetchSales = (url, params = {}) => {
        axios.get(url, { params })
            .then(response => {
                const data = response.data;
                setSales(data.sales || []);
                setTotalSales(data.totalSalesAmount || 0);
            })
            .catch(error => console.error("Error fetching sales:", error));
    };

    const fetchTodaySales = () => fetchSales('http://localhost:8080/api/sales/today');
    const fetchMonthlySales = () => fetchSales('http://localhost:8080/api/sales/month');
    const fetchQuarterlySales = () => fetchSales('http://localhost:8080/api/sales/quarter');
    const fetchYearlySales = () => fetchSales('http://localhost:8080/api/sales/year');
    const fetchSalesByPeriod = () => fetchSales('http://localhost:8080/api/sales/period', { startDate, endDate });

    return (
        <div>
            <h2>Sales</h2>
            <div>
                <button onClick={fetchTodaySales}>Today's Sales</button>
                <button onClick={fetchMonthlySales}>This Month's Sales</button>
                <button onClick={fetchQuarterlySales}>This Quarter's Sales</button>
                <button onClick={fetchYearlySales}>This Year's Sales</button>
            </div>
            <div>
                <label>Start Date:</label>
                <input type="datetime-local" value={startDate} onChange={e => setStartDate(e.target.value)} />
                <label>End Date:</label>
                <input type="datetime-local" value={endDate} onChange={e => setEndDate(e.target.value)} />
                <button onClick={fetchSalesByPeriod}>Fetch Sales By Period</button>
            </div>
            <div>
                <h3>Total Sales: {totalSales}</h3>
            </div>
            {sales.length > 0 && (
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Create Date</th>
                            <th>Total Price</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        {sales.map(sale => (
                            <tr key={sale.id}>
                                <td>{sale.id}</td>
                                <td>{new Date(sale.createDate).toLocaleString()}</td>
                                <td>{sale.totalPrice}</td>
                                <td>{sale.status}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
};

export default AdminSales;
